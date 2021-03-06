/*
 * Created on 29.10.2003
 * 
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package gui;

import java.awt.Graphics;

import objects.Car;
import objects.Route;
import objects.RouteCrossLane;
import objects.RouteSingleLane;
import objects.Truck;

/**
 * @author Jonas Sprenger
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class CrossLanePaint {

	Object list[][];
	Object routeList[];
	Route route;
	int cellSize = 5;
	int nullPunktX;
	int nullPunktY;
	int kr = cellSize + 1;

	public CrossLanePaint(int x, int y, Route r) {
		this.nullPunktX = x;
		this.nullPunktY = y;
		this.route = r;

	}
	public void init(Graphics g, GraphikPanel gp) {
		boolean status = false;
		int index = 0;
		routeList = ((RouteCrossLane) route).getLane();
		gp.drawCrossLane(g);
		list = route.getList();
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[i].length; j++) {
				if (list[i][j] instanceof Truck) {
					gp.drawTruck(
						g,
						nullPunktX + j * cellSize,
						nullPunktY + i * cellSize);
				} else if (list[i][j] instanceof Car) {
					gp.drawCar(
						g,
						nullPunktX + j * cellSize,
						nullPunktY + i * cellSize);

				} else {
					gp.drawRoadElement(
						g,
						nullPunktX + j * cellSize,
						nullPunktY + i * cellSize);

				}
			}
		}
// paint ligths .....
  
		gp.drawLigth(
			g,
			nullPunktX - 3 * cellSize,
			nullPunktY - 3 * cellSize,
		((RouteCrossLane) route).getAmpel());

		gp.drawLigth(
			g,
			nullPunktX - 3 * cellSize,
			nullPunktY + 4 * cellSize,
		!((RouteCrossLane) route).getAmpel());
		gp.drawLigth(
			g,
			nullPunktX + 5 * cellSize,
			nullPunktY + 4 * cellSize,
		((RouteCrossLane) route).getAmpel());

		gp.drawLigth(
			g,
			nullPunktX + 5 * cellSize,
			nullPunktY - 3 * cellSize,
		!((RouteCrossLane) route).getAmpel());
// end of paint lights 

		while (index < routeList.length) {
			route = (RouteSingleLane) routeList[index];
			list = route.getList();
			if (route instanceof RouteSingleLane) {
				int b = list[0].length * cellSize;
				int h = list.length * cellSize;

				//left lane <------
				if (index == 0) {
					gp.drawRouteSingleLane(g, nullPunktX - b, nullPunktY, b, h);
					for (int i = 0; i < list.length; i++) {
						for (int j = 0; j < list[i].length; j++) {
							if (list[i][j] instanceof Truck) {
								gp.drawTruck(
									g,
									nullPunktX - kr - j * cellSize,
									nullPunktY + i * cellSize);
							} else if (list[i][j] instanceof Car) {
								gp.drawCar(
									g,
									nullPunktX - kr - j * cellSize,
									nullPunktY + i * cellSize);

							} else {
								gp.drawRoadElement(
									g,
									nullPunktX - kr - j * cellSize,
									nullPunktY + i * cellSize);
							}
						}
					}
				}
				//				left lane ------>
				if (index == 1) {

					gp.drawRouteSingleLane(
						g,
						nullPunktX - b,
						nullPunktY + kr,
						b,
						h);
					for (int i = 0; i < list.length; i++) {
						for (int j = 0; j < list[i].length; j++) {
							if (list[i][j] instanceof Truck) {
								gp.drawTruck(
									g,
									nullPunktX - b + j * cellSize,
									nullPunktY + kr + i * cellSize);
							} else if (list[i][j] instanceof Car) {
								gp.drawCar(
									g,
									nullPunktX - b + j * cellSize,
									nullPunktY + kr + i * cellSize);

							} else {
								gp.drawRoadElement(
									g,
									nullPunktX - b + j * cellSize,
									nullPunktY + kr + i * cellSize);
							}
						}
					}
				}
				// bottom lane down...
				if (index == 2) {

					gp.drawRouteSingleLane(
						g,
						nullPunktX,
						nullPunktY + kr * 2,
						h,
						b);
					for (int i = list.length - 1; i >= 0; i--) {
						for (int j = list[i].length - 1; j >= 0; j--) {
							if (list[i][j] instanceof Truck) {
								gp.drawTruck(
									g,
									nullPunktX - i * cellSize,
									nullPunktY + kr * 2 + j * cellSize);
							} else if (list[i][j] instanceof Car) {
								gp.drawCar(
									g,
									nullPunktX - i * cellSize,
									nullPunktY + kr * 2 + j * cellSize);

							} else {
								gp.drawRoadElement(
									g,
									nullPunktX - i * cellSize,
									nullPunktY + kr * 2 + j * cellSize);

							}
						}
					}
				}
				// bottom lane up...
				if (index == 3) {

					gp.drawRouteSingleLane(
						g,
						nullPunktX + kr,
						nullPunktY + kr * 2,
						h,
						b);
					for (int i = list.length - 1; i >= 0; i--) {
						for (int j = list[i].length - 1; j >= 0; j--) {
							if (list[i][j] instanceof Truck) {
								gp.drawTruck(
									g,
									nullPunktX + kr - i * cellSize,
									nullPunktY + b + kr - j * cellSize);
							} else if (list[i][j] instanceof Car) {
								gp.drawCar(
									g,
									nullPunktX + kr - i * cellSize,
									nullPunktY + b + kr - j * cellSize);

							} else {
								gp.drawRoadElement(
									g,
									nullPunktX + kr - i * cellSize,
									nullPunktY + b + kr - j * cellSize);
							}
						}
					}
				}
				//	right lane ------->
				if (index == 4) {

					gp.drawRouteSingleLane(
						g,
						nullPunktX + kr * 2,
						nullPunktY + kr,
						b,
						h);
					for (int i = list.length - 1; i >= 0; i--) {
						for (int j = list[i].length - 1; j >= 0; j--) {
							if (list[i][j] instanceof Truck) {
								gp.drawTruck(
									g,
									nullPunktX + kr * 2 + j * cellSize,
									nullPunktY + kr + i * cellSize);
							} else if (list[i][j] instanceof Car) {
								gp.drawCar(
									g,
									nullPunktX + kr * 2 + j * cellSize,
									nullPunktY + kr + i * cellSize);

							} else {
								gp.drawRoadElement(
									g,
									nullPunktX + kr * 2 + j * cellSize,
									nullPunktY + kr + i * cellSize);

							}
						}
					}
				}
				//				right lane <-------
				if (index == 5) {

					gp.drawRouteSingleLane(
						g,
						nullPunktX + kr * 2,
						nullPunktY,
						b,
						h);
					for (int i = list.length - 1; i >= 0; i--) {
						for (int j = list[i].length - 1; j >= 0; j--) {
							if (list[i][j] instanceof Truck) {
								gp.drawTruck(
									g,
									nullPunktX + b + kr - j * cellSize,
									nullPunktY + i * cellSize);
							} else if (list[i][j] instanceof Car) {
								gp.drawCar(
									g,
									nullPunktX + b + kr - j * cellSize,
									nullPunktY + i * cellSize);

							} else {
								gp.drawRoadElement(
									g,
									nullPunktX + b + kr - j * cellSize,
									nullPunktY + i * cellSize);

							}
						}
					}
				}
				// top lane down
				if (index == 6) {

					gp.drawRouteSingleLane(
						g,
						nullPunktX + kr,
						nullPunktY - b,
						h,
						b);
					for (int i = list.length - 1; i >= 0; i--) {
						for (int j = list[i].length - 1; j >= 0; j--) {
							if (list[i][j] instanceof Truck) {
								gp.drawTruck(
									g,
									nullPunktX + kr + i * cellSize,
									nullPunktY - kr - j * cellSize);
							} else if (list[i][j] instanceof Car) {
								gp.drawCar(
									g,
									nullPunktX + kr + i * cellSize,
									nullPunktY - kr - j * cellSize);

							} else {
								gp.drawRoadElement(
									g,
									nullPunktX + kr + i * cellSize,
									nullPunktY - kr - j * cellSize);

							}
						}
					}
				}
				//				top lane down
				if (index == 7) {

					gp.drawRouteSingleLane(g, nullPunktX, nullPunktY - b, h, b);
					for (int i = list.length - 1; i >= 0; i--) {
						for (int j = list[i].length - 1; j >= 0; j--) {
							if (list[i][j] instanceof Truck) {
								gp.drawTruck(
									g,
									nullPunktX + i * cellSize,
									nullPunktY - b + j * cellSize);
							} else if (list[i][j] instanceof Car) {
								gp.drawCar(
									g,
									nullPunktX + i * cellSize,
									nullPunktY - b + j * cellSize);

							} else {
								gp.drawRoadElement(
									g,
									nullPunktX + i * cellSize,
									nullPunktY - b + j * cellSize);

							}
						}
					}
				}

			} //end if
			index++;
		} //end while

	}
}
