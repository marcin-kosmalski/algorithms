package coursera.alg1.week4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class BoardTest {

	@Test
	public void hammingZeroTest() {
		Board board = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 },
				{ 7, 8, 0 } });
		assertEquals(0, board.hamming());
	}

	@Test
	public void hammingFiveTest() {
		Board board = new Board(new int[][] { { 2, 1, 3 }, { 5, 6, 4 },
				{ 7, 8, 0 } });
		assertEquals(5, board.hamming());
	}

	@Test
	public void twin() {
		Board board = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 },
				{ 7, 8, 0 } });
		assertNotEquals(board.toString(), board.twin().toString());
		assertNotEquals(board.toString(), board.twin().toString());
		assertNotEquals(board.toString(), board.twin().toString());
		assertNotEquals(board.toString(), board.twin().toString());
	}

	@Test
	public void neighborsZeroInTheMiddle() {
		Board board = new Board(new int[][] { { 1, 2, 3 }, { 4, 0, 6 },
				{ 7, 8, 5 } });

		int num = 0;
		for (Board nBoard : board.neighbors()) {
			num++;
		}
		assertEquals(4, num);
	}

	@Test
	public void neighborsZeroTopMiddle() {
		Board board = new Board(new int[][] { { 1, 0, 2 }, { 4, 3, 6 },
				{ 7, 8, 5 } });

		int num = 0;
		for (Board nBoard : board.neighbors()) {
			num++;
		}
		assertEquals(3, num);
	}

	@Test
	public void neighborsZeroTopRightMiddle() {
		Board board = new Board(new int[][] { { 1, 2, 0 }, { 4, 3, 6 },
				{ 7, 8, 5 } });

		int num = 0;
		for (Board nBoard : board.neighbors()) {
			num++;
		}
		assertEquals(2, num);
	}

	@Test
	public void neighborsZeroBottomRightMiddle() {
		Board board = new Board(new int[][] { { 1, 2, 5 }, { 4, 3, 6 },
				{ 7, 8, 0 } });

		int num = 0;
		for (Board nBoard : board.neighbors()) {
			num++;
		}
		assertEquals(2, num);
	}

	@Test
	public void neighborsZeroLeftMiddle() {
		Board board = new Board(new int[][] { { 1, 2, 5 }, { 0, 3, 6 },
				{ 7, 8, 4 } });

		int num = 0;
		for (Board nBoard : board.neighbors()) {
			num++;
		}
		assertEquals(3, num);
	}

	@Test
	public void manhattanZeroTest() {
		Board board = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 },
				{ 7, 8, 0 } });
		assertEquals(0, board.manhattan());
	}

	@Test
	public void manhattanOneShiftTest() {
		Board board = new Board(new int[][] { { 2, 1, 3 }, { 4, 5, 6 },
				{ 7, 8, 0 } });
		assertEquals(2, board.manhattan());
	}


	@Test
	public void manhattanTest() {
		Board board = new Board(new int[][] { { 0, 1, 2 }, { 7, 5, 4 },
				{ 8, 6, 3 } });
		assertEquals(10, board.manhattan());
	}

	@Test
	public void manhattanTwoShiftsTest() {
		Board board = new Board(new int[][] { { 3, 2, 1 }, { 4, 5, 6 },
				{ 7, 8, 0 } });
		assertEquals(4, board.manhattan());
	}

	@Test
	public void manhattanTwoShiftsComplexTest() {
		Board board = new Board(new int[][] { { 8, 2, 3 }, { 4, 5, 6 },
				{ 7, 1, 0 } });
		assertEquals(6, board.manhattan());
	}

	@Test
	public void isGoal() {
		Board board = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 },
				{ 7, 8, 0 } });
		assertTrue(board.isGoal());
	}

	@Test
	public void isNotGoal() {
		Board board = new Board(new int[][] { { 8, 2, 3 }, { 4, 5, 6 },
				{ 7, 1, 0 } });
		assertFalse(board.isGoal());
	}
}
