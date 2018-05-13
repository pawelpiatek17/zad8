package visitors;

import javafx.scene.layout.Pane;
import mazeElements.StandardDoor;

public interface StandardDoorVisitor extends BaseVisitor{
	public default void visit(StandardDoor d, Pane pane) {
		if(!pane.getChildren().contains(d)){
			pane.getChildren().add(d);
		}
		d.toFront();
	}
}
