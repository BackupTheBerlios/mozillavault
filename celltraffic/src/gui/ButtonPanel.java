/*
 * Created on 15.10.2003
 *$Id: ButtonPanel.java,v 1.7 2003/10/25 14:41:22 jsprenger Exp $
 */
package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import objects.Route;
import objects.Vehicle;

/**
 * @author Jonas Sprenger
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ButtonPanel extends JPanel {
	JButton produzent, weiter;
	JSlider speed;

	Route w;
	GraphikPanel gp;
	GridLayout gridLayout;
	public ButtonPanel(Route w, GraphikPanel gp) {
		gridLayout = new GridLayout(3, 1);
		this.setLayout(gridLayout);

		JPanel p = new JPanel();
		speed = new JSlider(0, 100, 50);
		speed.addChangeListener(new SpeedAction());
		p.add(new JLabel("speed"));
		p.add(speed);
		this.add(p);
		
		produzent = new JButton("produzieren");
		produzent.addActionListener(new ProdAction());
		this.add(produzent);

		weiter = new JButton("weiter");
		weiter.addActionListener(new WeiterAction());
		this.add(weiter);

		this.w = w;
		this.gp = gp;
	}

	private class ProdAction extends AbstractAction {
		public void actionPerformed(ActionEvent evt) {
			//if (!w.produzentBelegt()) {
				//w.addPoint(new Vehicle(0, 0));

			//}
			
					
		}

	}
	private class SpeedAction implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			int value = ((JSlider)e.getSource()).getValue();
			System.out.println("Neuer Wert: " + value);
			
	}
	}
private class WeiterAction extends AbstractAction {
	public void actionPerformed(ActionEvent evt) {

		Object l[][] = w.getList();
		for (int i = 0; i < l.length; i++) {
			{
				Vehicle a = (Vehicle) l[i][0];
				//a.setX(a.getX()+10);
				gp.repaint();
			}

		}

	}

}

}
