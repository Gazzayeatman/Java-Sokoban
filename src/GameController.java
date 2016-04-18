import java.util.ArrayList;
import java.util.Scanner;


public class GameController {

	public static void main(String[] args) {
		View view = new View(1);
		MazeController maze = new MazeController(5,5, 1,0);
		maze.createTiles("########?????##.????##.????##?????##?????#########");
		Filer filer = new Filer(1);
		filer.saveFile("########?????##.????##.????##?????##?????#########", "Level One");
		filer.saveFile("?????????????##.????##.????##?????##?????#########", "Level Two");
		filer.loadFile("Level One");
		filer.loadFile("Level Two");
		view.say(filer.returnExistingMaps());
		Player player = new Player(1,1,1,maze);
		maze.AllPlayers.add(player);
		maze.createBox(2,3);
		maze.createBox(3,3);
		updateTiles(maze);
		Box box = maze.returnBox(2,3);
		view.say(maze.displayMaze(maze));
		Scanner sc = new Scanner(System.in);
		while (checkWin() == false){
			maze.displayMaze(maze);
			switch (sc.nextLine()){
				case "w": player.move(Direction.UP);
				break;
				case "a": player.move(Direction.LEFT);
				break;
				case "s": player.move(Direction.DOWN);
				break;
				case "d": player.move(Direction.RIGHT);
				break;
				default: view.say("W: Up, A: Left S: Down D: Right. Please enter a valid move");
				break;
			}
			updateTiles(maze);
			view.say(maze.displayMaze(maze));
		}
	}
	public static void updateTiles(MazeController maze){
		Player player = null;
		Box box = null;
		for (Tile t : MazeController.AllTiles){
			if (t.myMaze == maze){
				player = MazeController.returnPlayer(t.x, t.y);
			}
			if (t.myMaze == maze){
				box = MazeController.returnBox(t.x,t.y);
			}
			if (player != null){
				if (player.myMaze == maze){
					if (t.type == '.'){
						t.type = '+';
					} else if (t.type == '?'){
						t.type = '@';
					}
				}
			} else {
				if (t.type == '+'){
					t.type = '.';
				} else if (t.type == '@'){
					t.type = '?';
				}
			}
			if (box != null){
				if (box.myMaze == maze){
					if (t.type == '.'){
						t.type = '*';
					} else if (t.type == '?'){
						t.type = '$';
					}
				}
			} else {
				if (t.type == '*'){
					t.type = '.';
				} else if (t.type == '$'){
					t.type = '?';
				}
			}
		}
	}
	public static int numberOfGoals(){
		int goals = 0;
		for (Tile t : MazeController.AllTiles){
			if (t.type == '*'){
				goals += 1;
			}
		}
		return goals;
	}
	public static boolean checkWin(){
		int allBoxes = MazeController.AllBoxes.size();
		int goals = numberOfGoals();
		if (goals == allBoxes){
			View.Say("YOU WIN");
			return true;
		} else {
			return false;
		}
	}
}