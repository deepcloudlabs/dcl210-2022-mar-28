package org.godel.nio;

/**
 *
 * @author binnur kurt
 */
public class quiz {
    public static void main(String[] args) {
           double x= 4.35;
           int y= (int)(x*100);
           double z= 100*x;
           System.err.println("y= "+y);
           System.err.println("z= "+z);
           float m= 2_000_000_000;
           System.err.println("m= "+m);
           m=m+50;
           System.err.println("m= "+m);
    }
}
