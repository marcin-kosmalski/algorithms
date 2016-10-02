package dynamic.programming;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class ZigZag2 {

	@Test
	public void test() {
		assertEquals(6, longestZigZag(new int[] { 1, 7, 4, 9, 2, 5 }));
		assertEquals(7, longestZigZag(new int[] { 1, 17, 5, 10, 13, 15, 10, 5,
				16, 8 }));
		assertEquals(1, longestZigZag(new int[] { 44 }));
		assertEquals(8, longestZigZag(new int[] { 70, 55, 13, 2, 99, 2, 80, 80,
				80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32 }));
		assertEquals(36, longestZigZag(new int[] { 374, 40, 854, 203, 203, 156,
				362, 279, 812, 955, 600, 947, 978, 46, 100, 953, 670, 862, 568,
				188, 67, 669, 810, 704, 52, 861, 49, 640, 370, 908, 477, 245,
				413, 109, 659, 401, 483, 308, 609, 120, 249, 22, 176, 279, 23,
				22, 617, 462, 459, 244 }));
	}

	private int longestZigZag(int[] sequence) {

		int[] up = new int[sequence.length];
		int[] down = new int[sequence.length];

		up[0] = 1;
		down[0] = 1;
		int maxLength = 1;
		for (int i = 1; i < sequence.length; i++) {
			up[i] = 1;
			down[i] = 1;
			for (int j = 0; j < i; j++) {
				if (sequence[i] > sequence[j]) {
					up[i] = Math.max(down[j] + 1, up[i]);
				} else if (sequence[i] < sequence[j]) {
					down[i] = Math.max(up[j] + 1, down[i]);
				}
			}
			maxLength = Math.max(maxLength, Math.max(up[i], down[i]));
		}

		return maxLength;
	}

}
