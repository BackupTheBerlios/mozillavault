/*
 * Created on 23.10.2003
 * $Id: Drain.java,v 1.4 2003/10/29 05:21:22 moleman Exp $
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
  
    }

    /**
     * 
     */
    public Drain() {
        super();
    }
    
    public boolean isFree(int x, int y){
        return true;
    }
    
    public void update(){
        
    }
    
    public void setNextRoute(Route r){
        
    }
    
    public Route getNextRoute(){
        return null;
    }

}
