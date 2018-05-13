package mazeElements;

import java.util.ArrayList;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import visitors.BaseVisitor;
import visitors.Visitor;

public class StandardRoom implements Room{
	ArrayList<RoomPart> parts;
	public StandardRoom(double x, double y, double width, double heigth) {
		parts = new ArrayList<>();
		RoomPart w = new StandardWall(x, y, width, heigth);
		parts.add(w);
	}
	@Override
	public ArrayList<RoomPart> getParts() {
		return parts;
	}

	@Override
	public void addDoor(Door door) {
		door.addRoom((Room)this);
		parts.add(door);
	}


	@Override
	public Key addKey(Key key) {
		parts.add(0,key);
		return key;
	}
	
	private Wall getWalls() {
		for (RoomPart roomPart : parts) {
			if(roomPart.getType().equals(RoomPart.WALL)){
				return (Wall) roomPart;
			}
		}
		return null;
	}
	
	@Override
	public int getXCoordinate(){
		return getWalls().getXCoordinate();
	}
	
	@Override
	public int getYCoordinate(){
		return getWalls().getYCoordinate();
	}
	@Override
	public int getW() {
		return (int) getWalls().getW();
	}

	@Override
	public int getH() {
		return (int) getWalls().getH();
	}


	@Override
	public boolean intersects(Bounds b) {
		if(getWalls().intersects(b)){
			return true;
		}
		return false;
	}
	@Override
	public void accept(Visitor visitor, Pane pane) {
		for (RoomPart roomPart : parts) {
			roomPart.accept(visitor, pane);
		}
		
	}
	@Override
	public void accept(BaseVisitor acyclicDrawVisitor, Pane pane) {
		for (RoomPart roomPart : parts) {
			roomPart.accept(acyclicDrawVisitor, pane);
		}
	}
	@Override
	public boolean accept(Player player) {
		boolean result = false;
		for (RoomPart roomPart : parts) {
			if((player.getX() >= roomPart.getX()) && (player.getY() >= roomPart.getY()) && (player.getX() <= (roomPart.getX() + roomPart.getWidth()-10)) && (player.getY() <= (roomPart.getY() + roomPart.getHeight()-10))){
				result =  roomPart.accept(player);
			}
		}
		return result;
	}
	@Override
	public void action() {
	}
}
