/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    Node first;
    Node last;

    class Node {
        Item data;
        Node next;
    }

    public Deque() {
        this.first = new Node();
        this.last = new Node();
    }

    public void addFirst(Item item) {
        checkNull(item);
        if (isEmpty()) {
            first = new Node();
            first.data = item;
            first.next = null;
            last = first;
        }
        else {
            Node oldFirst = first;
            first.data = item;
            first.next = oldFirst;
        }
    }

    public void addLast(Item item) {
        checkNull(item);
        if (isEmpty()) {
            last = new Node();
            last.data = item;
            last.next = null;
            first = last;
        }
        else {
            Node oldLast = last;
            last.data = item;
            last.next = null;
            oldLast.next = last;
        }
    }

    public Item removeFirst() {
        emptyQueueCheck();
        Item item = first.data;
        first = first.next;
        if (isEmpty())
            last = null;
        return item;
    }

    public Item removeLast() {
        emptyQueueCheck();
        if (first.equals(last))
            return removeFirst();
        Item item = last.data;
        Node curr = first;
        while (!curr.next.equals(last))
            curr = curr.next;
        curr.next = null;
        last = curr;
        return item;
    }


    public boolean isEmpty() {
        return first == null;
    }

    public static void main(String[] args) {
    }

    public Iterator<Item> iterator() {
        return new FrontToEnd();
    }

    private void checkNull(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
    }

    private void emptyQueueCheck() {
        if (isEmpty())
            throw new NoSuchElementException();
    }

    public class FrontToEnd implements Iterator<Item> {
        Node current;

        public FrontToEnd() {
            current = new Node();
            current = first;
        }

        public boolean hasNext() {
            return !(current == null);
        }

        public Item next() {
            emptyQueueCheck();
            Item item = current.data;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
