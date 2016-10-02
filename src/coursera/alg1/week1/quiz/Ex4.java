package coursera.alg1.week1.quiz;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

//Hint: given an integer x and a sorted array a[] of n distinct integers,
//design a linear-time algorithm to determine if there exists two distinct indices i and j such that a[i]+a[j]==x.

public class Ex4 {

	@Test
	public void test() {
		// long t = System.currentTimeMillis();
		// k(gen(100000000), new Random().nextInt(1000));
		// k(new int[] { 1, 2, 3, 5, 6, 7 }, 8);
		m(new int[] { -40, -20, -10, 30, 40 });
		// System.out.println(System.currentTimeMillis() - t);
	}

	private int[] gen(int n) {
		Random rand = new Random();

		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = rand.nextInt(10000);
		}
		return array;
	}

	private int[] k(int[] a, int x) {
		Map<Integer,Integer> map=new HashMap<>();

		for (int i = 0; i < a.length; i++) {
			if(a[i]>0){
				continue;
			}
			// System.out.println(x+":"+a[i]);
			int r = a[i]+x;
			if (map.containsKey(a[i])) {
				return new int[] { i, 1 };
			} else {
				map.put(r, i);
			}
		}
		return null;
	}

	private void m(int[] a) {
		for (int i = 0; i < a.length; i++) {

			if (a[i] >= 0) {
				int[] p = k(a, a[i]);
				if (p != null) {
					System.out.println(i + ":" + p[0] + ":" + p[1]);
				}
			}
		}
	}
}
