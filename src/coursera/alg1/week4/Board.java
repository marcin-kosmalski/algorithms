package coursera.alg1.week4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

	private short[][] blocks;

	private short n = 0;

	private short num = 0;
	
	private String str=null;;

	public Board(int[][] blocks) { // construct a board from an n-by-n array of
									// blocks (where blocks[i][j] = block in row
									// i, column j)
		
		this.n = (short) blocks.length;
		this.num = (short) (n * n);
		this.blocks = copy(blocks);
	}

	public int dimension() { // board dimension n
		return n;
	}

	public int hamming() { // number of blocks out of place
		int counter = 1;
		int wrongBlocksCounter = 0;
		for (short i = 0; i < n; i++) {
			for (short j = 0; j < n; j++) {
				if (blocks[i][j] != counter++) {
					wrongBlocksCounter++;
				}
			}
		}
		wrongBlocksCounter--;
		return wrongBlocksCounter;
	}

	public int manhattan() {// sum of Manhattan distances between blocks and
		int counter = 1;
		int manhattanValue = 0;
		for (short i = 0; i < n; i++) {
			for (short j = 0; j < n; j++) {
				if (blocks[i][j] != counter++) {
					if (blocks[i][j] == 0) {
						continue;
					}
					short[] corPos = calcCorrectPos(n, blocks[i][j]);
					manhattanValue += (Math.abs(i - corPos[1])
							+ Math.abs(j - corPos[0]));
				}
			}
		}
		return manhattanValue;
	}

	public boolean isGoal() { // is this board the goal board?
		int counter = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (blocks[i][j] != counter++) {
					if (counter - 1 != num) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public Board twin() { // a board that is obtained by exchanging any pair of
							// blocks

		int[][] newBlocks = copyInt(this.blocks);
		Random rand = new Random();
		short x1 = 0;
		short y1 = 0;
		short x2 = 0;
		short y2 = 0;
		do {
			x1 = (short) rand.nextInt(n);
			y1 =(short) rand.nextInt(n);
			x2 = (short)rand.nextInt(n);
			y2 = (short)rand.nextInt(n);
		} while ((x1 == x2 && y1 == y2) || newBlocks[x1][y1] == 0
				|| newBlocks[x2][y2] == 0);
		exchange(new short[] { x1, y1 }, new short[] { x2, y2 }, newBlocks);

		return new Board(newBlocks);
	}

	public boolean equals(Object y) { // does this board equal y?
		if (y == null) {
			return false;
		}
		if (y.getClass() != this.getClass()) {
			return false;
		}
		
		return this.toString().equals(y.toString());
	}

	public Iterable<Board> neighbors() { // all neighboring boards
		short[] zeroPos = calcZeroPos();
		short zeroX = zeroPos[0];
		short zeroY = zeroPos[1];
		List<Board> boardList = new ArrayList<>();
		// move: down
		if (zeroY - 1 >= 0) {
			boardList.add(createWithNewPos(zeroPos, zeroX,(short)( zeroY - 1)));
		}
		// move: up
		if (zeroY + 1 < n) {
			boardList.add(createWithNewPos(zeroPos, zeroX,(short)( zeroY + 1)));
		}
		// move: left
		if (zeroX - 1 >= 0) {
			boardList.add(createWithNewPos(zeroPos, (short)(zeroX - 1), zeroY));
		}
		// move: right
		if (zeroX + 1 < n) {
			boardList.add(createWithNewPos(zeroPos, (short)(zeroX + 1), zeroY));
		}
		return boardList;
	}

	public String toString() { // string representation of this board (in the
		// output format specified below)
		if(str!=null){
			return str;
		}
		StringBuilder builder = new StringBuilder();
		builder.append(n);
		builder.append(System.lineSeparator());
		for (int i = 0; i < n; i++) {
			builder.append(" ");
			for (int j = 0; j < n; j++) {
				builder.append(String.format("%-2d ", blocks[i][j]));
			}
			builder.append(System.lineSeparator());
		}
		str=builder.toString();
		return str;
	}

	public static void main(String[] args) {// unit tests (not graded)

	}

	private Board createWithNewPos(short[] fromPos, short toX, short toY) {
		int[][] newBlocks = copyInt(this.blocks);
		exchange(fromPos, new short[] { toX, toY }, newBlocks);
		return new Board(newBlocks);
	}

	private void exchange(short[] pos1, short[] pos2, int[][] pBlocks) {
		int temp = pBlocks[pos1[0]][pos1[1]];
		pBlocks[pos1[0]][pos1[1]] = pBlocks[pos2[0]][pos2[1]];
		pBlocks[pos2[0]][pos2[1]] = temp;
	}
	
	
	private short[][] copy(int[][] board) {
		short[][] newBlocks = new short[n][n];
		for (short i = 0; i < n; i++) {
			for(short j=0;j<n;j++){
				newBlocks[i][j] =(short) board[i][j];
			}
			
		}
		return newBlocks;
	}
	
	private int[][] copyInt(short[][] board) {
		int[][] newBlocks = new int[n][n];
		for (short i = 0; i < n; i++) {
			for(short j=0;j<n;j++){
				newBlocks[i][j] = board[i][j];
			}
			
		}
		return newBlocks;
	}

	private short[] calcZeroPos() {
		for (short i = 0; i < n; i++) {
			for (short j = 0; j < n; j++) {
				if (blocks[i][j] == 0) {
					return new short[] { i, j };
				}
			}
		}
		throw new IllegalStateException("Zero position not found!");
	}

	private short[] calcCorrectPos(short n, short val) {
		short x = (short) ((val - 1) % n);
		short y = (short) ((val - 1) / n);
		return new short[] { x, y };
	}
}
