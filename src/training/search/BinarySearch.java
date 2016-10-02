package training.search;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinarySearch {

	@Test
	public void test() {
		assertEquals(3,
				binarySearch(new int[] { 1, 2, 3, 8, 10, 11, 12, 13, 14 }, 8));
		assertEquals(5, binarySearch(new int[] { 1, 2, 2, 2, 3, 8, 10 }, 8));
		assertEquals(0, binarySearch(new int[] { 1, 2, 2, 2, 3, 8, 10 }, 1));
		assertEquals(6, binarySearch(new int[] { 1, 2, 2, 2, 3, 8, 10 }, 10));
		assertEquals(-1, binarySearch(new int[] { 1, 2, 2, 2, 3, 8 }, 10));
	}

	private int binarySearch(int[] a,int v){
		
		int l=0;
		int h=a.length-1;
		
		while(l<=h){
			int mid=l+(h-l)/2;
			if(a[mid]<v){
				l=mid+1;
			}else if(a[mid]>v){
				h=mid-1;
			}else{
				return mid;
			}
		}
		return -1;
	}

}
