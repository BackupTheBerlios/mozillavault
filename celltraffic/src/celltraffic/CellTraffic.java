/*
 * Created on 15.10.2003
 * $Id: CellTraffic.java,v 1.3 2003/10/23 18:00:16 moleman Exp $
 */
package celltraffic;

import gui.ButtonPanel;
import gui.GraphikPanel;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.RouteSingleLane;

/**
 * @author Jonas Sprenger
 */
public class CellTraffic {

    public static void main(String args[]) {

        JFrame frame = new JFrame("Cell Traffic");
        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(1, 2);
        RouteSingleLane s = new RouteSingleLane(300);
        GraphikPanel gp = new GraphikPanel(s);
        s.setGraphikPanel(gp);

        ButtonPanel buttons = new ButtonPanel(s, gp);

        //	panel.add(gp,BorderLayout.WEST);
        //	panel.add(buttons,BorderLayout.EAST);
        frame.getContentPane().add(panel);

        frame.getContentPane().setLayout(gridLayout);
        frame.getContentPane().add(gp);
        frame.getContentPane().add(buttons);
        frame.setSize(600, 600);
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
