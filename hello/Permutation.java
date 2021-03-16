/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        String[] strings = StdIn.readAllStrings();
        RandomizedDeque randomizedDeque = new RandomizedDeque();
        for (String s : strings) {
            randomizedDeque.enqueue(s);
        }
        for (int i = 0; i < k; i++) {
            System.out.println(randomizedDeque.dequeue());
        }
    }
}
