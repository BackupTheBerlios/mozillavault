/*
 * Created on 15.10.2003
 * $Id: GraphikPanel.java,v 1.2 2003/10/23 17:58:19 moleman Exp $
 */
package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import objects.Vehicle;
import objects.RouteSingleLane;

/**
 * @author Jonas Sprenger
 * 
 */
public class GraphikPanel extends JPanel {
    Canvas canvas;
    List list;
    RouteSingleLane strasse;

    public GraphikPanel(RouteSingleLane s) {
        this.strasse = s;
        list = strasse.getList();

    }
    public void setList(List l) {
        this.list = l;
    }
    public List getList() {
        return list;
    }
    public GraphikPanel() {
        list = new ArrayList();

    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawRect(0, 0, strasse.getLaenge(), strasse.getBreite());
        g.setColor(Color.black);
        System.out.println("zeichnet...");
        for (int i = 0; i < list.size(); i++) {
        //    System.out.println(
       //         "neues Objekt gezeichnet..." + ((Vehicle)list.get(i)).getX());
          /*  g.fillRect(
                ((Vehicle)list.get(i)).getX(),
                ((Vehicle)list.get(i)).getY(),
                10,
                10);*/
        }

    }

}
