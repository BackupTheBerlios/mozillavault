/*
 * $Id: GraphikPanel.java,v 1.17 2003/10/30 19:51:21 moleman Exp $
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

import objects.Car;
import objects.Route;
import objects.RouteCrossLane;
import objects.RouteMultiLane;
import objects.RouteSingleLane;
import objects.Truck;

/**
 * @author Jonas Sprenger
 *  
 */
public class GraphikPanel extends JPanel {

    Object list[][];
    Object routeList[];
    Route strasse, route;
    int cellSize = 15;
    int nullPunktX = 500; //offset for cross lane
    int nullPunktY = 250; //offset for cross lane
    int offsetX = 50; //offset for standard lane
    int offsetY = 100; //offset for standard lane
    int co = 0;

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
        int krH = 0, krB = 0; // höhe und breite der Kreuzung
        route = strasse;
        list = strasse.getList();

        if (route instanceof RouteCrossLane) {
            CrossLanePaint clp =
                new CrossLanePaint(nullPunktX, nullPunktY, route);
            clp.init(g, this);
        } else if (strasse instanceof RouteSingleLane) {
            drawRouteSingleLaneL(g);
            for (int i = list.length - 1; i >= 0; i--) {
                for (int j = list[i].length - 1; j >= 0; j--) {
                    if (list[i][j] instanceof Truck) {
                        drawTruckMulti(g, j, i);
                    } else if (list[i][j] instanceof Car) {
                        drawCarMulti(g, j, i);
                    } else {
                        drawRoadElementMulti(g, j, i);
                    }
                }
            }
        } else if (strasse instanceof RouteMultiLane) {
            drawRouteMultiLaneL(g);
            for (int i = list.length - 1; i >= 0; i--) {
                for (int j = list[i].length - 1; j >= 0; j--) {
                    int y;
                    y = (i == 0 ? 1 : 0);
                    if (list[i][j] instanceof Truck) {
                        drawTruckMulti(g, j, y);
                    } else if (list[i][j] instanceof Car) {
                        drawCarMulti(g, j, y);
                    } else {
                        drawRoadElementMulti(g, j, y);
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
        //g.fillRect(nullPunktX, nullPunktY, 2*cellSize, 2*cellSize);
        g.drawRect(nullPunktX, nullPunktY, 2 * cellSize, 2 * cellSize);

        //g.fillRect(x, y, w, h);
        //	System.out.println("zeichnet...StrasseKreuzung");

    }

    void drawRouteSingleLane(Graphics g, int x, int y, int w, int h) {
        g.setColor(Color.WHITE);
        g.drawRect(x, y, w, h);
        //g.fillRect(x, y, w, h);
        //System.out.println("zeichnet...StrasseEinspurig " + x + " " + y);
    }

    void drawRouteSingleLaneL(Graphics g) {
        g.drawRect(
            offsetX - 1,
            offsetY - 1,
            list[0].length * cellSize + 1,
            list.length * cellSize + 1);

        //   System.out.println("zeichnet...StrasseEinspurig");
    }
    void drawLigth(Graphics g, int x, int y, boolean status) {
        if (status)
            g.setColor(Color.green);
        else
            g.setColor(Color.red);

        g.fillOval(x, y, cellSize - 1, cellSize - 1);

    }
    void drawCar(Graphics g, int x, int y) {
        g.setColor(Color.yellow);
        g.fillRect(x, y, cellSize - 1, cellSize - 1);
    }

    void drawShadedVehicle(Graphics g, int x, int y, Color c) {
        g.setColor(c);

        g.fillRect(
            offsetX + x * cellSize,
            offsetY + y * cellSize,
            cellSize - 1,
            cellSize - 1);
        g.setColor(new Color(0, 0, 0));
        g.drawLine(
            (offsetX + x * cellSize) + 1,
            (offsetY + y * cellSize) + cellSize - 1,
            (offsetX + x * cellSize) + cellSize - 1,
            (offsetY + y * cellSize) + cellSize - 1);
        g.drawLine(
            (offsetX + x * cellSize) + cellSize - 1,
            (offsetY + y * cellSize) + 1,
            (offsetX + x * cellSize) + cellSize - 1,
            (offsetY + y * cellSize) + cellSize - 1);
        g.setColor(new Color(255, 255, 255));
        g.drawLine(
            (offsetX + x * cellSize),
            (offsetY + y * cellSize),
            (offsetX + x * cellSize) + cellSize - 1,
            (offsetY + y * cellSize));
        g.drawLine(
            (offsetX + x * cellSize),
            (offsetY + y * cellSize),
            (offsetX + x * cellSize),
            (offsetY + y * cellSize));

    }
    void drawCarMulti(Graphics g, int x, int y) {
        drawShadedVehicle(g, x, y, Color.yellow);

    }
    void drawTruck(Graphics g, int x, int y) {
        //    drawShadedVehicle(g,x,y,Color.blue);
        g.setColor(Color.blue);
        g.fillRect(x, y, cellSize - 1, cellSize - 1);

    }
    void drawTruckMulti(Graphics g, int x, int y) {
        drawShadedVehicle(g, x, y, Color.blue);
    }
    void drawRoadElement(Graphics g, int x, int y) {
        //g.setColor(EmptyVehicle.getColor());
        g.setColor(Color.LIGHT_GRAY);
        //g.setColor(Color.BLACK);
        g.fillRect(x, y, cellSize - 1, cellSize - 1);
        g.fillRect(
            nullPunktX + x * cellSize,
            nullPunktY + y * cellSize,
            cellSize - 1,
            cellSize - 1);
    }
    void drawRoadElementMulti(Graphics g, int x, int y) {
        //g.setColor(EmptyVehicle.getColor());
        g.setColor(Color.LIGHT_GRAY);
        //g.setColor(Color.BLACK);
        g.fillRect(
            offsetX + x * cellSize,
            offsetY + y * cellSize,
            cellSize - 1,
            cellSize - 1);
    }
    void drawRouteMultiLaneL(Graphics g) {
        g.drawRect(
            offsetX - 1,
            offsetY - 1,
            list[0].length * cellSize + 1,
            list.length * cellSize + 1);

        //   System.out.println("zeichnet...StrasseEinspurig");
    }

}
