/*
 * Created on 15.10.2003
 */
package objects;

/**
 * @author Jonas Sprenger
 *  
 */
public class Vehicle {
   // int x, y;
    /**
	 * current velocity of vehicle
	 */
    int velocity = 0;
    /**
	 * max velocity of vehicle
	 */
    int maxVelocity = 5;
    
//    /**
//     * constructor
//	 * @param x
//	 * @param y
//	 */
//    public Vehicle(int x, int y) {
//       // this.x = x;
//       // this.y = y;
//    }
    
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
	 * @param v
	 */
    public void setVelocity(int v) {
        if ((velocity != v) && (v <= maxVelocity)) {
            velocity = v;
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
	 *        The maxVelocity to set.
	 */
    public void setMaxVelocity(int maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    /**
     * constructor
     */
    public Vehicle() {
        super();
    }
}
