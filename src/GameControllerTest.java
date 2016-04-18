import static org.junit.Assert.*;

import org.junit.Test;


public class GameControllerTest{

	
	@Test
	public void createPlayer(){
		//Tests to see if a player can be created and if the player is visible on the maze
		//and is drawn correctly
		MazeController maze = new MazeController(5,5,4,4);
		maze.createTiles("?????????????????????????");
		Player player = new Player(1,2,2,maze);
		assertEquals(1,player.id);
	}
	@Test
	public void createPlayerOnGoal(){
		//Checks to see if a player can be created on a goal
		MazeController maze = new MazeController (1,1,1,1);
		maze.createTiles(".");
		Player player = new Player(1,0,0,maze);
		maze.AllPlayers.add(player);
		assertEquals(0, player.x);
		assertEquals(0, player.y);
		GameController.updateTiles(maze);
		assertEquals(" +\n", maze.displayMaze(maze));
	}
	@Test
	public void createPlayerOnWall(){
		//Checks to see if a player can be created on a wall
		MazeController maze = new MazeController (1,1,1,1);
		maze.createTiles("#");
		Player player = new Player(1,0,0,maze);
		maze.AllPlayers.add(player);
		assertEquals(0, player.x);
		assertEquals(0, player.y);
		GameController.updateTiles(maze);
		assertEquals(" #\n", maze.displayMaze(maze));
	}
	@Test
	public void createPlayerOnBox(){
		//Checks to see if a player can be created on a box
		MazeController maze = new MazeController (2,2,2,2);
		maze.createTiles("?");
		Box box = new Box(0,0,maze);
		maze.AllBoxes.add(box);
		Player player = new Player(1,0,0,maze);
		maze.AllPlayers.add(player);
		assertEquals(0, player.x);
		assertEquals(0, player.y);
		GameController.updateTiles(maze);
		assertEquals(" $\n", maze.displayMaze(maze));
	}
	@Test
	public void createPlayerOnPlayer(){
		//Checks to see if a player can be created on a player
		MazeController maze = new MazeController (1,1,1,1);
		maze.createTiles("?");
		Player player = new Player(1,0,0,maze);
		maze.AllPlayers.add(player);
		assertEquals(0, player.x);
		assertEquals(0, player.y);
		Player player1 = new Player(1,0,0,maze);
		GameController.updateTiles(maze);
		assertEquals(" @\n", maze.displayMaze(maze));
	}
	@Test
	public void testCreateMaze(){
		//Checks to see if a maze can be created
		MazeController a = new MazeController(5,5,2,2);
		assertEquals(5,a.gridX);
		assertEquals(5,a.gridY);
	}
	@Test
	public void testDisplayMaze(){
		//Checks if a maze can be displayed correctly
		MazeController maze = new MazeController(5,5,4,4);
		maze.createTiles("?????????????????????????");
		GameController.updateTiles(maze);
		assertEquals(" ? ? ? ? ?\n ? ? ? ? ?\n ? ? ? ? ?\n ? ? ? ? ?\n ? ? ? ? ?\n", maze.displayMaze(maze));
		//Put into another test
	}
	@Test
	public void testDisplayPlayer(){
		//Testing the player exists on the field and is displayed correctly
		MazeController maze = new MazeController(5,5,2,3);
		maze.createTiles("?????????????????????????");
		Player player = new Player(1,0,0,maze);
		maze.AllPlayers.add(player);
		GameController.updateTiles(maze);
		assertEquals(" @ ? ? ? ?\n ? ? ? ? ?\n ? ? ? ? ?\n ? ? ? ? ?\n ? ? ? ? ?\n", maze.displayMaze(maze));
	}
	@Test
	public void testDisplayPlayerAndBox(){
		//Testing the player exists on the field
		MazeController maze = new MazeController(5,5,2,3);
		maze.createTiles("?????????????????????????");
		Player player = new Player(1,0,0,maze);
		Box box = new Box(0,1,maze);
		maze.AllBoxes.add(box);
		maze.AllPlayers.add(player);
		GameController.updateTiles(maze);
		assertEquals(" @ ? ? ? ?\n $ ? ? ? ?\n ? ? ? ? ?\n ? ? ? ? ?\n ? ? ? ? ?\n", maze.displayMaze(maze));
	}
	@Test
	public void testThreeBoxesAtTheSameTime(){
		//Testing the player exists on the field
		MazeController maze = new MazeController(5,5,2,3);
		maze.createTiles("?????????????????????????");
		Player player = new Player(1,0,0,maze);
		Box box = new Box(0,1,maze);
		Box box2 = new Box(0,2,maze);
		Box box3 = new Box(0,3,maze);
		maze.AllBoxes.add(box);
		maze.AllBoxes.add(box2);
		maze.AllBoxes.add(box3);
		maze.AllPlayers.add(player);
		GameController.updateTiles(maze);
		assertEquals(" @ ? ? ? ?\n $ ? ? ? ?\n $ ? ? ? ?\n $ ? ? ? ?\n ? ? ? ? ?\n", maze.displayMaze(maze));
	}
	@Test
	public void testMoveUp(){
		//Tests to see if the player can move up
		MazeController maze = new MazeController(5,5,3,3);
		maze.createTiles("?????????????????????????");
		Player player = new Player(1,2,2,maze);
		assertEquals(2, player.x);
		assertEquals(2, player.y);
		//after move
		player.move(Direction.UP);
		assertEquals(2, player.x);
		assertEquals(1, player.y);
	}
	@Test
	public void testMoveRight(){
		//tests to see if the player can move right
		MazeController maze = new MazeController(5,5,3,3);
		maze.createTiles("?????????????????????????");
		Player player = new Player(1,2,2,maze);
		assertEquals(2, player.x);
		assertEquals(2, player.y);
		//after move
		player.move(Direction.RIGHT);
		assertEquals(3, player.x);
		assertEquals(2, player.y);
	}
	@Test
	public void testMoveDown(){
		//tests to see if the player can move down
		MazeController maze = new MazeController(5,5,4,4);
		maze.createTiles("?????????????????????????");
		Player player = new Player(1,1,1, maze);
		assertEquals(1, player.x);
		assertEquals(1, player.y);
		//after move
		player.move(Direction.DOWN);
		assertEquals(1, player.x);
		assertEquals(2, player.y);
	}
	@Test
	public void testMoveLeft(){
		//Tests to see if the player can move left
		MazeController maze = new MazeController(5,5,1,1);
		maze.createTiles("?????????????????????????");
		Player player = new Player(1,2,2, maze);
		assertEquals(2, player.x);
		assertEquals(2, player.y);
		//after move
		player.move(Direction.LEFT);
		assertEquals(1, player.x);
		assertEquals(2, player.y);
	}
	@Test
	public void testMoveAgainstBoxUp(){
		//Yests to see if the player can move the box up
		MazeController maze = new MazeController(5,5,4,1);
		maze.createTiles("?????????????????????????");
		Player player = new Player(1,2,2,maze);
		assertEquals(2, player.x);
		assertEquals(2, player.y);
		maze.createBox(2,3);
		Box box = maze.returnBox(2, 3);
		//after move
		player.move(Direction.UP);
		assertEquals(2, player.x);
		assertEquals(1, player.y);
		assertEquals(2, box.x);
		assertEquals(3, box.y);
	}
	@Test
	public void testMoveAgainstBoxDown(){
		//Tests to see if the player can move the box down
		MazeController maze = new MazeController(5,5,4,1);
		maze.createTiles("?????????????????????????");
		Player player = new Player(1,2,2,maze);
		assertEquals(2, player.x);
		assertEquals(2, player.y);
		maze.createBox(2,3);
		Box box = maze.returnBox(2, 3);
		//after move
		player.move(Direction.DOWN);
		assertEquals(2, player.x);
		assertEquals(3, player.y);
		assertEquals(2, box.x);
		assertEquals(4, box.y);
	}
	@Test
	public void testMoveAgainstBoxRight(){
		//Tests to see if the player can move the box right
		MazeController maze = new MazeController(5,5,4,1);
		maze.createTiles("?????????????????????????");
		Player player = new Player(1,2,2,maze);
		assertEquals(2, player.x);
		assertEquals(2, player.y);
		maze.createBox(3,2);
		Box box = maze.returnBox(3, 2);
		//after move
		player.move(Direction.RIGHT);
		assertEquals(3, player.x);
		assertEquals(2, player.y);
		assertEquals(4, box.x);
		assertEquals(2, box.y);
	}
	@Test
	public void testMoveAgainstBoxLeft(){
		//Testing to see if the player can move the box left
		MazeController maze = new MazeController(5,5,4,1);
		maze.createTiles("?????????????????????????");
		Player player = new Player(1,2,2,maze);
		assertEquals(2, player.x);
		assertEquals(2, player.y);
		maze.createBox(1,2);
		Box box = maze.returnBox(1, 2);
		//after move
		player.move(Direction.LEFT);
		assertEquals(1, player.x);
		assertEquals(2, player.y);
		assertEquals(0, box.x);
		assertEquals(2, box.y);
	}
	@Test
	public void testMoveBoxAgainstBoxUp(){
		//Testing to see if the player can move the box up
		MazeController maze = new MazeController(5,5,4,1);
		maze.createTiles("?????????????????????????");
		Player player = new Player(1,2,2,maze);
		assertEquals(2, player.x);
		assertEquals(2, player.y);
		maze.createBox(2,3);
		maze.createBox(2,4);
		Box box = maze.returnBox(2, 3);
		Box box2 = maze.returnBox(2, 3);
		//after move
		player.move(Direction.UP);
		assertEquals(2, player.x);
		assertEquals(1, player.y);
		assertEquals(2, box.x);
		assertEquals(3, box.y);
	}
	@Test
	public void testMoveBoxAgainstBoxLeft(){
		//Testing to see if a box can be pushed against a box left
		MazeController maze = new MazeController(5,5,4,1);
		maze.createTiles("?????????????????????????");
		Player player = new Player(1,2,2,maze);
		assertEquals(2, player.x);
		assertEquals(2, player.y);
		maze.createBox(1,2);
		maze.createBox(0,2);
		Box box = maze.returnBox(1, 2);
		Box box2 = maze.returnBox(0, 2);
		//after move
		player.move(Direction.LEFT);
		assertEquals(2, player.x);
		assertEquals(2, player.y);
		assertEquals(1, box.x);
		assertEquals(2, box.y);
	}
	@Test
	public void testMoveBoxAgainstBoxDown(){
		//Testing to see if a box can be pushed against a box down
		MazeController maze = new MazeController(5,5,4,1);
		maze.createTiles("?????????????????????????");
		Player player = new Player(1,2,2,maze);
		assertEquals(2, player.x);
		assertEquals(2, player.y);
		maze.createBox(2,3);
		maze.createBox(2,4);
		Box box = maze.returnBox(2, 3);
		Box box2 = maze.returnBox(2, 4);
		//after move
		player.move(Direction.DOWN);
		assertEquals(2, player.x);
		assertEquals(2, player.y);
		assertEquals(2, box.x);
		assertEquals(3, box.y);
	}
	@Test
	public void testMoveBoxAgainstBoxRight(){
		//Testing to see if a box can be pushed against a box right
		MazeController maze = new MazeController(5,5,4,1);
		maze.createTiles("?????????????????????????");
		Player player = new Player(1,2,2,maze);
		assertEquals(2, player.x);
		assertEquals(2, player.y);
		maze.createBox(3,2);
		maze.createBox(4,2);
		Box box = maze.returnBox(3, 2);
		Box box2 = maze.returnBox(3, 2);
		//after move
		player.move(Direction.RIGHT);
		assertEquals(2, player.x);
		assertEquals(2, player.y);
		assertEquals(3, box.x);
		assertEquals(2, box.y);
	}
	@Test
	public void testMoveAgainstBoxAgainstWall(){
		//Tests to see if a box can be moved against a wall
		MazeController maze = new MazeController(5,5,4,1);
		maze.createTiles("########?????##?????##.????##?????##?????#########");
		Player player = new Player(1,2,3,maze);
		assertEquals(2, player.x);
		assertEquals(3, player.y);
		maze.createBox(1,3);
		Box box = maze.returnBox(1, 3);
		//after move
		player.move(Direction.LEFT);
		assertEquals(2, player.x);
		assertEquals(3, player.y);
		assertEquals(1, box.x);
		assertEquals(3, box.y);
	}
	@Test
	public void testNumberOfGoals() {
		//Check to see the number of boxes on goals
		assertEquals(0,GameController.numberOfGoals());
	}
	@Test
	public void testPlayerMoveAgainstWall(){
		//Checks to see if a player can move against a wall
		MazeController maze = new MazeController(3,3,1,1);
		maze.createTiles("####?####");
		Player player = new Player (1,1,1,maze);
		assertEquals(1, player.x);
		assertEquals(1, player.y);
		assertEquals(false, maze.canIMove(player.x, player.y, Direction.UP, player.getMyTile(player.x, player.y)));
		player.move(Direction.UP);
		assertEquals(1, player.x);
		assertEquals(1, player.y);
	}
	@Test
	public void createWall(){
		//Tests to see if a wall can be created
		MazeController maze = new MazeController(1,1,1,1);
		maze.createTiles("#");
		assertEquals(" #\n", maze.displayMaze(maze));
	}
	@Test
	public void createEmptyGoal(){
		//Tests to see if an empty goal can be created
		MazeController maze = new MazeController(1,1,1,1);
		maze.createTiles(".");
		assertEquals(" .\n", maze.displayMaze(maze));
	}
	@Test
	public void createEmptyTile(){
		//Checks to see if an empty tile can be created
		MazeController maze = new MazeController(1,1,1,1);
		maze.createTiles("?");
		assertEquals(" ?\n", maze.displayMaze(maze));
	}
	@Test
	public void tryCreatingTileWithEmptyString(){
		//Tries to create a maze with an empty string
		MazeController maze = new MazeController(1,1,1,1);
		maze.createTiles();
		assertEquals(" ?\n", maze.displayMaze(maze));
	}
	@Test
	public void createManOnGoal(){
		//Tries to create a player on a goal
		MazeController maze = new MazeController (1,1,1,1);
		maze.createTiles(".");
		Player player = new Player(1,0,0,maze);
		maze.AllPlayers.add(player);
		assertEquals(0, player.x);
		assertEquals(0, player.y);
		GameController.updateTiles(maze);
		assertEquals(" +\n", maze.displayMaze(maze));
	}
	@Test
	public void createManOnWall(){
		//Tries to create a player on a wall
		MazeController maze = new MazeController(1,1,1,1);
		maze.createTiles("#");
		Player player = new Player(1,0,0,maze);
		maze.AllPlayers.add(player);
		assertEquals(0, player.x);
		assertEquals(0, player.y);
		GameController.updateTiles(maze);
		assertEquals(" #\n", maze.displayMaze(maze));
	}
	@Test
	public void createMazeWithIncorrectString(){
		//Tries to create a maze with an incorrect string
		MazeController maze = new MazeController(2,2,1,1);
		maze.createTiles("ABCD");
		assertEquals(" ? ?\n ? ?\n", maze.displayMaze(maze));
	}
	@Test
	public void createMan(){
		//Tries to create a player
		MazeController maze = new MazeController (1,1,1,1);
		maze.createTiles("?");
		Player player = new Player(1,0,0,maze);
		maze.AllPlayers.add(player);
		assertEquals(0, player.x);
		assertEquals(0, player.y);
		GameController.updateTiles(maze);
		assertEquals(" @\n", maze.displayMaze(maze));
	}
	@Test
	public void createBox(){
		//Tries to create a box
		MazeController maze = new MazeController (1,1,1,1);
		maze.createTiles("?");
		Box box = new Box(0,0,maze);
		maze.AllBoxes.add(box);
		assertEquals(0, box.x);
		assertEquals(0, box.y);
		GameController.updateTiles(maze);
		assertEquals(" $\n", maze.displayMaze(maze));
	}
	@Test
	public void createBoxOnGoal(){
		//create a box on a goal
		MazeController maze = new MazeController (1,1,1,1);
		maze.createTiles(".");
		Box box = new Box(0,0,maze);
		maze.AllBoxes.add(box);
		assertEquals(0, box.x);
		assertEquals(0, box.y);
		GameController.updateTiles(maze);
		assertEquals(" *\n", maze.displayMaze(maze));
	}
	@Test
	public void createBoxOnWall(){
		//create a box on a wall
		MazeController maze = new MazeController (1,1,1,1);
		maze.createTiles("#");
		Box box = new Box(0,0,maze);
		maze.AllBoxes.add(box);
		assertEquals(0, box.x);
		assertEquals(0, box.y);
		GameController.updateTiles(maze);
		//Won't create the box because of the wall tile so the tile will stay as a wall
		assertEquals(" #\n", maze.displayMaze(maze));
	}
	@Test
	public void testMoveOffField(){
		//tests if a player moves off the field
		MazeController maze = new MazeController (1,1,1,1);
		maze.createTiles("?");
		Player player = new Player(1,0,0,maze);
		maze.AllPlayers.add(player);
		assertEquals(0, player.x);
		assertEquals(0, player.y);
		GameController.updateTiles(maze);
		player.move(Direction.LEFT);
		assertEquals(0, player.x);
		assertEquals(0, player.y);
	}
	@Test
	public void testMoveBoxOffField(){
		//Tests if a player moves a box off the field
		MazeController maze = new MazeController (1,1,1,1);
		maze.createTiles("?");
		Box box = new Box(0,0,maze);
		maze.AllBoxes.add(box);
		assertEquals(0, box.x);
		assertEquals(0, box.y);
		GameController.updateTiles(maze);
		box.move(Direction.LEFT);
		assertEquals(0, box.x);
		assertEquals(0, box.y);
	}
	@Test
	public void createBoxOnBox(){
		//tries to create a box on a box
		MazeController maze = new MazeController (1,1,1,1);
		maze.createTiles("?");
		Box box = new Box(0,0,maze);
		maze.AllBoxes.add(box);
		assertEquals(0, box.x);
		assertEquals(0, box.y);
		Box newBox = new Box(0,0,maze);
		assertEquals(null, newBox.myTile);
	}
	@Test
	public void createPlayerOutsideOfRange(){
		//tries to create a player out of the maze range
		MazeController maze = new MazeController(1,1,1,1);
		maze.createTiles();
		Player player = new Player(1,3,3,maze);
		assertEquals(-1, player.x);
	}
}
