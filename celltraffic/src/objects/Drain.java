/*
 * Created on 23.10.2003
 * $Id: Drain.java,v 1.3 2003/10/25 12:46:07 moleman Exp $
 */
package objects;

/**
 * @author tfranz
 */
public class Drain extends Route {

    /**
     * @param length
     */
    public Drain(int length) {
        super(length);
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     */
    public Drain() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public boolean isFree(int x, int y){
        return true;
    }

}
