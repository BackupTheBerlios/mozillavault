/*
 * Created on 15.10.2003
 * $Id: RouteSingleLane.java,v 1.3 2003/10/25 12:46:25 moleman Exp $
 */
package objects;

import gui.GraphikPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonas Sprenger
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class RouteSingleLane extends Route{
	int laenge;
	int breite;
	List list;
	GraphikPanel gp;
	
	public RouteSingleLane(int l){
		this.laenge = l;
		breite = 10;
		list = new ArrayList();
		
	}
	public void setGraphikPanel(GraphikPanel gp){
		this. gp = gp;
	}
	public void addPoint(Vehicle a){
	list.add(a);
	gp.repaint();
	
	}
	
	public int getLaenge(){
		return laenge;
	}
	public int getBreite(){
			return breite;
		}
	public boolean produzentBelegt(){
	//	return istBelegt(0,0);
        return true;
				
	}
	

}
