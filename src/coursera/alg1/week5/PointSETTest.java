package coursera.alg1.week5;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.TreeSet;

import org.junit.Test;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSETTest {

	@Test
	public void nearestPoint() {

		PointSET pointSet = new PointSET();

		Point2D a = new Point2D(2d, 2d);
		pointSet.insert(a);
		pointSet.insert(new Point2D(1d, 1d));
		pointSet.insert(a);

		assertEquals(a, pointSet.nearest(new Point2D(3d, 3d)));
	}

	@Test
	public void containsPoint() {

		PointSET pointSet = new PointSET();

		Point2D a = new Point2D(2d, 2d);
		pointSet.insert(a);
		pointSet.insert(new Point2D(1d, 1d));
		pointSet.insert(a);

		assertTrue(pointSet.contains(a));
	}

	@Test
	public void range() {

		PointSET pointSet = new PointSET();

		Point2D a = new Point2D(2d, 2d);
		pointSet.insert(a);
		pointSet.insert(new Point2D(1d, 1d));
		pointSet.insert(a);
		Iterable<Point2D> iter = pointSet.range(new RectHV(1.5d, 1.5d, 2.5d,
				2.5d));

		assertEquals(a, iter.iterator().next());
	}
}
