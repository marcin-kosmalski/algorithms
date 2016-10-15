package coursera.alg1.week4;

import static org.junit.Assert.*;

import org.junit.Test;

public class MaxPQTest {

	@Test
	public void test() {

		MaxPQ q=new MaxPQ(10);
		q.insert(3);
		q.insert(9);
		q.insert(1);
		
		assertEquals(9,q.delMax());
		assertEquals(false,q.isEmpty());
		assertEquals(3,q.delMax());
		assertEquals(false,q.isEmpty());
		assertEquals(1,q.delMax());
		assertEquals(true,q.isEmpty());	
	}
}
