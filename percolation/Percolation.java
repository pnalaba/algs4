/**
 * Auto Generated Java Class.
 */
public class Percolation {
    /* ADD YOUR CODE HERE */
    
    private int N;
    private boolean[][] open;
    private boolean[] bottom;
    private WeightedQuickUnionUF unionFind;
    private int TOP, BOTTOM, topRoot;
    private boolean percolates;
    
    
    public Percolation(int N) {
        if (N <= 0) throw new 
            IllegalArgumentException("Invalid value for size of grid="+N);
        this.N = N;
        open = new boolean[N][N];
        bottom = new boolean[N*N+2];
        
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++) {
                open[i][j] = false;   //All sites initialized to blocked
                bottom[i*N+j] = false;
        }
        TOP = N*N;
        topRoot = TOP;
        BOTTOM = TOP+1;
        unionFind = new WeightedQuickUnionUF(BOTTOM+1);
        percolates = false;
    }
    
    private int oneD(int i, int j) {
        return (i*N + j);
    }
    
    private int checkPercolates(int currentRoot, int other, boolean isTopIn,
                                 boolean isBottom) {
        int root = unionFind.find(other);
        
        boolean isTop = isTopIn || (currentRoot == topRoot);
        boolean isBot = isBottom || bottom[currentRoot];
        
        unionFind.union(currentRoot, root);
        if (isBot && root == topRoot) //bottom  connected to TOP
            percolates = true;
        else if (isTop && bottom[root]) //Top cell connected to bottom
            percolates = true;
        int postRoot = unionFind.find(currentRoot);
        if (isBot || bottom[root])
            bottom[postRoot] = true; //if one of inputs is bottom, update new root
        if (isTop || (root == topRoot))
            topRoot = postRoot; //if one of the inputs is top, update global topRoot
        return postRoot; //return new root
    }
                                   
    
    public void open(int ii, int jj) {
        int i = ii -1; //convert to 0 to N-1
        int j = jj -1; //convert to 0 to N-1
        checkBounds(i, j);
        open[i][j] = true;
        boolean isBottom = false;
        boolean isTop = false;
        if (N == 1) percolates = true; //corner case
        //check neighbors
        if (i == 0)  {
            unionFind.union(TOP, oneD(i, j));
            topRoot = unionFind.find(TOP);
            isTop = true;
        }
        int curRoot = unionFind.find(oneD(i, j));
        if (i == (N-1)) { 
            isBottom = true; //mark as connected to bottom
            bottom[oneD(i, j)] = true;
        }
        if ((i > 0) && open[i-1][j]) { //if TOP nbr is open 
            curRoot = checkPercolates(curRoot, oneD(i-1, j), isTop, isBottom);
        }  
        if ((i < (N-1)) && open[i+1][j]) { //if BOTTOM nbr is open
            curRoot = checkPercolates(curRoot, oneD(i+1, j), isTop, isBottom);
        }
        if ((j > 0) && open[i][j-1]) { // if LEFT nbr is open
            curRoot = checkPercolates(curRoot, oneD(i, j-1), isTop, isBottom);
        }
        if ((j < (N-1)) && open[i][j+1]) { //if RIGHT nbr is open
            curRoot = checkPercolates(curRoot, oneD(i, j+1), isTop, isBottom);
        }
        //System.out.printf("After %d, %d percolates = %s\n", ii, jj, percolates);
    } 
   
    public boolean isOpen(int ii, int jj) {
        int i = ii -1; //convert to 0 to N-1 
        int j = jj -1; //convert to 0 to N-1
        checkBounds(i, j);
        return open[i][j];
    }
    
    public boolean isFull(int ii, int jj) {
        int i = ii-1; //convert to 0 to N-1
        int j = jj-1; //convert to 0 to N-1
        checkBounds(i, j);
        if (!open[i][j]) return false;
        return (unionFind.connected(oneD(i, j), TOP));
    }
       
    public boolean percolates() {
        return percolates;
    }
    //checkBounds assumes coordinates are 0 to N-1
    private void checkBounds(int i, int j) {
        if ((i < 0) || (i > (N-1)) || (j < 0) || (j > (N-1))) 
            throw new IndexOutOfBoundsException(
                "Index ("+i+","+j+") out of bounds for grid of size="+N);
    }
    
}
