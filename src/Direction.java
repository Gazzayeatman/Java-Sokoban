
public enum Direction {
	UP(-0, -1),
	RIGHT(1, 0),
	DOWN(0, 1),
	LEFT(-1, 0);
	
	public final int vertical;
	public final int horizontal;
	
	Direction ( int horizontal, int vertical ){
		this.vertical = vertical;
		this.horizontal = horizontal;
	}
}