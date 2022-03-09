/**
 * A custom infinite line class that provides additional functionality beyond Java's Line2D.
 *
 * @author Thomas Finn Lidbetter
 */
package com.williamfiset.algorithms.geometry;

import static java.lang.Math.*;

import java.awt.geom.Point2D;

public class Line {

  // For a 2D line, the internal representation of a line is
  // in general form which is: ax + by = c

  // For all other dimensions it's defined by a vector and a point
  private double a, b, c;
  private Vector vector;
  private Point point;
  
  // A very same epsilon value used as a threshold for double equality
  private static final double EPS = 0.0000001;

  // Construct a line given a line segment
  public Line(Point2D p1, Point2D p2) {
    this(p1.getX(), p1.getY(), p2.getX(), p2.getY());
  }

  // Construct a line given a line segment as (x1, y1), (x2, y2)
  public Line(double x1, double y1, double x2, double y2) {
    a = y1 - y2;
    b = x2 - x1;
    c = x2 * y1 - x1 * y2;
    normalise();
  }
  

  //creates a 3D line from two points. 
  public Line(double x1, double y1, double z1, double x2, double y2, double z2) {
    Point point1 = new Point(x1, y1, z1);
    Point point2 = new Point(x2, y2, z2);
    this.vector = vector.makeTwoPointsVector(point1, point2);
    this.point = point1;
  }

  // Constructs a N-Dimensional line from a vector and point (parametric form).
  public Line(Vector vector, Point point){
    if (vector.getDim() == point.getDim()){
      this.vector = vector;
      this.point = point;
    }
    else throw new ArithmeticException("The point and vector must be in the same dimension");
    
  }

  // Constructs a N-Dimensional line from two vectors (parametric form).
  public Line(Vector posVector, Vector dirVector){
    if (posVector.getDim() == dirVector.getDim()){
      this.vector = posVector;
      this.point = dirVector;
    }
    else throw new ArithmeticException("The vectors must be in the same dimension");
    
  }

  public Vector getVector(){
    return vector;
  }

  public Point getPoint(){
    return point;
  }

  // Constructs a line from a slope and a point
  public static Line slopePointToLine(double slope, Point2D pt) {
    Point2D p2 = null;
    if (slope == Double.POSITIVE_INFINITY || slope == Double.NEGATIVE_INFINITY) {
      p2 = new Point2D.Double(pt.getX(), pt.getY() + 1);
    } else {
      p2 = new Point2D.Double(pt.getX() + 1, pt.getY() + slope);
    }
    return new Line(pt, p2);
  }

  // Normalize the line in general form
  public void normalise() {
    if (abs(b) < EPS) {
      c /= a;
      a = 1;
      b = 0;
    } else {
      a = (abs(a) < EPS) ? 0 : a / b;
      c /= b;
      b = 1;
    }
  }

  // Given a line segment this method finds the line which goes
  // through the middle of the segment and cuts it in half.
  public static Line getPerpBisector(double x1, double y1, double x2, double y2) {

    // Find middle point of segment
    Point2D middle = new Point2D.Double((x1 + x2) / 2.0, (y1 + y2) / 2.0);

    // The slope perpendicular to (y2-y1)/(x2-x1) is the negative
    // reciprocal or -(x2-x1)/(y2-y1) = (x1-x2)/(y2-y1)
    double perpSlope = (x1 - x2) / (y2 - y1);

    if (abs(y2 - y1) < EPS) perpSlope = Double.POSITIVE_INFINITY;
    else if (abs(x1 - x2) < EPS) perpSlope = 0;

    return slopePointToLine(perpSlope, middle);
  }

  // Finds the intersection point of two infinite lines
  // and returns null if line1 and line2 are parallel
  public static Point2D intersection(Line l1, Line l2) {

    l1.normalise();
    l2.normalise();

    // Lines are parallel
    if (abs(l1.a - l2.a) < EPS && abs(l1.b - l2.b) < EPS) return null;

    double x = Double.NaN, y = Double.NaN;
    if (abs(l1.b) < EPS) {
      x = l1.c / l1.a;
      y = (l2.c - l2.a * x) / l2.b;
    } else if (abs(l2.b) < EPS) {
      x = l2.c / l2.a;
      y = (l1.c - l1.a * x) / l1.b;
    } else if (abs(l1.a) < EPS) {
      y = l1.c / l1.b;
      x = (l2.c - l2.b * y) / l2.a;
    } else if (abs(l2.a) < EPS) {
      y = l2.c / l2.b;
      x = (l1.c - l1.b * y) / l1.a;
    } else {
      x = (l1.c - l2.c) / (l1.a - l2.a);
      y = (l1.c - l1.a * x) / l1.b;
    }

    return new Point2D.Double(x, y);
  }
  
  public boolean pointOnLine(Point point){
    Vector comparizonVector = vector.makeTwoPointsVector(point, this.point);
    comparizonVector.getNormalized();
    this.vector.getNormalized();
    Vector cross_value = Vector.crossProduct(comparizonVector, this.vector);

    for(int i = 0; i<comparizonVector.getDim(); i++){
      if(cross_value.getValue(i) != 0) return false;
    }
    return true;

  }
  
  // Get a printable representation of a this Line  
  @Override
  public String toString() {
    return a + "x + " + b + "y = " + c;
  }

  @Override
  public boolean equals(Object other){
    //Makes it possible to compare two lines to each other.
    if (!(other instanceof Line)) return false;
    if (other == this) return true;
    Line line = (Line) other;
    Vector thisNorm = this.getVector().getNormalized();
    Vector otherNorm = line.getVector().getNormalized();
    Vector crossVal = Vector.crossProduct(thisNorm, otherNorm);
    for(int i = 0; i<crossVal.getDim(); i++){
      if(crossVal.getValue(i) != 0) return false;
    }
    return pointOnLine(line.getPoint());
  }
  
}
