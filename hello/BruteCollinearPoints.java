/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class BruteCollinearPoints {
    private int count;
    private Point[] pointsSet;

    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        if (points == null)
            throw new IllegalArgumentException();
        count = 0;
        pointsSet = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();
            pointsSet[i] = points[i];
        }
    }

    public int numberOfSegments() {
        // the number of line segments
        return count;
    }

    public LineSegment[] segments() {
        // the line segments
        LineSegment[] lineSegments = new LineSegment[pointsSet.length];
        for (int i = 0; i < pointsSet.length; i++) {
            for (int j = i + 1; j < pointsSet.length; j++) {
                for (int k = j + 1; k < pointsSet.length; k++) {
                    for (int m = k + 1; m < pointsSet.length; m++) {
                        if ((pointsSet[i].slopeTo(pointsSet[j]) == pointsSet[i]
                                .slopeTo(pointsSet[k])) && (pointsSet[i].slopeTo(pointsSet[k])
                                == pointsSet[i].slopeTo(pointsSet[m]))) {
                            lineSegments[count++] = new LineSegment(pointsSet[i], pointsSet[m]);
                        }
                    }
                }
            }
        }

        return derefArrayExcess(lineSegments, count, pointsSet.length);
    }

    private LineSegment[] derefArrayExcess(LineSegment[] lineSegments, int countDup, int total) {
        for (int i = countDup; i < total; i++) {
            lineSegments[i] = null;
        }
        return lineSegments;
    }

    public static void main(String[] args) {
    }
}
