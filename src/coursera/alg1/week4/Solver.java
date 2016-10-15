package coursera.alg1.week4;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import edu.princeton.cs.algs4.MinPQ;

public class Solver {

	private SearchNode goalSearchNode;

	private List<Board> solution;

	private boolean isSolvable = true;

	public Solver(Board initial) { // find a solution to the initial board
									// (using the A* algorithm)
		if (initial == null) {
			throw new NullPointerException();
		}

		this.goalSearchNode = findTheGoal(initial);
		this.solution = createSolution();
	}

	public boolean isSolvable() { // is the initial board solvable?
		return isSolvable;
	}

	public int moves() { // min number of moves to solve initial board; -1 if
							// unsolvable
		if (!isSolvable) {
			return -1;
		}
		return solution.size() - 1;
	}

	public Iterable<Board> solution() { // sequence of boards in a shortest
		if (!isSolvable) { // solution; null if unsolvable
			return null;
		}
		return solution;
	}

	public static void main(String[] args) {// solve a slider puzzle (given
											// below)

	}

	private SearchNode findTheGoal(Board initial) {

		MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
		MinPQ<SearchNode> pqTwin = new MinPQ<SearchNode>();

		// System.out.println("INIT: " + initial);
		Board twinBoard = initial.twin();
		// System.out.println("TWIN: " + twinBoard);
		SearchNode initSearchNode = new SearchNode(initial, 0);
		SearchNode initTwinSearchNode = new SearchNode(twinBoard, 0);
		pq.insert(initSearchNode);
		pqTwin.insert(initTwinSearchNode);

		while (true) {

			SearchNode sn = pq.delMin();
			// System.out.println(sn.getBoard());
			Optional<SearchNode> pqStep = makeStep(pq, sn);
			if (pqStep.isPresent()) {
				isSolvable = true;
				return pqStep.get();
			}

			SearchNode twinSn = pqTwin.delMin();
			Optional<SearchNode> twinPQStep = makeStep(pqTwin, twinSn);
			if (twinPQStep.isPresent()) {
				isSolvable = false;
				return twinPQStep.get();
			}
		}
	}

	private Optional<SearchNode> makeStep(MinPQ<SearchNode> pq, SearchNode sn) {
		if (sn.isGoal()) {
			return Optional.of(sn);
		}

		for (Board neighbour : sn.getBoard().neighbors()) {
			if (sn.getPrevSearchNode() != null
					&& neighbour.equals(sn.getPrevSearchNode().getBoard())) {
				continue;
			}
			SearchNode neighbourSearchNode = new SearchNode(sn, neighbour,
					sn.getMovesMadeNum() + 1);
			pq.insert(neighbourSearchNode);
		}
		return Optional.empty();
	}

	private List<Board> createSolution() {
		List<Board> solution = new ArrayList<>();
		SearchNode currSearchNode = goalSearchNode;
		while (currSearchNode != null) {
			solution.add(0, currSearchNode.getBoard());
			currSearchNode = currSearchNode.getPrevSearchNode();
		}
		return solution;
	}

	private class SearchNode implements Comparable<SearchNode> {

		private Board board;
		private int movesMadeNum;
		private SearchNode prevSearchNode;

		private int priority = -1;
		private int heuristic=-1;

		public SearchNode(SearchNode prevSearchNode, Board board,
				int movesMadeNum) {
			super();
			this.board = board;
			this.movesMadeNum = movesMadeNum;
			this.prevSearchNode = prevSearchNode;
		}

		public SearchNode(Board board, int movesMadeNum) {
			super();
			this.board = board;
			this.movesMadeNum = movesMadeNum;
		}

		public Board getBoard() {
			return board;
		}

		public int getMovesMadeNum() {
			return movesMadeNum;
		}

		public SearchNode getPrevSearchNode() {
			return prevSearchNode;
		}
		
		public boolean isGoal(){
			if(heuristic==-1){
				calcPriority();
			}
			return heuristic==0;
		}

		public int calcPriority() {
			if (priority == -1) {
				// System.out.println(board.toString());
				// System.out.println("M: "+board.manhattan());
				heuristic=board.manhattan();
				priority = movesMadeNum +heuristic;
			}
			return priority;
		}

		@Override
		public int compareTo(SearchNode o) {
			return Integer.compare(this.calcPriority(), o.calcPriority());
		}

	}

}