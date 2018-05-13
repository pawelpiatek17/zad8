package visitors;

import javafx.scene.layout.Pane;
import mazeElements.Key;
import mazeElements.MagicDoor;
import mazeElements.MagicHighRoom;
import mazeElements.MagicRoom;
import mazeElements.MagicWall;
import mazeElements.Player;
import mazeElements.StandardDoor;
import mazeElements.StandardHighRoom;
import mazeElements.StandardRoom;
import mazeElements.StandardWall;

public interface Visitor {
	public void visit(StandardRoom r, Pane pane);
	public void visit(MagicRoom r, Pane pane);
	public void visit(MagicWall w, Pane pane);
	public void visit(MagicDoor d, Pane pane);
	public void visit(StandardWall w, Pane pane);
	public void visit(StandardDoor d, Pane pane);
	public void visit(Key k, Pane pane);
	public void visit(Player p, Pane pane);
	public void visit(StandardHighRoom r, Pane pane);
	public void visit(MagicHighRoom magicHighRoom, Pane pane);
}
