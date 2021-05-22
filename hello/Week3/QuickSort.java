/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package Week3;

import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {

    private static int partitionb(int[] arr, int lo, int high) {
        int i = lo;
        int j = high + 1;
        while (true) {
            while (arr[++i] < arr[lo]) {
                if (i == high)
                    break;
            }
            while (arr[--j] > arr[lo]) {

            }
            if (i >= j)
                break;
            if (arr[i] > arr[j]) {
                int temp1 = arr[i];
                arr[i] = arr[j];
                arr[j] = temp1;
            }
        }
        int temp = arr[lo];
        arr[lo] = arr[j];
        arr[j] = temp;
        return j;
    }

    private static void quickSortb(int[] arr, int lo, int high) {
        // There should be a random shuffle here
        if (lo >= high)
            return;
        int par = partitionb(arr, lo, high);
        quickSortb(arr, lo, par - 1);
        quickSortb(arr, par + 1, high);
    }

    private static void quickSortb(int[] arr) {
        quickSortb(arr, 0, arr.length - 1);
    }

    private static void quickSorte(int[] arr) {
        // There should be a random shuffle here
        quickSorte(arr, 0, arr.length - 1);
    }

    private static void quickSorte(int[] arr, int lo, int high) {
        if (lo >= high)
            return;
        int par = partitione(arr, lo, high);
        quickSorte(arr, lo, par - 1);
        quickSorte(arr, par + 1, high);
    }

    private static int partitione(int[] arr, int lo, int high) {
        int i = lo - 1;
        int j = high;
        while (true) {
            while (arr[++i] < arr[high]) {
            }
            while (arr[--j] > arr[high]) {
                if (j == lo)
                    break;
            }
            if (i >= j)
                break;
            int temp1 = arr[i];
            arr[i] = arr[j];
            arr[j] = temp1;
        }
        int temp = arr[high];
        arr[high] = arr[i];
        arr[i] = temp;
        return i;
    }

    private static void quickSortm(int[] arr) {
        StdRandom.shuffle(arr);
        System.out.println("shuffled array");
        printArray(arr);
        quickSortm(arr, 0, arr.length - 1);
    }

    private static void quickSortm(int[] arr, int lo, int high) {
        if (lo >= high)
            return;
        int par = partitionm(arr, lo, high);
        quickSortm(arr, lo, par - 1);
        quickSortm(arr, par + 1, high);
    }

    private static int partitionm(int[] arr, int lo, int high) {
        int pivot = (lo + high) / 2;
        int i = lo - 1;
        int j = high + 1;
        while (true) {
            while (arr[++i] < arr[pivot]) {

            }
            while (arr[--j] > arr[pivot]) {

            }
            if ((i == pivot) && (j == pivot))
                return pivot;
            if (j < i)
                break;
            else if (i == pivot) {
                int temp1 = arr[i];
                arr[i] = arr[j];
                arr[j] = temp1;
                pivot = j;
                continue;
            }
            else if (j == pivot) {
                int temp2 = arr[j];
                arr[j] = arr[i];
                arr[i] = temp2;
                pivot = i;
                continue;
            }
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return pivot;
    }

    public static void main(String[] args) {
        System.out.println("pivot at beginning");
        int[] sample = { 3, 7, 1, 9, 4 };
        quickSortb(sample);
        printArray(sample);
        System.out.println("End pivot");
        int[] sample1 = { 4, 9, 1, 7, 3 };
        quickSorte(sample1);
        printArray(sample1);
        System.out.println("Middle pivot");
        int[] sample2 = { 3, 7, 1, 9, 4 };
        quickSortm(sample2);
        printArray(sample2);
    }

    static private void printArray(int[] sample) {
        for (int i = 0; i < sample.length; i++) {
            System.out.println(sample[i]);
        }
    }
}
