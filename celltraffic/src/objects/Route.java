/*
 * Created on 15.10.2003 $Id: Route.java,v 1.5 2003/10/25 12:41:35 moleman Exp $
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
	 * iterates over the road, starts at the end
	 * 
	 * TODO make multi-dimensional algorithm
	 */
    public void update() {
        for (int i = road[1].length; i > 0; i--) {
            if (road[1][i] instanceof Vehicle) {
                accelerate(1, i);
            }
        }
        for (int i = road[1].length; i > 0; i--) {
            decelerate(1, i);
        }
        for (int i = road[1].length; i > 0; i--) {
            stochasticDecelerate(1, i);
        }
        for (int i = road[1].length; i > 0; i--) {
            advance(1, i);
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
	 * 
	 * @author tfranz
	 */
    protected void advance(int x, int y) {
        Vehicle a = getVehicle(x, y);
        if (y + a.getVelocity() > road[x].length) {
            //TODO advance for position outside current road
            System.out.println(
                "TODO: New Position of Vehicle exceeds current road");
        } else {
            road[x][y + a.getVelocity()] = road[x][y];
            road[x][y] = new EmptyVehicle();
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
        while ((y <= road[x].length) && (isFree(x, y))) {
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

    protected void setVehicle(Vehicle v, int x, int y) {
        road[x][y] = v;
    }
    
    public Object[][] getList(){
        return road;
    }

}
