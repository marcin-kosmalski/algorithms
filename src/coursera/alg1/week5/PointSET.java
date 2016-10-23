package coursera.alg1.week5;

import java.util.TreeSet;
import java.util.stream.Collectors;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSET {
	
	private TreeSet<Point2D> set;

	public PointSET() {
		this.set=new TreeSet<>();
	}

	public boolean isEmpty() { // is the set empty?
		return this.set.size()==0;
	}

	public int size() { // number of points in the set
		return this.set.size();
	}

	public void insert(Point2D p) { // add the point to the set (if it is not
									// already in the set)
		if(p==null){
			throw new NullPointerException();
		}
		set.add(p);
	}

	public boolean contains(Point2D p) {// does the set contain point p?
		return set.contains(p);
	}

	public void draw() { // draw all points to standard draw
		
	}

	public Iterable<Point2D> range(RectHV rect) {// all points that are inside
													// the rectangle
		if(rect==null){
			throw new NullPointerException();
		}
		return set.stream().filter(p->rect.contains(p)).collect(Collectors.toList());
	}

	public Point2D nearest(Point2D p) {// a nearest neighbor in the set to point
										// p; null if the set is empty
		if(p==null){
			throw new NullPointerException();
		}
		double min=Double.MAX_VALUE;
		Point2D nearestPoint=null;
		for(Point2D point:set){
			double dist=point.distanceSquaredTo(p);
			if(min>dist){
				min=dist;
				nearestPoint=point;
			}
		}
		return nearestPoint;
	}
}
