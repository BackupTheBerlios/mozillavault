/*
 * Created on 15.10.2003 $Id: ButtonPanel.java,v 1.10 2003/10/29 05:21:22
 * moleman Exp $
 */
package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import objects.Route;

/**
 * @author Jonas Sprenger
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ButtonPanel extends JPanel implements Observer {
    JButton /* produzent, */
    weiter;
    JSlider speed;

    JLabel l_numVehicles;

    Route w, x;
    GraphikPanel gp;
    GridLayout gridLayout;
	WeiterAction wa;
    public ButtonPanel(Route w, Route x, GraphikPanel gp) {
        gridLayout = new GridLayout(3, 2);
        this.setLayout(gridLayout);
        l_numVehicles = new JLabel("numVehicles");
        this.add(l_numVehicles);
        JPanel p = new JPanel();
        speed = new JSlider(0, 100, 50);
        speed.addChangeListener(new SpeedAction());
        p.add(new JLabel("speed"));
        p.add(speed);
        this.add(p);
        /*
		 * produzent = new JButton("produzieren");
		 * produzent.addActionListener(new ProdAction());
		 */

        weiter = new JButton("weiter");
		wa = new WeiterAction();
		
        weiter.addActionListener(wa);
        this.add(weiter);

        this.w = w;
        this.x = x;
        this.gp = gp;
    }
	public WeiterAction getWeiterAction(){
		return wa;
	}
    public void update(Observable o, Object obj) {
        l_numVehicles.setText(obj.toString() );
        //System.out.println( name + " lacht �ber \"" + obj + "\"" );
    }

    /*
	 * private class ProdAction extends AbstractAction { public void
	 * actionPerformed(ActionEvent evt) { //if (!w.produzentBelegt()) {
	 * //w.addPoint(new Vehicle(0, 0));
	 * 
	 * //}
	 *  }
	 *  
	 */
    private class SpeedAction implements ChangeListener {
        int value = 0;
        public void stateChanged(ChangeEvent e) {
            int value = ((JSlider)e.getSource()).getValue();
            if (value != this.value) {
                System.out.println("Neuer Wert: " + value);
            }

        }
    }
    public  class WeiterAction extends AbstractAction {
        public void actionPerformed(ActionEvent evt) {
           // w.update();
            //x.update();
            fireActionPerformed();
            gp.repaint();

        }
		Vector listeners = new Vector();
			public void addActionListener(ActionListener l) {
				listeners.add(l);
			}
			public void removeActionListener(ActionListener l) {
				listeners.remove(l);
			}
			void fireActionPerformed() {
				if (listeners.size() == 0)
					return;
				ActionEvent e = new ActionEvent(this,1234, "updated");
				
				ActionListener l;
				for (int i = 0; i < listeners.size(); i++) {
					l = (ActionListener)listeners.get(i);
					l.actionPerformed(e);
				}
			}


    }

}
