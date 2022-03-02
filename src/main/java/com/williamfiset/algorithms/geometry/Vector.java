package com.williamfiset.algorithms.geometry;

import static java.lang.Math.*;

public class Vector extends Point {
    public Vector(double[] coordinates) {
        super(coordinates);
    }

    public Vector(double x, double y) {
        super(x, y);
    }

    public Vector(double x, double y, double z) {
        super(x, y, z);
    }

    public double getNorm() {
        double pyth = 0;
        for (double v : coordinates) pyth += pow(v,2);
        return sqrt(pyth);
    }

    public Vector getNormalized() {
        double norm = getNorm();
        double[] normalizedCoord = new double[coordinates.length];
        for (int i = 0; i < normalizedCoord.length; i++) {
            normalizedCoord[i] = coordinates[i] / norm;
        }
        return new Vector(normalizedCoord);
    }
    public Vector makeTwoPointsVector(Point point1, Point point2){
        if (point1.coordinates.length != point2.coordinates.length) throw new ArithmeticException("Points must be in the same dimension");
        double[] vector = new double[point1.coordinates.length];
        for (int i = 0; i < point1.coordinates.length; i++) {
            vector[i] = point1.getCoordinat(i)-point2.getCoordinat(i);
        }
        
        return new Vector(vector);
    }
}
