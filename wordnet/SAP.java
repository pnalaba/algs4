import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.HashSet;



public class SAP {
    private static final int INFINITY = Integer.MAX_VALUE;
    private Digraph G, R;
    private boolean[] marked;  // marked[v] = is there an s->v path?
    private int[] edgeTo;      // edgeTo[v] = last edge on shortest s->v path
    private int[] distTo;      // distTo[v] = length of shortest s->v path
    private HashMap<Integer, Integer> ancestorH;
    private int closestW;
    private int lastV, lastW;

    // constructor takes a digraph(not necessarily a DAG)
    public SAP(Digraph G) {
        this.G = new Digraph(G);
        // create a reverse Digraph R;
        R = G.reverse();
        resetState();
    }

    private void resetState() {
        lastV = -1;
        lastW = -1;
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        ancestorH = new HashMap<>();

        for (int v = 0; v < G.V(); v++) {
            distTo[v] = INFINITY;
        }

    }
    
    
    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        Queue<Integer> bfsQ = new Queue<>();
        Queue<Integer> bfsPreQ = new Queue<>(); // elements are queued into pre_q first
        Queue<Integer> ancQ = new Queue<>(); // ancestor queue
        Queue<Integer> ancPreQ = new Queue<>();
        resetState();
        lastV = v;
        lastW = w;

        ancQ.enqueue(v);
        ancestorH.put(v, v);
        bfsQ.enqueue(v);
        distTo[v] = 0;
        marked[v] = true;
        if (v == w) {
            return (distTo[w]);
        }
        while (bfsQ.size() > 0) {
            while (bfsQ.size() > 0) {
                int t = bfsQ.dequeue();
                // StdOut.printf("dequeued %d\n", t);
                for (int u: R.adj(t)) {
                    if (marked[u]) 
                        continue;
                    distTo[u] = distTo[t] +1;
                    edgeTo[u] = t;
                    marked[u] = true;
                    ancestorH.put(u, ancestorH.get(t));
                    if (u == w) {
                        return (distTo[u]);
                    }
                    bfsPreQ.enqueue(u);
                }
            }
            while (ancQ.size() > 0) {
                int t = ancQ.dequeue();
                // StdOut.printf("dequeued %d\n", t);
                // add ancestors to the queue
                for (int a: G.adj(t)) {
                    if (marked[a]) 
                        continue;
                    distTo[a] = distTo[t] + 1;
                    edgeTo[a] = t;
                    marked[a] = true;
                    ancestorH.put(a, a);
                    if (a == w) {
                        return (distTo[a]);
                    }
                    bfsPreQ.enqueue(a);
                    ancPreQ.enqueue(a);
                }
            }
            if (bfsPreQ.size() > 0) {
                Queue<Integer> temp = bfsPreQ;
                bfsPreQ = bfsQ;
                bfsQ = temp;
            }
            if (ancPreQ.size() > 0) {
                Queue<Integer> temp = ancPreQ;
                ancPreQ = ancQ;
                ancQ = temp;
            }

        }
        return (-1);
    }
    
    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if (v != lastV || w != lastW) {
            // calculate path again
            int len = length(v, w);
        }
        if (ancestorH.containsKey(w)) {
            return (ancestorH.get(w));
        } 
        else {
            return (-1);
        }
    }
    
    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {

        Queue<Integer> bfsQ = new Queue<>();
        Queue<Integer> bfsPreQ = new Queue<>(); // elements are queued into pre_q first
        Queue<Integer> ancQ = new Queue<>(); // ancestor queue
        Queue<Integer> ancPreQ = new Queue<>();
        HashSet<Integer> whash = new HashSet<>();
        for (int ww: w) {
            whash.add(ww);
        }
        for (int vv: v) {
            ancQ.enqueue(vv);
            ancestorH.put(vv, vv);
            bfsQ.enqueue(vv);
            distTo[vv] = 0;
            marked[vv] = true;
            if (whash.contains(vv)) {
                return (distTo[vv]);
            }
        }
        while (bfsQ.size() > 0) {
            while (bfsQ.size() > 0) {
                int t = bfsQ.dequeue();
                // StdOut.printf("dequeued %d\n", t);
                for (int u: R.adj(t)) {
                    if (marked[u]) 
                        continue;
                    distTo[u] = distTo[t] +1;
                    edgeTo[u] = t;
                    marked[u] = true;
                    ancestorH.put(u, ancestorH.get(t));
                    if (whash.contains(u)) {
                        closestW = u;
                        return (distTo[u]);
                    }
                    bfsPreQ.enqueue(u);
                }
            }
            while (ancQ.size() > 0) {
                int t = ancQ.dequeue();
                // StdOut.printf("dequeued %d\n", t);
                // add ancestors to the queue
                for (int a: G.adj(t)) {
                    if (marked[a]) 
                        continue;
                    distTo[a] = distTo[t] + 1;
                    edgeTo[a] = t;
                    marked[a] = true;
                    ancestorH.put(a, a);
                    if (whash.contains(a)) {
                        closestW = a;
                        return (distTo[a]);
                    }
                    bfsPreQ.enqueue(a);
                    ancPreQ.enqueue(a);
                }
            }
            if (bfsPreQ.size() > 0) {
                Queue<Integer> temp = bfsPreQ;
                bfsPreQ = bfsQ;
                bfsQ = temp;
            }
            if (ancPreQ.size() > 0) {
                Queue<Integer> temp = ancPreQ;
                ancPreQ = ancQ;
                ancQ = temp;
            }

        }
        return (0);
    }
    


    
    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (ancestorH.containsKey(closestW)) {
            return (ancestorH.get(closestW));
        } 
        else {
            return (-1);
        }
    }
    
    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        StdOut.printf("Instantiating SAP \n");
        SAP sap = new SAP(G);
        StdOut.printf("Calling while loop in Main\n");
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
                    // StdOut.printf("v=%d w=%d\n", v, w);
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }

    }
};
