package coursera.alg1.week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * @author Marcin Kosmalski
 * 
 *         Can be use to perform a series of computational experiments of
 *         percolation.
 *
 *         Run example: % java PercolationStats 200 100 mean =
 *         0.5929934999999997 stddev = 0.00876990421552567 95% confidence
 *         interval = 0.5912745987737567, 0.5947124012262428
 */
public class PercolationStats {

	/**
	 * The two sided confidence level for the 95% confidence interval is 1.96.
	 */
	private static final double CONFIDENCE_LEVEL = 1.96d;

	private double[] executionResults;

	private int n;
	private int trials;

	/**
	 * perform trials independent experiments on an n-by-n grid
	 * 
	 * @param n
	 * @param trials
	 */
	public PercolationStats(int n, int trials) {
		this.n = n;
		this.trials = trials;
		if (this.n <= 0) {
			throw new IllegalArgumentException("n must be positive number");
		}
		if (trials <= 0) {
			throw new IllegalArgumentException("trials must be positive number");
		}

		this.executionResults = execPercolation();
	}

	/**
	 * @return sample mean of percolation threshold
	 */
	public double mean() // sample mean of percolation threshold
	{
		return StdStats.mean(executionResults);
	}

	/**
	 * @return sample standard deviation of percolation threshold
	 */
	public double stddev() // sample standard deviation of percolation{
	{
		return StdStats.stddev(executionResults);
	}

	/**
	 * @return low endpoint of 95% confidence interval
	 */
	public double confidenceLo() // low endpoint of 95% confidence interval
	{
		return mean() - (CONFIDENCE_LEVEL * stddev()) / Math.sqrt(trials);
	}

	/**
	 * @return high endpoint of 95% confidence interval
	 */
	public double confidenceHi() // high endpoint of 95% confidence interval
	{
		return mean() + (CONFIDENCE_LEVEL * stddev()) / Math.sqrt(trials);
	}

	public static void main(String[] args) {
		int n = Integer.valueOf(args[0]).intValue();
		int trials = Integer.valueOf(args[1]);
		PercolationStats ps = new PercolationStats(n, trials);

		String meanStr = String.valueOf(ps.mean());
		String stddevStr = String.valueOf(ps.stddev());
		System.out.println("mean"
				+ String.format("%1$" + (22 + meanStr.length()) + "s", " = "
						+ meanStr));
		System.out.println("stddev"
				+ String.format("%1$" + (20 + stddevStr.length()) + "s", " = "
						+ stddevStr));
		System.out.println("95% confidence interval"
				+ String.format("%1$s",
						" = " + String.valueOf(ps.confidenceLo()) + ", "
								+ String.valueOf(ps.confidenceHi())));
	}

	private double[] execPercolation() {
		double[] res = new double[trials];
		for (int tNo = 0; tNo < trials; tNo++) {
			Percolation p = new Percolation(n);
			int t = 0;
			int size = n * n;
			while (t <= size) {
				int i = StdRandom.uniform(n) + 1;
				int j = StdRandom.uniform(n) + 1;
				if (!p.isOpen(i, j)) {
					t++;
					p.open(i, j);
					if (p.percolates()) {
						res[tNo] = Double.valueOf((double) t / (double) (size));
						break;
					}
				}
			}
		}
		return res;
	}

}
