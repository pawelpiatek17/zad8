package factories;

import mazeElements.Door;
import mazeElements.HighRoom;
import mazeElements.Key;
import mazeElements.MagicDoor;
import mazeElements.MagicHighRoom;
import mazeElements.MagicRoom;
import mazeElements.Room;

public class MagicMazeElementsFactory extends MazeElementsFactory{

	@Override
	public Room createRoom(double x, double y, double width, double heigth) {
		return new MagicRoom(x, y,width,heigth);
	}

	@Override
	public Door createDoor(double x, double y) {
		return new MagicDoor(x,y);
	}

	@Override
	public Key createKey(double x, double y) {
		return new Key(x,y);
	}
	@Override
	public HighRoom createHighRoom(double x, double y, double width, double heigth) {
		return new MagicHighRoom(x, y,width,heigth);
	}
}
