/*
 * Created on 15.10.2003 $Id: Route.java,v 1.6 2003/10/25 14:41:22 jsprenger
 * Exp $
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

    public Object road[][];

    public Route(int length) {
        nextRoute = null;
        road = new Object[1][length];
        for (int i = 0; i < length-1; i++) {
            road[0][i] = new EmptyVehicle();
        }
    }

    public Route() {
        nextRoute = null;
        road = new Object[1][100];
        for (int i = 0; i < 100; i++) {
            road[0][i] = new EmptyVehicle();
        }
    }
    /**
	 * iterates over the road, starts at the end
	 * 
	 * TODO make multi-dimensional algorithm
	 */
    public void update() {
        for (int i = road[0].length-1; i > 0; i--) {
            if (road[0][i] instanceof Vehicle) {
                accelerate(0, i);
            }
        }
        for (int i = road[0].length-1; i > 0; i--) {
            decelerate(0,i);
        }
        for (int i = road[0].length-1; i > 0; i--) {
            stochasticDecelerate(0, i);
        }
        for (int i = road[0].length-1; i > 0; i--) {
            advance(0, i);
        }
    }

    /**
	 * Returns, wether the road piece at the given coordnates is free so that a
	 * vehicle may move to that coordinates
	 * 
	 * @param x x-coordinate of the field to test
	 * @param y y-coordinate of the field to test
	 * @return true if the field at the coordnate is empty
	 */
    public boolean isFree(int x, int y) {
        if (y > road[x].length) {
            return nextRoute.isFree(x, y);
        } else if (road[x][y] instanceof EmptyVehicle) {
            return true;
        }
        return false;
    }

    /**
	 * convenience function. Behaves exactly like the function above.
	 * 
	 * @param p coordinates of the field to test
	 * @return true if the field at the coordnate is empty
	 * @see #isFree(int,int)
	 */
    public boolean isFree(Point p) {
        return isFree(p.x, p.y);
    }

    /**
	 * accelerates the given car one speed unit
	 * 
	 * @param a the car to accelereate
	 */
    protected void accelerate(int x, int y) {
        Vehicle a = getVehicle(x, y);
        if (a.getVelocity() < a.getMaxVelocity()) {
            a.setVelocity(a.getVelocity() + 1);
        }
    }

    /**
	 * decelerates the given car depending on the gap between the current
	 * vehicle and the vehicle ahead
	 * 
	 * @param x x-coordinate of the vehicle
	 * @param y y-coordinate of the vehicle
	 */
    protected void decelerate(int x, int y) {
        Vehicle a = getVehicle(x, y);
        int gap = getGap(x, y);
        a.setVelocity(gap - 1);
    }

    /**
	 * Stochastic behavior. Slow down given vehicle with probability _p_dec
	 * 
	 * @param x x-coordinate of the vehicle
	 * @param y y-coordinate of the vehicle
	 *  
	 */
    protected void stochasticDecelerate(int x, int y) {
        Vehicle a = getVehicle(x, y);
        if ((a.getVelocity() > 0) && (Math.random() <= p_dec))
            a.setVelocity(a.getVelocity() - 1);
    }
    /**
	 * @author tfranz
	 */
    protected void advance(int x, int y) {
        Vehicle a = getVehicle(x, y);
        if (y + a.getVelocity() > road[x].length) {
            nextRoute.advance(x, getOverflow(x, y + a.getVelocity()));
        } else {

            setVehicle(a, x, y + a.getVelocity());
            setVehicle(new EmptyVehicle(), x, y);
        }
        // a.setX(a.getX() + a.getVelocity());
    }
    /**
	 * gets the gap between the vehicle at position p and the vehicle in front
	 * of it
	 * 
	 * @param p position of current vehicle
	 * @return gap
	 */
    protected int getGap(Point p) {
        return getGap(p.x, p.y);
    }

    protected int getGap(int x, int y) {
        int gap = 0;
        boolean nextVehicleFound = false;
        while ((y <= road[x].length-1) && (isFree(x, y))) {
            if (road[x][y] instanceof Car) {
                nextVehicleFound = true;
            }
            y++;
            gap++;
        }

        return gap;
    }

    protected Vehicle getVehicle(int x, int y) {
        return (Vehicle)road[x][y];
    }
    /**
	 * sets a vehicle at the given position
	 * 
	 * @param v vehicle to set
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
    protected void setVehicle(Vehicle v, int x, int y) {
        road[x][y] = v;
    }
    /**
	 * returns the Road
	 * 
	 * @return Road
	 */
    public Object[][] getList() {
        return road;
    }
    /**
	 * returns the amount of fields the current position exceeds the road
	 * length
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
    public int getOverflow(int x, int y) {
        int overflow = y - road[x].length;
        if (overflow <= 0) {
            return 0;
        } else {
            return overflow;
        }
    }

    public void setNextRoute(Route r) {
        nextRoute = r;
    }

}
