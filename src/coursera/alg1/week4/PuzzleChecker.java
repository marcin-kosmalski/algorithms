/******************************************************************************
 *  Compilation:  javac PuzzleChecker.java
 *  Execution:    java PuzzleChecker filename1.txt filename2.txt ...
 *  Dependencies: Board.java Solver.java
 *
 *  This program creates an initial board from each filename specified
 *  on the command line and finds the minimum number of moves to
 *  reach the goal state.
 *
 *  % java PuzzleChecker puzzle*.txt
 *  puzzle00.txt: 0
 *  puzzle01.txt: 1
 *  puzzle02.txt: 2
 *  puzzle03.txt: 3
 *  puzzle04.txt: 4
 *  puzzle05.txt: 5
 *  puzzle06.txt: 6
 *  ...
 *  puzzle3x3-impossible: -1
 *  ...
 *  puzzle42.txt: 42
 *  puzzle43.txt: 43
 *  puzzle44.txt: 44
 *  puzzle45.txt: 45
 *
 ******************************************************************************/

package coursera.alg1.week4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class PuzzleChecker {

	public static void main(String[] args) throws IOException {

		// for each command-line argument

		List<String> list=Files.list(Paths.get(args[0])).map(f -> f.toAbsolutePath().toString())
				.collect(Collectors.toList());

		for (String filename : list) {
			if(filename.endsWith("puzzle12.txt")){
				continue;
			}
			System.out.println("Processing: "+filename);
			// read in the board specified in the filename
			In in = new In(filename);
			int n = in.readInt();
			int[][] tiles = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					tiles[i][j] = in.readInt();
				}
			}

			// solve the slider puzzle
			Board initial = new Board(tiles);
			Solver solver = new Solver(initial);
			StdOut.println(filename + ": " + solver.moves()+" : "+solver.isSolvable());
		}
	}
}
