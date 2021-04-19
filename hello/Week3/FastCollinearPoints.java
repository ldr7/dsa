package Week3;/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private int count;
    private Point[] pointsSet;
    private int pointCount;

    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points
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
        return count;
    }

    public LineSegment[] segments() {
        // the line segments
        List<Point> blacklistPoints = new ArrayList<>();
        Stack<LineSegment> lineSegments = new Stack<>();
        int collinearCount = 0;
        for (int i = 0; i < pointCount; i++) {
            Point p = pointsSet[i];
            if (!blacklistPoints.contains(p)) {
                double[] slopeToPoint = new double[pointCount];
                Point[] pointBySlopes = new Point[pointCount];
                pointBySlopes = pointsSet.clone();
                for (int j = 0; j < pointCount; j++) {
                    double slope = p.slopeTo(pointsSet[j]);
                    slopeToPoint[j] = slope;
                }
                Arrays.sort(pointBySlopes, p.slopeOrder());
                Arrays.sort(slopeToPoint);
                Result resultCollinear = calcSegment(slopeToPoint);
                if (resultCollinear != null) {
                    Point[] shortlistedPoints = new Point[
                            resultCollinear.endIndex - resultCollinear.orignalIndex + 2];
                    shortlistedPoints[0] = p;
                    int index = 1;
                    for (int k = resultCollinear.orignalIndex; k <= resultCollinear.endIndex; k++) {
                        shortlistedPoints[index] = pointBySlopes[k];
                        blacklistPoints.add(pointBySlopes[k]);
                        index++;
                    }
                    Point[] minMax = findMinMax(shortlistedPoints);
                    collinearCount++;
                    lineSegments.push(new LineSegment(minMax[0], minMax[1]));
                }
            }
        }
        count = collinearCount;
        return stackToArray(lineSegments);
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

    private Point[] findMinMax(Point[] rankPoints) {
        Point min;
        Point max;
        min = rankPoints[0];
        max = rankPoints[0];
        for (int i = 0; i < rankPoints.length; i++) {
            if (rankPoints[i].compareTo(min) < 0)
                min = rankPoints[i];
            if (rankPoints[i].compareTo(max) > 0)
                max = rankPoints[i];
        }
        Point[] minMax = new Point[2];
        minMax[0] = min;
        minMax[1] = max;
        return minMax;
    }

    private Result calcSegment(double[] slopeToPoint) {
        Result result = null;
        int countCollinear = 0;
        for (int i = 1; i < slopeToPoint.length - 1; i++) {
            if (slopeToPoint[i] == slopeToPoint[i - 1]) {
                countCollinear++;
            }
            else {
                if (countCollinear > 1) {
                    result = new Result(i - 1, i - 1 - countCollinear);
                    return result;
                }
                countCollinear = 0;
            }
        }
        return result;
    }

    private class Result {
        private int endIndex;
        private int orignalIndex;

        private Result(int eIndex, int index) {
            this.endIndex = eIndex;
            this.orignalIndex = index;
        }
    }

    public static void main(String[] args) {
        // read the n points from a file
        /*Week3.Point[] points = new Week3.Point[4];
        points[0] = new Week3.Point(145, 9566);
        points[1] = new Week3.Point(1325, 11538);
        points[2] = new Week3.Point(6025, 10518);
        points[3] = new Week3.Point(8965, 10994);
        Week3.FastCollinearPoints fastCollinearPoints = new Week3.FastCollinearPoints(points);
        System.out.println(fastCollinearPoints.count);*/
    }

}
