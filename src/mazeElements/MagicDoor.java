package mazeElements;

import java.util.ArrayList;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import maze.Maze;
import visitors.BaseVisitor;
import visitors.MagicDoorVisitor;
import visitors.Visitor;

public class MagicDoor extends Rectangle implements Door{
	private int keyId;
	private String type;
	private String status;
	private ArrayList<Room> rooms;
	private Paint mainColor = Color.DEEPPINK;
	private Paint secondaryColor = Color.PINK;
	public MagicDoor(double x, double y) {
		super(x, y, 10,10);
		type = RoomPart.DOOR;
		status = Door.OPEN;
		rooms = new ArrayList<>(2);
		setFill(mainColor);
		toFront();
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public Room enter(Room currentRoom) {
		for (Room room : rooms) {
			if (room != currentRoom) {
				status = OPEN;
				return room;
			}
		}
		return null;
	}

	@Override
	public boolean isOpen() {
		if(status.equals(OPEN)){
			return true;
		}
		return false;
	}

	@Override
	public void addRoom(Room room) {
		rooms.add(room);
		
	}

	@Override
	public int getKeyId() {
		return keyId;
	}

	@Override
	public void swapColor() {
		setFill(secondaryColor);
	}

	@Override
	public void setKeyId(int id) {
		keyId = id;
	}
	@Override
	public Bounds getBoundsInP() {
		return getBoundsInParent();
	}

	@Override
	public boolean accept(Player player) {
		if (player.getX() == getX() && player.getY() == getY()) {
			if(isOpen() || player.hasKey(getKeyId())){
				swapColor();
				Maze.currentRoom = enter(Maze.currentRoom);
				return true;
			}
		}
		return false;
	}

	@Override
	public void accept(Visitor visitor, Pane pane) {
		visitor.visit(this, pane);
		
	}

	@Override
	public void accept(BaseVisitor acyclicDrawVisitor, Pane pane) {
		if(acyclicDrawVisitor instanceof MagicDoorVisitor){
			((MagicDoorVisitor) acyclicDrawVisitor).visit(this, pane);
		}
	}
}
