/*
 * Created on 23.10.2003
 * $Id: Drain.java,v 1.5 2003/10/29 17:58:19 moleman Exp $
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
        super(25 , 255 );
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
