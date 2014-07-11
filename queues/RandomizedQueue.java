/**
 * Auto Generated Java Class.
 */
import java.util.NoSuchElementException;
import java.util.Iterator;
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int N; //number of items in queue 
    private int INITLENGTH = 8; //initial size of array
    private Item[] s;
    
    public RandomizedQueue() { 
        /* YOUR CONSTRUCTOR CODE HERE*/
        N = 0;
        s = (Item[]) new Object [INITLENGTH];
    }
    
    public boolean isEmpty() {
        return (N == 0);
    }
    
    public int size() {
        return N;
    }
    
    public void enqueue(Item item) {
        if (item == null) throw new 
            NullPointerException("item null in call to enqueue");
        int length = s.length;
        if (length == N) {
            length *= 2;
            resize(length);
        }
        s[N] = item;
        N++;
        
    }
    
    public Item dequeue() {
        if (isEmpty()) throw new 
            NoSuchElementException("dequeue on empty RandomzedQueue");
        int length = s.length;
        int randIndex = StdRandom.uniform(N);
        Item item = s[randIndex];
        if (randIndex != (N-1)) s[randIndex] = s[N-1];
        s[N-1] = null;
        N--;
        if (N < (length/4)) resize(length/2);
        return item;
    }
   
    public Item sample() {
        if (isEmpty()) throw new 
            NoSuchElementException("dequeue on empty RandomzedQueue");
        int randIndex = StdRandom.uniform(N);
        return s[randIndex];
    }
           
    private void resize(int capacity) {
        Item[] newS = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++)
            newS[i] = s[i];
        s = newS;
    }
    
    public Iterator<Item> iterator() { return new ArrayIterator(); }
    
    private class ArrayIterator implements Iterator<Item> {
        private int current;
        private int[] indices;
        
        public ArrayIterator() {
            indices = new int[N];
            for (int i = 0; i < N; i++)
                indices[i] = i;
            StdRandom.shuffle(indices);
            current = 0;
        }
        public boolean hasNext() {
            return (current < N);
        }
        public void remove() {
            throw new  UnsupportedOperationException("iterator.remove unsupported");
        }
        public Item next() {
            if (!hasNext()) throw new 
                NoSuchElementException("Next called on iterator that reached end");
            return s[indices[current++]]; 
        }
    }
    
    public static void main(String[] args) { 
        
    }
    
    /* ADD YOUR CODE HERE */
    
}
