package com.williamfiset.algorithms.geometry;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
public class PlaneTest {

    @Test
        public void testGetNormalVector(){
            //Vector expectedOutput = new Vector(1, 1, 1);
            double[] expectedOutput = {-1, 0, 0};
            Plane plane = new Plane(new double[]{1,2,3}, new  double[]{1,1,1}, new double[]{1,3,6});
            double[] result = plane.getNormalVector();
            for (int i = 0; i< expectedOutput.length; i++) {
                assertEquals(expectedOutput[i], result[i]);
            }
        }



}
