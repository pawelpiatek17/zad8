package visitors;

import javafx.scene.layout.Pane;
import mazeElements.StandardWall;

public interface StandardWallVisitor extends BaseVisitor {
	public default void visit(StandardWall w, Pane pane) {
		pane.getChildren().add(w);
		w.toBack();
	}
}
