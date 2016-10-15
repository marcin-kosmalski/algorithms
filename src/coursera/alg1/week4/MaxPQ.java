package coursera.alg1.week4;

import java.util.Arrays;

public class MaxPQ {

	private int[] array;

	private int n = 0;

	public MaxPQ(int capacity) {
		this.array = new int[capacity+1];
	}

	public void insert(int val) {
		array[++n] = val;
		swim(n);
	}

	public int delMax() {
		int max = array[1];
		array[1] = array[n];
		array[n] = max;
		array[n--] = 0;
		sink(1);

		return max;
	}
	
	public boolean isEmpty(){
		return n==0;
	}

	private void sink(int k) {
		while (2 * k <= n) {
			int j = 2 * k;
			if (j < n && array[j] < array[j + 1]) {
				j++;
			}
			if (array[k] >= array[j]) {
				break;
			}
			int temp = array[k];
			array[k] = array[j];
			array[j] = temp;
			k = j;
		}
	}

	private void swim(int k) {
		while (k > 1 && array[k / 2] < array[k]) {
			int temp = array[k / 2];
			array[k / 2] = array[k];
			array[k] = temp;

			k = k / 2;
		}
	}

}
