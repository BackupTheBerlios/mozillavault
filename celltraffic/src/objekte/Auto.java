/*
 * Created on 15.10.2003
 */
package objekte;

/**
 * @author Jonas Sprenger
 *  
 */
public class Auto {
    int x, y;
    /**
	 * die momentane Geschwindigkeit des Fahrzeugs
	 */
    int velocity = 0;
    /**
	 * maximale Geschwindigkeit des Fahrzeugs
	 */
    int maxVelocity = 5;
    public Auto(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int i) {
        this.y = i;
    }

    public void setVelocity(int v) {
        if ((velocity != v) && (v <= maxVelocity)) {
            velocity = v;
        }
    }

    public int getMaxVelocity() {
        return maxVelocity;
    }

    public int getVelocity() {
        return velocity;
    }

}
