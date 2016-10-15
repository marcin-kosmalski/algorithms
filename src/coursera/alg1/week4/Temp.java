package coursera.alg1.week4;

public class Temp {

	private int[][] board;

	public String toString() {
		StringBuilder builder=new StringBuilder();
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board.length;j++){
				builder.append(String.format("%-"+2+"d ",board[i][j]));
			} 
			//System.out.printf("%-12.5s%s", "Hello World","World");

			builder.append(System.lineSeparator());
		}
		return builder.toString();
	}

	public Temp(int[][] board) {
		this.board = board;
	}

	public static void main(String[] args) {
		System.out.println(new Temp(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } }));
	}
}
