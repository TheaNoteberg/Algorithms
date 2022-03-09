/*
* A class to represent an n-dimensional vector.
*
* @author Edvard Aldor, Jonathan Hedin, Oscar Ingels, Thea Nöteberg
*/

package com.williamfiset.algorithms.geometry;

import static java.lang.Math.*;

public class Vector extends Point {

    // Define a vector using coordinate array
    public Vector(double[] coordinates) {
        super(coordinates);
    }

    // Define a 2D vector using x and y
    public Vector(double x, double y) {
        super(x, y);
    }

    // Define a 3D vector using x, y and z
    public Vector(double x, double y, double z) {
        super(x, y, z);
    }

    // Get the vector norm
    public double getNorm() {
        //Get the norm from a vector
        double pyth = 0;
        for (double v : coordinates) pyth += pow(v,2);
        return sqrt(pyth);
    }

    // Generate a normalized vector
    public Vector getNormalized() {
        //Normalizes the vector
        double norm = getNorm();
        double[] normalizedCoord = new double[coordinates.length];
        for (int i = 0; i < normalizedCoord.length; i++) {
            normalizedCoord[i] = coordinates[i] / norm;
        }
        return new Vector(normalizedCoord);
    }

    // Return a vector that spanns two points.
    public Vector makeTwoPointsVector(Point point1, Point point2){
        if (point1.coordinates.length != point2.coordinates.length) throw new ArithmeticException("Points must be in the same dimension");
        
        double[] vector = new double[point1.coordinates.length];
        for (int i = 0; i < point1.coordinates.length; i++) {
            vector[i] = point1.getCoordinat(i)-point2.getCoordinat(i);
        }
        
        return new Vector(vector);
    }

    //Calculates the orthogonal vector to two input vectors with cross product
    public static Vector crossProduct(Vector vector1, Vector vector2){
        if( vector1.getDim() != 3 || vector2.getDim() != 3) throw new ArithmeticException("The vectors need to be in 3D");
        
        double x = vector1.getValue(1)*vector2.getValue(2) - vector1.getValue(2)*vector2.getValue(1);
        double y = -(vector1.getValue(0)*vector2.getValue(2) - vector1.getValue(2)*vector2.getValue(0));
        double z = vector1.getValue(0)*vector2.getValue(1) - vector1.getValue(1)*vector2.getValue(0);
        return new Vector(x, y, z);
    }

    public double getValue(int index) {
        return coordinates[index];
    }

    public int getDim(){
        return coordinates.length;
    }
}
