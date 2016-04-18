import java.util.ArrayList;

public class Map {

	String level;
	String levelName;

	public Map( String level, String levelName) {
		this.level = level;
		this.levelName = levelName;
		MazeController.AllMaps.add(this);
	}

}
