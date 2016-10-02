package coursera.alg1.week1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Test;

import edu.princeton.cs.algs4.In;

public class PercolationTest {

	@Test
	public void file1() {
		String inputFile = "percolation//input1-no.txt";
		assertFalse(testWithFileInput(inputFile));
	}

	@Test
	public void file2() {
		String inputFile = "percolation//input1.txt";
		assertTrue(testWithFileInput(inputFile));
	}

	@Test
	public void file3() {
		String inputFile = "percolation//input10-no.txt";
		assertFalse(testWithFileInput(inputFile));
	}
	

	@Test
	public void allFiles() {

		try {
			for (String fileName : Files.list(Paths.get("percolation"))
					.map(v -> v.toString())
					.filter(n -> n.toString().endsWith("txt"))
					.collect(Collectors.toList())) {
				boolean shouldPercolate = !fileName.endsWith("-no.txt");
				if(Arrays.asList("percolation\\greeting57.txt","percolation\\heart25.txt").contains(fileName)){
					continue;
				}
				if(shouldPercolate){
					assertTrue(testWithFileInput(fileName));
				}else{
					assertFalse(testWithFileInput(fileName));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private boolean testWithFileInput(String fileName) {
		In in = new In(fileName); // input file
		int n = in.readInt(); // n-by-n percolation system

		// repeatedly read in sites to open and draw resulting system
		Percolation perc = new Percolation(n);
		while (!in.isEmpty()) {
			int i = in.readInt();
			int j = in.readInt();
			perc.open(i, j);
			if (perc.percolates()) {
				break;
			}
		}
		return perc.percolates();
	}

	@Test
	public void testn1() {
		Percolation p = new Percolation(1);
		assertFalse(p.percolates());

		p.open(1, 1);
		assertTrue(p.percolates());
	}
	

	@Test
	public void testn2() {
		Percolation p = new Percolation(2);
		assertFalse(p.percolates());

		p.open(1, 1);
		assertFalse(p.percolates());
		p.open(2, 1);
		assertTrue(p.percolates());
	}

	@Test
	public void testn3() {
		Percolation p = new Percolation(3);
		assertFalse(p.percolates());

		p.open(1, 1);
		assertFalse(p.percolates());
		p.open(2, 1);
		assertFalse(p.percolates());
		p.open(2, 2);
		assertFalse(p.percolates());
		p.open(3, 2);
		assertTrue(p.percolates());
	}

	@Test
	public void testMonteCarlo() {
		int n = 100;
		Percolation p = new Percolation(n);

		for (int x = 0; x < 100; x++) {
			p = new Percolation(n);
			Random rand = new Random();
			int t = n * n;
			while (t > 0) {
				int i = rand.nextInt(n) + 1;
				int j = rand.nextInt(n) + 1;
				if (!p.isOpen(i, j)) {
					t--;
					p.open(i, j);

					if (p.percolates()) {
						break;
					}
				}
			}
		}
		assertTrue(p.percolates());
	}

}
