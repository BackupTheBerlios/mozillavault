/*
 * $Id: GraphikPanel.java,v 1.13 2003/10/29 17:58:19 moleman Exp $
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
import objects.RouteMultiLane;
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
	int nullPunktX = 500;
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
		int krH = 0, krB = 0; // höhe und greite der Kreuzung
		route = strasse;
		list = strasse.getList();

		if (route instanceof RouteCrossLane) {
			CrossLanePaint clp =
				new CrossLanePaint(nullPunktX, nullPunktY, route);
			clp.init(g, this);
		} else if (strasse instanceof RouteSingleLane) {
			drawRouteSingleLaneL(g);
			//	CrossLanePaint clp =
			//new CrossLanePaint(nullPunktX, nullPunktY, route);
			//clp.init(g, this);
			//} else if (strasse instanceof RouteSingleLane) {
			//	drawRouteSingleLaneL(g);

			for (int i = list.length - 1; i >= 0; i--) {
				for (int j = list[i].length - 1; j >= 0; j--) {
					if (list[i][j] instanceof Truck) {
						drawTruck(g, j, i);

					} else if (list[i][j] instanceof Car) {
						drawCar(g, j, i);

					} else {
						drawRoadElement(g, j, i);
					}
				}
			}
		} else if (strasse instanceof RouteMultiLane) {
			drawRouteMultiLaneL(g);
			/*for (int i = list.length - 1; i >= 0; i--) {
							for (int j = list[i].length - 1; j >= 0; j--) {
								if (list[i][j] instanceof Truck) {
									drawTruck(g, j, i);

								} else if (list[i][j] instanceof Car) {
									drawCar(g, j, i);

								} else {
									drawRoadElement(g, j, i);
								}
							}
						}*/
			for (int i = list.length - 1; i >= 0; i--) {
				for (int j = list[i].length - 1; j >= 0; j--) {
					if (list[i][j] instanceof Truck) {
						int y;
						if (i == 0) {
							 y = 1;
						} else {
							y = 0;
						}
						drawTruck(g, j, y);

					} else if (list[i][j] instanceof Car) {
						int y;
						if (i == 0) {
							 y = 1;
						} else {
							y = 0;
						}
						drawCar(g, j, y);

					} else {
						int y;
						if (i == 0) {
							 y = 1;
						} else {
							y = 0;
						}
						drawRoadElement(g, j, y);
					}
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

		g.setColor(Color.WHITE);
		//g.fillPolygon(p);

		//g.setColor(this.getBackground());
		//g.drawPolygon(p);

		g.drawRect(nullPunktX, nullPunktY, 2 * cellSize, 2 * cellSize);
		//g.fillRect(x, y, w, h);
		//	System.out.println("zeichnet...StrasseKreuzung");
	}

	void drawRouteSingleLane(Graphics g, int x, int y, int w, int h) {
		g.setColor(Color.BLACK);
		g.drawRect(x, y, w, h);
		//g.fillRect(x, y, w, h);
		//System.out.println("zeichnet...StrasseEinspurig " + x + " " + y);
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
		g.setColor(Color.red);
		//System.out.println("zeichnet Vehicle..." + x + "." + y);
		g.fillRect(x, y, cellSize - 1, cellSize - 1);
		g.fillRect(
			nullPunktX + x * cellSize,
			nullPunktY + y * cellSize,
			cellSize - 1,
			cellSize - 1);

	}
	void drawTruck(Graphics g, int x, int y) {
		//g.setColor(Car.getColor());
		g.setColor(Color.green);
		//System.out.println("zeichnet Truck..." + x + "." + y);
		g.fillRect(
			nullPunktX + x * cellSize,
			nullPunktY + y * cellSize,
			cellSize - 1,
			cellSize - 1);
		//g.setColor(Car.getColor());
		g.setColor(Color.green);
		//System.out.println("zeichnet Truck..." + x + "." + y);
		g.fillRect(x, y, cellSize - 1, cellSize - 1);

	}

	void drawRoadElement(Graphics g, int x, int y) {
		//g.setColor(EmptyVehicle.getColor());
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, cellSize - 1, cellSize - 1);
		g.fillRect(
			nullPunktX + x * cellSize,
			nullPunktY + y * cellSize,
			cellSize - 1,
			cellSize - 1);
	}

	void drawRouteMultiLaneL(Graphics g) {
		g.drawRect(
			nullPunktX - 1,
			nullPunktY - 1,
			list[0].length * cellSize + 1,
			list.length * cellSize + 1);

		//   System.out.println("zeichnet...StrasseEinspurig");
	}

}
