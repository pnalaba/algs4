/**
 * Auto Generated Java Class.
 */
import java.util.ArrayList;
import java.util.Arrays;

public class Fast {
   
    private Point[] points;
    private int numPoints;
    
    private ArrayList<ArrayList<Point>> tuples;
    
    public Fast() { 
        /* YOUR CONSTRUCTOR CODE HERE*/
        tuples = new ArrayList<ArrayList<Point>>();
        numPoints = 0;
    }
    
    private void addPoint(Point p) {
        points[numPoints++] = p;
    }
    
    private void calculateTuples() {
        Arrays.sort(points); //first sort array by x and y
        //System.out.print("Original points ");
        //printArray(points, numPoints);
        for (int i = 0; i < points.length; i++) {
            Point[] sorted = points.clone(); //copy array  
            Point p = sorted[i];
            Arrays.sort(sorted, p.SLOPE_ORDER);
            //System.out.print("Sorted "+i+" ");
            //printArray(sorted, p, numPoints);
            ArrayList<Point> tuple = new ArrayList<Point>();
            int slopeCount = 0;
            double prevSlope = Double.NEGATIVE_INFINITY;
            boolean hasLowerPoint = false;
            for (int j = 1; j < points.length; j++) {
                Point q = sorted[j];   
                double currSlope = p.slopeTo(q);
                if (currSlope != prevSlope) {
                    //if atlest 3 points with same slope
                    if (slopeCount > 2 && !hasLowerPoint) { 
                        tuples.add(tuple);
                    } 
                    hasLowerPoint = false;
                    slopeCount = 1;
                    prevSlope = currSlope;
                    tuple = new ArrayList<Point>();
                    tuple.add(p); // add point p = points[i]
                    
                } else {
                    slopeCount++;
                }
                
                if (points[i].compareTo(q) > 0) //mark if a lower point in set
                    hasLowerPoint = true;
                if (!hasLowerPoint) 
                    tuple.add(q);
            
            }
            if (slopeCount > 2 && !hasLowerPoint) {
                tuples.add(tuple);
            }
            
            
        }            
    }
    
    private void drawLines() {
       
        for (ArrayList<Point> t: tuples) {
            t.get(0).drawTo(t.get(t.size()-1));
        }
        
    }
    
    private void printCollinear() {
        
        for (ArrayList<Point> t: tuples) {
            for (int i = 0; i < t.size()-1; i++) {
                System.out.print(t.get(i) + " -> ");
            }
            if (t.size() > 0) {
                System.out.println(t.get(t.size()-1));
            }
        }
    }
    
    private void printArray(Point[] pa, int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(pa[i] + " ");
        }
        System.out.println("");
    }
    
    private void printArray(Point[] pa, Point p, int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(pa[i] + ":" +p.slopeTo(pa[i])+"  ");
        }
        
        System.out.println("\n========================================");
    }
    
    public static void main(String[] args) { 
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Fast fast = new Fast();
        fast.points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            fast.addPoint(p);
            p.draw();
        }
        fast.calculateTuples();
        
        //draw the lines
        StdDraw.setPenRadius(0.001);
        fast.drawLines();
        
        //print the collinear points
        fast.printCollinear();
        
        // display to screen all at once
        StdDraw.show(0);
        
        
            
        
    }
    
    /* ADD YOUR CODE HERE */
    
}
