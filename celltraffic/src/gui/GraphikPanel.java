/*
<<<<<<< GraphikPanel.java
 * Created on 15.10.2003 $Id: GraphikPanel.java,v 1.4 2003/10/25 14:41:22
 * jsprenger Exp $
=======
 * Created on 15.10.2003
 * $Id: GraphikPanel.java,v 1.8 2003/10/28 21:17:33 moleman Exp $
>>>>>>> 1.5
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

import objects.Car;
import objects.EmptyVehicle;
import objects.Route;
import objects.RouteCrossLane;
import objects.RouteSingleLane;
import objects.Vehicle;
import objects.Car;

/**
 * @author Jonas Sprenger
 *  
 */
public class GraphikPanel extends JPanel {

	Object list[][];
	Route strasse;
	int cellSize = 7;
	int nullPunktX = 10;
	int nullPunktY = 100;

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

		if (strasse instanceof RouteCrossLane) {
			drawCrossLane(g);
		} else if (strasse instanceof RouteSingleLane) {
			drawRouteSingleLane(g);
		}

		for (int i = list.length - 1; i >= 0; i--) {
			for (int j = list[i].length - 1; j >= 0; j--) {
				if (list[i][j] instanceof Car) {
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

	void drawCrossLane(Graphics g) {
		Polygon p = new Polygon();
int maxX = list[0].length * cellSize + 1;
int maxY = list.length * cellSize + 1;
int breite = 10;
int hoehe = 10;

		p.addPoint(nullPunktX, nullPunktY);
		p.addPoint(maxX/2, nullPunktY);
		p.addPoint(maxX/2,nullPunktY-maxY/2);
		p.addPoint(maxX/2+breite,nullPunktY-maxY/2);

		p.addPoint(maxX/2+breite, nullPunktY);
		p.addPoint(maxX+breite,nullPunktY);
		p.addPoint(maxX+breite,nullPunktY+hoehe);
		p.addPoint(maxX/2+breite, nullPunktY+hoehe);

		p.addPoint(maxX/2+breite,  nullPunktY+hoehe+maxY/2);
		p.addPoint(maxX/2, nullPunktY+hoehe+maxY/2);
		p.addPoint(maxX/2, nullPunktY+hoehe);
		p.addPoint(nullPunktX, nullPunktY+hoehe);

		g.drawPolygon(p);

		g.setColor(Color.black);
		System.out.println("zeichnet...StrasseKreuzung");
	}

	void drawRouteSingleLane(Graphics g) {
		g.drawRect(
			nullPunktX - 1,
			nullPunktY - 1,
			list[0].length * cellSize + 1,
			list.length * cellSize + 1);

		System.out.println("zeichnet...StrasseEinspurig");
	}

	void drawCar(Graphics g, int x, int y) {
		g.setColor(Car.getColor());
		System.out.println("zeichnet Vehicle..." + x + "." + y);
		g.fillRect(
			nullPunktX + x * cellSize,
			nullPunktY + y * cellSize,
			cellSize - 1,
			cellSize);

	}

	void drawRoadElement(Graphics g, int x, int y) {
		g.setColor(EmptyVehicle.getColor());
		//System.out.println("zeichnet Straﬂe..." + i + "." + j);
		g.fillRect(
			nullPunktX + x * cellSize,
			nullPunktY + y * cellSize,
			cellSize - 1,
			cellSize);
	}

}
