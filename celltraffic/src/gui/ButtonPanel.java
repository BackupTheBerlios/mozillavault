/*
 * Created on 15.10.2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;

import objekte.Auto;
import objekte.StrasseEinspurig;

/**
 * @author Jonas Sprenger
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ButtonPanel extends JPanel {
	JButton produzent,weiter;
	
	StrasseEinspurig w;
	GraphikPanel gp;
	GridLayout gridLayout;
	public ButtonPanel(StrasseEinspurig w, GraphikPanel gp) {
		gridLayout = new GridLayout(2, 1);	
		this.setLayout(gridLayout);
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
			if (!w.produzentBelegt()) {
				System.out.println("neues Object erzeugt...");
				w.addPoint(new Auto(0, 0));

			}

		}

	}
	private class WeiterAction extends AbstractAction {
		public void actionPerformed(ActionEvent evt) {

			System.out.println("neues Object erzeugt...");
			List l = w.getList();
			for (int i = 0; i < l.size(); i++) {
				{
					Auto a = (Auto) l.get(i);
					a.setX(a.getX()+10);
					gp.repaint();
				}

			}

		}

	}

}
