package factories;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.util.Pair;
import mazeElements.Door;
import mazeElements.Key;
import mazeElements.Player;
import mazeElements.Room;
import mazeElements.RoomPart;
import utils.CustomScanner;

public class MazeElementsFromFileFactory {
CustomScanner customScanner;
	public MazeElementsFromFileFactory(String file) throws FileNotFoundException,IOException,NullPointerException {
		customScanner = new CustomScanner(file);
	}
	public ArrayList<Room> getRooms(){
		MazeElementsFactory elementsFactory = MazeFactoryProducer.getFactory(customScanner.getMazeType());
		ArrayList<Room> rooms = new ArrayList<>();
		for (Pair<Point2D, Dimension2D> pair : customScanner.getHighRooms()) {
			rooms.add(elementsFactory.createHighRoom(pair.getKey().getX(), pair.getKey().getY(), pair.getValue().getWidth(), pair.getValue().getHeight()));
		}
		for (Pair<Point2D, Dimension2D> pair : customScanner.getRooms()) {
			rooms.add(elementsFactory.createRoom(pair.getKey().getX(), pair.getKey().getY(), pair.getValue().getWidth(), pair.getValue().getHeight()));
		}
		
		return rooms;
	}
	public ArrayList<RoomPart> getRoomParts() {
		ArrayList<RoomPart> roomParts = new ArrayList<>();
		MazeElementsFactory elementsFactory = MazeFactoryProducer.getFactory(customScanner.getMazeType());
		for (Pair<Point2D, Point2D> pair : customScanner.getRoomParts()) {
			Door d = elementsFactory.createDoor(pair.getKey().getX(), pair.getKey().getY());
			Key k = elementsFactory.createKey(pair.getValue().getX(), pair.getValue().getY());
			d.setKeyId(k.getKeyId());
			roomParts.add(d);
			roomParts.add(k);
		}
		return roomParts;
	}
	public Player getPlayer(){
		Point2D p = customScanner.getPlayer();
		return new Player(p.getX(), p.getY());
		
	}
	
	public String getMazeType(){
		return customScanner.getMazeType();
	}
}
