/*
 * Created on 15.10.2003
 * $Id: Route.java,v 1.2 2003/10/23 18:00:16 moleman Exp $
 */
package objects;

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
        nextRoute=null;
        road = new Object[1][length];
    }
    
    public Route() {
           nextRoute=null;
        road = new Object[1][100];
       }


    /**
     * accelerates the given car one speed unit 
     * @param a the car to accelereate
     */
    public void accelerate(Vehicle a) {
        if (a.getVelocity() < a.getMaxVelocity()) {
            a.setVelocity(a.getVelocity() + 1);
        }
    }
    
    /**
     * decelerates the given car depending on the gap between 
     * the current vehicle and the vehicle ahead 
	 * @param a
     * TODO Fill with logic
	 */
    public void decelerate(Vehicle a) {

    }
    
    /**
	 * Stochastic behavior. Slow down given vehicle with probability _p_dec
	 * 
	 * @param a
	 *            the car to slow down
	 */
    public void stochasticDecelerate(Vehicle a) {
        if ((a.getVelocity() > 0) && (Math.random() <= p_dec))
            a.setVelocity(a.getVelocity() - 1);
    }
/**
 * TODO advance
 * @author tfranz
 */
    public void advance(Vehicle a) {
       // a.setX(a.getX() + a.getVelocity());
    }
    /**
     * 
     * @param v
     * @return
     */
    int getGap(Vehicle v){
    return 0;    
    }

}
