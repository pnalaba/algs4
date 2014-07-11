/**
 * Auto Generated Java Class.
 */
import java.util.NoSuchElementException;
import java.util.Iterator;
public class Deque<Item> implements Iterable<Item> {
    
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }
    private Node first, last;
    private int N;
    public Deque() { 
        /* YOUR CONSTRUCTOR CODE HERE*/
        first = null;
        last = null;
        N = 0;
    }
    
    public boolean isEmpty() {
        return (N == 0);
    }
    
    public int size() {
        return N;
    }
    
    public void addFirst(Item item) {
        if (item == null) throw new 
            NullPointerException("item null in call to addFirst");
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.prev = null;
        if (oldfirst != null) oldfirst.prev = first;
        else last = first;
        N++;
        //System.out.println("last="+last+" first="+first);
    }
    
    public void addLast(Item item) {
        if (item == null) throw new 
            NullPointerException("Item null in call to addLast");
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldlast;
        if (oldlast != null) oldlast.next = last;
        else first = last;
        N++;
        //System.out.println("last="+last+" first="+first);
    }
    
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Dequeue empty");
        Item item = first.item;
        first = first.next;
        if (first != null) first.prev = null;
        else last = first;
        N--;
        //System.out.println("last="+last+" first="+first);
        return item;
    }
    
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Dequeue empty");
        Item item = last.item;
        last = last.prev;
        if (last != null) last.next = null;
        else first = last;
        N--;
        return item;
    }
    
    public Iterator<Item> iterator() { return new ListIterator(); }
    
    private class ListIterator implements Iterator<Item> {
        private Node current;
        public ListIterator() {
            current = first;
        }
        public boolean hasNext() {
            return (current != null);
        }
        public void remove() {
            throw new UnsupportedOperationException("remove() not supported");   
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("Dequeue empty");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    public static void main(String[] args) { 
             
    }
    
    /* ADD YOUR CODE HERE */
    
}
