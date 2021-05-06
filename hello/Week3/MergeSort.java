/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package Week3;

public class MergeSort {
    int[] ele;
    int[] aux;

    public MergeSort(int[] input) {
        ele = new int[input.length];
        aux = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            ele[i] = input[i];
            aux[i] = input[i];
        }
    }

    public static void main(String[] args) {
        // driver method
        int[] sample = { 5, 3, 7, 6, 8, 9, 2, 4 };
        MergeSort mergeSort = new MergeSort(sample);
        mergeSort.mergeSort(0, 7);
        // display sorted array
        for (int i = 0; i < mergeSort.ele.length; i++) {
            System.out.println(mergeSort.ele[i]);
        }
    }

    private void mergeSort(int low, int high) {
        if (high <= low)
            return;
        int mid = (low + high) / 2;
        mergeSort(low, mid);
        mergeSort(mid + 1, high);
        merge(low, mid, high);
    }

    private void merge(int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        // copy main into copy
        for (int il = 0; il < ele.length; il++) {
            aux[il] = ele[il];
        }
        for (int k = low; k <= high; k++) {
            if (i > mid)
                ele[k] = aux[j++];
            else if (j > high)
                ele[k] = aux[i++];
            else if (aux[i] <= aux[j])
                ele[k] = aux[i++];
            else
                ele[k] = aux[j++];
        }
    }
}
