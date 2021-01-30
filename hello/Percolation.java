/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    int[] id;
    WeightedQuickUnionUF weightedQuickUnionUF;
    private int squareLength;

    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        squareLength = n;
        weightedQuickUnionUF = new WeightedQuickUnionUF((n * n) + 2);
        id = new int[(n * n) + 2];
        for (int i = 0; i < (n * n) + 2; i++) {
            id[i] = 0;
        }
        id[0] = 1;
        id[(n * n) + 1] = 1;
    }

    public void open(int row, int col) {
        if (!validateIndices(row, col))
            throw new IllegalArgumentException();
        if (id[convert2DTo1D(row, col)] != 1) {
            id[convert2DTo1D(row, col)] = 1;
            if (isTopRow(row, col)) {
                topMatching(row, col);
            }
            if (isBottomRow(row, col)) {
                bottomMatching(row, col);
            }
            matchingGeneral(row, col);
        }
    }

    public boolean isOpen(int row, int col) {
        if (!validateIndices(row, col))
            throw new IllegalArgumentException();
        if (id[convert2DTo1D(row, col)] == 1)
            return true;
        return false;
    }

    public boolean isFull(int row, int col) {
        if (!validateIndices(row, col))
            throw new IllegalArgumentException();
        if (weightedQuickUnionUF.find(convert2DTo1D(row, col)) == weightedQuickUnionUF.find(0))
            return true;
        return false;
    }

    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 1; i < (squareLength * squareLength) + 1; i++) {
            if (id[i] == 1)
                count++;
        }
        return count;
    }

    private void topMatching(int row, int col) {
        weightedQuickUnionUF.union(0, convert2DTo1D(row, col));
    }

    private void bottomMatching(int row, int col) {
        weightedQuickUnionUF.union((squareLength * squareLength) + 1, convert2DTo1D(row, col));
    }

    private void matchingGeneral(int row, int col) {
        // look top,left,right and below for possible open sites and perform union
        if (validateIndices(row - 1, col))
            weightedQuickUnionUF.union(convert2DTo1D(row - 1, col), convert2DTo1D(row, col));
        if (validateIndices(row, col - 1))
            weightedQuickUnionUF.union(convert2DTo1D(row, col - 1), convert2DTo1D(row, col));
        if (validateIndices(row, col + 1))
            weightedQuickUnionUF.union(convert2DTo1D(row, col + 1), convert2DTo1D(row, col));
        if (validateIndices(row, col - 1))
            weightedQuickUnionUF.union(convert2DTo1D(row, col - 1), convert2DTo1D(row, col));
        // are diagonals to be considered?
    }

    private boolean validateIndices(int row, int col) {
        if ((row < 1) || (row > squareLength) || (col < 1) || (col > squareLength)) {
            return false;
        }
        return true;
    }

    private int convert2DTo1D(int row, int col) {
        return ((row - 1) * squareLength + (col - 1) + 1);
    }

    private boolean isTopRow(int row, int col) {
        if (row == 1)
            return true;
        return false;
    }

    private boolean isBottomRow(int row, int col) {
        if (col == squareLength)
            return true;
        return false;
    }

    public static void main(String[] args) {

    }
}
