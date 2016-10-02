package coursera.alg1.week2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Object[] a;

	private int currIndex = 0;

	public RandomizedQueue() { // construct an empty randomized queue
		a = new Object[1];
	}

	private Item getItem(int i) {
		return (Item) a[i];
	}

	public boolean isEmpty() { // is the queue empty?
		return size() == 0;
	}

	public int size() { // return the number of items on the queue
		return currIndex;
	}

	public void enqueue(Item item) { // add the item
		Objects.requireNonNull(item);
		extendArrayIfRequired();
		a[currIndex] = item;
		currIndex++;
	}

	public Item dequeue() { // remove and return a random item
		if (currIndex == 0) {
			throw new NoSuchElementException();
		}
		int idx = StdRandom.uniform(currIndex);
		Item item = getItem(idx);
		a[idx] = a[currIndex - 1];
		a[currIndex - 1]=null;
		currIndex--;
		reduceArray();
		return item;
	}

	public Item sample() { // return (but do not remove) a random item
		if (currIndex == 0) {
			throw new NoSuchElementException();
		}
		return getItem(StdRandom.uniform(currIndex));
	}

	public Iterator<Item> iterator() { // return an independent iterator over
		return new MyIterator(this.a, currIndex);// items in random order
	}

	private class MyIterator implements Iterator<Item> {

		private Object[] array;

		private int currentIter = 0;

		public MyIterator(Object[] array, int size) {
			this.currentIter = size;
			this.array = Arrays.copyOfRange(array, 0, size);
		}

		@Override
		public boolean hasNext() {
			return currentIter > 0;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			int idx = 0;
			if (currentIter > 1) {
				idx = StdRandom.uniform(currentIter);
			}

			Item item = (Item) array[idx];
			array[idx] = array[currentIter - 1];
			array[currIndex - 1]=null;
			currentIter--;
			return item;
		}
	}

	private void extendArrayIfRequired() {
		if (currIndex == a.length) {
			resize(2 * a.length);
		}
	}

	private void resize(int capacity) {
		Object[] copy = new Object[capacity];
		for (int i = 0; i < currIndex; i++) {
			copy[i] = a[i];
		}
		a = copy;
	}

	private void reduceArray() {
		if (currIndex > 0 && currIndex == a.length / 4) {
			resize(a.length / 2);
		}
	}

}
