package visitors;

import javafx.scene.layout.Pane;
import mazeElements.Player;

public interface PlayerVisitor extends BaseVisitor {
	public default void visit(Player p, Pane pane){
		p.toFront();
		pane.getChildren().add(p);
	}
}
