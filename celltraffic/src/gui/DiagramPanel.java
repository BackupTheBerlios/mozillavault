/*
 * $id$
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import objects.Route;

public class DiagramPanel extends JPanel implements ActionListener  {
    Route route;
    int offsetX = 0;
    int offsetY = 0;

    public DiagramPanel(Route r) {
        route = r;
        super.setBounds(0, 0, 150, 150);
        setSize(150,150);
    }

    public void changed() {

    }

    public void paint(Graphics g) {
        drawAxes(g, 110,110, 100, 100);
        drawData(g);

    }
    public void drawAxes(Graphics g, int x, int y, int length, int height) {
        g.setColor(Color.black);
        g.drawLine(x + offsetX, y + offsetY, x + offsetX + length, y + offsetY);
        g.drawLine(x + offsetX, y + offsetY, x + offsetX, y + offsetY - height);
        drawArrow(g, x + offsetX + length, y + offsetY, 90);
        drawArrow(g, x + offsetX, y + offsetY - height, 0);

    }

    public void drawData(Graphics g) {
        g.drawRect(offsetX + (150-(int)route.density()*100) , offsetY+(150-(route.flow()*10)),1,1 );
    }

    public void drawArrow(Graphics g, int x, int y, int orientation) {
        Polygon arrow = new Polygon();
        if (orientation == 0) {
            arrow.addPoint(x + 4, y);
            arrow.addPoint(x - 4, y);
            arrow.addPoint(x, y - 8);
        }
        if (orientation == 90) {
            arrow.addPoint(x, y + 4);
            arrow.addPoint(x, y - 4);
            arrow.addPoint(x + 8, y);
        }

        g.setColor(Color.black);
        g.fillPolygon(arrow);

    }
    public void actionPerformed(ActionEvent e) {
                if(e.getID() == 1234){
              //  paint();
                    }
               

            }    
    
  

}
