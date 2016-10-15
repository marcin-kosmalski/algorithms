package coursera.alg1.week4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.princeton.cs.algs4.MinPQ;

public class SolverTest {

	private class Unit {

		private String text;

		public Unit(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}

	}

	@Test
	public void test1() {

		Board board = new Board(new int[][] { { 1,2,3}, { 4, 5, 6 },
				{ 7, 0, 8 } });
		Solver solver = new Solver(board);
		assertEquals(1,solver.moves());
		assertTrue(solver.isSolvable());
	}
	
	@Test
	public void test4() {

		Board board = new Board(new int[][] { { 7,2,3}, { 4, 5, 6 },
				{ 8, 1, 0 } });
		Solver solver = new Solver(board);
		//assertEquals(1,solver.moves());
		assertTrue(solver.isSolvable());
	}
	
	@Test
	public void test2() {

		Board board = new Board(new int[][] { { 1,2,3}, { 0, 5, 6 },
				{ 4, 7, 8 } });
		Solver solver = new Solver(board);
		System.out.println(solver.solution());
		assertEquals(3,solver.moves());
		assertTrue(solver.isSolvable());
	}
	

	//@Test
	public void test3() {

		Board board = new Board(new int[][] { { 1,2,3}, { 4, 5, 6 },
				{ 8, 7, 0 } });
		Solver solver = new Solver(board);
		//System.out.println(solver.solution());
		assertEquals(3,solver.moves());
	}

	//@Test
	public void test() {

		MinPQ<Unit> pq = new MinPQ<>();

		pq.insert(new Unit("1111"));
		pq.insert(new Unit("3333"));
		pq.insert(new Unit("2222"));
		System.out.println(pq.delMin());

	}

}
