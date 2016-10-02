package training.datastructs;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

public class OpenAddressingHashMap {

	private int size = 0;
	private int[] values;

	private final int INIT_VALUE = -1;

	private final int DEL_VALUE = -2;

	public OpenAddressingHashMap(int size) {
		this.size = size;
		this.values = new int[size];
		Arrays.fill(this.values, INIT_VALUE);
	}

	public boolean find(int value) {

		int initHash = value % size;
		if (values[initHash] == value) {
			return true;
		}

		int tempHash = initHash;
		do {
			tempHash = (tempHash + 1) % size;
			if (values[tempHash] == value) {
				return true;
			}
		} while (tempHash != initHash);

		return false;
	}

	public void remove(int value) {

		int initHash = value % size;
		if (values[initHash] == value) {
			values[initHash] = DEL_VALUE;
		}

		int tempHash = initHash;
		do {
			tempHash = (tempHash + 1) % size;
			if (values[tempHash] == value) {
				values[tempHash] = DEL_VALUE;
			}
		} while (tempHash != initHash);
	}

	public void print() {
		System.out.println(Arrays.toString(values));
	}

	public void insert(int value) {

		int orgHash = value % size;

		if (values[orgHash] == INIT_VALUE) {
			values[orgHash] = value;
		} else {
			int i = 1;
			int tempHash = orgHash;
			do {
				tempHash = (value % size + i) % size;
				if (values[tempHash] == INIT_VALUE) {
					values[tempHash] = value;
					break;
				}
				i++;
			} while (tempHash != orgHash);
		}

	}
}
