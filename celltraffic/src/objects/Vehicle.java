/*
 * Created on 15.10.2003 $Id: Vehicle.java,v 1.4 2003/10/28 15:48:56 jsprenger
 * Exp $
 */
package objects;

import java.awt.Color;

/**
 * @author Jonas Sprenger
 *  
 */
public class Vehicle {
	// int x, y;
	RandomGenerator rg;
	int random;
	/**
	 * current velocity of vehicle
	 */
	int velocity = 0;
	/**
	 * max velocity of vehicle
	 */
	int maxVelocity = 5;
	boolean handled = false;
    static Color color = Color.LIGHT_GRAY ;
    
    int direction =0; // 0: forward; 1: left; 2: right 
    
    public int getDirection(){
        return direction;
    }
    
    public void setDirection(int d){
        direction = d;
    }
    
    
    

	/**
	 * @return
	 */
	//    public int getX() {
	//        return x;
	//    }

	/**
	 * @param x
	 */
	//    public void setX(int x) {
	//        this.x = x;
	//    }
	//    
	//    /**
	//	 * @return
	//	 */
	//    public int getY() {
	//        return y;
	//    }
	//    
	//    /**
	//	 * @param i
	//	 */
	//    public void setY(int i) {
	//        this.y = i;
	//    }
	//    
	/**
	 * sets velocity according
	 * 
	 * @param v
	 */
	public void setVelocity(int v) {
		if (velocity != v) {
			v = Math.max(0, v);
			velocity = Math.min(v, maxVelocity);
		}

	}

	/**
	 * @return
	 */
	public int getMaxVelocity() {
		return maxVelocity;
	}

	/**
	 * @return
	 */
	public int getVelocity() {
		return velocity;
	}

	/**
	 * @param maxVelocity
	 *            The maxVelocity to set.
	 */
	public void setMaxVelocity(int maxVelocity) {
		this.maxVelocity = maxVelocity;
	}

	/**
	 * constructor
	 */
	public Vehicle() {
		super();
        color = Color.LIGHT_GRAY ;
	}

	public boolean isHandled() {
		return handled;
	}

	public void setHandled(boolean h) {
		handled = h;
	}

    public static Color getColor(){
        return color;
    }
}
