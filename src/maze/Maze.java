package maze;

import java.io.IOException;
import java.util.ArrayList;

import factories.MazeElementsFactory;
import factories.MazeElementsFromFileFactory;
import factories.MazeFactoryProducer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import mazeElements.Door;
import mazeElements.Key;
import mazeElements.Player;
import mazeElements.Room;
import mazeElements.RoomPart;
import visitors.AcyclicDrawVisitor;
import visitors.BaseVisitor;
import visitors.DrawVisitor;

public class Maze {
	public static final String MAGICMAZE = "magicmaze";
	public static final String STANDARDMAZE = "standardmaze";
	public static final String UP = "up";
	public static final String DOWN = "down";
	public static final String LEFT = "left";
	public static final String RIGHT = "right";
	public static String currentMazeType = STANDARDMAZE;
	private Player player;
	public static Pane pane;
	private ArrayList<Room> rooms;
	public static Room currentRoom;
	private MazeElementsFactory mazeElementsFactory;
	private MazeElementsFromFileFactory elementsFromFileFactory;
	public Maze(Scene scene){
        pane = (Pane) ((Pane)(scene.getRoot())).getChildren().get(1);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()){
				case W:{
					move(UP);
					break;
				}
				case S :{
					move(DOWN);
					break;
				}
				case A:{
					move(LEFT);
					break;
				}
				case D:{
					move(RIGHT);
					break;
				}
				case Q:{
					currentRoom.action();
					break;
				}
				default:
					break;
				}
				event.consume();
			}
		});
      
	}
	public void start(String filePath) {
		pane.getChildren().clear();
		mazeElementsFactory = MazeFactoryProducer.getFactory(currentMazeType);
		try {
			elementsFromFileFactory = new MazeElementsFromFileFactory(filePath);
			rooms = createMazeElementsFromFile();
			//rooms = createMazeElements();
			player = elementsFromFileFactory.getPlayer();
			addElementsToMaze(rooms);
			setCurrentRoom();
		} catch (NullPointerException | IOException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<Room> createMazeElements() {
		ArrayList<RoomPart> parts = new ArrayList<>();
		rooms = new ArrayList<>();
		
		rooms.add(mazeElementsFactory.createRoom(460, 360, 100, 50));
		rooms.add(mazeElementsFactory.createRoom(480, 300, 30, 50));
		rooms.add(mazeElementsFactory.createRoom(350, 360, 100, 20));
		rooms.add(mazeElementsFactory.createRoom(350, 390 ,100, 20));
		rooms.add(mazeElementsFactory.createRoom(350, 390 ,100, 20));
		rooms.add(mazeElementsFactory.createRoom(380, 250, 30, 100));
		rooms.add(mazeElementsFactory.createRoom(360, 420, 30, 100));
		rooms.add(mazeElementsFactory.createRoom(290, 360 , 50, 10));
		rooms.add(mazeElementsFactory.createRoom(290, 380, 30, 70));
		rooms.add(mazeElementsFactory.createRoom(290, 300, 30, 50));
		rooms.add(mazeElementsFactory.createRoom(80, 420, 200, 30));
		rooms.add(mazeElementsFactory.createRoom(290, 380, 30, 70));
		rooms.add(mazeElementsFactory.createRoom(290, 300, 30, 50));
		
		Pair<RoomPart, RoomPart> p = createDoorAndKey(490,350,550,400);
		parts.add(p.getValue());
		parts.add(p.getKey());
		p = createDoorAndKey(450,400,490,300);
		parts.add(p.getValue());
		parts.add(p.getKey());
		p = createDoorAndKey(450,360,360,400);
		parts.add(p.getValue());
		parts.add(p.getKey());
		p = createDoorAndKey(390, 350,360,370);
		parts.add(p.getValue());
		parts.add(p.getKey());
		p = createDoorAndKey(340,360,390,300);
		parts.add(p.getValue());
		parts.add(p.getKey());
		p = createDoorAndKey(370, 410,390,280);
		parts.add(p.getValue());
		parts.add(p.getKey());
		p = createDoorAndKey(300, 350,290,360);
		parts.add(p.getValue());
		parts.add(p.getKey());
		p = createDoorAndKey(300, 370,300,310);
		parts.add(p.getValue());
		parts.add(p.getKey());
		p = createDoorAndKey(280, 430, 300,440);
		parts.add(p.getValue());
		parts.add(p.getKey());		
		addPartsToRooms(parts);
		return rooms;
	}
	private ArrayList<Room> createMazeElementsFromFile(){
		currentMazeType = elementsFromFileFactory.getMazeType();
		ArrayList<RoomPart> parts = elementsFromFileFactory.getRoomParts();
		rooms = elementsFromFileFactory.getRooms();
		addPartsToRooms(parts);
		return rooms;
	}
	private Pair<RoomPart, RoomPart> createDoorAndKey(int doorX, int doorY, int keyX, int keyY) {
		Key k = mazeElementsFactory.createKey(keyX, keyY);
		Door d = mazeElementsFactory.createDoor(doorX, doorY);
		d.setKeyId(k.getKeyId());
		return new Pair<RoomPart, RoomPart>(d,k);
	}
	private void addPartsToRooms(ArrayList<RoomPart> parts) {
		for (Room room : rooms) {
			for (RoomPart rp: parts) {
				if(room.intersects(rp.getBoundsInP())){
					if(rp.getType().equals(RoomPart.DOOR)){
						room.addDoor((Door) rp);
					}else{
						room.addKey((Key) rp);
					}
				}
			}
		}
	}
	private void setCurrentRoom(){
		for (Room room : rooms) {
			if(room.intersects(player.getBoundsInParent())){
				currentRoom = room;
			}
		}
	}
	public void move(String direction){
		double x = player.getX();
		double y = player.getY();
		double oldX = x;
		double oldY = y;
		switch (direction){
		case Maze.UP:{
			y -= 10;
			break;
		}
		case Maze.DOWN: {
			y += 10;
			break;
		}
		case Maze.LEFT: {
			x -= 10;
			break;
		}
		case Maze.RIGHT: {
			x += 10;
			break;
		}
		default:{
			break;
		}
		}
		player.setXY(x, y);
		if(checkBounds(x, y) == true){
		}else{
			player.setXY(oldX,oldY);
		}
	}
	private void addElementsToMaze(ArrayList<Room> rooms){
//		DrawVisitor drawVisitor = new DrawVisitor();
//		player.accept(drawVisitor,pane);
//		for (Room room : rooms) {
//			room.accept(drawVisitor, pane);
//		}
		BaseVisitor acyclicDrawVisitor = new AcyclicDrawVisitor();
		for (Room room : rooms) {
			room.accept(acyclicDrawVisitor , pane);
		}
		player.accept(acyclicDrawVisitor, pane);
		
	}
	 private boolean checkBounds(double x, double y){
		return currentRoom.accept(player);
	}
}