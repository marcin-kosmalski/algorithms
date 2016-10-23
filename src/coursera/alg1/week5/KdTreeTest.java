package coursera.alg1.week5;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTreeTest {

	@Test
	public void containsPoint() {
		KdTree kdTree = new KdTree();
		Point2D a = new Point2D(2d, 2d);
		kdTree.insert(a);
		kdTree.insert(new Point2D(1d, 1d));

		assertTrue(kdTree.contains(a));
	}
	
	@Test
	public void tryToAddDuplicates(){
		KdTree kdTree = new KdTree();
		Point2D a = new Point2D(2d, 2d);
		kdTree.insert(a);
		kdTree.insert(a);

		assertEquals(1, kdTree.size());
	}
	
	@Test
	public void range() {

		KdTree kdTree = new KdTree();

		Point2D a = new Point2D(0.2d, 0.2d);
		kdTree.insert(a);
		kdTree.insert(new Point2D(0.1d, 0.1d));
		Iterator<Point2D> iter = kdTree.range(new RectHV(0.15d, 0.15d, 0.25d,
				0.25d)).iterator();
		
		assertEquals(a, iter.next());
		assertFalse(iter.hasNext());
	}
	
	@Test
	public void range2() {

		KdTree kdTree = new KdTree();

		Point2D a = new Point2D(0d, 0d);
		kdTree.insert(a);
		
		Iterator<Point2D> iter = kdTree.range(new RectHV(0d, 0d, 0.25d,
				0.25d)).iterator();
		
		assertEquals(a, iter.next());
		assertFalse(iter.hasNext());
	}

	@Test
	public void containsPointComplex() {
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(0.7d, 0.2d));
		kdTree.insert(new Point2D(0.5d, 0.4d));
		Point2D a = new Point2D(0.2d, 0.3d);
		kdTree.insert(a);
		kdTree.insert(new Point2D(0.4d, 0.7d));
		kdTree.insert(new Point2D(0.9d, 0.6d));

		assertTrue(kdTree.contains(a));
	}
	
	@Test
	public void nearestPoint() {

		KdTree kdTree = new KdTree();

		Point2D a = new Point2D(0.2d, 0.2d);
		kdTree.insert(a);
		kdTree.insert(new Point2D(0.1d, 0.1d));

		assertEquals(a, kdTree.nearest(new Point2D(0.3d, 0.3d)));
	}

	@Test
	public void containsPointComplex2() {
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(0.7d, 0.2d));

		Point2D a = new Point2D(0.5d, 0.4d);
		kdTree.insert(a);
		kdTree.insert(new Point2D(0.2d, 0.3d));
		kdTree.insert(new Point2D(0.4d, 0.7d));
		kdTree.insert(new Point2D(0.9d, 0.6d));

		assertTrue(kdTree.contains(a));
	}
}
