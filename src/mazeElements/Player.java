package mazeElements;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import visitors.BaseVisitor;
import visitors.DrawVisitor;
import visitors.PlayerVisitor;

public class Player extends Rectangle{
	public static final String PLAYER = "player";
	private ArrayList<Integer> keys;
	public Player(double d, double e) {
		super();
		this.setWidth(10);
		this.setHeight(10);
		this.setFill(Color.FORESTGREEN);
		setX(d);
		setY(e);
		keys = new ArrayList<>();
	}	
	public void setXY(double x, double y){
		setX(x);
		setY(y);
	}
	public boolean hasKey(int keyId) {
		return keys.contains(keyId);
	}
	public void addKey(int key){
		keys.add(key);
	}
	public void accept(DrawVisitor drawVisitor, Pane pane) {
		drawVisitor.visit(this, pane);
	}
	public void accept(BaseVisitor acyclicDrawVisitor, Pane pane) {
		if(acyclicDrawVisitor instanceof PlayerVisitor){
			((PlayerVisitor) acyclicDrawVisitor).visit(this, pane);
		}
	}
}
