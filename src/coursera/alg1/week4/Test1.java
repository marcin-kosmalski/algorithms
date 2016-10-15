package coursera.alg1.week4;

import java.util.Arrays;

public class Test1 {

	private int[] array;

	private int n = 0;

	private Test1(int capacity) {
		this.array = new int[capacity];
	}

	public static void main(String[] args) {
		Test1 test = new Test1(10);

		test.insert(3);
		test.insert(8);
		test.insert(5);
		test.insert(1);
		test.insert(9);
		test.insert(7);
		test.print();
		System.out.println(test.delMax());
		test.print();
		System.out.println(test.delMax());
		test.print();
		System.out.println(test.delMax());
		test.print();

	}

	private void print() {
		System.out.println(Arrays.toString(array));
	}

	private void insert(int val) {
		array[++n] = val;
		swim(n);
	}

	private int delMax() {

		int max = array[1];
		array[1] = array[n];
		array[n] = max;

		sink(1);

		array[n--] = 0;

		return max;

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
