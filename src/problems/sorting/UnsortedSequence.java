package problems.sorting;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

public class UnsortedSequence {

	@Test
	public void test() {
		assertArrayEquals(new int[] { 2, 1 }, getUnsorted(new int[] { 1, 2 }));
		assertArrayEquals(new int[] { 1, 3, 2 }, getUnsorted(new int[] { 1, 2,
				3 }));
		assertArrayEquals(new int[] { 2, 7, 2 }, getUnsorted(new int[] { 7, 2,
				2 }));
		assertArrayEquals(new int[] {}, getUnsorted(new int[] { 1000 }));
		assertArrayEquals(new int[] {}, getUnsorted(new int[] { 1, 1 }));
		assertArrayEquals(new int[] { 1, 2, 4, 3 }, getUnsorted(new int[] { 1,
				2, 4, 3 }));
		assertArrayEquals(new int[] { 1, 2, 2, 2, 3, 5, 5, 5, 6, 6, 8, 8, 9,
				10, 9, 10, 10 }, getUnsorted(new int[] { 2, 8, 5, 1, 10, 5, 9,
				9, 3, 10, 5, 6, 6, 2, 8, 2, 10 }));
	}

	public int[] getUnsorted(int[] s) {
		if (s.length == 1) {
			return new int[0];
		}
		int[] sortS = Arrays.copyOf(s, s.length);
		Arrays.sort(sortS);
		int lastNum = sortS[sortS.length - 1];
		for (int i = sortS.length - 1; i >= 0; i--) {
			if (sortS[i] != lastNum) {
				sortS[i+1] = sortS[i];
				sortS[i] = lastNum;
				return sortS;
			}
		}
		return new int[0];
	}

}
