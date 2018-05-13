package mazeElements;

public interface Door extends RoomPart{
	public static final String LOCKED = "locked";
	public static final String OPEN = "open";
	public Room enter(Room currentRoom);
	public boolean isOpen();
	public void addRoom(Room room);
	public int getKeyId();
	public void swapColor();
	public void setKeyId(int id);
}
