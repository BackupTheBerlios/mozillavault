/*
<<<<<<< GraphikPanel.java
 * Created on 15.10.2003 $Id: GraphikPanel.java,v 1.4 2003/10/25 14:41:22
 * jsprenger Exp $
=======
 * Created on 15.10.2003
 * $Id: GraphikPanel.java,v 1.11 2003/10/29 11:25:46 jsprenger Exp $
>>>>>>> 1.5
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

import javax.swing.JPanel;

import objects.Car;
import objects.EmptyVehicle;
import objects.Route;
import objects.RouteCrossLane;
import objects.RouteSingleLane;
import objects.Truck;
import objects.Vehicle;
import objects.Car;

/**
 * @author Jonas Sprenger
 *  
 */
public class GraphikPanel extends JPanel {

	Object list[][];
	Object routeList[];
	Route strasse, route;
	int cellSize = 5;
	int nullPunktX = 400;
	int nullPunktY = 250;

	public GraphikPanel(Route s) {
		this.strasse = s;
		list = strasse.getList();
		super.setBounds(
			0,
			0,
			list.length * cellSize,
			list[0].length * cellSize);

	}
	public GraphikPanel() {
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
		int index = 0;
		int krH = 0,krB=0; // höhe und greite der Kreuzung
		route = strasse;
		list = strasse.getList();

		if (route instanceof RouteCrossLane) {
			CrossLanePaint clp = new CrossLanePaint(nullPunktX,nullPunktY,route);
			clp.init(g,this);
		}
		else if (strasse instanceof RouteSingleLane) {
					drawRouteSingleLaneL(g);
			for (int i = list.length - 1; i >= 0; i--) {
					  for (int j = list[i].length - 1; j >= 0; j--) {
						  if (list[i][j] instanceof Truck) {
							  drawTruck(g, j, i);

						  } else if (list[i][j] instanceof Car) {
							  drawCar(g, j, i);

						  } else {
							  drawRoadElement(g, j, i);
						  }
						  /*
						   * if (list[i][j] instanceof Ampel) { g.setColor(Color.red);
						   * System.out.println("zeichnet Vehicle...");
						   * g.fillRect(nullPunktx + j, nullPunkty+i, 10, 10);
						   *  
						   */
					  }
				  }
				}
	}

	void drawCrossLane(Graphics g) {
		Polygon p = new Polygon();
		int maxX = list[0].length * cellSize + 1;
		int maxY = list.length * cellSize + 1;
		int breite = 10;
		int hoehe = 10;

		p.addPoint(nullPunktX, nullPunktY);
		p.addPoint(nullPunktX + maxX / 2, nullPunktY);
		p.addPoint(nullPunktX + maxX / 2, nullPunktY - maxY / 2);
		p.addPoint(nullPunktX + maxX / 2 + breite, nullPunktY - maxY / 2);

		p.addPoint(nullPunktX + maxX / 2 + breite, nullPunktY);
		p.addPoint(nullPunktX + maxX + breite, nullPunktY);
		p.addPoint(nullPunktX + maxX + breite, nullPunktY + hoehe);
		p.addPoint(nullPunktX + maxX / 2 + breite, nullPunktY + hoehe);

		p.addPoint(
			nullPunktX + maxX / 2 + breite,
			nullPunktY + hoehe + maxY / 2);
		p.addPoint(nullPunktX + maxX / 2, nullPunktY + hoehe + maxY / 2);
		p.addPoint(nullPunktX + maxX / 2, nullPunktY + hoehe);
		p.addPoint(nullPunktX, nullPunktY + hoehe);

		g.setColor(Color.WHITE);
		g.fillPolygon(p);

		//g.setColor(this.getBackground());
		//g.drawPolygon(p);

		g.setColor(Color.black);
		System.out.println("zeichnet...StrasseKreuzung");
	}

	void drawRouteSingleLane(Graphics g, int x, int y, int w, int h) {
		g.setColor(Color.WHITE);
		//g.drawRect(x, y, w, h);
		g.fillRect(x, y, w, h);

		System.out.println("zeichnet...StrasseEinspurig " + x + "  " + y);
	}
	void drawRouteSingleLaneL(Graphics g) {
			g.drawRect(
				nullPunktX - 1,
				nullPunktY - 1,
				list[0].length * cellSize + 1,
				list.length * cellSize + 1);

		 //   System.out.println("zeichnet...StrasseEinspurig");
		}
	void drawCar(Graphics g, int x, int y) {
		g.setColor(Car.getColor());
		System.out.println("zeichnet Vehicle..." + x + "." + y);
		g.fillRect(x, y, cellSize - 1, cellSize);

	}
	void drawTruck(Graphics g, int x, int y) {
			//g.setColor(Car.getColor());
			g.setColor(Color.red);
			//System.out.println("zeichnet Truck..." + x + "." + y);
			g.fillRect(
				nullPunktX + x * cellSize,
				nullPunktY + y * cellSize,
				cellSize - 1,
				cellSize);

		}

	void drawRoadElement(Graphics g, int x, int y) {
		//g.setColor(EmptyVehicle.getColor());
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, cellSize -1, cellSize-1);
	}

}
