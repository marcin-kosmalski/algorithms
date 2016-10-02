package dynamic.programming;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class MaxIncSubSeq {

	@Test
	public void test() {

		 int[] input=gen(200000,10000);
		 System.out.println("INPUT GEN");
		 long time=System.currentTimeMillis();
		 maxIncSubSeq2(input);
		
		System.out.println(System.currentTimeMillis()-time);
		// assertArrayEquals(new int[] { 3, 5, 10, 14, 15 },
		// maxIncSubSeq(new int[] { 16, 3, 5, 19, 10, 14, 12, 0, 15 }));

		assertEquals(5, maxIncSubSeq2(new int[] {  16, 3, 5, 19, 10, 14, 12, 0, 15 }));

	}

	private int[] gen(int n, int maxVal) {
		Random rand = new Random();

		int[] val = new int[n];
		for (int i = 0; i < n; i++) {
			val[i] = rand.nextInt(maxVal);
		}
		return val;
	}

	private int maxIncSubSeq2(int[] seq) {

		int[] sums = new int[seq.length];

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < seq.length; i++) {
			sums[i] = 1;
			for (int j = 0; j < i; j++) {
				if (seq[i] > seq[j] && (sums[j] + 1 > sums[i])) {
					sums[i] = sums[j] + 1;
				}
			}
			if (max < sums[i]) {
				max = sums[i];
			}
		}
		return max;
	}

	private int[] maxIncSubSeq(int[] seq) {

		List<Integer>[] incSeq = new List[seq.length];

		for (int i = 0; i < seq.length; i++) {
			incSeq[i] = new ArrayList<>();
			incSeq[i].add(seq[i]);
		}

		List<Integer> longestIncSeq = new ArrayList<>();
		for (int i = 1; i < seq.length; i++) {
			for (int j = 0; j < i; j++) {
				if (seq[i] > seq[j]
						&& (incSeq[j].size() + 1 > incSeq[i].size())) {
					incSeq[i] = new ArrayList<>(incSeq[j]);
					incSeq[i].add(seq[i]);
				}
			}
			if (longestIncSeq.size() < incSeq[i].size()) {
				longestIncSeq = incSeq[i];
			}
		}
		return longestIncSeq.stream().mapToInt(v -> v).toArray();
	}

}
