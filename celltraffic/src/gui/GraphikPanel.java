/*
 * Created on 15.10.2003
 * $Id: GraphikPanel.java,v 1.5 2003/10/28 15:48:56 jsprenger Exp $
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

import objects.Car;
import objects.Route;
import objects.RouteCrossLane;
import objects.RouteSingleLane;
import objects.Vehicle;

/**
 * @author Jonas Sprenger
 * 
 */
public class GraphikPanel extends JPanel {

	Object list[][];
	Route strasse;

	public GraphikPanel(Route s) {
		//this.setBackground(Color.RED);
		this.strasse = s;
		list = strasse.getList();

	}
	public GraphikPanel() {
		//super.setBounds(0, 0, 300, 300);
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
		int nullPunktx = 0;
		int nullPunkty = 100;
		if (strasse instanceof RouteCrossLane) {
			Polygon p = new Polygon();

			p.addPoint(nullPunktx, nullPunkty);
			p.addPoint(100, 100);
			p.addPoint(100, 0);
			p.addPoint(110, 0);

			p.addPoint(110, 100);
			p.addPoint(210, 100);
			p.addPoint(210, 110);
			p.addPoint(110, 110);

			p.addPoint(110, 210);
			p.addPoint(100, 210);
			p.addPoint(100, 110);
			p.addPoint(0, 110);

			g.drawPolygon(p);

			g.setColor(Color.black);
			System.out.println("zeichnet...StrasseKreuzung");
		} else if (strasse instanceof RouteSingleLane) {
			g.drawRect(
				nullPunktx,
				nullPunkty,
				((RouteSingleLane) strasse).getLaenge(),
				((RouteSingleLane) strasse).getBreite());
			
			System.out.println("zeichnet...StrasseEinspurig");

		}
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[i].length; j++) {
				if (list[i][j] instanceof Car) {
					g.setColor(Color.red);
					
					//System.out.println("zeichnet Vehicle..."+((Vehicle)list[i][j]).getRandom());
					g.fillRect(nullPunktx + j, nullPunkty+i, 5, 5);
					
				}
				/*if (list[i][j] instanceof Ampel) {
									g.setColor(Color.red);
									System.out.println("zeichnet Vehicle...");
									g.fillRect(nullPunktx + j, nullPunkty+i, 10, 10);
					
								}*/
			}
		}

	}

}
