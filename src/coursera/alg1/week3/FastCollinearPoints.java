package coursera.alg1.week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import coursera.alg1.week3.LineSegment;
import coursera.alg1.week3.Point;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

	private final Point[] points;

	private final LineSegment[] lineSegments;

	public FastCollinearPoints(Point[] points) {// finds all line segments
												// containing 4 or more points

		if (points == null) {
			throw new NullPointerException();
		}
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null) {
				throw new NullPointerException("Point in an array is null!");
			}
			for (int j = 0; j < points.length; j++) {
				if (i != j && points[i].compareTo(points[j]) == 0) {
					throw new IllegalArgumentException(String.format(
							"Point %s and point %s are equal!", points[i],
							points[j]));
				}
			}
		}

		this.points = Arrays.copyOf(points, points.length);
		this.lineSegments = findLineSegments();
	}

	public int numberOfSegments() { // the number of line segments
		return lineSegments.length;
	}

	public LineSegment[] segments() { // the line segments
		return lineSegments.clone();
	}

	private LineSegment[] findLineSegments() {
		Arrays.sort(points);
		// System.out.println(Arrays.toString(points));
		// Arrays.sort(points,(Point p1, Point p2) ->
		// Double.compare(p1.slopeTo(p2)));
		// System.out.println(Arrays.toString(points));

		// Point[] pointsCopy = Arrays.copyOf(points, points.length);
		List<Point> pointList = new ArrayList<>(Arrays.asList(points));
		// Map<Point, List<Point>> map = new HashMap<>();
		// List<TreeSet<Point>> alreadyExistingSegments = new ArrayList<>();
		List<LineSegment> lineSegmentList = new ArrayList<>();
		// List<Point> toRemoveList = new ArrayList<>();
		Map<Double, List<Point>> map = new HashMap<>();
		for (Point point : points) {
			Collections.sort(pointList, point.slopeOrder());

			List<Point> colinearPointList = new ArrayList<>();
			double lastSlope = Double.MAX_VALUE;
			Point lastPoint = null;
			// System.out.println(point);
			for (int i = 1; i < pointList.size(); i++) {
				Point currentPoint = pointList.get(i);
				double currentSlope = currentPoint.slopeTo(point);
				// System.out.println(currentPoint + ":" + currentSlope);

				if (doublesEqual(currentSlope, lastSlope)) {
					if (colinearPointList.size() == 0) {
						colinearPointList.add(lastPoint);
					}
					colinearPointList.add(currentPoint);
				} else {

					tryToExtendSegments(colinearPointList, point,
							lineSegmentList, map, lastSlope);

					colinearPointList.clear();
				}
				lastSlope = currentSlope;
				lastPoint = currentPoint;
			}
			// System.out.println("<");
			// System.out.println("=========");
			tryToExtendSegments(colinearPointList, point, lineSegmentList, map,
					lastSlope);

			pointList.remove(point);
		}

		if (lineSegmentList.size() > 0) {
			return lineSegmentList.toArray(new LineSegment[0]);
		}
		return new LineSegment[0];
	}

	private boolean doublesEqual(double d1, double d2) {
		return  Math.abs(d1 - d2) < 0.00000007
				|| (d1 == Double.POSITIVE_INFINITY && d2 == Double.POSITIVE_INFINITY);
	}


	private void tryToExtendSegments(List<Point> collinearPointList,
			Point point, List<LineSegment> lineSegmentList,
			Map<Double, List<Point>> map, double slope) {
		if (collinearPointList.size() >= 3) {
			collinearPointList.add(point);

			Point lastPoint = Collections.max(collinearPointList);

			List<Point> lastPointBySlope = map.get(slope);
			if (lastPointBySlope != null) {
				if (lastPointBySlope.stream().anyMatch(
						p -> p.compareTo(lastPoint) == 0)) {
					return;
				}
			}

			
			map.putIfAbsent(slope, new ArrayList<>());
			List<Point> list2 = map.get(slope);
			list2.add(lastPoint);
			map.put(slope, list2);

			lineSegmentList.add(new LineSegment(Collections
					.min(collinearPointList), lastPoint));
			return;
		}
		return;
	}

	public static void main(String[] args) {

		In in = new In(args[0]);
		int n = in.readInt();

		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);

		for (Point p : points) {

			p.draw();
		}

		StdDraw.show();

		FastCollinearPoints collinear = new FastCollinearPoints(points);

		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();

	}
}
