package coursera.alg1.week2;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class RandomizedQueueTest {

	private RandomizedQueue<String> queue;

	@Before
	public void init() {
		queue = new RandomizedQueue<String>();
	}

	@Test
	public void enqueue() {
		queue.enqueue("test1");
		queue.enqueue("test2");
		assertEquals(2, queue.size());
	}

	@Test
	public void enqueueDequeue() {
		queue.enqueue("test1");
		queue.enqueue("test2");
		queue.enqueue("test3");
		queue.enqueue("test4");
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		assertEquals(0, queue.size());
	}

	@Test
	public void enqueueInTheMiddleDequeue() {
		queue.enqueue("test1");
		queue.enqueue("test2");
		queue.dequeue();
		queue.dequeue();
		queue.enqueue("test3");
		queue.enqueue("test4");
		queue.dequeue();
		queue.dequeue();
		assertEquals(0, queue.size());
	}
	
	@Test
	public void enqueueInTheMiddleDequeueEmpty() {
		assertTrue(queue.isEmpty());
		queue.enqueue("test1");
		assertFalse(queue.isEmpty());
		queue.enqueue("test2");
		queue.dequeue();
		queue.dequeue();
		assertTrue(queue.isEmpty());
		queue.enqueue("test3");
		queue.enqueue("test4");
		queue.dequeue();
		queue.dequeue();
		assertTrue(queue.isEmpty());
		assertEquals(0, queue.size());
	}
	

	@Test
	public void sample() {
		queue.enqueue("test1");
		assertEquals("test1",queue.sample());
		assertEquals(1, queue.size());
	}
	
	@Test
	public void iter() {
		queue.enqueue("test1");
		queue.enqueue("test2");
		queue.enqueue("test3");
		queue.enqueue("test4");
		queue.enqueue("test5");
		Iterator<String> iter=queue.iterator();
		iter.next();
		iter.next();
		iter.next();
		iter.next();
		assertTrue(iter.hasNext());
		iter.next();
		assertFalse(iter.hasNext());
	}

}
