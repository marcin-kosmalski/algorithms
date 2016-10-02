package coursera.alg1.week1.quiz;

import static org.junit.Assert.*;

import org.junit.Test;

public class Ex2Tests {

	@Test
	public void test1() {
		Ex2 e=new Ex2(new int[]{1,2,3});
		
		assertEquals(2, e.successor(1));
		assertEquals(3, e.successor(2));
	}
	
	@Test
	public void test2() {
		Ex2 e=new Ex2(new int[]{1,2,3});
		
		e.remove(2);
		assertEquals(3, e.successor(1));
	}
	
	@Test
	public void test3() {
		Ex2 e=new Ex2(new int[]{1,2,3});
		
		e.remove(1);
		assertEquals(3, e.successor(2));
	}
	

	@Test
	public void test4() {
		Ex2 e=new Ex2(new int[]{1,2,3});
		
		e.remove(3);
		assertEquals(2, e.successor(1));
	}
	
	@Test
	public void test5() {
		Ex2 e=new Ex2(new int[]{3,2,1});
		
		e.remove(3);
		assertEquals(2, e.successor(1));
	}
	
	//3 remove first
	//3 remove last
	//3 remove middle
	
	


}
