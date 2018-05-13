package mazeElements;

import java.util.ArrayList;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import visitors.BaseVisitor;
import visitors.Visitor;

public interface Room {
	public static final String ROOM = "room";
	ArrayList<RoomPart> getParts();
	Key addKey(Key key);
	void addDoor(Door door);
	int getXCoordinate();
	int getYCoordinate();
	int getH();
	int getW();
	boolean intersects(Bounds b);
	void accept(Visitor visitor, Pane pane);
	void accept(BaseVisitor acyclicDrawVisitor, Pane pane);
	boolean accept(Player player);
	void action();
}
