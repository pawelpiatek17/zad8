package mazeElements;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import maze.Maze;
import visitors.BaseVisitor;
import visitors.KeyVisitor;
import visitors.Visitor;

public class Key extends Rectangle implements RoomPart{
	static int counter = 0;
	private int id;
	private String type;
	public Key(double x, double y) {
		super(x,y,10,10);
		this.id = counter;
		counter++;
		setFill(Color.GOLD);
		type = RoomPart.KEY;
	}

	public int getKeyId() {
		return id;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public Bounds getBoundsInP() {
		return getBoundsInParent();
	}

	@Override
	public boolean accept(Player player) {
		if(player.getX() == getX() && player.getY() == getY()){
			player.addKey(getKeyId());
			Maze.pane.getChildren().remove(this);
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
		if(acyclicDrawVisitor instanceof KeyVisitor){
			((KeyVisitor) acyclicDrawVisitor).visit(this, pane);
		}
	}
}
