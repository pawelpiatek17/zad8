package visitors;
import javafx.scene.layout.Pane;
import mazeElements.Door;
import mazeElements.Key;
import mazeElements.MagicDoor;
import mazeElements.MagicHighRoom;
import mazeElements.MagicRoom;
import mazeElements.MagicWall;
import mazeElements.Player;
import mazeElements.Room;
import mazeElements.RoomPart;
import mazeElements.StandardDoor;
import mazeElements.StandardHighRoom;
import mazeElements.StandardRoom;
import mazeElements.StandardWall;
import mazeElements.Wall;

public class DrawVisitor implements Visitor{
	@Override
	public void visit(MagicWall w, Pane pane) {
		pane.getChildren().add(w);
		w.toBack();
	}
	@Override
	public void visit(MagicDoor d, Pane pane) {
		if(!pane.getChildren().contains(d)){
			pane.getChildren().add(d);
		}
		d.toFront();
	}
	@Override
	public void visit(StandardWall w, Pane pane) {
		pane.getChildren().add(w);
		w.toBack();
	}
	@Override
	public void visit(StandardDoor d, Pane pane) {
		if(!pane.getChildren().contains(d)){
			pane.getChildren().add(d);
		}
		d.toFront();
	}
	@Override
	public void visit(Key k, Pane pane) {
		if(!pane.getChildren().contains(k)){
			pane.getChildren().add(k);
		}
	}
	@Override
	public void visit(Player p, Pane pane){
		p.toFront();
		pane.getChildren().add(p);
	}
	@Override
	public void visit(StandardRoom r, Pane pane) {
		for (RoomPart rp : r.getParts()) {
			rp.accept(this, pane);
		}	
	}
	@Override
	public void visit(MagicRoom r, Pane pane) {
		for (RoomPart rp : r.getParts()) {
			rp.accept(this, pane);
		}	
	}
	@Override
	public void visit(StandardHighRoom r, Pane pane) {
		for (RoomPart rp : r.getParts()) {
			rp.accept(this, pane);
		}	
	}
	@Override
	public void visit(MagicHighRoom r, Pane pane) {
		for (RoomPart rp : r.getParts()) {
			rp.accept(this, pane);
		}	
	}
}
