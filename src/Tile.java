
public class Tile implements iTile {
	int x;
	int y;
	char type;
	MazeController myMaze;
	
	public Tile(int x, int y, char t, MazeController maze){
		this.set(x,y,t, maze);
	}
	public void set(int x, int y, char t, MazeController maze){
		if (t == '#' || t == '.' || t == '@' || t == '+' || t == '$' || t == '*' || t == '?'){
			this.x = x;
			this.y = y;
			this.type = t;
			this.myMaze = maze;
		} else { //If t is not a recognised type then this will set the type to a null tile.
			this.x = x;
			this.y = y;
			this.type = '?';
			this.myMaze = maze;
		}
	}
	public Tile checkTileBeside(int x, int y, Direction direction){
		Tile tile = null;
		int nx = x + direction.horizontal;
		int ny = y + direction.vertical;
		for (Tile t : MazeController.AllTiles){
			if (t.x == nx && t.y == ny){
				tile = t;
			}
		}
		return tile;
	}
}