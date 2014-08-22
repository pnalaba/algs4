/**
 * Auto Generated Java Class.
 */
import java.util.ArrayList;
import java.util.Arrays;

public class Brute {
   
    private Point[] points;
    private int numPoints;
    
    private class Tuple {
        private Point a, b, c, d;
        Tuple(Point A, Point B, Point C, Point D) {
            a = A;
            b = B;
            c = C;
            d = D;
        }
    }
    
    private ArrayList<Tuple> tuples;
    
    public Brute() { 
        /* YOUR CONSTRUCTOR CODE HERE*/
        tuples = new ArrayList<Tuple>();
        numPoints = 0;
    }
    
    private void addPoint(Point p) {
        points[numPoints++] = p;
    }
    
    private void calculateTuples() {
        Arrays.sort(points);
        for (int i = 0; i < points.length-3; i++) {
            for (int j = i+1; j < points.length-2; j++) {
                double slope1 = points[i].slopeTo(points[j]);
                //System.out.println(points[i]+"->"+points[j]+slope1);
                for (int k = j+1; k < points.length-1; k++) {
                   double slope2 = points[i].slopeTo(points[k]);
                   //System.out.println("  "+points[i]+"->"+points[k]+slope2);
                    for (int l = k+1; l < points.length && (slope1 == slope2); l++) {
                        double slope3 = points[i].slopeTo(points[l]);
                        //System.out.println("    "+points[i]+"->"+points[l]+slope3);
                        if (slope1 == slope3) {
                          Tuple tuple = new 
                              Tuple(points[i], points[j], points[k], points[l]);
                          tuples.add(tuple);
                        }
                    }
                    
                }   
            }
            //System.out.println("=====================================");
        }
                     
    }
    
    private void drawLines() {
        for (Tuple t: tuples) {
            t.a.drawTo(t.d);
        }
        
    }
    
    private void printCollinear() {
        for (Tuple t: tuples) {
            System.out.println(t.a+" -> "+t.b+" -> "+t.c+" -> "+t.d);
        }
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
        Brute brute = new Brute();
        brute.points = new Point[N];
        
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            brute.addPoint(p);
            p.draw();
        }
        brute.calculateTuples();
        
        
        //draw the lines
        StdDraw.setPenRadius(0.001);
        brute.drawLines();
        
        //print the collinear points
        brute.printCollinear();
        
        // display to screen all at once
        StdDraw.show(0);
        
        
            
        
    }
    
    /* ADD YOUR CODE HERE */
    
}
