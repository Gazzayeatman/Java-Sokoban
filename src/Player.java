
public class Player implements iMover {
	int id;
	int moves;
	int x;
	int y;
	MazeController myMaze;
	public Player(int id, int x, int y, MazeController maze) {
		Tile newTile = getMyTile(x,y);
		if (MazeController.isTileFree(x, y, maze) == false){
			View.Say("Player cannot be placed on a wall, or a Box");
		}
		else {
				this.set(id,x,y,maze);
			}
		if (x > maze.gridX || y > maze.gridY){
			View.Say("Player cannot be placed outside the of the maze");
			this.set(id,-1,-1,maze);
		}
	}
	public void set(int id, int x, int y, MazeController maze){
		this.id = id;
		this.moves = 0;
		this.x = x;
		this.y = y;
		this.myMaze = maze;
	}
	public Tile getMyTile(int x, int y){
		Tile tile = null;
		for (Tile a : MazeController.AllTiles){
			if (a.x == x && a.y == y){
				return a;
			}
		}
		return tile;
	}
	public void move(Direction direction){
		boolean canMove = false;
		GameController.updateTiles(myMaze);
		Tile tile = getMyTile(this.x,this.y);
		canMove = MazeController.canIMove(this.x, this.y, direction, tile);
		if (canMove == true){
			this.x = this.x + direction.horizontal;
			this.y = this.y + direction.vertical;
			this.moves++;
			//GameController.updateTiles();
		}
		GameController.updateTiles(myMaze);
	}
}