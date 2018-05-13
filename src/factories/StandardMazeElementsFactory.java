package factories;

import mazeElements.Door;
import mazeElements.HighRoom;
import mazeElements.Key;
import mazeElements.Room;
import mazeElements.StandardDoor;
import mazeElements.StandardHighRoom;
import mazeElements.StandardRoom;

public class StandardMazeElementsFactory extends MazeElementsFactory{

	@Override
	public Room createRoom(double x, double y, double width, double heigth) {
		return new StandardRoom(x, y,width,heigth);
	}

	@Override
	public Door createDoor(double x, double y) {
		return new StandardDoor(x,y);
	}

	@Override
	public Key createKey(double x, double y) {
		return new Key(x,y);
	}

	@Override
	public HighRoom createHighRoom(double x, double y, double width, double heigth) {
		return new StandardHighRoom(x, y, width, heigth);
	}
}
