package visitors;

import javafx.scene.layout.Pane;
import mazeElements.RoomPart;
import mazeElements.StandardHighRoom;

public interface StandardHighRoomVisitor extends BaseVisitor {
	public default void visit(StandardHighRoom r, Pane pane){
		for (RoomPart rp : r.getParts()) {
			rp.accept(this, pane);
		}	
	}
}
