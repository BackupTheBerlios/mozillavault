/*
 * Created on 15.10.2003
 * $Id: RouteCrossLane.java,v 1.1 2003/10/25 12:38:58 jsprenger Exp $
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
public class RouteCrossLane extends Route{
	int breite;
	List list;
	GraphikPanel gp;
	
	public RouteCrossLane(){
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
	//public List getList(){
	//	return list;
	//}
	public int getBreite(){
			return breite;
		}
	public boolean produzentBelegt(){
	//	return istBelegt(0,0);
        return true;
				
	}
	

}

