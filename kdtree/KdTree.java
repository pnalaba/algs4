/**
 * Auto Generated Java Class.
 */
import java.util.ArrayList;
public class KdTree {
    
    private class Node {
        private Point2D p;
        private Node left;
        private Node right;
        private int level;
        private double minX, maxX, minY, maxY;
        Node(Point2D q, Node l, Node r, int lvl, 
              double nx, double ny, double xx, double xy) {
            p = q;
            left = l;
            right = r;
            level = lvl;
            minX = nx;
            maxX = xx;
            minY = ny;
            maxY = xy;
            //System.out.println("For Point "+p+"Creating rectangle with corners ("
              //                     +minX+","+minY+") and ("+maxX+","+maxY+")");
        }
    };
   
    private Node root;
    private int size;
    
    public KdTree() { 
        /* YOUR CONSTRUCTOR CODE HERE*/
        root = null;
        size = 0;     
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    
    public void insert(Point2D p) {
        
        if (root == null) {
            root = new Node(p, null, null, 0, 0, 0, 1, 1);
        } else {
            Node n = root; 
            while (true) { 
                if ((n.level % 2 == 0 && p.x() < n.p.x()) 
                        || (n.level % 2 != 0 && p.y() < n.p.y())) {
                    if (n.left == null) {
                        if (n.level % 2 == 0) { //split vertically
                            n.left = new Node(p, null, null, n.level+1, 
                                          n.minX, n.minY, n.p.x(), n.maxY);
                        } else { //split horizontally lower side
                            n.left = new Node(p, null, null, n.level+1,
                                              n.minX, n.minY, n.maxX, n.p.y());
                        }
                        break;
                    } else {
                        n = n.left;
                    }
                } else if ((n.level % 2 == 0 
                                && (p.x() > n.p.x() || p.y() != n.p.y()))
                           || (n.level % 2 != 0 
                                   && (p.y() > n.p.y() || p.x() != n.p.x()))) {
                    if (n.right == null) {
                        if (n.level % 2 == 0) { //split vertically right side
                            n.right = new Node(p, null, null, n.level+1,
                                               n.p.x(), n.minY, n.maxX, n.maxY);
                        } else { //split horizontally upper half
                            n.right = new Node(p, null, null, n.level+1,
                                               n.minX, n.p.y(), n.maxX, n.maxY);
                        }
                        break;
                    } else {
                        n = n.right;
                    }
                } else {
                    return;
                }            
                 
            } 
        }
        size++;
        
    }
    
    public boolean contains(Point2D p) {
        Node n = root;
        while (n != null) {
            if ((n.level % 2 == 0 && p.x() < n.p.x()) 
                    || (n.level % 2 != 0 && p.y() < n.p.y())) {
                if (n.left == null) {
                        return false;
                    } else {
                        n = n.left;
                    }
                } else if ((n.level % 2 == 0 
                                && (p.x() > n.p.x() || p.y() != n.p.y()))
                           || (n.level % 2 != 0 
                                   && (p.y() > n.p.y() || p.x() != n.p.x()))) {
                    if (n.right == null) {
                        return false;
                    } else {
                        n = n.right;
                    }
                } else if ((n.level % 2 == 0 && p.y() == n.p.y()) 
                              || (n.level % 2 != 0 && p.x() == n.p.x())) {
                    return true;
                } 
            }
        return false;
    }
    
    public void draw() {
        draw(root);
    }
    
    private void draw(Node n) {
        StdDraw.setPenRadius(0.01);
        n.p.draw();
        StdDraw.setPenRadius();
        if (n.level % 2 == 0) { //draw vertical line
            StdDraw.setPenColor(StdDraw.RED);
            (new Point2D(n.p.x(), n.minY)).drawTo(new Point2D(n.p.x(), n.maxY));
        } else { //draw horizontal line
            StdDraw.setPenColor(StdDraw.BLUE);
            (new Point2D(n.minX, n.p.y())).drawTo(new Point2D(n.maxX, n.p.y()));
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        
        if (n.left != null) draw(n.left);
        if (n.right != null) draw(n.right);
    }
                                  
            
            
    
    public Iterable<Point2D> range(RectHV rect) {
        ArrayList<Point2D> ar = new ArrayList<Point2D>();
        //System.out.println("range called with rectangle "+rect);
        if (root != null) addToRange(root, rect, ar);
        return ar;
    }
    
    private void addToRange(Node n, RectHV rect, ArrayList<Point2D> array) {
        RectHV leftRect;
        RectHV rightRect;
        
        //System.out.println("Examining node at point "+n.p);
        if (rect.contains(n.p)) { 
            array.add(n.p);
            //System.out.println("Adding point "+n.p);
        }
        
        if (n.level % 2 == 0) {
            leftRect = new RectHV(n.minX, n.minY, n.p.x(), n.maxY);
            rightRect = new RectHV(n.p.x(), n.minY, n.maxX, n.maxY);
        } else {
            leftRect = new RectHV(n.minX, n.minY, n.maxX, n.p.y());
            rightRect = new RectHV(n.minX, n.p.y(), n.maxX, n.maxY);
        }
        if (n.left != null && leftRect.intersects(rect)) {
            //System.out.println("Descending into left rectangle "+leftRect);
            addToRange(n.left, rect, array);
        } 
        if (n.right != null && rightRect.intersects(rect)) {
            //System.out.println("Descending into right rectangle "+rightRect);
            addToRange(n.right, rect, array);
        }
        
    }
    public Point2D nearest(Point2D p) {
        return nearest(root, p, 2);
    }
    
    private Point2D nearest(Node n, Point2D p, double minD) {
        RectHV leftRect;
        RectHV rightRect;
        double leftDist, rightDist;
        Point2D nearest = null;
        double minDist = minD;
        if (n == null) return nearest;
        //System.out.println("Examining point "+n.p);
        if (n.p.distanceTo(p) < minDist) {
            minDist = n.p.distanceTo(p);
            nearest = n.p;
        }
                
        
        if (n.level % 2 == 0) {
            leftRect = new RectHV(n.minX, n.minY, n.p.x(), n.maxY);
            rightRect = new RectHV(n.p.x(), n.minY, n.maxX, n.maxY);
        } else {
            leftRect = new RectHV(n.minX, n.minY, n.maxX, n.p.y());
            rightRect = new RectHV(n.minX, n.p.y(), n.maxX, n.maxY);
        }
     
        leftDist = leftRect.distanceTo(p);
        rightDist = rightRect.distanceTo(p);
        //System.out.println("minDist = "+minDist+" leftDist="+leftDist+
             //              " rightDist="+rightDist);
                          
       
        if (leftDist < rightDist && leftDist < minDist) {
            if (n.left != null) {
                Point2D leftNearest = nearest(n.left, p, minDist);
                if (leftNearest != null) {
                    nearest = leftNearest;
                    minDist = leftNearest.distanceTo(p);
                }
            }
            if (rightDist < minDist) {
                if (n.right != null) {
                    Point2D rightNearest = nearest(n.right, p, minDist);
                    if (rightNearest != null) {
                        nearest = rightNearest;
                        minDist = rightNearest.distanceTo(p);
                    }
                }
            }
        }   else if (rightDist <= leftDist && rightDist < minDist) { 
            if (n.right != null) {
                Point2D rightNearest = nearest(n.right, p, minDist);
                if (rightNearest != null) {
                    nearest = rightNearest;
                    minDist = rightNearest.distanceTo(p);
                }
            }

            if (leftDist < minDist) {           
                if (n.left != null) {
                    Point2D leftNearest = nearest(n.left, p, minDist);
                    if (leftNearest != null) {
                        nearest = leftNearest;
                        minDist = leftNearest.distanceTo(p);
                    }
                }
            }
            
        }

                    
        return nearest;
    }
    
    public static void main(String[] args) { 
        KdTree kdtree = new KdTree();
        /*
        kdtree.insert(new Point2D(0.3, 0.3));
        kdtree.insert(new Point2D(0.2, 0.2));
        kdtree.insert(new Point2D(0.1, 0.1));
        kdtree.insert(new Point2D(0.4, 0.4));
        */
        String filename = args[0];
        In in = new In(filename);

        // initialize the data structures with N points from standard input
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            kdtree.draw();
            StdDraw.show(1);
        }
        kdtree.draw();
        StdDraw.show(1);
        
        RectHV rect = new RectHV(0.25, 0.25, 0.9, 0.9);
        rect.draw();
        for (Point2D p: kdtree.range(rect)) {
            System.out.println("Point "+p);
        }
        Point2D p = new Point2D(0.22, 0.50);
        StdDraw.setPenRadius(0.01);
        p.draw();
        StdDraw.setPenRadius();
        StdDraw.show(1);
        System.out.println("Nearest to point "+p+" is "+kdtree.nearest(p));
    }
    
    /* ADD YOUR CODE HERE */
    
}
