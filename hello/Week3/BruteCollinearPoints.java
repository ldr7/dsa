package Week3;/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Stack;

public class BruteCollinearPoints {
    private int count;
    private Point[] pointsSet;
    private int pointCount;

    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        if (points == null)
            throw new IllegalArgumentException();
        count = 0;
        pointCount = 0;
        Point[] pointsSetTemp = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();
            if (containsElement(points[i], pointsSetTemp))
                throw new IllegalArgumentException();
            pointsSetTemp[i] = points[i];
            pointCount++;
        }
        pointsSet = pointsSetTemp.clone();
    }

    private boolean containsElement(Point p, Point[] pointArray) {
        if (pointCount == 0)
            return false;
        for (int i = 0; i < pointCount; i++) {
            if (p.slopeTo(pointArray[i]) == Double.NEGATIVE_INFINITY)
                return true;
        }
        return false;
    }

    public int numberOfSegments() {
        // the number of line segments
        if (count != 0)
            return count;
        else {
            this.segments();
            return count;
        }
    }

    public LineSegment[] segments() {
        // the line segments
        int tempCount = 0;
        Stack<LineSegment> lineSegmentStack = new Stack<>();
        for (int i = 0; i < pointsSet.length; i++) {
            for (int j = i + 1; j < pointsSet.length; j++) {
                for (int k = j + 1; k < pointsSet.length; k++) {
                    for (int m = k + 1; m < pointsSet.length; m++) {
                        if ((pointsSet[i].slopeTo(pointsSet[j]) == pointsSet[i]
                                .slopeTo(pointsSet[k])) && (pointsSet[i].slopeTo(pointsSet[k])
                                == pointsSet[i].slopeTo(pointsSet[m]))) {
                            Point[] original = new Point[4];
                            original[0] = pointsSet[i];
                            original[1] = pointsSet[j];
                            original[2] = pointsSet[k];
                            original[3] = pointsSet[m];
                            Point[] minMax = findMinMax(original);
                            lineSegmentStack.push(new LineSegment(minMax[0], minMax[1]));
                            tempCount++;
                        }
                    }
                }
            }
        }
        if (tempCount != count)
            count = tempCount;
        return stackToArray(lineSegmentStack);
    }

    private LineSegment[] stackToArray(Stack<LineSegment> lineSegmentsStack) {
        LineSegment[] lineSegments = new LineSegment[lineSegmentsStack.size()];
        int i = 0;
        while (!lineSegmentsStack.isEmpty()) {
            lineSegments[i] = lineSegmentsStack.pop();
            i++;
        }
        return lineSegments;
    }

    private Point[] findMinMax(Point[] fourPoints) {
        Point min;
        Point max;
        min = fourPoints[0];
        max = fourPoints[0];
        for (int i = 0; i < fourPoints.length; i++) {
            if (fourPoints[i].compareTo(min) < 0)
                min = fourPoints[i];
            if (fourPoints[i].compareTo(max) > 0)
                max = fourPoints[i];
        }
        Point[] minMax = new Point[2];
        minMax[0] = min;
        minMax[1] = max;
        return minMax;
    }

    public static void main(String[] args) {
    }
}
