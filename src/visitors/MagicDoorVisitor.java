package visitors;

import javafx.scene.layout.Pane;
import mazeElements.MagicDoor;

public interface MagicDoorVisitor extends BaseVisitor {
	public default void visit(MagicDoor d, Pane pane) {
		if(!pane.getChildren().contains(d)){
			pane.getChildren().add(d);
		}
		//d.toFront();
	}
}
