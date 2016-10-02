package training.sorting;

import static org.junit.Assert.*;

import org.junit.Test;

public class InsertionSort {

	@Test
	public void test() {
		
		assertArrayEquals(new int[]{1,2,3,6}, insertionSort(new int[]{3,6,1,2}));
		assertArrayEquals(new int[]{1,2,3,4,5,6}, insertionSort(new int[]{6,5,4,3,2,1}));
	}
	
	public int[] insertionSort(int[] values){
		
		for(int i=1;i<values.length;i++){
			//locate shift
			int j=i;
			while(j>0&&values[i]<values[j-1]){
				j--;
			}
			//save value to be inserted
			int temp=values[i];
			//shift values to the right
			for(int k=i;k>j;k--){
				values[k]=values[k-1];
			}
			//insert value
			values[j]=temp;
		}
		
		
		
		return values;
	}

}
