/*
 * Created on 15.10.2003
 * $Id: RouteSingleLane.java,v 1.6 2003/10/29 05:21:22 moleman Exp $
 */
package objects;

public class RouteSingleLane extends Route{
	
    public RouteSingleLane(){
        super();
    }
    
	public RouteSingleLane(int l){
        super(l);
	}
    
    public RouteSingleLane(int length,int numLanes){
        super(length);
    }
 

}
