package coursera.alg1.week1.quiz;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Ex2 {

	private int[] set;

	private int size;

	private Map<Integer, Integer> map = new HashMap<>();

	public Ex2(int[] set) {
		this.set = new int[set.length];
		this.size = set.length;
		this.set = Arrays.copyOf(set, set.length);
		Arrays.sort(this.set);
		for (int i = 0; i < this.size - 1; i++) {
			map.put(this.set[i], this.set[i + 1]);
		}
	}

	public void remove(int value) {

		int idx = Arrays.binarySearch(this.set, value);
		if (idx > 0 && idx < size - 1) {
			map.put(this.set[idx - 1], this.set[idx + 1]);
		}

		map.remove(value);
	}

	public int successor(int value) {
		return map.get(value);
	}
}
