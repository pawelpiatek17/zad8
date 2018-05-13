package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.util.Pair;
import maze.Maze;
import mazeElements.HighRoom;
import mazeElements.Player;
import mazeElements.Room;
import mazeElements.RoomPart;

public class CustomScanner{
	private Scanner scanner;
	private ArrayList<Pair<Point2D, Dimension2D>> rooms;
	private ArrayList<Pair<Point2D, Dimension2D>> highRooms;
	private ArrayList<Pair<Point2D,Point2D>> roomParts;
	private Point2D player;
	private String mazeType;
	public CustomScanner(String file) throws FileNotFoundException,IOException,NullPointerException,NoSuchElementException,IllegalStateException {
		scanner = new Scanner(new File(file));
		rooms = new ArrayList<>();
		roomParts = new ArrayList<>();
		highRooms = new ArrayList<>();
		String s = scanner.next();
		switch (s) {
		case Maze.STANDARDMAZE:
			mazeType = s;
			break;
		case Maze.MAGICMAZE:
			mazeType = s;
			break;
		default:
			throw new IllegalArgumentException("File must contain maze type in first line");
		}
		mazeType = s;
		while(scanner.hasNextLine()){
			s = scanner.next();
			switch (s) {
			case Room.ROOM:
				rooms.add(new Pair<Point2D, Dimension2D>(new Point2D(scanner.nextInt(), scanner.nextInt()),new Dimension2D(scanner.nextInt(), scanner.nextInt())));
				break;
			case RoomPart.DOOR:
				roomParts.add(new Pair<Point2D,Point2D>(new Point2D(scanner.nextInt(), scanner.nextInt()),new Point2D(scanner.nextInt(), scanner.nextInt())));
				break;
			case Player.PLAYER:
				player = new Point2D(scanner.nextInt(), scanner.nextInt());
				break;
			case HighRoom.HIGHROOM:
				highRooms.add(new Pair<Point2D, Dimension2D>(new Point2D(scanner.nextInt(), scanner.nextInt()),new Dimension2D(scanner.nextInt(), scanner.nextInt())));
				break;
			default:
				break;
			}
		}
	}

	public ArrayList<Pair<Point2D, Dimension2D>> getRooms() {
		return rooms;
	}

	public ArrayList<Pair<Point2D, Point2D>> getRoomParts() {
		return roomParts;
	}

	public Point2D getPlayer() {
		return player;
	}
	public ArrayList<Pair<Point2D, Dimension2D>> getHighRooms() {
		return highRooms;
	}
	public String getMazeType(){
		return mazeType;
	}
}
