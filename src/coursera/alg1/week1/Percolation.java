package coursera.alg1.week1;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author Marcin Kosmalski
 *
 *         A program to estimate the value of the percolation threshold via
 *         Monte Carlo simulation.
 *
 */
public class Percolation {

	private boolean[] fields;

	private WeightedQuickUnionUF uf;

	private WeightedQuickUnionUF ufBw;

	private int n;

	private int top = 0;
	
	private int bottom = 0;

	/**
	 * create n-by-n grid, with all sites blocked
	 * 
	 * @param n
	 */
	public Percolation(int n) {
		this.n = n;
		if (this.n <= 0) {
			throw new IllegalArgumentException("n must be positive number");
		}
		this.bottom = this.n * this.n + 1;
		this.fields = new boolean[this.n * this.n];
		uf = new WeightedQuickUnionUF(this.n * this.n + 2);
		ufBw = new WeightedQuickUnionUF(this.n * this.n + 1);
	}

	/**
	 * 0 0 1 0 0 1 1 0 1 open site (row i, column j) if it is not open already
	 * 
	 * @param i
	 *            row index
	 * @param j
	 *            column index
	 */
	public void open(int i, int j) {
		validate(i, j);

		this.fields[toNum(i, j)] = true;
		if (i == 1) {
			// open top
			uf.union(this.top, toENum(i, j));
			ufBw.union(this.top, toENum(i, j));
		}
		if (i == n) {
			// open bottom
			uf.union(toENum(i, j), bottom);
		}
		if (i + 1 <= n && this.fields[toNum(i + 1, j)]) {
			uf.union(toENum(i, j), toENum(i + 1, j)); // up
			ufBw.union(toENum(i, j), toENum(i + 1, j)); // up

		}
		if (j + 1 <= n && this.fields[toNum(i, j + 1)]) {
			uf.union(toENum(i, j), toENum(i, j + 1)); // right
			ufBw.union(toENum(i, j), toENum(i, j + 1)); // right
		}
		if (j - 1 > 0 && this.fields[toNum(i, j - 1)]) {
			uf.union(toENum(i, j), toENum(i, j - 1)); // left
			ufBw.union(toENum(i, j), toENum(i, j - 1)); // left
		}
		if (i - 1 > 0 && this.fields[toNum(i - 1, j)]) {
			uf.union(toENum(i, j), toENum(i - 1, j)); // down
			ufBw.union(toENum(i, j), toENum(i - 1, j)); // down
		}

	}

	/**
	 * @return does the system percolate?
	 */
	public boolean percolates() {
		return uf.connected(this.top, this.bottom);
	}

	/**
	 * @param i
	 *            row index
	 * @param j
	 *            column index
	 * @return is site (row i, column j) full?
	 */
	public boolean isFull(int i, int j) {
		validate(i, j);
		return ufBw.connected(this.top, toENum(i, j));
	}

	/**
	 * @param i
	 *            row index
	 * @param j
	 *            column index
	 * @return is site (row i, column j) open?
	 */
	public boolean isOpen(int i, int j) {
		validate(i, j);
		return this.fields[toNum(i, j)];
	}

	private void validate(int i, int j) {
		if (i <= 0 || i > n) {
			throw new IndexOutOfBoundsException("row index i out of bounds");
		}
		if (j <= 0 || j > n) {
			throw new IndexOutOfBoundsException("row index j out of bounds");
		}
	}

	private int toENum(int row, int col) {
		// row/col index should be withing 1..n
		// array is indexed from 0 to n-1 so row and col need to be adjusted
		row -= 1;
		col -= 1;
		return n * row + col + 1;
	}

	private int toNum(int row, int col) {
		return toENum(row, col) - 1;
	}
}
