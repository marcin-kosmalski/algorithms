package coursera.alg1.week3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class BruteCollinearPointsTests {

	
	@Test
	public void immutability(){
		Point point1 = new Point(1, 1);
		Point point2 = new Point(2, 2);
		Point point3 = new Point(3, 3);
		Point point4 = new Point(4, 4);
		
		Point[] a = new Point[] { point2, point1, point4, point3 };

		BruteCollinearPoints bcp = new BruteCollinearPoints(a);

		assertEquals(1, bcp.numberOfSegments());
		assertEquals(1, bcp.segments().length);
		assertEquals("(1, 1) -> (4, 4)", bcp.segments()[0].toString());
		a[0]= new Point(1000, 1);
		bcp.segments();
		bcp.segments();
		bcp.numberOfSegments();
		bcp.numberOfSegments();
		bcp.segments();
		assertEquals("(1, 1) -> (4, 4)", bcp.segments()[0].toString());
	}
	
	@Test
	public void colinear() {
		Point point1 = new Point(1, 1);
		Point point2 = new Point(2, 2);
		Point point3 = new Point(3, 3);
		Point point4 = new Point(4, 4);

		Point point5 = new Point(10, 12);
		Point point6 = new Point(11, 13);
		Point point7 = new Point(12, 14);
		Point point8 = new Point(13, 15);

		Point[] a = new Point[] { point2, point1, point4, point3, point7,
				point8, point5, point6 };

		BruteCollinearPoints bcp = new BruteCollinearPoints(a);

		assertEquals(2, bcp.numberOfSegments());
		assertEquals(2, bcp.segments().length);
		assertEquals("(1, 1) -> (4, 4)", bcp.segments()[0].toString());
		assertEquals("(10, 12) -> (13, 15)", bcp.segments()[1].toString());		
	}

	@Test
	public void twoPointAreTheSame() {

		Point point1 = new Point(1, 1);
		Point point2 = new Point(2, 2);
		Point point3 = new Point(4, 4);
		Point point4 = new Point(4, 4);
		Point[] a = new Point[] { point1, point2, point3, point4 };

		try {
			BruteCollinearPoints bcp = new BruteCollinearPoints(a);
			fail();
		} catch (IllegalArgumentException e) {

		}

	}

	@Test
	public void onePointIsNull() {
		Point point1 = new Point(1, 1);
		Point point2 = new Point(2, 2);
		Point point3 = new Point(3, 3);
		Point point4 = null;
		Point[] a = new Point[] { point1, point2, point3, point4 };
		try {
			BruteCollinearPoints bcp = new BruteCollinearPoints(a);
			fail();
		} catch (NullPointerException e) {

		}
	}

	@Test
	public void notColinear() {
		Point point1 = new Point(1, 1);
		Point point2 = new Point(2, 2);
		Point point3 = new Point(3, 3);
		Point point4 = new Point(5, 4);
		Point[] a = new Point[] { point1, point2, point3, point4 };

		BruteCollinearPoints bcp = new BruteCollinearPoints(a);

		assertEquals(0, bcp.numberOfSegments());
	}
}
