/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int MINIMUM_SIZE = 2;
    private Item[] queue;
    private int size;
    private int totalSize;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[MINIMUM_SIZE];
        size = 0;
        totalSize = MINIMUM_SIZE;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        nullCheck(item);
        if (overSizeCheck())
            queue = resizeArray(totalSize * 2);
        queue[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        emptyQueueCheck();
        int random = StdRandom.uniform(size);
        Item toReturn = queue[random];
        queue[random] = queue[size - 1];
        queue[size - 1] = null;
        size -= 1;
        if (underSizeCheck())
            queue = resizeArray(totalSize / 2);
        return toReturn;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        emptyQueueCheck();
        int random = StdRandom.uniform(size);
        return queue[random];
    }


    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.dequeue());
    }

    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    private Item[] resizeArray(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = queue[i];
        }
        totalSize = capacity;
        return copy;
    }

    private boolean underSizeCheck() {
        if (size < (totalSize) / 4)
            return true;
        return false;
    }

    private boolean overSizeCheck() {
        if (size == totalSize)
            return true;
        return false;
    }

    private void nullCheck(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
    }

    private void emptyQueueCheck() {
        if (size == 0)
            throw new NoSuchElementException();
    }

    private class StackIterator implements Iterator<Item> {
        private int copySize;
        private Item[] copyQueue;

        public StackIterator() {
            copySize = 0;
            copyQueue = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                copyQueue[i] = queue[i];
            }
            copySize = size;
        }

        public boolean hasNext() {
            return copySize != 0;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            int randomIteratorElement = StdRandom.uniform(copySize);
            Item toReturn = copyQueue[randomIteratorElement];
            copyQueue[randomIteratorElement] = copyQueue[copySize - 1];
            copyQueue[copySize - 1] = null;
            copySize -= 1;
            return toReturn;
        }
    }
}
