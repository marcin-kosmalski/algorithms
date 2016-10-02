package training.sorting;

import static org.junit.Assert.*;

import java.util.PriorityQueue;

import org.junit.Test;

public class HeapSort {

	@Test
	public void test() {

		assertArrayEquals(new int[] { 1, 2, 3, 6 }, heapSort(new int[] { 3, 6,
				1, 2 }));
		assertArrayEquals(new int[] { 1, 2, 3, 4, 5, 6 }, heapSort(new int[] {
				6, 5, 4, 3, 2, 1 }));
	}

	public int[] heapSort(int[] values) {

		PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
		for (int value : values) {
			priorityQueue.add(value);
		}
		
		int i = 0;
		int[] result = new int[values.length];
		while (i < values.length) {
			result[i++] = priorityQueue.poll();
		}
		return result;
	}

}
