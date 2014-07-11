/**
 * Auto Generated Java Class.
 */
public class TestDeque {
    private static final int N = 10;
    public TestDeque() { 
        /* YOUR CONSTRUCTOR CODE HERE*/
    }
    
    public void testAddFirstRemoveLast() {
        Deque<Integer> d = new Deque<Integer>();
        for (int i = 0; i < N; i++) 
            d.addFirst(i);
        System.out.println("AddFirstRemoveLast Ascending order");
        for (int i = 0; i < N; i++)
            System.out.printf(d.removeLast()+" ");
        System.out.println(""); 
        //System.out.println("first="+d.first+" last="+d.last);
    }
    
    public void testAddFirstRemoveFirst() {
        Deque<Integer> d = new Deque<Integer>();
        for (int i = 0; i < N; i++) 
            d.addFirst(i);
        System.out.println("AddFirstRemoveFirst Descending order");
        for (int i = 0; i < N; i++)
            System.out.printf(d.removeFirst()+" ");
        System.out.println(""); 
        //System.out.println("first="+d.first+" last="+d.last);
    }
    
    public void testAddLastRemoveLast() {
        Deque<Integer> d = new Deque<Integer>();
        for (int i = 0; i < N; i++) 
            d.addLast(i);
        System.out.println("AddLastRemoveLast Descending order");
        for (int i = 0; i < N; i++)
            System.out.printf(d.removeLast()+" ");
        System.out.println(""); 
    }
    
    public void testAddLastRemoveFirst() {
        Deque<Integer> d = new Deque<Integer>();
        for (int i = 0; i < N; i++) 
            d.addLast(i);
        System.out.println("AddLastRemoveFirst Ascending order");
        for (int i = 0; i < N; i++)
            System.out.printf(d.removeFirst()+" ");
        System.out.println(""); 
    }
    public void testAddFirstPrintIterator() {
        Deque<Integer> d = new Deque<Integer>();
        for (int i = 0; i < N; i++) 
            d.addLast(i);
        System.out.println("AddFirstPrintIterator Descending order");
        for (int e : d)
            System.out.printf(e+" ");
        System.out.println(""); 
    }
    public static void main(String[] args) { 
        TestDeque t = new TestDeque();
        t.testAddFirstRemoveLast();
        t.testAddFirstRemoveFirst();
        t.testAddLastRemoveFirst();
        t.testAddLastRemoveLast();
        t.testAddFirstPrintIterator();
    }
    
    /* ADD YOUR CODE HERE */
    
}
