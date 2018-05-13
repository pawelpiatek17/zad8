package mazeElements;

import java.util.ArrayList;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import visitors.BaseVisitor;
import visitors.Visitor;

public class MagicRoom implements Room{
	ArrayList<RoomPart> parts;
	private MagicWall wall;
	public MagicRoom(double x, double y, double width, double heigth) {
		parts = new ArrayList<>();
		wall = new MagicWall(x, y, width, heigth);
		parts.add(wall);
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
	
	@Override
	public int getXCoordinate(){
		return wall.getXCoordinate();
	}
	
	@Override
	public int getYCoordinate(){
		return wall.getYCoordinate();
	}
	@Override
	public int getW() {
		return (int) wall.getW();
	}

	@Override
	public int getH() {
		return (int) wall.getH();
	}
	@Override
	public boolean intersects(Bounds b) {
		if(wall.intersects(b)){
			return true;
		}
		return false;
	}
	@Override
	public void accept(Visitor visitor, Pane pane) {
		visitor.visit(this, pane);
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
