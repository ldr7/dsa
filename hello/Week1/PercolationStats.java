package Week1;/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private Percolation percolation;
    private double[] threshold;

    public PercolationStats(int n, int trials) {
        if (!validateIndices(n, trials))
            throw new IllegalArgumentException();
        simulation(n, trials);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, trials);
        System.out.println("mean = " + percolationStats.mean());
        System.out.println("stddev = " + percolationStats.stddev());
        System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", "
                                   + percolationStats.confidenceHi() + "]");
    }

    private void simulation(int n, int trials) {
        if (!validateIndices(n, trials))
            throw new IllegalArgumentException();
        double totalSites = n * n;
        this.threshold = new double[trials];
        for (int i = 0; i < trials; i++) {
            this.percolation = new Percolation(n);
            while (!this.percolation.percolates()) {
                int[] blockedSite;
                blockedSite = randomBlockedSite(n);
                this.percolation.open(blockedSite[0], blockedSite[1]);
            }
            this.threshold[i] = this.percolation.numberOfOpenSites() / totalSites;
        }
    }

    private int[] randomBlockedSite(int n) {
        int randomNumberRow = StdRandom.uniform(1, n + 1);
        int randomNumberColumn = StdRandom.uniform(1, n + 1);
        while (this.percolation.isOpen(randomNumberRow, randomNumberColumn)) {
            randomNumberRow = StdRandom.uniform(1, n);
            randomNumberColumn = StdRandom.uniform(1, n);
        }
        int[] blockedCoordinates = new int[2];
        blockedCoordinates[0] = randomNumberRow;
        blockedCoordinates[1] = randomNumberColumn;
        return blockedCoordinates;
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.threshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.threshold);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double low = mean() - ((1.96 * stddev()) / Math.sqrt(this.threshold.length));
        return low;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double high = mean() + ((1.96 * stddev()) / Math.sqrt(this.threshold.length));
        return high;
    }

    private boolean validateIndices(int n, int m) {
        if ((n <= 0) || (m <= 0)) {
            return false;
        }
        return true;
    }
}
