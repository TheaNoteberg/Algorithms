package com.williamfiset.algorithms.geometry;

/*
A simple point in n-dimmensional space.
*/

public class Point {
    public double[] coordinates;
    private int dim;

    // Define a point with arbitrary dimensionality
    public Point(double[] coordinates) {
        this.coordinates = coordinates;
        this.dim = coordinates.length;
    }

    // Define a 2D point
    public Point(double x, double y) {
        coordinates = new double[2];
        this.coordinates[0] = x;
        this.coordinates[1] = y;
        this.dim = 2;
    }

    // Define a 3D point
    public Point(double x, double y, double z) {
        coordinates = new double[3];
        this.coordinates[0] = x;
        this.coordinates[1] = y;
        this.coordinates[2] = z;
        this.dim = 3;
    }

    public int getDim(){
        return coordinates.length;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public double getX() {
        if (dim >= 1) return coordinates[0];
        else throw new ArithmeticException("Point has 0 dimensions");
    }

    public double getY() {
        if (dim >= 2) return coordinates[1];
        else throw new ArithmeticException("Point does not have 2 dimensions");
    }

    public double getZ() {
        if (dim >= 3) return coordinates[2];
        else throw new ArithmeticException("Point does not have 3 dimensions");
    }
    public double getCoordinat(int x){
        return coordinates[x];
    }
    //Add for loop functionality for larger dimensions.
}
