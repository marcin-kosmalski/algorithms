package coursera.alg1.week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

	private final Point[] points;

	private final LineSegment[] lineSegments;
	
	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
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

		this.points = Arrays.copyOf(points,points.length);
		this.lineSegments =findLineSegments();
	}

	// the number of line segments
	public int numberOfSegments() {
		return lineSegments.length;
	}

	// the line segments
	public LineSegment[] segments() {
		return this.lineSegments.clone();
	}

	private LineSegment[] findLineSegments() {
		List<LineSegment> lsList = new ArrayList<>();
		List<TreeSet<Point>> alreadyExistingSegments = new ArrayList<>();
		for (int a = 0; a < points.length; a++) {
			for (int b = a + 1; b < points.length; b++) {
				for (int c = b + 1; c < points.length; c++) {
					for (int d = c + 1; d < points.length; d++) {
						double slopeAB = points[a].slopeTo(points[b]);
						double slopeBC = points[b].slopeTo(points[c]);
						double slopeCD = points[c].slopeTo(points[d]);
	
						if (doublesEqual(slopeAB, slopeBC)
								&& doublesEqual(slopeBC, slopeCD)) {
	
							TreeSet<Point> collinearPointSet = new TreeSet<>(
									Arrays.asList(points[a], points[b],
											points[c], points[d]));
							collinearPointSet =  new TreeSet<>(Arrays.asList(collinearPointSet.first(),collinearPointSet.last()));
							if (!alreadyExistingSegments
									.contains(collinearPointSet)) {
								alreadyExistingSegments.add(collinearPointSet);
								lsList.add(new LineSegment(collinearPointSet
										.first(), collinearPointSet.last()));
							}
	
						}
					}
				}
			}
		}
	
		return lsList.toArray(new LineSegment[0]);
	}

	private boolean doublesEqual(double d1, double d2) {
		return (d1 == Double.POSITIVE_INFINITY && d2 == Double.POSITIVE_INFINITY)
				|| d1 == d2 || Math.abs(d1 - d2) < 0.0000000001;
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

		BruteCollinearPoints collinear = new BruteCollinearPoints(points);

		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();

	}
}
