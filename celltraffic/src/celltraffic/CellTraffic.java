/*
 * Created on 15.10.2003
 * $Id: CellTraffic.java,v 1.5 2003/10/25 14:41:22 jsprenger Exp $
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
import objects.RouteSingleLane;
import objects.Vehicle;

/**
 * @author Jonas Sprenger
 */
public class CellTraffic {

    public static void main(String args[]) {

        JFrame frame = new JFrame("Cell Traffic");
        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(1, 2);
        BorderLayout bl = new BorderLayout();
        RouteSingleLane s = new RouteSingleLane(300);
        GraphikPanel gp = new GraphikPanel(s);
        s.setGraphikPanel(gp);
		
		//######################## Test
		s.road[0][2]= new Vehicle();
		s.road[0][4]= new Vehicle();
		s.road[0][10]= new Vehicle();
				s.road[0][20]= new Vehicle();
				s.road[0][22]= new Vehicle();
		s.road[0][2]= new Vehicle();
				s.road[0][40]= new Vehicle();
				s.road[0][42]= new Vehicle();
		gp.setList(s.road);
		//#######################ENDE Test	
		
		ButtonPanel buttons = new ButtonPanel(s, gp);

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
