/*
 * Created on 15.10.2003
 * $Id: CellTraffic.java,v 1.14 2003/10/29 15:41:24 jsprenger Exp $
 */
package celltraffic;

import gui.ButtonPanel;
import gui.GraphikPanel;
import gui.ButtonPanel.WeiterAction;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.Drain;
import objects.RouteCrossLane;
import objects.RouteSingleLane;
import objects.Source;

/**
 * @author Jonas Sprenger
 */
public class CellTraffic {

	public static void main(String args[]) {

		JFrame frame = new JFrame("Cell Traffic");
		JPanel panel = new JPanel();
		GridLayout gridLayout = new GridLayout(1, 2);
		BorderLayout bl = new BorderLayout();
		//<<<<<<< CellTraffic.java
		//  RouteSingleLane s = new RouteSingleLane(300);

		//   s.setGraphikPanel(gp);
		/*
		//=======
		RouteSingleLane s = new RouteSingleLane(300);
		GraphikPanel gp = new GraphikPanel(s);
		s.setGraphikPanel(gp);
		
		*/

		//RouteSingleLane rsl1 = new RouteSingleLane();
		RouteCrossLane rsl1 = new RouteCrossLane(1, 1);
		GraphikPanel gp = new GraphikPanel(rsl1);
		//  Source s1= new Source();
		Drain d1 = new Drain();
		Source s = new Source();
		rsl1.setNextRoute(d1);
		//s.setNextRoute(rsl1);

		/*  for(int i=1; i<50;i++){
		   //   s1.update();
		     // rsl1.update();
		      
		   //   s1.update();
		  }*/

		ButtonPanel buttons = new ButtonPanel(rsl1, s, gp);
		//###################test jonas 
		Object lanes[] = rsl1.getLane();
		WeiterAction wa = buttons.getWeiterAction();

		for (int i = 0; i < lanes.length;i++) {
			
			
			s = new Source();
			RouteSingleLane tmpR = (RouteSingleLane) lanes[i];
			tmpR.setNextRoute(new Drain());
			s.setNextRoute(tmpR);
			wa.addActionListener(s);
			wa.addActionListener(tmpR);
				
		}

		//		###################test jonas 
		rsl1.addObserver(buttons);
		//	panel.add(gp,BorderLayout.WEST);
		//	panel.add(buttons,BorderLayout.EAST);
		// frame.getContentPane().add(panel);

		//frame.getContentPane().setLayout(bl);

		frame.getContentPane().add(BorderLayout.CENTER, gp);
		frame.getContentPane().add(BorderLayout.SOUTH, buttons);
		frame.setSize(800, 400);
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
