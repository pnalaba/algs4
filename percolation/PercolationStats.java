/**
 * Auto Generated Java Class.
 */

public class PercolationStats {
    
    private int[] p;
    private double mean, stddev, confidenceLo, confidenceHi;
    
    /* ADD YOUR CODE HERE */
    public PercolationStats(int N, int T) {
        if (N <= 0) throw new 
            IllegalArgumentException("Invalid value for size of grid="+N);
        if (T <= 0) throw new 
            IllegalArgumentException("Invalid value for number of tests ="+T);
        p = new int [T];
        
        for (int count = 0; count < T; count++) {
            int i, j, pp;
            //start with new percolation
            Percolation percolation = new Percolation(N); 
            pp = 0; 
            while (!percolation.percolates()) {
                i = 1+ StdRandom.uniform(N); //random in [1:N]
                j = 1+ StdRandom.uniform(N); //random in [1,N]
                if (!percolation.isOpen(i, j)) {
                    percolation.open(i, j);    
                    pp++;
                }
                
            } 
            p[count] = pp;
        }
        mean = StdStats.mean(p)/(N*N);
        stddev = StdStats.stddev(p)/ (N*N);
        double confidenceRange = 1.96 *stddev/Math.sqrt(T);
        confidenceLo = mean - confidenceRange;
        confidenceHi = mean + confidenceRange;
    }
    
    public double mean() {
        return (mean);
    }
    
    public double stddev() {
        return stddev;
    }
    
    public double confidenceLo() {
        return confidenceLo;
    }
    
    public double confidenceHi() {
        return confidenceHi; 
    }
    
    public static void main(String[] args) {
        int N, T;
        N = Integer.parseInt(args[0]);
        T = Integer.parseInt(args[1]);
        PercolationStats pstat = new PercolationStats(N, T); 
        System.out.printf("mean\t\t\t= %.15f\n", pstat.mean());
        System.out.printf("stddev\t\t\t= %.15f\n", pstat.stddev());
        System.out.printf("95%% confidence interval\t= %.15f, %.15f\n",
                          pstat.confidenceLo(), pstat.confidenceHi());
        
    }
    
}
