/**
 * Auto Generated Java Class.
 */
import java.util.Comparator;
public class Solver {
    
    private class Node {
        private final Comparator<Node> MANHATTAN_ORDER = new ByManhattan();
        private Board board;
        private int moves;
        private Node prev;
        
        Node(Board b, int m, Node p) {
            board = b;
            moves = m;
            prev = p;
        }
        
        private class ByManhattan implements Comparator<Node> {
            public int compare(Node v, Node w) {
                if ((v.board.manhattan() + v.moves) 
                        < (w.board.manhattan() + w.moves))
                    return -1;
                else if ((v.board.manhattan() + v.moves) 
                             > (w.board.manhattan() + w.moves))
                    return 1;
                else return 0;
            }
        } 
        public String toString() {
            String str;
            str = board + " moves="+moves+" this="+hashCode() + " prev=";
            if (prev == null) return str +"null";
            else return str +prev.hashCode();
        }
        
    }
    
    private MinPQ<Node> pq, pqTwin;
    private Node n, nTwin;
    private Stack<Board> solutionStack;
    
    public Solver(Board initial) { 
        /* YOUR CONSTRUCTOR CODE HERE*/
        n = new Node(initial, 0, null);
        nTwin = new Node(initial.twin(), 0, null);
        pq = new MinPQ<Node>(1, n.MANHATTAN_ORDER);
        pqTwin =  new MinPQ<Node>(1, nTwin.MANHATTAN_ORDER);
        int count = 0;
        //System.out.println("initial is "+initial);
        //System.out.println("initial twin is "+nTwin.board);
        //System.out.println("goal is "+new Board(initial.goal));
        pq.insert(n);
        pqTwin.insert(nTwin);
        while (!n.board.isGoal() && !pq.isEmpty()
            && !nTwin.board.isGoal() && !pqTwin.isEmpty()) {
            n = pq.delMin();
            //System.out.println("removing "+n);
            for (Board b : n.board.neighbors()) {
                if (n.prev == null || !b.equals(n.prev.board)) {
                    Node m = new Node(b, n.moves+1, n);
                    pq.insert(m);
                }
            }
            nTwin = pqTwin.delMin();
            for (Board b : nTwin.board.neighbors()) {
                if (nTwin.prev == null || !b.equals(nTwin.prev.board)) {
                    Node m = new Node(b, nTwin.moves+1, nTwin); 
                    pqTwin.insert(m);
                }
            }
            
            //solutionNodeList.add(n);
            //System.out.println("===============================================");
        }
        Node m = n;
        if (!nTwin.board.isGoal()) {
            solutionStack = new Stack<Board>();            
            do {
                solutionStack.push(m.board);
                m = m.prev;
            } while (m != null);
        }
       
    }
    
    public boolean isSolvable() {
        return !nTwin.board.isGoal();
    }
    
    public int moves() {
        if (nTwin.board.isGoal()) return -1;
        return n.moves;
    }
    
    public Iterable<Board> solution() {
        return solutionStack;
    }
    
    public static void main(String[] args) { 
          // for each command-line argument
        for (String filename : args) {

            // read in the board specified in the filename
            In in = new In(filename);
            int N = in.readInt();
            int[][] tiles = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    tiles[i][j] = in.readInt();
                }
            }

            // solve the slider puzzle
            Board initial = new Board(tiles);
            Solver solver = new Solver(initial);
            // print solution to standard output
            if (!solver.isSolvable())
                StdOut.println("No solution possible");
            else {
                StdOut.println("Minimum number of moves = " + solver.moves());
                for (Board board : solver.solution())
                    StdOut.println(board);
            }
        }
        
    }
    
    /* ADD YOUR CODE HERE */
    
}
