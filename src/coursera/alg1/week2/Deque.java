package coursera.alg1.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Deque<Item> implements Iterable<Item> {

	private class Node {
		private Item item;
		private Node next;
		private Node prev;
	}

	private int size = 0;

	private Node head, tail;

	public Deque() {
		head = null;
		tail = null;
	}

	// is the deque empty?
	public boolean isEmpty() {
		return size == 0;

	}

	// return the number of items on the deque
	public int size() {
		return size;
	}

	// add the item to the front
	public void addFirst(Item item) {
		Objects.requireNonNull(item);
		Node newHead = new Node();
		newHead.item = item;
		newHead.next = head;
		if (head != null) {
			head.prev = newHead;
		}
		head = newHead;
		if (size == 0) {
			tail = head;
		}
		size++;
	}

	public void addLast(Item item) {
		Objects.requireNonNull(item);
		Node newTail = new Node();
		newTail.item = item;
		if (tail != null) {
			tail.next = newTail;
		}
		newTail.prev = tail;
		tail = newTail;
		if (size == 0) {
			head = tail;
		}
		size++;
	}

	public Item removeFirst() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		Node currHead = head;
		head = head.next;

		if (head == null) {
			tail = head;
		} else {
			head.prev = null;
		}
		size--;
		return currHead.item;
		// remove and return the item from the front
	}

	public Item removeLast() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		Node currTail = tail;
		tail = currTail.prev;

		currTail.prev = null;
		if (tail == null) {
			head = tail;
		} else {
			tail.next = null;
		}
		size--;
		return currTail.item;
		// remove and return the item from the end
	}

	public Iterator<Item> iterator() {
		Iterator<Item> iter = new Iterator<Item>() {

			private Node tempNode = head;

			@Override
			public boolean hasNext() {
				return tempNode != null;
			}

			@Override
			public Item next() {
				if (!hasNext()) {
					throw new java.util.NoSuchElementException();
				}
				Item currItem = tempNode.item;
				tempNode = tempNode.next;
				return currItem;
			}

		};
		return iter;
		// return an iterator over items in order from front to end
	}
}