/**
 * Auto Generated Java Class.
 */
public class TestRandomizedQueue {
    private static final int N = 10;
    public TestRandomizedQueue() { 
        /* YOUR CONSTRUCTOR CODE HERE*/
    }
    
    public void testEnqueue() {
        RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
        for (int i = 0; i < N; i++) 
            d.enqueue(i);
        System.out.println("testEnqueue with "+N+" elements");
        System.out.println("Size = "+d.size());
        for (int i = 0; i < N; i++)
            System.out.printf(d.dequeue()+" ");
        System.out.println(""); 
        //System.out.println("first="+d.first+" last="+d.last);
    }
    
    /*
    public void testAddFirstRemoveFirst() {
        RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
        for (int i = 0; i < N; i++) 
            d.addFirst(i);
        System.out.println("AddFirstRemoveFirst Descending order");
        for (int i = 0; i < N; i++)
            System.out.printf(d.removeFirst()+" ");
        System.out.println(""); 
        //System.out.println("first="+d.first+" last="+d.last);
    }
    
    public void testAddLastRemoveLast() {
        RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
        for (int i = 0; i < N; i++) 
            d.addLast(i);
        System.out.println("AddLastRemoveLast Descending order");
        for (int i = 0; i < N; i++)
            System.out.printf(d.removeLast()+" ");
        System.out.println(""); 
    }
    
    public void testAddLastRemoveFirst() {
        RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
        for (int i = 0; i < N; i++) 
            d.addLast(i);
        System.out.println("AddLastRemoveFirst Ascending order");
        for (int i = 0; i < N; i++)
            System.out.printf(d.removeFirst()+" ");
        System.out.println(""); 
    }
    public void testAddFirstPrintIterator() {
        RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
        for (int i = 0; i < N; i++) 
            d.addLast(i);
        System.out.println("AddFirstPrintIterator Descending order");
        for (int e : d)
            System.out.printf(e+" ");
        System.out.println(""); 
    }
    */
    public static void main(String[] args) { 
        TestRandomizedQueue t = new TestRandomizedQueue();
        t.testEnqueue();
    }
    
    /* ADD YOUR CODE HERE */
    
}
