/**
 * Auto Generated Java Class.
 */
import java.util.ArrayList;
public class Board {
    private char[][] blocks;
    private int N;
    private boolean isGoal, isSetGoal;
    private int hamming = -1; //initial invalid value
    private int manhattan = -1;
    
    
    public Board(int[][] blocksInt) { 
        N = blocksInt.length;
        blocks = new char[N][N];
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++) 
                blocks[i][j] = (char) blocksInt[i][j];

        isSetGoal = false;
        /* YOUR CONSTRUCTOR CODE HERE*/
    }
    
    public int dimension() {
        return N;
    }
    
    public int hamming() {
        if (hamming >= 0) return hamming;
        hamming = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] != (i*N+j+1) && blocks[i][j] != 0) 
                    hamming++;
            }
        }
        return hamming;
    }
    
    public int manhattan() {
        if (manhattan >= 0) return manhattan;
        manhattan = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) 
                if (blocks[i][j] > 0) {
                    int ii = (blocks[i][j]-1)/N;
                    int jj = (blocks[i][j]-1) % N;
                    manhattan += Math.abs(ii- i) + Math.abs(jj - j);
            }
        }
        
        return manhattan;
            
    }
    
    
    public boolean isGoal() {
        if (isSetGoal) return isGoal;
        else isSetGoal = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == (N-1) && j == (N-1)) {
                    if (blocks[i][j] != 0) {
                        isGoal = false;
                        return isGoal;
                    }
                }
                else if (blocks[i][j] != (i*N+j+1)) {
                    isGoal = false;
                    return isGoal;
                }
            }
        }
        
        isGoal = true;
        return isGoal;
    }
    public Board twin() {
        int[][] b = new int[N][N];
        boolean flipped = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                b[i][j] = (int) blocks[i][j];
                if ((j > 0) && (!flipped) && (b[i][j-1] != 0) && (b[i][j] != 0)) {
                    exch(b, i, j-1, i, j);
                    flipped = true;
                }
            }
        }
        return new Board(b);
    }
    
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (that.N != N) return false;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (this.blocks[i][j] != that.blocks[i][j]) return false;
        return true;
    }
    
    public Iterable<Board> neighbors() {
        int[][] b = new int[N][N];
        boolean flipped = false;
        int i = N;
        int j = N;
        for (int ii = 0; ii < N; ii++) {
            for (int jj = 0; jj < N; jj++) {
                b[ii][jj] = (int) blocks[ii][jj];
                if (b[ii][jj] == 0) {
                    i = ii;
                    j = jj;
                }
            }
        }
        ArrayList<Board> q = new ArrayList<Board>();
        if (i > 0) {
            exch(b, i, j, i-1, j);
            q.add(new Board(b));
            exch(b, i-1, j, i, j);
        } 
        if (j > 0) {
            exch(b, i, j-1, i, j);
            q.add(new Board(b));
            exch(b, i, j, i, j-1);
        }
        if (i < N-1) {
            exch(b, i, j, i+1, j);
            q.add(new Board(b));
            exch(b, i, j, i+1, j);
        }
        if (j < N-1) {
            exch(b, i, j, i, j+1);
            q.add(new Board(b));
            exch(b, i, j, i, j+1);
        }
        return q;
        
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int val = (int) blocks[i][j];
                s.append(String.format("%3d ", val));
            }
            s.append("\n");
        }
        return s.toString();
    }
    
    private void exch(int[][]a, int ia, int ja, int ib, int jb) {
        int tmp = a[ia][ja];
        a[ia][ja] = a[ib][jb];
        a[ib][jb] = tmp;
    }
        
    /* ADD YOUR CODE HERE */
    
}
