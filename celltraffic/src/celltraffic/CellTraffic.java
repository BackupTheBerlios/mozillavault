/*
 * Created on 15.10.2003
 * $Id: CellTraffic.java,v 1.4 2003/10/25 12:38:36 jsprenger Exp $
 */
package celltraffic;

import gui.ButtonPanel;
import gui.GraphikPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.RouteCrossLane;

/**
 * @author Jonas Sprenger
 */
public class CellTraffic {

    public static void main(String args[]) {

        JFrame frame = new JFrame("Cell Traffic");
        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(1, 2);
        BorderLayout bl = new BorderLayout();
        RouteCrossLane s = new RouteCrossLane();
        GraphikPanel gp = new GraphikPanel(s);
        s.setGraphikPanel(gp);

        ButtonPanel buttons = new ButtonPanel(s, gp);

        //	panel.add(gp,BorderLayout.WEST);
        //	panel.add(buttons,BorderLayout.EAST);
       // frame.getContentPane().add(panel);

	    //frame.getContentPane().setLayout(bl);
        frame.getContentPane().add(BorderLayout.CENTER,gp);
        frame.getContentPane().add(BorderLayout.EAST,buttons);
        frame.setSize(800, 800);
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        frame.addWindowListener(l);
        //frame.pack();
        frame.setVisible(true);

    }
}
