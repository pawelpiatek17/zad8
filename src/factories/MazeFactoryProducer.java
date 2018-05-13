package factories;

import java.util.HashMap;

import maze.Maze;

public class MazeFactoryProducer {
	private static HashMap<String, MazeElementsFactory> map = new HashMap<String, MazeElementsFactory>();
	static{
		if(map.isEmpty()){
			map.put(Maze.MAGICMAZE, new MagicMazeElementsFactory());
			map.put(Maze.STANDARDMAZE, new StandardMazeElementsFactory());
		}
	}
	public static MazeElementsFactory getFactory(String type) {
		return map.get(type);
	}
}
