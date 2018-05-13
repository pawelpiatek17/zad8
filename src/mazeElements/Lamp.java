package mazeElements;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Lamp extends Circle{
	Color lightColor = Color.YELLOW;
	private Color lampColor = Color.LIGHTCYAN;
	public Lamp(double x, double y) {
		super(x,y,4);
		setFill(lampColor);
	}

}
