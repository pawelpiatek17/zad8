package visitors;

import javafx.scene.layout.Pane;
import mazeElements.Key;

public interface KeyVisitor extends BaseVisitor {
	public default void visit(Key k, Pane pane) {
		if(!pane.getChildren().contains(k)){
			pane.getChildren().add(k);
		}
	}
}
