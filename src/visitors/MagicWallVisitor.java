package visitors;

import javafx.scene.layout.Pane;
import mazeElements.MagicWall;

public interface MagicWallVisitor extends BaseVisitor {
	public default void visit(MagicWall w, Pane pane) {
		pane.getChildren().add(w);
		w.toBack();
	}
}
