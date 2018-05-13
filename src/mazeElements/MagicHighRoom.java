package mazeElements;

import java.util.ArrayList;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import visitors.BaseVisitor;
import visitors.MagicHighRoomVisitor;
import visitors.Visitor;

public class MagicHighRoom implements HighRoom {
	ArrayList<RoomPart> parts;
	private MagicWall wall;
	private boolean lightStatus = false;
	Lamp lamp;
	public MagicHighRoom(double x, double y, double width, double heigth) {
		parts = new ArrayList<>();
		wall = new MagicWall(x, y, width, heigth);
		parts.add(wall);
		lamp = new Lamp(((x+width)-x)/2+x,((heigth+y)-y)/2+y);
	}
	@Override
	public ArrayList<RoomPart> getParts() {
		return parts;
	}
	@Override
	public Key addKey(Key key) {
		parts.add(0,key);
		return key;
	}

	@Override
	public void addDoor(Door door) {
		door.addRoom((Room)this);
		parts.add(door);
	}

	@Override
	public int getXCoordinate() {
		return wall.getXCoordinate();
	}

	@Override
	public int getYCoordinate() {
		return wall.getYCoordinate();
	}

	@Override
	public int getH() {
		return (int) wall.getH();
	}

	@Override
	public int getW() {
		return (int) wall.getW();
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
		pane.getChildren().add(lamp);
		lamp.toFront();
	}

	@Override
	public void accept(BaseVisitor acyclicDrawVisitor, Pane pane) {
		if (acyclicDrawVisitor instanceof MagicHighRoomVisitor) {
			((MagicHighRoomVisitor) acyclicDrawVisitor).visit(this,pane);
			pane.getChildren().add(lamp);
			lamp.toFront();
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
	public void turnLightOn() {
		wall.setFill(lamp.lightColor);
	}
	@Override
	public void turnLightOff() {
		wall.setFill(wall.DEFAULTCOLOR);
	}
	@Override
	public void action() {
		if(lightStatus){
			turnLightOff();
			lightStatus = false;
		}
		else{
			turnLightOn();
			lightStatus = true;
		}
	}
}
