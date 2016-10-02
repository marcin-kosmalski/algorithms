package coursera.alg1.week1;

import org.junit.Test;

public class PercolationStatsTest {

	
	@Test
	public void test(){
		PercolationStats s=new PercolationStats(100, 1000);
		System.out.println(s.mean());
		System.out.println(s.stddev());
	}
}
