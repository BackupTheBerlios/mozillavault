/*
 * $Id: Source.java,v 1.10 2003/10/30 10:36:08 moleman Exp $
 */
package objects;

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
        if (timer == interval) {


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

}
