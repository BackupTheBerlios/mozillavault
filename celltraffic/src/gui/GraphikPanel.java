/*
 * Created on 15.10.2003
 * $Id: GraphikPanel.java,v 1.3 2003/10/25 12:38:45 jsprenger Exp $
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

import objects.Route;
import objects.RouteCrossLane;
import objects.RouteSingleLane;

/**
 * @author Jonas Sprenger
 * 
 */
public class GraphikPanel extends JPanel {
    
   Object  list[][];
    Route strasse;

    public GraphikPanel(Route s) {
        //this.setBackground(Color.RED);
        this.strasse = s;
        list = strasse.getList();
        

    }
	public GraphikPanel() {
		super.setBounds(0,0,300,300);
			list = null;

		}
    public void setList(Object l[][]) {
        this.list = l;
    }
    public Object[][] getList() {
        return list;
    }
    
    public void paint(Graphics g) {
		super.paint(g);
	if(strasse instanceof RouteCrossLane)
	{
		Polygon p = new Polygon();
		
		p.addPoint(0,100);
		p.addPoint(100,100);
		p.addPoint(100,0);
		p.addPoint(110,0);	 
		
		p.addPoint(110,100);
		p.addPoint(210,100);
		p.addPoint(210,110);
		p.addPoint(110,110);	 
		
		p.addPoint(110,210);
		p.addPoint(100,210);
		p.addPoint(100,110);
		p.addPoint(0,110);
		
				
		g.drawPolygon(p);	
		
		g.setColor(Color.black);
		System.out.println("zeichnet...StrasseKreuzung");
	}
	else if(strasse instanceof RouteSingleLane)
	{
				g.drawRect(0, 0, ((RouteSingleLane)strasse).getLaenge(), ((RouteSingleLane)strasse).getBreite());
				g.setColor(Color.black);
				System.out.println("zeichnet...StrasseEinspurig");
		
	} 
	//for (int i = 0; i < list.length; i++) {
        //    System.out.println(
       //         "neues Objekt gezeichnet..." + ((Vehicle)list.get(i)).getX());
          /*  g.fillRect(
                ((Vehicle)list.get(i)).getX(),
                ((Vehicle)list.get(i)).getY(),
                10,
                10);*/
       // }

    }

}
