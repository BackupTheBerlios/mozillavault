/*
 * Created on 15.10.2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package objekte;

import gui.GraphikPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonas Sprenger
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class StrasseEinspurig extends VerkehrsWeg{
	int laenge;
	int breite;
	List list;
	GraphikPanel gp;
	
	public StrasseEinspurig(int l){
		this.laenge = l;
		breite = 10;
		list = new ArrayList();
		
	}
	public void setGraphikPanel(GraphikPanel gp){
		this. gp = gp;
	}
	public void addPoint(Auto a){
	list.add(a);
	gp.repaint();
	
	}
	public List getList(){
		return list;
	}
	public int getLaenge(){
		return laenge;
	}
	public int getBreite(){
			return breite;
		}
	public boolean produzentBelegt(){
		return istBelegt(0,0);
				
	}
	
	public boolean istBelegt(int x,int y){
		for(int i=0;i<list.size();i++){
			Auto a = (Auto)list.get(i);
			if(x==a.getX()&& y == a.getY())
				{
				 return true;
				 } 		
		}
		
		return false;}

}
