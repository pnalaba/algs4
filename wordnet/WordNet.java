/**
 * Auto Generated Java Class.
 */
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class WordNet {
    private static final int INFINITY = Integer.MAX_VALUE;
    private HashMap<String, Queue<Integer>> nounId;
    private ArrayList<String> synsetAl;
    private Digraph G;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        In inSynset = new In(synsets);
        In inHypernyms = new In(hypernyms);
        nounId = new HashMap<>();
        synsetAl = new ArrayList<>();

        String line = null;
        int count = 0;
        // read synsets. Add each noun to arrayList nouns
        // and store the noun -> id mapping in hashMaop nounId
        while ((line = inSynset.readLine()) != null)  {
            String[] tokens = line.split(",");
            int id = Integer.parseInt(tokens[0]);
            synsetAl.add(tokens[1]);

            for (String name : Arrays.asList(tokens[1].split(" "))) {
                // nouns.add(name);
                if (nounId.containsKey(name)) {
                    Queue<Integer> al = nounId.get(name);
                    al.enqueue(id);
                }
                else {
                    Queue<Integer> al = new Queue<>();
                    al.enqueue(id);
                    nounId.put(name, al);
                }
            }
            count++;
        }
        G = new Digraph(count);
        line = null;
        while ((line = inHypernyms.readLine()) != null) {
            String[] tokens = line.split(",");
            int v = Integer.parseInt(tokens[0]);
            for (int i = 1; i < tokens.length; i++) {
                G.addEdge(v, Integer.parseInt(tokens[i]));
            }
        }

    }

    public Iterable<String> nouns() {
        return nounId.keySet();
    }

    public boolean isNoun(String word) {
        return nounId.containsKey(word); 
    }

    public int distance(String nounA, String nounB) {
        boolean[] marked;  // marked[v] = is there an s->v path?
        int[] edgeTo;      // edgeTo[v] = last edge on shortest s->v path
        int[] distTo;      // distTo[v] = length of shortest s->v path

        if (!nounId.containsKey(nounA)) { 
            // throw new IllegalArgumentException("In distance :" +nounA+" not a valid word\n");
            StdOut.println("In distance :" +nounA+" not a valid word\n");
            // System.exit(-1);
        } 
        else if (!nounId.containsKey(nounB)) {
            // throw new IllegalArgumentException("In distance :" +nounB+" not a valid word\n");
            StdOut.println("In distance :" +nounB+" not a valid word\n");
            // System.exit(-1);
        }
        
        // find distance in the graph by doing breadth first search
        // Go to ancestors V0  add all V0's children in reverse digraph of G to queue
        // add V1, the ancestors of V0 to the queue and repeat 
        //  until nodeB is reached
        Queue<Integer> idA = nounId.get(nounA);
        Queue<Integer> idB = nounId.get(nounB);
        SAP sap = new SAP(G);
        return sap.length(idA, idB);
    }

    public String sap(String nounA, String nounB) {
        Queue<Integer> idA = nounId.get(nounA);
        Queue<Integer> idB = nounId.get(nounB);
        SAP sap = new SAP(G);
        int sapId = sap.ancestor(idA, idB);
        if (sapId == -1) {
            return ("");
        } 
        else {
            return (synsetAl.get(sapId));
        }
    }
    
    public static void main(String[] args) {

    }

};
