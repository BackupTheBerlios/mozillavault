/*
 * Created on 15.10.2003 $Id: CellTraffic.java,v 1.14 2003/10/29 15:41:24
 * jsprenger Exp $
 */
package celltraffic;

import gui.ButtonPanel;
import gui.DiagramPanel;
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
import objects.RouteMultiLane;
import objects.RouteSingleLane;
import objects.Source;

/**
 * @author Jonas Sprenger,Tim Franz
 */
public class CellTraffic {

	public static void runMultiLane() {
		JFrame frame = new JFrame("Cell Traffic");
				JPanel panel = new JPanel();
				GridLayout gridLayout = new GridLayout(1, 2);
				BorderLayout bl = new BorderLayout();

				RouteMultiLane rsl1 = new RouteMultiLane();
				Source s = new Source();
				Drain d = new Drain();
				s.setNextRoute(rsl1);

				rsl1.setNextRoute(d);
				s.setNextRoute(rsl1);

				GraphikPanel gp = new GraphikPanel(rsl1);
				ButtonPanel buttons = new ButtonPanel(rsl1, s, gp);
				rsl1.addObserver(buttons);

				WeiterAction wa = buttons.getWeiterAction();
				wa.addActionListener(s);
				wa.addActionListener(rsl1);
				wa.addActionListener(d);

				frame.getContentPane().add(BorderLayout.CENTER, gp);
				frame.getContentPane().add(BorderLayout.SOUTH, buttons);
				frame.setSize(800, 400);
				WindowListener l = new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				};
				frame.addWindowListener(l);
				frame.setVisible(true);
		
	}

	public static void runSingleLane() {
		JFrame frame = new JFrame("Cell Traffic");
		JPanel panel = new JPanel();
	/*	GridLayout gridLayout = new GridLayout(2, 2);
		BorderLayout bl = new BorderLayout();*/

		RouteSingleLane rsl1 = new RouteSingleLane();
		Source s = new Source();
		Drain d = new Drain();
		s.setNextRoute(rsl1);

		rsl1.setNextRoute(d);
		s.setNextRoute(rsl1);

		GraphikPanel gp = new GraphikPanel(rsl1);
		ButtonPanel buttons = new ButtonPanel(rsl1, s, gp);
        DiagramPanel dgp = new DiagramPanel(rsl1);
		rsl1.addObserver(buttons);

		WeiterAction wa = buttons.getWeiterAction();
		wa.addActionListener(s);
		wa.addActionListener(rsl1);
		wa.addActionListener(d);

		frame.getContentPane().add(BorderLayout.CENTER, gp);
		frame.getContentPane().add(BorderLayout.SOUTH, buttons);
        frame.getContentPane().add(BorderLayout.NORTH, dgp);
		frame.setSize(800, 400);
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		frame.addWindowListener(l);
		frame.setVisible(true);
	}

	public static void runCrossLane() {
		JFrame frame = new JFrame("Cell Traffic");
		JPanel panel = new JPanel();
		GridLayout gridLayout = new GridLayout(1, 2);
		BorderLayout bl = new BorderLayout();
		//<<<<<<< CellTraffic.java
		//  RouteSingleLane s = new RouteSingleLane(300);

		//   s.setGraphikPanel(gp);
		/*
		 * //======= RouteSingleLane s = new RouteSingleLane(300); GraphikPanel
		 * gp = new GraphikPanel(s); s.setGraphikPanel(gp);
		 *  
		 */

		//RouteSingleLane rsl1 = new RouteSingleLane();
		RouteCrossLane rsl1 = new RouteCrossLane(2, 2);
		GraphikPanel gp = new GraphikPanel(rsl1);
		//  Source s1= new Source();
		Drain d1 = new Drain();
		Source s = new Source();
		rsl1.setNextRoute(d1);
		//s.setNextRoute(rsl1);

		/*
		 * for(int i=1; i <50;i++){ // s1.update(); // rsl1.update(); //
		 * s1.update();
		 */

		ButtonPanel buttons = new ButtonPanel(rsl1, s, gp);
		//###################test jonas
		Object lanes[] = rsl1.getLane();
		WeiterAction wa = buttons.getWeiterAction();

		wa.addActionListener(rsl1);
		for (int i = 0; i < lanes.length;i++) {
			
			if(i % 2 !=0)
			{
			s = new Source();
			RouteSingleLane tmpR = (RouteSingleLane) lanes[i];
			tmpR.setNextRoute(new Drain());
			s.setNextRoute(tmpR);
			tmpR.setNextRoute(rsl1);
			wa.addActionListener(s);
			wa.addActionListener(tmpR);

			}
			else{
				RouteSingleLane tmpR = (RouteSingleLane) lanes[i];
				tmpR.setNextRoute(new Drain());
				wa.addActionListener(tmpR);
				
										
			}
				

		}

		//		###################test jonas
		rsl1.addObserver(buttons);
		//	panel.add(gp,BorderLayout.WEST);
		//	panel.add(buttons,BorderLayout.EAST);
		// frame.getContentPane().add(panel);

		//frame.getContentPane().setLayout(bl);

		frame.getContentPane().add(BorderLayout.CENTER, gp);
		frame.getContentPane().add(BorderLayout.SOUTH, buttons);
		frame.setSize(800, 500);
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		frame.addWindowListener(l);
		//frame.pack();
		frame.setVisible(true);

	}

	public static void main(String args[]) {
		//runMultiLane();
		runSingleLane();
		//runCrossLane();

	}
}
