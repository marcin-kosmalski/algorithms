package dynamic.programming;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Apples {

	@Test
	public void test() {
		int[][] apples = new int[][] {//
		        { 5, 2, 3 },// 5 7 10
				{ 3, 9, 7 },// 8 17 24
				{ 8, 4, 9 } // 16 21 33
		};
		assertEquals(33,findMaxApple(apples));
		
		apples = new int[][] {//
		        { 1, 1, 1 },// 1 2 3
				{ 1, 1, 1 },// 2 3 4
				{ 1, 1, 1 } // 3 4 5
		};
		assertEquals(5,findMaxApple(apples));
		apples = new int[][] {//
		        { 1, 1, 1 },// 1 2 3
				{ 1, 1, 1 },// 2 3 4
				{ 1, 1, 8 } // 3 4 5
		};
		assertEquals(12,findMaxApple(apples));
	}

	private int findMaxApple(int[][] apples) {
		int[][] weights = new int[apples.length][apples.length];
		for(int appleRowIndex=0;appleRowIndex<apples.length;appleRowIndex++){
			for(int appleColIndex=0;appleColIndex<apples[appleRowIndex].length;appleColIndex++){
				int wRow=0;
				if(appleRowIndex-1>=0){
					wRow=weights[appleRowIndex-1][appleColIndex];
				}
				int wCol=0;
				if(appleColIndex-1>=0){
					wCol=weights[appleRowIndex][appleColIndex-1];
				}
				 weights[appleRowIndex][appleColIndex]=Math.max(wRow,wCol)+apples[appleRowIndex][appleColIndex];
			}
		}
		return weights[apples.length-1][apples.length-1];
		
	}
}
