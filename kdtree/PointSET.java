/**
 * Auto Generated Java Class.
 */
import java.util.ArrayList;

public class PointSET {
    private SET<Point2D> set;
    public PointSET() { 
        /* YOUR CONSTRUCTOR CODE HERE*/
        set = new SET<Point2D>();
    }
    
    public boolean isEmpty() {
        return set.isEmpty();
    }
    
    public int size() {
        return set.size();
    }
    
    public void insert(Point2D p) {
        set.add(p);
    }
    
    public boolean contains(Point2D p) {
        return set.contains(p);
    }
    
    public void draw() {
        for (Point2D p : set) {
            p.draw();
        }
    }
    
    public Iterable<Point2D> range(RectHV rect) {
        ArrayList<Point2D> list = new ArrayList<Point2D>();
        for (Point2D p: set) {
            if (rect.contains(p)) list.add(p);
        }
        return list;
    }
    
    public Point2D nearest(Point2D p) {
        double minDistance = 2;
        Point2D nearest = null;
        for (Point2D q: set) {
            if (p.distanceTo(q) < minDistance) {
                nearest = q;
                minDistance = p.distanceTo(q);
            }
        }
        return nearest;
    }
            
    public static void main(String[] args) { 
        
    }
    
    /* ADD YOUR CODE HERE */
    
}
