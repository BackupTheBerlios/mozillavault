/*
 * Created on 15.10.2003 $Id: CellTraffic.java,v 1.14 2003/10/29 15:41:24
 * jsprenger Exp $
 */
package celltraffic;

import gui.ButtonPanel;
import gui.DiagramPanel;
import gui.GraphikPanel;
import gui.ButtonPanel.BrakeAction;
import gui.ButtonPanel.OutputrateAction;
import gui.ButtonPanel.RoadMaxSpeedAction;
import gui.ButtonPanel.TruckrateAction;
import gui.ButtonPanel.WeiterAction;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        GridLayout gridLayout = new GridLayout(2, 2);
        //  FlowLayout gridLayout = new FlowLayout();
        BorderLayout bl = new BorderLayout();

        RouteMultiLane rsl1 = new RouteMultiLane(150);
        Source s = new Source();
        Drain d = new Drain();
        s.setNextRoute(rsl1);

        rsl1.setNextRoute(d);
        s.setNextRoute(rsl1);

        GraphikPanel gp = new GraphikPanel(rsl1);
        ButtonPanel buttons = new ButtonPanel(rsl1, s, gp);
        rsl1.addObserver(buttons);
        DiagramPanel dgp = new DiagramPanel(rsl1);

        WeiterAction wa = buttons.getWeiterAction();
        wa.addActionListener(s);
        wa.addActionListener(rsl1);
        wa.addActionListener(d);
        //   wa.addActionListener(dgp);

        OutputrateAction orAction = buttons.getOutputrateAction();
        orAction.addActionListener(s);

        TruckrateAction trAction = buttons.getTruckrateAction();
        trAction.addActionListener(s);

        BrakeAction brAction = buttons.getBrakeAction();
        brAction.addActionListener(s);
        brAction.addActionListener(rsl1);

        RoadMaxSpeedAction msAction = buttons.getRoadMaxSpeedAction();
        msAction.addActionListener(rsl1);

        frame.getContentPane().add(gp);
        //  frame.getContentPane().add(dgp);
        frame.getContentPane().add(buttons);

        /*
		 * frame.getContentPane().add(BorderLayout.CENTER, gp);
		 * frame.getContentPane().add(BorderLayout.EAST, buttons);
		 */

        frame.setSize(800, 700);
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        frame.getContentPane().setLayout(gridLayout);
        frame.addWindowListener(l);
        frame.setVisible(true);

    }

    public static void runSingleLane() {
        JFrame frame = new JFrame("Cell Traffic");
        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(2, 2);
        //  FlowLayout gridLayout = new FlowLayout();
        BorderLayout bl = new BorderLayout();

        RouteSingleLane rsl1 = new RouteSingleLane(150);
        Source s = new Source();
        Drain d = new Drain();
        s.setNextRoute(rsl1);

        rsl1.setNextRoute(d);
        s.setNextRoute(rsl1);

        GraphikPanel gp = new GraphikPanel(rsl1);
        ButtonPanel buttons = new ButtonPanel(rsl1, s, gp);
        rsl1.addObserver(buttons);
        DiagramPanel dgp = new DiagramPanel(rsl1);

        WeiterAction wa = buttons.getWeiterAction();
        wa.addActionListener(s);
        wa.addActionListener(rsl1);
        wa.addActionListener(d);
        //   wa.addActionListener(dgp);

        OutputrateAction orAction = buttons.getOutputrateAction();
        orAction.addActionListener(s);

        TruckrateAction trAction = buttons.getTruckrateAction();
        trAction.addActionListener(s);

        BrakeAction brAction = buttons.getBrakeAction();
        brAction.addActionListener(s);
        brAction.addActionListener(rsl1);

        RoadMaxSpeedAction msAction = buttons.getRoadMaxSpeedAction();
        msAction.addActionListener(rsl1);

        frame.getContentPane().add(gp);
        //  frame.getContentPane().add(dgp);
        frame.getContentPane().add(buttons);

        frame.setSize(800, 700);
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        frame.getContentPane().setLayout(gridLayout);
        frame.addWindowListener(l);
        frame.setVisible(true);
    }

    public static void runCrossLane() {
        JFrame frame = new JFrame("Cell Traffic");
        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(1, 2);
        BorderLayout bl = new BorderLayout();

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
        for (int i = 0; i < lanes.length; i++) {

            if (i % 2 != 0) {
                s = new Source();
                RouteSingleLane tmpR = (RouteSingleLane)lanes[i];
                tmpR.setNextRoute(new Drain());
                s.setNextRoute(tmpR);
                tmpR.setNextRoute(rsl1);
                wa.addActionListener(s);
                wa.addActionListener(tmpR);

                OutputrateAction orAction = buttons.getOutputrateAction();
                orAction.addActionListener(s);

                TruckrateAction trAction = buttons.getTruckrateAction();
                trAction.addActionListener(s);

                BrakeAction brAction = buttons.getBrakeAction();
                brAction.addActionListener(s);
                brAction.addActionListener(tmpR);

                RoadMaxSpeedAction msAction = buttons.getRoadMaxSpeedAction();
                msAction.addActionListener(tmpR);

            } else {
                RouteSingleLane tmpR = (RouteSingleLane)lanes[i];
                tmpR.setNextRoute(new Drain());
                wa.addActionListener(tmpR);

                BrakeAction brAction = buttons.getBrakeAction();

                brAction.addActionListener(tmpR);

                RoadMaxSpeedAction msAction = buttons.getRoadMaxSpeedAction();
                msAction.addActionListener(tmpR);

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
        frame.setSize(1000, 700);
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        frame.addWindowListener(l);
        //frame.pack();
        frame.setVisible(true);

    }
    public class ComboAction extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox)e.getSource();
            String Name = (String)cb.getSelectedItem();

            if (Name.equals("SingleLane"))
                runSingleLane();
            else if (Name.equals("MultiLane"))
                runMultiLane();
            else if (Name.equals("CrossLane"))
                runCrossLane();
        }

    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("CellTraffic");
        JPanel panel = new JPanel();

        String[] Strings = { "SingleLane", "MultiLane", "CrossLane" };
        JComboBox box = new JComboBox(Strings);
        CellTraffic ct = new CellTraffic();
        box.addActionListener(ct.new ComboAction());
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 2));
        panel.add(new JLabel("Welcome, please chose...."));
        panel.add(box, "North");
        frame.getContentPane().add(panel);
        frame.setLocation(100, 100);
        frame.setSize(200, 200);
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        frame.addWindowListener(l);
        frame.setVisible(true);

        //runMultiLane();
        //runSingleLane();
        //runCrossLane();

    }
}
