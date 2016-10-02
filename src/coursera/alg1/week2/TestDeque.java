package coursera.alg1.week2;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class TestDeque {

	private Deque<String> d;

	@Before
	public void init() {
		d = new Deque<String>();
	}

	@Test
	public void addFirst() {
		d.addFirst("test");
		assertEquals(1, d.size());
	}

	@Test
	public void addFirstThreeTimes() {
		d.addFirst("test1");
		d.addFirst("test2");
		d.addFirst("test3");
		assertEquals(3, d.size());
	}

	@Test
	public void addLastThreeTimes() {
		d.addLast("test1");
		d.addLast("test2");
		d.addLast("test3");
		assertEquals(3, d.size());
	}

	@Test
	public void addLastFirst() {
		d.addLast("test3");
		d.addFirst("test1");
		d.addFirst("test2");
		d.addLast("test3");
		assertEquals(4, d.size());
	}

	@Test
	public void addFirstAndThenRemove() {
		d.addFirst("test1");
		d.addFirst("test2");
		assertEquals(2, d.size());
		assertEquals("test2", d.removeFirst());
		assertEquals(1, d.size());
		assertEquals("test1", d.removeFirst());
		assertEquals(0, d.size());
	}

	@Test
	public void addLastThenRemove() {
		d.addLast("test1");
		d.addLast("test2");
		d.addLast("test3");
		assertEquals(3, d.size());
		assertEquals("test1", d.removeFirst());
		assertEquals(2, d.size());
		assertEquals("test3", d.removeLast());
		assertEquals(1, d.size());
	}

	@Test
	public void addFirstAndIterate() {
		d.addFirst("test1");
		d.addFirst("test2");
		d.addFirst("test3");
		d.addFirst("test4");
		Iterator<String> iter=d.iterator();
		assertEquals(true,iter.hasNext());
		assertEquals("test4",iter.next());
		assertEquals(true,iter.hasNext());
		assertEquals("test3",iter.next());
		assertEquals(true,iter.hasNext());
		assertEquals("test2",iter.next());
		assertEquals(true,iter.hasNext());
		assertEquals("test1",iter.next());
		assertEquals(false,iter.hasNext());
	}
	
	@Test
	public void addFirstRemoveAndIterate() {
		d.addFirst("test1");
		d.addFirst("test2");
		d.addFirst("test3");
		d.addFirst("test4");
		d.removeFirst();
		Iterator<String> iter=d.iterator();
		assertEquals(true,iter.hasNext());
		assertEquals("test3",iter.next());
		assertEquals(true,iter.hasNext());
		assertEquals("test2",iter.next());
		assertEquals(true,iter.hasNext());
		assertEquals("test1",iter.next());
		assertEquals(false,iter.hasNext());
	}
	
	@Test
	public void addFirstRemoveLastAndIterate() {
		d.addFirst("test1");
		d.addFirst("test2");
		d.addFirst("test3");
		d.removeFirst();
		d.removeLast();
		assertEquals(1, d.size());
		Iterator<String> iter=d.iterator();
		assertEquals(true,iter.hasNext());
		assertEquals("test2",iter.next());
		assertEquals(false,iter.hasNext());
	}
	
	@Test
	public void addFirstRemoveLastAndAddAndIterate() {
		d.addFirst("test1");
		d.addFirst("test2");
		d.addFirst("test3");
		d.removeFirst();
		d.removeLast();
		d.addFirst("testLast1");
		d.addLast("testLast3");
		assertEquals(3, d.size());
		Iterator<String> iter=d.iterator();
		assertEquals(true,iter.hasNext());
		assertEquals("testLast1",iter.next());
		assertEquals(true,iter.hasNext());
		assertEquals("test2",iter.next());
		assertEquals(true,iter.hasNext());
		assertEquals("testLast3",iter.next());
		assertEquals(false,iter.hasNext());
	}


}
