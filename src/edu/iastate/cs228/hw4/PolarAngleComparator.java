package edu.iastate.cs228.hw4;

/**
 *  
 * @author Thomas Wesolowski
 *
 */

import edu.iastate.cs228.hw4.Point;

import java.util.Comparator;

/**
 * 
 * This class compares two points p1 and p2 by polar angle with respect to a reference point.  
 * It is known that the reference point is not above either p1 or p2, and in the case that
 * either or both of p1 and p2 have the same y-coordinate, not to their right. 
 *
 */
public class PolarAngleComparator implements Comparator<edu.iastate.cs228.hw4.Point>
{
	private edu.iastate.cs228.hw4.Point referencePoint;
	private boolean flag;

	/**
	 *
	 * @param p reference point
	 */
	public PolarAngleComparator(edu.iastate.cs228.hw4.Point p, boolean flag)
	{
		referencePoint = p;
		this.flag = flag;
	}

	/**
	 * Use cross product and dot product to implement this method.  Do not take square roots
	 * or use trigonometric functions. See the PowerPoint notes on how to carry out cross and
	 * dot products. Calls private methods crossProduct() and dotProduct().
	 *
	 * Call comparePolarAngle() and compareDistance().
	 *
	 * @param p1
	 * @param p2
	 * @return  0 if p1 and p2 are the same point
	 *         -1 otherwise, if one of the following three conditions holds:
	 *                a) p1 and referencePoint are the same point (hence p2 is a different point);
	 *                b) neither p1 nor p2 equals referencePoint, and the polar angle of
	 *                   p1 with respect to referencePoint is less than that of p2;
	 *                c) neither p1 nor p2 equals referencePoint, p1 and p2 have the same polar
	 *                   angle w.e.t. referencePoint, and p1 is closer to referencePoint than p2.
	 *
	 *          1  otherwise.
	 *
	 */
	public int compare(edu.iastate.cs228.hw4.Point p1, edu.iastate.cs228.hw4.Point p2)
	{
		int angleRes = comparePolarAngle(p1,p2);
        int distRes = compareDistance(p1,p2);

        if(angleRes!= 0) return angleRes;
        else {
            if(flag)return distRes;
            else return -distRes;
        }
    }


	/**
	 * Compare the polar angles of two points p1 and p2 with respect to referencePoint.  Use
	 * cross products.  Do not use trigonometric functions.
	 *
	 * Ought to be private but made public for testing purpose.
	 *
	 * @param p1
	 * @param p2
	 * @return    0  if p1 and p2 have the same polar angle.
	 * 			 -1  if p1 equals referencePoint or its polar angle with respect to referencePoint
	 *               is less than that of p2.
	 *            1  otherwise.
	 */
    public int comparePolarAngle(edu.iastate.cs228.hw4.Point p1, edu.iastate.cs228.hw4.Point p2)
    {
        if (p1.equals(referencePoint) && p2.equals(referencePoint)) return 0;
        if (p1.equals(referencePoint)) return -1;
        if (p2.equals(referencePoint)) return 1;

        int crossp = crossProduct(p1,p2);

        if (crossp==0)return 0;
        else if (crossp>0)return -1;
        else return 1;
    }


    /**
     * Compare the distances of two points p1 and p2 to referencePoint.  Use dot products.
     * Do not take square roots.
     *
     * Ought to be private but made public for testing purpose.
     *
     * @param p1
     * @param p2
     * @return    0   if p1 and p2 are equidistant to referencePoint
     * 			 -1   if p1 is closer to referencePoint
     *            1   otherwise (i.e., if p2 is closer to referencePoint)
     */
    public int compareDistance(edu.iastate.cs228.hw4.Point p1, edu.iastate.cs228.hw4.Point p2)
    {
        /*
        *   compare using dot products, p1(dot)p1 and p2(dot)p2 and compare the resulting values
        * without taking square root, as magnitude of p1 vector is equal to sqrt(p1(dot)p1)
        */
        int p1Res = dotProduct(p1,p1);
        int p2Res = dotProduct(p2,p2);

        if (p1Res == p2Res)return 0;
        else if (p1Res < p2Res)return -1;
        else return 1;
    }


    /**
     *
     * @param p1
     * @param p2
     * @return cross product of two vectors p1 - referencePoint and p2 - referencePoint
     */
    private int crossProduct(edu.iastate.cs228.hw4.Point p1, edu.iastate.cs228.hw4.Point p2)
    {
    	// TODO
		int product1 = (p1.getX()-referencePoint.getX()) * (p2.getY()-referencePoint.getY());
		int product2 = (p2.getX()-referencePoint.getX()) * (p1.getY()-referencePoint.getY());
    	return product1-product2;
    }

    /**
     *
     * @param p1
     * @param p2
     * @return dot product of two vectors p1 - referencePoint and p2 - referencePoint
     */
    private int dotProduct(edu.iastate.cs228.hw4.Point p1, Point p2)
    {
		int xProduct = (p1.getX()-referencePoint.getX()) * (p2.getX()-referencePoint.getX());
		int yProduct = (p1.getY()-referencePoint.getY()) * (p2.getY()-referencePoint.getY());

		return xProduct+yProduct;
    }
}
