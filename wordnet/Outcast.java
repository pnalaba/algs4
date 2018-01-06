import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;



public class Outcast {
    private WordNet wn;
    public Outcast(WordNet wordnet) {
        wn = wordnet;
    }

    public String outcast(String[] nouns) {
        int maxDist = 0;
        String maxNoun = "";
        for (String ni : nouns) {
            int dist = 0;
            for (String nj : nouns) {
                dist += wn.distance(ni, nj);
            }
            if (dist > maxDist) {
                maxDist = dist;
                maxNoun = ni;
            }

        }
        return (maxNoun);
    }

    public static void main(String[] args)  {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }

}
