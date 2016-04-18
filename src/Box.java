
public class Box implements iMover {

	int x;
	int y;
	Tile myTile;
	MazeController myMaze;
	char type;
	
	public Box(int x, int y, MazeController maze) {
		Tile newTile = getMyTile(x,y);
		char newtype = ' ';
		if (MazeController.isTileFree(x, y, maze) == false){
			View.Say("Tile cannot be placed on a wall, or a current player");
		} else if (newTile.type == '.'){
			this.set(x,y,'*', newTile, maze);
		} else if (newTile.type == '?'){
			this.set(x,y,'$', newTile, maze);
		}
	}
	public void set(int x, int y, char type, Tile newTile, MazeController maze){
		this.x = x;
		this.y = y;
		this.myTile = newTile;
		this.type = type;
		this.myMaze = maze;
	}
	
	public void move(Direction direction){
		boolean canMove = false;
		Tile tile = getMyTile(this.x,this.y);
		canMove = MazeController.canIMove(tile.x, tile.y, direction, tile);
		if (canMove == true){
			this.x = this.x + direction.horizontal;
			this.y = this.y + direction.vertical;
		}
		GameController.updateTiles(myTile.myMaze);
	}

	@Override
	public Tile getMyTile(int x, int y) {
		Tile tile = null;
		for (Tile t : MazeController.AllTiles){
			if (t.x == x && t.y == y){
				tile = t;
			}
		}
		return tile;
	}
}