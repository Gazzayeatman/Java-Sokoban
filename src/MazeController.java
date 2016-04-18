import java.util.ArrayList;


public class MazeController implements iMazeController {
	int gridX;
	int gridY;
	int defaultX;
	int defaultY;
	public static ArrayList<Tile> AllTiles = new ArrayList<Tile>();
	public static ArrayList<Box> AllBoxes = new ArrayList<Box>();
	public static ArrayList<Player> AllPlayers = new ArrayList<Player>();
	public static ArrayList<Map> AllMaps = new ArrayList<Map>();
	public MazeController(int x, int y, int dx, int dy) {
		this.set(x,y,dx,dy);
	}
	public void set(int x, int y, int dx, int dy){
		this.gridX = x;
		this.gridY = y;
		this.defaultX = dx;
		this.defaultY = dy;
	}
	public static boolean isTileFree(int x, int y, MazeController maze){
		boolean b = true;
		for (Player p : AllPlayers){
			if (p.myMaze == maze){
				if (p.x == x && p.y == y){
					b = false;
				}
			}
		}
		for (Box box : AllBoxes){
			if (box.myMaze == maze){
				if (box.x == x && box.y == y){
					b = false;
				}
			}
		}		
		return b;
	}
	public static Box returnBox(int x, int y){
		Box box = null;
		for (Box b : AllBoxes){
			if (b.x == x && b.y == y){
				box = b;
			}
		}
		return box;
	}
	public static Player returnPlayer(int x, int y){
		Player player = null;
		for (Player b : AllPlayers){
			if (b.x == x && b.y == y){
				player = b;
			}
		}
		return player;
	}
	public void createTiles(){
		for (int y = 0; y < this.gridY; y++){
			for (int x= 0; x < this.gridX; x++){
				Tile tile = new Tile(x,y,'?', this);
				AllTiles.add(tile);
			}
		}
	}
	public void createTiles(String maze){
		int length = maze.length();
		int mazeSize = (int) Math.sqrt(length);
		int i = 0;
			for (int y = 0; y < mazeSize; y++){
				for (int x= 0; x < mazeSize; x++){
					Tile tile = new Tile(x,y,maze.charAt(i), this);
					AllTiles.add(tile);
					i++;
				}
			}
	}
	public void createBox(int x, int y){
		Box box = new Box(x,y,this);
		MazeController.AllBoxes.add(box);
	}
	public static boolean canIMove(int x, int y, Direction direction, Tile tile){
		Tile tileBeside = tile.checkTileBeside(x,y, direction);
		boolean b = false;
		if (tileBeside == null){
			b = false;
		} else {
			if (tileBeside.type == '#'){
				b = false;
			} else if (tileBeside.type == '$' || tileBeside.type == '*'){
				Tile otherTile = tile.checkTileBeside(x + direction.horizontal, y + direction.vertical, direction);
				if (otherTile.type == '$' || otherTile.type == '*' || otherTile.type == '#'){
					b = false;
				} else if (otherTile.type == '.' || otherTile.type == '?'){
					b = true;
					// Pushing the box
					Box box = returnBox(x + direction.horizontal,y + direction.vertical);
					box.move(direction);
				}
			} else if (tileBeside.type == '.' || tileBeside.type == '?'){
				b = true;
			}
		}
		return b;
	}
	public int returnTileCount(MazeController maze){
		int result = 0;
		for (Tile t : AllTiles){
			if (t.myMaze == this){
				result++;
			}
		}
		return result;
	}
	public String displayMaze(MazeController maze){
		String result = "";
		String temp = "";
		int row = (int) Math.sqrt(returnTileCount(maze));
		int size = this.AllTiles.size();
		int count = 0;
		for (int y = 0; y < size; y++){
			Tile t = AllTiles.get(y);
			if (t.myMaze == maze){
				temp += " " + AllTiles.get(y).type;
				count++;
				if (count >= row){
					temp += "\n";
					count = 0;
				}
			}
		}
		result = temp;
		return result;
	}
}