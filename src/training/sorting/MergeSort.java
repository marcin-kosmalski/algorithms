package training.sorting;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class MergeSort {

	@Test
	public void test() {
		assertArrayEquals(new int[] { 1, 2, 3, 6 }, mergeSort(new int[] { 3, 6,
				1, 2 }));
		assertArrayEquals(new int[] { 1, 2, 4, 5, 6 }, mergeSort(new int[] { 6,
				5, 4, 2, 1 }));

		int[] a = new int[] { 3, 6, 1, 2 };
		sort(a, new int[] { 0, 0, 0, 0 }, 0, 3);
		assertArrayEquals(new int[] { 1, 2, 3, 6 }, a);
	}

	public void sort(int[] a, int[] aux, int lo, int hi) {

		if (hi <= lo) {
			return;
		}
		int mid = lo + (hi - lo) / 2;

		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		merge(a, aux, lo, mid, hi);
	}

	public void merge(int[] a, int[] aux, int lo, int mid, int hi) {

		for (int i = lo; i <= hi; i++) {
			aux[i] = a[i];
		}

		int leftCnt = lo;
		int rightCnt = mid + 1;
		for (int c = lo; c <= hi; c++) {
			if (leftCnt > mid) {
				a[c] = aux[rightCnt++];
			} else if (rightCnt > hi) {
				a[c] = aux[leftCnt++];
			} else if (aux[leftCnt] < aux[rightCnt]) {
				a[c] = aux[leftCnt++];
			} else {
				a[c] = aux[rightCnt++];
			}
		}
	}

	public int[] mergeSort(int[] values) {
		if (values.length == 1) {
			return values;
		}

		int middleIndex = values.length / 2;
		int[] left = mergeSort(Arrays.copyOfRange(values, 0, middleIndex));
		int[] right = mergeSort(Arrays.copyOfRange(values, middleIndex,
				values.length));

		int[] result = new int[values.length];
		int valCnt = 0;
		int rCnt = 0;
		int lCnt = 0;

		while (valCnt < values.length) {
			if (lCnt == left.length) {
				result[valCnt] = right[rCnt];
				rCnt++;
			} else if (rCnt == right.length) {
				result[valCnt] = left[lCnt];
				lCnt++;
			} else if (left[lCnt] < right[rCnt]) {
				result[valCnt] = left[lCnt];
				lCnt++;
			} else {
				result[valCnt] = right[rCnt];
				rCnt++;
			}
			valCnt++;
		}
		return result;
	}
}
