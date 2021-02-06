/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private Percolation[] percolations;
    private int[] threshold;

    public PercolationStats(int n, int trials) {
        if (!validateIndices(n, trials))
            throw new IllegalArgumentException();
        this.percolations = new Percolation[trials];
        for (int i = 0; i < trials; i++) {
            this.percolations[i] = new Percolation(n);
        }
        simulation(n, trials);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, T);
        System.out.println(percolationStats.mean());
        System.out.println(percolationStats.stddev());
        System.out.println(percolationStats.confidenceLo());
        System.out.println(percolationStats.confidenceHi());
    }

    private void simulation(int n, int T) {
        this.threshold = new int[T];
        intialize(n, T);
        for (int i = 0; i < T; i++) {
            int numberOfOpenSites = percolations[i].numberOfOpenSites();
            while (!percolations[i].percolates()) {
                int[] blockedSite;
                blockedSite = randomBlockedSite(i, n);
                percolations[i].open(blockedSite[0], blockedSite[1]);
                numberOfOpenSites += 1;
            }
            this.threshold[i] = numberOfOpenSites;
        }
    }

    private int[] randomBlockedSite(int index, int n) {
        int randomNumberRow = StdRandom.uniform(1, n);
        int randomNumberColumn = StdRandom.uniform(1, n);
        while (this.percolations[index].isOpen(randomNumberRow, randomNumberColumn)) {
            randomNumberRow = StdRandom.uniform(1, n);
            randomNumberColumn = StdRandom.uniform(1, n);
        }
        int[] blockedCoordinates = new int[2];
        blockedCoordinates[0] = randomNumberRow;
        blockedCoordinates[1] = randomNumberColumn;
        return blockedCoordinates;
    }

    private void intialize(int n, int trials) {
        if (!validateIndices(n, trials))
            throw new IllegalArgumentException();
        this.percolations = new Percolation[n];
        for (int i = 0; i < trials; i++) {
            this.percolations[i] = new Percolation(n);
        }
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
