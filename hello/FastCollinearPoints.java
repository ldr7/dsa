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
        
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
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
        StdDraw.show();
    }
}
