/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class FastCollinearPoints {
    private int count;
    private Point[] pointsSet;

    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points
        if (points == null)
            throw new IllegalArgumentException();
        count = 0;
        pointsSet = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();
            if (containsElement(points[i], pointsSet))
                throw new IllegalArgumentException();
            pointsSet[i] = points[i];
        }
    }

    private boolean containsElement(Point p, Point[] pointArray) {
        for (int i = 0; i < pointArray.length; i++) {
            if (p.slopeTo(pointArray[i]) == Double.NEGATIVE_INFINITY)
                return true;
        }
        return false;
    }

    public int numberOfSegments() {
        // the number of line segments
        return count;
    }

    public LineSegment[] segments() {
        // the line segments
        LineSegment[] lineSegments = new LineSegment[pointsSet.length];
        int collinearCount = 0;
        for (int i = 0; i < pointsSet.length; i++) {
            Point p = pointsSet[i];
            double[] slopeToPoint = new double[pointsSet.length - 1];
            double[] aux = new double[pointsSet.length - 1];
            double[] copy = new double[pointsSet.length - 1];
            for (int j = 0; j < pointsSet.length; j++) {
                if (j != i) {
                    double slope = p.slopeTo(pointsSet[j]);
                    slopeToPoint[j] = slope;
                    aux[j] = slope;
                    copy[j] = slope;
                }
            }
            mergeSort(slopeToPoint, aux, 0, pointsSet.length - 2);
            int collinearIndex = calcSegment(slopeToPoint);
            if (collinearIndex != -1)
                lineSegments[collinearCount++] = new LineSegment(p, pointsSet[recoverOrgPoint(copy,
                                                                                              slopeToPoint[collinearIndex])]);
        }
        count = collinearCount;
        return lineSegments;
    }

    private int recoverOrgPoint(double[] slopeCopy, double slope) {
        int index = -1;
        for (int i = 0; i < slopeCopy.length; i++) {
            if (slope == slopeCopy[i])
                index = i;
        }
        return index;
    }

    private static void mergeSort(double[] slopeToPoint, double[] aux, int low, int high) {
        if (high <= low)
            return;
        int mid = low + (high - low) / 2;
        mergeSort(slopeToPoint, aux, low, mid);
        mergeSort(slopeToPoint, aux, mid + 1, high);
        if (slopeToPoint[mid + 1] > slopeToPoint[mid])
            return;
        merge(slopeToPoint, aux, low, mid, high);
    }

    private static void merge(double[] slopeToPoint, double[] aux, int low, int mid, int high) {
        for (int k = 0; k < slopeToPoint.length; k++) {
            aux[k] = slopeToPoint[k];
        }
        int i = low;
        int j = mid + 1;
        for (int k = 0; k <= high; k++) {
            if (i > mid)
                slopeToPoint[k] = slopeToPoint[j++];
            else if (j > high)
                slopeToPoint[k] = slopeToPoint[i++];
            else if (aux[i] > aux[j])
                slopeToPoint[k] = aux[j++];
            else
                slopeToPoint[k] = aux[i++];
        }
    }

    private int calcSegment(double[] slopeToPoint) {
        int collinearIndex = -1;
        for (int i = 1; i < slopeToPoint.length; i++) {
            if (slopeToPoint[i] == slopeToPoint[i - 1]) {
                int start = i - 1;
                int j = i;
                int collinearcount = 2;
                while (slopeToPoint[j + 1] == slopeToPoint[j]) {
                    collinearcount += 1;
                    j += 1;
                }
                if (collinearcount >= 3) {
                    collinearIndex = start;
                    break;
                }
            }
        }
        return collinearIndex;
    }

    public static void main(String[] args) {
        // read the n points from a file
        /*In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();*/
    }
}
