/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item data;
        Node next;
    }

    public Deque() {
        this.first = new Node();
        this.last = new Node();
        size = 0;
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
            first = new Node();
            first.data = item;
            first.next = oldFirst;
        }
        size += 1;
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
            last = new Node();
            last.data = item;
            last.next = null;
            oldLast.next = last;
        }
        size += 1;
    }

    public Item removeFirst() {
        emptyQueueCheck();
        Item item = first.data;
        first = first.next;
        if (isEmpty())
            last = null;
        size -= 1;
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
        size -= 1;
        return item;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        // empty method body
    }

    public Iterator<Item> iterator() {
        return new FrontToEnd();
    }

    public int size() {
        return size;
    }

    private void checkNull(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
    }

    private void emptyQueueCheck() {
        if (isEmpty())
            throw new NoSuchElementException();
    }

    private class FrontToEnd implements Iterator<Item> {
        private Node current;

        public FrontToEnd() {
            current = new Node();
            current.data = first.data;
            current.next = first.next;
        }

        public boolean hasNext() {
            return (size != 0);
        }

        public Item next() {
            emptyQueueCheck();
            iterateToEndCheck();
            Item item = current.data;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void iterateToEndCheck() {
            if (current == null)
                throw new NoSuchElementException();
        }

    }
}
