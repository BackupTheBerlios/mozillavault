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
import java.util.Timer;
import java.util.TimerTask;
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
    JButton weiter;
    JButton speedBtn;
    JSlider speed;
    JSlider sl_outputRate;
    JSlider sl_pTruck;
    JSlider sl_pBrake;
    JSlider sl_roadMaxSpeed;

    JLabel l_numVehicles;

    Route w;
    Route x;
    GraphikPanel gp;
    GridLayout gridLayout;
    WeiterAction wa;
    CelltrafficTask ctt;
    OutputrateAction orAction;
    TruckrateAction trAction;
    BrakeAction brAction;
    RoadMaxSpeedAction msAction;

    public ButtonPanel(Route w, Route x, GraphikPanel gp) {
      //  setSize(200,300);
        ctt = new CelltrafficTask();
        setTimer(5);
        gridLayout = new GridLayout(3, 3);
        this.setLayout(gridLayout);
        l_numVehicles = new JLabel("numVehicles");
       // this.add(l_numVehicles);
        JPanel p = new JPanel();
        speed = new JSlider(1, 20, 5);
        speed.addChangeListener(new SpeedAction());

        sl_outputRate = new JSlider(1, 10);
        sl_outputRate.addChangeListener(new OutputrateListener());
        
        sl_pTruck = new JSlider(0,100);
        sl_pTruck.addChangeListener( new TruckrateListener());
        
        sl_pBrake = new JSlider(0,100);
        sl_pBrake.addChangeListener( new BrakeListener());
        
        sl_roadMaxSpeed = new JSlider(0,5);
               sl_roadMaxSpeed.addChangeListener( new RoadMaxSpeedListener());

        speedBtn = new JButton("Stop");
        speedBtn.addActionListener(new speedBtnAction());
        p.add(new JLabel("simulation speed"));
        p.add(speed);

        p.add(new JLabel("vehicle production rate"));
        p.add(sl_outputRate);
        
        p.add(new JLabel("truck rate"));
        p.add(sl_pTruck);
        
        p.add(new JLabel("brake probability"));
        p.add(sl_pBrake);
        
        p.add(new JLabel("global max speed"));
                p.add(sl_roadMaxSpeed);
        
        p.add(new JLabel());
        p.add(speedBtn);
       
        /*
		 * produzent = new JButton("produzieren");
		 * produzent.addActionListener(new ProdAction());
		 */

        weiter = new JButton("weiter");
        wa = new WeiterAction();
        orAction = new OutputrateAction();
        trAction = new TruckrateAction();
        brAction = new BrakeAction();
        msAction = new RoadMaxSpeedAction();

        weiter.addActionListener(wa);
        p.add(new JLabel());
        p.add(weiter);
        this.add(p);
      
        this.w = w;
        this.x = x;
        this.gp = gp;

    }
    public WeiterAction getWeiterAction() {
        return wa;
    }

    public OutputrateAction getOutputrateAction() {
        return orAction;
    }
    
    public TruckrateAction getTruckrateAction() {
           return trAction;
       }
    
    public BrakeAction getBrakeAction() {
              return brAction;
          }
    
    public RoadMaxSpeedAction getRoadMaxSpeedAction(){
        return msAction;
    }

    public void update(Observable o, Object obj) {
        l_numVehicles.setText(obj.toString());
        //System.out.println( name + " lacht über \"" + obj + "\"" );
    }

    private class SpeedAction implements ChangeListener {
        int value = 0;
        public void stateChanged(ChangeEvent e) {
            int value = ((JSlider)e.getSource()).getValue();
            if (value != this.value) {
                //System.out.println("Neuer Wert: " + value);

                ctt.cancel();
                ctt = new CelltrafficTask();
                setTimer(value);
            }

        }
    }

    public class OutputrateListener implements ChangeListener {

        int value = 0;

        public void stateChanged(ChangeEvent e) {
            int value = ((JSlider)e.getSource()).getValue();
            if (value != this.value) {       
                orAction.fireActionPerformed(value);
            }

        }

    }
    
    public class BrakeListener implements ChangeListener {

           int value = 0;

           public void stateChanged(ChangeEvent e) {
               int value = ((JSlider)e.getSource()).getValue();
               if (value != this.value) {       
                   brAction.fireActionPerformed(value);
               }

           }

       }
    
    public class RoadMaxSpeedListener implements ChangeListener {

              int value = 0;

              public void stateChanged(ChangeEvent e) {
                  int value = ((JSlider)e.getSource()).getValue();
                  if (value != this.value) {       
                      msAction.fireActionPerformed(value);
                  }

              }

          }
    
    public class TruckrateListener implements ChangeListener {

            int value = 0;

            public void stateChanged(ChangeEvent e) {
                int value = ((JSlider)e.getSource()).getValue();
                if (value != this.value) {
                    trAction.fireActionPerformed(value);
                }

            }

        }
    
    
    

    public class OutputrateAction extends AbstractAction {
        Vector listeners = new Vector();

        public void addActionListener(ActionListener l) {
            listeners.add(l);
        }

        public void removeActionListener(ActionListener l) {
            listeners.remove(l);
        }

        public void actionPerformed(ActionEvent evt) {
            fireActionPerformed(Integer.parseInt( evt.getActionCommand() ));
        }

        void fireActionPerformed(int j) {
            if (listeners.size() == 0)
                return;
            ActionEvent e = new ActionEvent(this, 1235, Integer.toString(j));

            ActionListener l;
            for (int i = 0; i < listeners.size(); i++) {
                l = (ActionListener)listeners.get(i);
                l.actionPerformed(e);
            }
        }

    }
    
    public class RoadMaxSpeedAction extends AbstractAction {
            Vector listeners = new Vector();

            public void addActionListener(ActionListener l) {
                listeners.add(l);
            }

            public void removeActionListener(ActionListener l) {
                listeners.remove(l);
            }

            public void actionPerformed(ActionEvent evt) {
                fireActionPerformed(Integer.parseInt( evt.getActionCommand() ));
            }

            void fireActionPerformed(int j) {
                if (listeners.size() == 0)
                    return;
                ActionEvent e = new ActionEvent(this, 1238, Integer.toString(j));

                ActionListener l;
                for (int i = 0; i < listeners.size(); i++) {
                    l = (ActionListener)listeners.get(i);
                    l.actionPerformed(e);
                }
            }

        }
    
    public class TruckrateAction extends AbstractAction {
           Vector listeners = new Vector();

           public void addActionListener(ActionListener l) {
               listeners.add(l);
           }

           public void removeActionListener(ActionListener l) {
               listeners.remove(l);
           }

           public void actionPerformed(ActionEvent evt) {
               fireActionPerformed(Integer.parseInt( evt.getActionCommand() ));
           }

           void fireActionPerformed(int j) {
               if (listeners.size() == 0)
                   return;
               ActionEvent e = new ActionEvent(this, 1236, Integer.toString(j));

               ActionListener l;
               for (int i = 0; i < listeners.size(); i++) {
                   l = (ActionListener)listeners.get(i);
                   l.actionPerformed(e);
               }
           }

       }
    
    
    public class BrakeAction extends AbstractAction {
               Vector listeners = new Vector();

               public void addActionListener(ActionListener l) {
                   listeners.add(l);
               }

               public void removeActionListener(ActionListener l) {
                   listeners.remove(l);
               }

               public void actionPerformed(ActionEvent evt) {
                   fireActionPerformed(Integer.parseInt( evt.getActionCommand() ));
               }

               void fireActionPerformed(int j) {
                   if (listeners.size() == 0)
                       return;
                   ActionEvent e = new ActionEvent(this, 1237, Integer.toString(j));

                   ActionListener l;
                   for (int i = 0; i < listeners.size(); i++) {
                       l = (ActionListener)listeners.get(i);
                       l.actionPerformed(e);
                   }
               }

           }
    
    

    public class speedBtnAction extends AbstractAction {

        public void actionPerformed(ActionEvent evt) {
            System.out.println(evt.getActionCommand());

            if (evt.getActionCommand().equals("Start")) {
                speedBtn.setText("Stop");
                ctt = new CelltrafficTask();
                setTimer(speed.getValue());

            } else {
                ctt.cancel();
                speedBtn.setText("Start");

            }
        }

    }

    public class WeiterAction extends AbstractAction {

        public void actionPerformed(ActionEvent evt) {
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
            ActionEvent e = new ActionEvent(this, 1234, "updated");

            ActionListener l;
            for (int i = 0; i < listeners.size(); i++) {
                l = (ActionListener)listeners.get(i);
                l.actionPerformed(e);
            }
        }

    }

    class CelltrafficTask extends TimerTask {
        public void run() {
            // TODO int setzen
            wa.fireActionPerformed();
            gp.repaint();

        }
    }

    public void setTimer(int i) {
        Timer timer = new Timer();
        int interval = 30 * (21 - i);
        timer.schedule(ctt, 0, interval);

    }

}
