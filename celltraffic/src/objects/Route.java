/*
 * Created on 15.10.2003 $Id: Route.java,v 1.3 2003/10/24 15:39:32 moleman Exp $
 */
package objects;

import java.awt.Point;

/**
 * @author Jonas Sprenger
 *  
 */
public abstract class Route {
	double p_dec = 0.2;
	/**
	 * reference to the next route object
	 */
	Route nextRoute;

	Object road[][];

	public Route(int length) {
		nextRoute = null;
		road = new Object[1][length];
	}

	public Route() {
		nextRoute = null;
		road = new Object[1][100];
	}

	/**
	 * Returns, wether the road piece at the given coordnates is free so that a
	 * vehicle may move to that coordinates
	 * 
	 * @param x
	 *            x-coordinate of the field to test
	 * @param y
	 *            y-coordinate of the field to test
	 * @return true if the field at the coordnate is empty
	 */
	public boolean isFree(int x, int y) {
		return false;
	}

	/**
	 * convenience function. Behaves exactly like the function above.
	 * 
	 * @param p
	 *            coordinates of the field to test
	 * @return true if the field at the coordnate is empty
	 * @see #isFree(int,int)
	 */
	public boolean isFree(Point p) {
		return isFree(p.x, p.y);
	}

	/**
	 * accelerates the given car one speed unit
	 * 
	 * @param a
	 *            the car to accelereate
	 */
	protected void accelerate(Vehicle a) {
		if (a.getVelocity() < a.getMaxVelocity()) {
			a.setVelocity(a.getVelocity() + 1);
		}
	}

	/**
	 * decelerates the given car depending on the gap between the current
	 * vehicle and the vehicle ahead
	 * 
	 * @param a
	 *            TODO Fill with logic
	 */
	protected void decelerate(Vehicle a) {

	}

	/**
	 * Stochastic behavior. Slow down given vehicle with probability _p_dec
	 * 
	 * @param a
	 *            the car to slow down
	 */
	protected void stochasticDecelerate(Vehicle a) {
		if ((a.getVelocity() > 0) && (Math.random() <= p_dec))
			a.setVelocity(a.getVelocity() - 1);
	}
	/**
	 * TODO advance
	 * 
	 * @author tfranz
	 */
	protected void advance(Vehicle a) {
		// a.setX(a.getX() + a.getVelocity());
	}
	/**
	 * gets the gap between the vehicle at position p and the vehicle in front
	 * of it
	 * 
	 * @param p
	 *            position of current vehicle
	 * @return gap
	 */
	protected int getGap(Point p) {
		return 0;
	}

}
