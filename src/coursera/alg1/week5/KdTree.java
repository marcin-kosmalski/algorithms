package coursera.alg1.week5;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

	private Node root;

	private int size = 0;

	public Point2D nearest(Point2D p) {// a nearest neighbor in the set to point
		// p; null if the set is empty
		if (p == null) {
			throw new NullPointerException();
		}

		minDist = Double.MAX_VALUE;
		findNearest(p, root);
		return closestPoint2d;
	}

	private double minDist = Double.MAX_VALUE;

	private Point2D closestPoint2d = null;

	private void findNearest(Point2D p, Node node) {

		// if the closest point discovered so far is closer than the distance
		// between the query point and the rectangle corresponding to a node,
		// there is no need to explore that node (or its subtrees).
		if (node == null || minDist < node.getRectHV().distanceSquaredTo(p)) {
			return;
		}

		double newMinDist = node.getKey().distanceSquaredTo(p);
		if (newMinDist < minDist) {
			closestPoint2d = node.getKey();
			minDist = newMinDist;
		}
		Node leftNode = node.getLeft();
		Node rightNode = node.getRight();

		if (node.isHorizontal()) {
			if (p.y() < node.getKey().y()) {
				findNearest(p, leftNode);
				findNearest(p, rightNode);
			} else {
				findNearest(p, rightNode);
				findNearest(p, leftNode);
			}
		} else {
			// x
			if (p.x() < node.getKey().x()) {
				findNearest(p, leftNode);
				findNearest(p, rightNode);
			} else {
				findNearest(p, rightNode);
				findNearest(p, leftNode);
			}
		}

	}

	public void draw() {
		StdDraw.setPenRadius(0.01d);
		StdDraw.setPenColor(Color.BLACK);
		draw(root);
	}

	private void draw(Node node) {
		StdDraw.setPenColor(Color.BLACK);
		node.getKey().draw();
		StdDraw.setPenColor(node.isHorizontal() ? Color.BLUE : Color.RED);
		if (node.isHorizontal()) {
			node.getKey().drawTo(
					new Point2D(node.getRectHV().xmax(), node.getKey().y()));
			node.getKey().drawTo(
					new Point2D(node.getRectHV().xmin(), node.getKey().y()));
		} else {
			node.getKey().drawTo(
					new Point2D(node.getKey().x(), node.getRectHV().ymin()));
			node.getKey().drawTo(
					new Point2D(node.getKey().x(), node.getRectHV().ymax()));
		}
		if (node.getLeft() != null) {
			draw(node.getLeft());
		}
		if (node.getRight() != null) {
			draw(node.getRight());
		}
	}

	public Iterable<Point2D> range(RectHV rect) {// all points that are inside

		// the rectangle
		if (rect == null) {
			throw new NullPointerException();
		}

		List<Point2D> list = new ArrayList<Point2D>();
		checkNodeAndAddPoints(rect, root, list);

		return list;
	}

	private void checkNodeAndAddPoints(RectHV rect, Node node,
			List<Point2D> list) {
		if (node != null) {
			if (node.getRectHV().intersects(rect)) {
				if (rect.contains(node.getKey())) {
					list.add(node.getKey());
				}
				checkNodeAndAddPoints(rect, node.getLeft(), list);
				checkNodeAndAddPoints(rect, node.getRight(), list);
			}
		}
	}

	public void insert(Point2D newPoint) {

		if (root == null) {
			root = new Node(newPoint, false, new RectHV(0, 0, 1, 1));
		} else {
			Node currNode = root;
			Node parentNode = null;

			boolean right = false;
			while (currNode != null) {
				if (currNode.getKey().equals(newPoint)) {
					return;
				}
				parentNode = currNode;
				if (currNode.isHorizontal()) {
					if (newPoint.y() < currNode.getKey().y()) {
						currNode = currNode.left;
						right = false;
					} else {
						currNode = currNode.right;
						right = true;
					}
				} else {
					if (newPoint.x() < currNode.getKey().x()) {
						currNode = currNode.left;
						right = false;
					} else {
						currNode = currNode.right;
						right = true;
					}

				}
			}

			Node newNode = new Node(newPoint, !parentNode.isHorizontal(),
					createRectHV(parentNode, newPoint, right));

			if (right) {
				parentNode.setRight(newNode);
			} else {
				parentNode.setLeft(newNode);
			}
		}
		size++;
	}

	private RectHV createRectHV(Node parentNode, Point2D newPoint, boolean right) {
		if (parentNode.isHorizontal()) {
			// must be vertical
			if (right) {
				return new RectHV(parentNode.getRectHV().xmin(), parentNode
						.getKey().y(), parentNode.getRectHV().xmax(),
						parentNode.getRectHV().ymax());
			} else {
				return new RectHV(parentNode.getRectHV().xmin(), parentNode
						.getRectHV().ymin(), parentNode.getRectHV().xmax(),
						parentNode.getKey().y());
			}
		} else {
			// horizontal
			if (right) {
				return new RectHV(parentNode.getKey().x(), parentNode
						.getRectHV().ymin(), parentNode.getRectHV().xmax(),
						parentNode.getRectHV().ymax());
			} else {
				return new RectHV(parentNode.getRectHV().xmin(), parentNode
						.getRectHV().ymin(), parentNode.getKey().x(),
						parentNode.getRectHV().ymax());
			}
		}
	}

	public boolean isEmpty() { // is the set empty?
		return size == 0;
	}

	public int size() { // number of points in the set
		return size;
	}

	public boolean contains(Point2D point) {// does the set contain point p?
		Node currNode = root;
		while (currNode != null && !currNode.getKey().equals(point)) {
			if (currNode.isHorizontal()) {
				if (point.y() < currNode.getKey().y()) {
					currNode = currNode.left;
				} else {
					currNode = currNode.right;
				}
			} else {
				if (point.x() < currNode.getKey().x()) {
					currNode = currNode.left;
				} else {
					currNode = currNode.right;
				}
			}
		}
		return currNode != null;
	}

	private class Node {

		private Point2D key;

		private RectHV rectHV;

		private boolean horizontal = false;

		private Node left, right;

		private Node(Point2D key, boolean horizontal, RectHV rectHV) {
			this.key = key;
			this.horizontal = horizontal;
			this.rectHV = rectHV;
		}

		public RectHV getRectHV() {
			return rectHV;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public Point2D getKey() {
			return key;
		}

		public boolean isHorizontal() {
			return horizontal;
		}

	}

}
