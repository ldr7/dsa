/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

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
        if (overSizeCheck())
            queue = resizeArray(totalSize * 2);
        queue[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        int random = StdRandom.uniform(size);
        Item toReturn = queue[random];
        queue[random] = queue[size];
        queue[size] = null;
        size -= 1;
        if (underSizeCheck())
            queue = resizeArray(totalSize / 2);
        return toReturn;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int random = StdRandom.uniform(size);
        return queue[random];
    }


    public static void main(String[] args) {
        // empty method body
    }

    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    private Item[] resizeArray(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < totalSize; i++) {
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

    private class StackIterator implements Iterator<Item> {
        private int current;

        public StackIterator() {
            current = 0;
        }

        public boolean hasNext() {
            return current < size;
        }

        public Item next() {
            Item toReturn = queue[current];
            current += 1;
            return toReturn;
        }
    }
}
