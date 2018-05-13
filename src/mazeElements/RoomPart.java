package mazeElements;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import visitors.BaseVisitor;
import visitors.Visitor;

public interface RoomPart {
	public static final String WALL = "wall";
	public static final String DOOR = "door";
	public static final String KEY = "key";
	public String getType();
	public Bounds getBoundsInP();
	public boolean intersects(Bounds localBounds);
	public boolean accept(Player player);
	public double getX();
	public double getY();
	public double getWidth();
	public double getHeight();
	public void accept(Visitor visitor, Pane pane);
	public void accept(BaseVisitor acyclicDrawVisitor, Pane pane);
}
