package mazeElements;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import visitors.BaseVisitor;
import visitors.StandardWallVisitor;
import visitors.Visitor;

public class StandardWall extends Rectangle implements Wall {
	private String type;
	final Color DEFAULTCOLOR =  Color.DIMGREY;
	public StandardWall(double x, double y, double width, double height) {
		super(x, y, width, height);
		type = RoomPart.WALL;
		setFill(Color.DIMGREY);
		setStroke(Color.FIREBRICK);
		toBack();
		setStrokeWidth(10);
		setStrokeType(StrokeType.OUTSIDE);
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public int getXCoordinate() {
		return (int) getX();
	}

	@Override
	public int getYCoordinate() {
		return (int) getY();
	}
	@Override
	public int getW() {
		return (int) getWidth();
	}

	@Override
	public int getH() {
		return (int) getHeight();
	}

	@Override
	public Bounds getBoundsInP() {
		return getBoundsInParent();
	}

	public boolean intersects(Bounds localBounds) {
		return getBoundsInParent().intersects(localBounds);
	}

	@Override
	public boolean accept(Player player) {
		if((player.getX() >= getX()) && (player.getY() >= getY()) && (player.getX() <= (getX() + getWidth()-10)) && (player.getY() <= (getY() + getHeight()-10))){
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
		if(acyclicDrawVisitor instanceof StandardWallVisitor){
			((StandardWallVisitor) acyclicDrawVisitor).visit(this, pane);
		}
	}
}
