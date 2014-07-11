/**
 * Auto Generated Java Class.
 */
public class Subset {
    
    
    public static void main(String[] args) { 
        int k = Integer.parseInt(args[0]);
        String input;
        int number;
        RandomizedQueue<String> r = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            input = StdIn.readString();
            r.enqueue(input);    
        }
        for (int i = 0; i < k && (r.size() > 0); i++)
            System.out.println(r.dequeue());
        
    }
    
    /* ADD YOUR CODE HERE */
    
}
