/*
 * Created on 15.10.2003
 */
package objekte;

/**
 * @author Jonas Sprenger
 *  
 */
public abstract class VerkehrsWeg {
    double _p_dec = 0.2;

    public VerkehrsWeg() {
    }

    public void setPoint() {
    }

    public boolean isBelegt(int x, int y) {
        return true;
    }
    
    public Auto getPoint() {
        return null;
    }

    public void accelerate(Auto a) {
        if (a.getVelocity() < a.getMaxVelocity()) {
            a.setVelocity(a.getVelocity() + 1);
        }
    }
    
    /**
	 * @param a
     * @todo Fill with logic
	 */
    public void decelerate(Auto a) {

    }
    
    /**
	 * Stochastic behavior. Slow down with probability _p_dec
	 * 
	 * @param a
	 *            the car to slow down
	 */
    public void stochasticDecelerate(Auto a) {
        if ((a.getVelocity() > 0) && (Math.random() <= _p_dec))
            a.setVelocity(a.getVelocity() - 1);
    }

    public void advance(Auto a) {
        a.setX(a.getX() + a.getVelocity());
    }

}
