/*
 * Created on 15.10.2003
 */
package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import objekte.Auto;
import objekte.StrasseEinspurig;

/**
 * @author Jonas Sprenger
 * 
 */
public class GraphikPanel extends JPanel {
    Canvas canvas;
    List list;
    StrasseEinspurig strasse;

    public GraphikPanel(StrasseEinspurig s) {
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
            System.out.println(
                "neues Objekt gezeichnet..." + ((Auto)list.get(i)).getX());
            g.fillRect(
                ((Auto)list.get(i)).getX(),
                ((Auto)list.get(i)).getY(),
                10,
                10);
        }

    }

}
