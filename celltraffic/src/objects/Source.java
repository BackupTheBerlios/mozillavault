/*
 * $Id: Source.java,v 1.11 2003/10/31 01:56:47 moleman Exp $
 */
package objects;

import java.awt.event.ActionEvent;
import java.util.Random;

public class Source extends Route {

    int timer = 0;
    int interval = 2;
    double p_truck = 0.4;

    public Source() {
        super(1, 1);
        speedLimit = 1;
    }

    public void update() {
        timer++;
        if (timer >= interval) {


            Random rand = new Random();
            double rnf = rand.nextFloat();
           // System.out.println(rnf);

          //  Random rand = new Random();
          //  double rnf = rand.nextFloat();
           // System.out.println(rnf);

            if (getVehicle(0, 0) instanceof EmptyVehicle) {
                if (Math.random()  < p_truck) {
                    Truck v = new Truck();
                    v.setHandled(false);
                    setVehicle(v, 0, 0);
             //       System.out.println("new Truck created");
                } else {
                    Car v = new Car();
                    v.setHandled(false);
                    setVehicle(v, 0, 0);
				//System.out.println("new car created");
               }
            }

            super.update();
            timer = 0;
        }

    }

    /**
	 * @return Returns the interval.
	 */
    public int getInterval() {
        return interval;
    }

    /**
	 * @param interval The interval to set.
	 */
    public void setInterval(int interval) {
        this.interval = interval;
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getID() == 1235){ //output rate
            int newInterval=11 - Integer.parseInt(e.getActionCommand());
        System.out.println("ändere outputrate: " +newInterval);  
         interval=newInterval;
        }
        else if(e.getID()==1236){ // p_truck
            p_truck=(double)(Double.parseDouble(e.getActionCommand()) / 100.00);
            System.out.println("pTruck: "+Double.toString( p_truck));
        }
        else{
                super.actionPerformed(e) ;
        }

    }

}
