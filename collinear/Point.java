/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new BySlope();

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
      if (x != that.x) {
        if (y == that.y) 
          return 0; //horizontal segment
        else
          return ((double) (that.y - y))/(that.x - x); //y is lesser
      } 
      else if (that.y != y)
        return Double.POSITIVE_INFINITY;
      else 
        return Double.NEGATIVE_INFINITY;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
      if (y < that.y) 
        return -1;
      else if (y > that.y) 
        return 1;
      else if (x < that.x)
        return -1;
      else if (x > that.x)
        return 1;
      else 
        return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    private class BySlope implements Comparator<Point> 
    { 
      public int compare(Point v, Point w) {
        return Double.compare(slopeTo(v), slopeTo(w));
      }
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
