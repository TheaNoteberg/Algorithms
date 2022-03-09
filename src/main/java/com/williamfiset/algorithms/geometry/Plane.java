/*
* A class to represent a plane in 3D space.
*
* @author Edvard Aldor, Jonathan Hedin, Oscar Ingels, Thea Nöteberg
*/

package com.williamfiset.algorithms.geometry;

import static java.lang.Math.*;

import java.util.Arrays;

public class Plane {
    // The internal representation of a plane is
    // in Hessian normal form which is: ax + by + cz + d = 0
    private double a, b, c, d;
    
    public Plane(double a, double b, double c, double d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        checkInput();
    }

    //Constructs a plane from 3 points.
    public Plane(Point point1, Point point2, Point point3) {
        double a_bX  = point1.getX() - point2.getX();
        double a_bY  = point1.getY() - point2.getY();
        double a_bZ  = point1.getZ() - point2.getZ();
        double a_cX  = point1.getX() - point3.getX();
        double a_cY  = point1.getY() - point3.getY();
        double a_cZ  = point1.getZ() - point3.getZ();

        Vector a_b = new Vector(a_bX, a_bY, a_bZ);
        Vector a_c = new Vector(a_cX, a_cY, a_cZ);

        Vector normalVector = Vector.crossProduct(a_b, a_c);
        
        this.a = normalVector.getValue(0);
        this.b = normalVector.getValue(1);
        this.c = normalVector.getValue(2);
        this.d = -(a*point1.getX() + b*point1.getY() + c*point1.getZ());
        checkInput();
    }
    //Construct plane from three points in doubles
    public Plane(double[] point1, double[] point2, double[] point3) {
        double a_bX  = point1[0] - point2[0];
        double a_bY  = point1[1] - point2[1];
        double a_bZ  = point1[2] - point2[2];
        double a_cX  = point1[0] - point3[0];
        double a_cY  = point1[1] - point3[1];
        double a_cZ  = point1[2] - point3[2];
        Vector a_b = new Vector(a_bX, a_bY, a_bZ);
        Vector a_c = new Vector(a_cX, a_cY, a_cZ);

        Vector normalVector = Vector.crossProduct(a_b, a_c);

        this.a = normalVector.coordinates[0];
        this.b = normalVector.coordinates[1];
        this.c = normalVector.coordinates[2];
        this.d = -(this.a*point1[0] + this.b*point1[1] + this.c*point1[2]);
        checkInput();
    }
    
    //Constructs a plane from 2 lines
    public Plane(Line line1, Line line2){

        Vector vector1 = line1.getVector();
        Vector vector2 = line2.getVector();

        Vector normalVector = Vector.crossProduct(vector1, vector2);
        
        this.a = normalVector.coordinates[0];
        this.b = normalVector.coordinates[1];
        this.c = normalVector.coordinates[2];
        double[] point = line1.getPoint().coordinates;
        this.d = -(this.a*point[0] + this.b*point[1] + this.c*point[2]);
        checkInput();
    }

    public Vector getNormalVector() {
        double pyth = sqrt(pow(a,2) + pow(b,2) + pow(c,2));
        double [] norm = {a/pyth,b/pyth,c/pyth};
        return new Vector(norm);
    }

    // Checks so that input is not invalid
    private void checkInput() {
        double[] abcdOne = {a,b,c};

        double[] zeroArr = {0,0,0};

        if (Arrays.equals(abcdOne, zeroArr)) {
         throw new ArithmeticException("there is no plane with only zero values");}
    }

    public double[] getABCD() {
        return new double[] {a,b,c,d}; 
       
    }
    public String toString() {
        String a_val=Double.toString(this.a);
        String b_val=Double.toString(this.b);
        String c_val=Double.toString(this.c);
        String d_val=Double.toString(this.d);
        return  a_val + "x + " +  b_val + "y + " + c_val + "z + " + d_val + "= 0";
    }

    @Override
    public boolean equals(Object other) {
        //Possible to compare two planes to each other.
        if (!(other instanceof Plane)) return false;
        if (other == this) return true;
        Plane plane = (Plane) other;
        double[] abcdOther = plane.getABCD();
        double[] abcdThis = new double[] {a,b,c,d};
        if (Arrays.equals(abcdThis, abcdOther)) return true;
        else return false;
    }
}   
