/*
 * Created on 15.10.2003
 * $Id: CellTraffic.java,v 1.7 2003/10/28 15:48:56 jsprenger Exp $
 */
package celltraffic;

import gui.ButtonPanel;
import gui.GraphikPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.Car;
import objects.Drain;
import objects.RouteCrossLane;
import objects.RouteSingleLane;
import objects.Source;
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
		s.road[0][2]= new Car();
		s.road[0][4]= new Car();
		s.road[0][10]= new Car();
				s.road[0][20]= new Car();
				s.road[0][22]= new Car();
		s.road[0][2]= new Car();
				s.road[0][40]= new Car();
				s.road[0][42]= new Car();
		gp.setList(s.road);
		
		//#######################ENDE Test	
		
		


        
        RouteSingleLane rsl1 = new RouteSingleLane();
        Source s1= new Source();
        Drain d1 = new Drain();
        
        rsl1.setNextRoute(s1);
        s1.setNextRoute(d1);
        
        for(int i=1; i<50;i++){
            s1.update();
            rsl1.update();
            s1.update();
        }
        
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
