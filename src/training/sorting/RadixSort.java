package training.sorting;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class RadixSort {

	@Test
	public void test() {
		assertArrayEquals(new int[] { 1, 2, 3, 6 }, radixSort(new int[] { 3, 6,
				1, 2 }));
		assertArrayEquals(new int[] { 1, 2, 3, 4, 5, 6 }, radixSort(new int[] {
				6, 5, 4, 3, 2, 1 }));
		assertArrayEquals(new int[] { 2, 24, 45, 66, 75, 90, 170, 802 },
				radixSort(new int[] { 170, 45, 75, 90, 802, 2, 24, 66 }));
	}

	public int[] radixSort(int[] values) {

		List<Integer>[] buckets = new List[10];
		for (int i = 0; i <= 9; i++) {
			buckets[i] = new ArrayList<Integer>();
		}

		int pos = 1;
		int[] tempValues = Arrays.copyOf(values, values.length);
		while (true) {
			for (int i = 0; i < tempValues.length; i++) {
				buckets[(tempValues[i] / pos) % 10].add(tempValues[i]);
			}

			if (buckets[0].size() == values.length) {
				break;
			}
			tempValues = Arrays.stream(buckets)
					.flatMap(bucket -> bucket.stream()).mapToInt(v -> v)
					.toArray();
			for (int i = 0; i <= 9; i++) {
				buckets[i] = new ArrayList<Integer>();
			}
			pos *= 10;
		}
		return tempValues;
	}

}
