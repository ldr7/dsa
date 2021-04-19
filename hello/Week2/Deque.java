package Week2;/* *****************************************************************************
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
        Node prev;
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
            first.prev = null;
            last = first;
        }
        else {
            Node oldFirst = first;
            first = new Node();
            first.data = item;
            first.next = oldFirst;
            first.prev = null;
            oldFirst.prev = first;
        }
        size += 1;
    }

    public void addLast(Item item) {
        checkNull(item);
        if (isEmpty()) {
            last = new Node();
            last.data = item;
            last.next = null;
            last.prev = null;
            first = last;
        }
        else {
            Node oldLast = last;
            last = new Node();
            last.data = item;
            last.next = null;
            last.prev = oldLast;
            oldLast.next = last;
        }
        size += 1;
    }

    public Item removeFirst() {
        emptyQueueCheck();
        Item item = first.data;
        first = first.next;
        size -= 1;
        if (isEmpty())
            last = null;
        else
            first.prev = null;
        return item;
    }

    public Item removeLast() {
        emptyQueueCheck();
        if (first.equals(last))
            return removeFirst();
        Item item = last.data;
        last = last.prev;
        last.next = null;
        size -= 1;
        return item;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        Iterator<Integer> integerIterator = deque.iterator();
        System.out.println(integerIterator.hasNext());
    }

    @Override
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
            current = first;
        }

        @Override
        public boolean hasNext() {
            if (isEmpty())
                return false;
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.data;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
