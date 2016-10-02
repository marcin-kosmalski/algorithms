package training.sorting;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import org.junit.Test;

public class QuickSort {

	@Test
	public void test() {
		assertArrayEquals(new int[] { 1, 2, 3, 6 }, quickSort(new int[] { 3, 6,
				1, 2 }));
		assertArrayEquals(new int[] { 1, 2, 4, 5, 6 }, quickSort(new int[] { 6,
				5, 4, 2, 1 }));
	}

	public int[] quickSort(int[] values) {
		if (values.length <= 1) {
			return values;
		}

		int middleIndex = values.length / 2;
		int middleValue=values[middleIndex];
		
		List<Integer> right=new ArrayList<>();
		List<Integer> left=new ArrayList<>();
		
		for(int i=0;i<values.length;i++){
			if(i==middleIndex){
				continue;
			}
			if(values[i]>middleValue){
				right.add(values[i]);
			}else{
				left.add(values[i]);
			}
		}
		
		int[] result=new int[values.length];
		int[] leftResult=quickSort(left.stream().mapToInt(v->v).toArray());
		int[] rightResult=quickSort(right.stream().mapToInt(v->v).toArray());
		int resultIndex=0;
		for(int i=0;i<leftResult.length;i++){
			result[resultIndex++]=leftResult[i];
		}
		result[resultIndex++]=middleValue;
		for(int i=0;i<rightResult.length;i++){
			result[resultIndex++]=rightResult[i];
		}
		return result;
	}
	
}
