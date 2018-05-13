package visitors;

import javafx.scene.layout.Pane;
import mazeElements.MagicHighRoom;
import mazeElements.RoomPart;

public interface MagicHighRoomVisitor extends BaseVisitor {
	public default void visit(MagicHighRoom r, Pane pane){
		for (RoomPart rp : r.getParts()) {
			rp.accept(this, pane);
		}	
	}
}
