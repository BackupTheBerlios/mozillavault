/*
 * Created on 29.10.2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
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
	
	public CrossLanePaint(int x,int y,Route r){
	this.nullPunktX = x;
	this.nullPunktY = y;
	this.route = r;
	
	}
		public void init(Graphics g,GraphikPanel gp){
		int index = 0;
		routeList = ((RouteCrossLane) route).getLane();
					gp.drawCrossLane(g);
					route = (RouteSingleLane) routeList[index];
					list = route.getList();
					
					while (index < routeList.length) {
						if (route instanceof RouteSingleLane) {
							int b = list[0].length * cellSize + 1;
							int h = list.length * cellSize + 1;
							//left lane
							if (index == 0) {

								gp.drawRouteSingleLane(
									g,
									nullPunktX - b,
									nullPunktY,
									b,
									h);
								for (int i = list.length - 1; i >= 0; i--) {
									for (int j = list[i].length - 1; j >= 0; j--) {
										if (list[i][j] instanceof Car) {
											gp.drawCar(
												g,
												nullPunktX - j * cellSize,
												nullPunktY + i * cellSize);

										} else {
											gp.drawRoadElement(
												g,
												nullPunktX - j * cellSize,
												nullPunktY + i * cellSize);
										}
									}
								}
							}
							// bottom lane
							if (index == 1) {

								gp.drawRouteSingleLane(
									g,
									nullPunktX + cellSize,
									nullPunktY,
									h,
									b);
								for (int i = list.length - 1; i >= 0; i--) {
									for (int j = list[i].length - 1; j >= 0; j--) {
										if (list[i][j] instanceof Car) {
											gp.drawCar(
												g,
												nullPunktX + cellSize - i * cellSize,
												nullPunktY + j * cellSize);

										} else {
											gp.drawRoadElement(
												g,
												nullPunktX + cellSize - i * cellSize,
												nullPunktY + j * cellSize);
										}
									}
								}
							}
							//	right lane
							if (index == 2) {

								gp.drawRouteSingleLane(g, nullPunktX, nullPunktY, b, h);
								for (int i = list.length - 1; i >= 0; i--) {
									for (int j = list[i].length - 1; j >= 0; j--) {
										if (list[i][j] instanceof Car) {
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
							}
							// top lane
							if (index == 3) {

								gp.drawRouteSingleLane(
									g,
									nullPunktX + cellSize,
									nullPunktY - b,
									h,
									b);
								for (int i = list.length - 1; i >= 0; i--) {
									for (int j = list[i].length - 1; j >= 0; j--) {
										if (list[i][j] instanceof Car) {
											gp.drawCar(
												g,
												nullPunktX + cellSize + i * cellSize,
												nullPunktY - j * cellSize);

										} else {
											gp.drawRoadElement(
												g,
												nullPunktX + cellSize + i * cellSize,
												nullPunktY - j * cellSize);

										}
									}
								}
							}
						}
			
					
					route = (RouteSingleLane) routeList[index++];
					list = route.getList();
					}

				}
	}


