/*
 * Created on 15.10.2003
 * $Id: RouteCrossLane.java,v 1.9 2003/10/30 14:16:55 moleman Exp $
 */
package objects;

import java.awt.Color;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Jonas Sprenger
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class RouteCrossLane extends Route {
	int breite;
	List list;
	
	boolean ampel=false;
	
	/*
	 * 
	 * index 0 = left side <----
	 * index 1 = left side ---->
	 * index 2 = bottom v
	 * index 3 = bottom A
	 * index 4 = right side ---->
	 * index 5 = right side <----
	 * index 6 = top A
	 * index 7 = top v
	 */

	Object nextRoute[] = new Object[8];

	//TODO Random generator holen 
	public RandomGenerator rg;
	
	public Color getR() {
			return new Color(rg.getRandom());
		}
	public Object[] getLane() {
		return nextRoute;
	}
	public int getIndexOfRoute(RouteSingleLane srl) {
		for (int i = 0; i < nextRoute.length; i++) {
			if (nextRoute[i] == srl)
				return i;
		}
		return 0;

	}
	public RouteCrossLane() {
		super();
		rg = new RandomGenerator();

	}
	public RouteCrossLane(int x, int y) {
		super(x, y);
		rg = new RandomGenerator();
		nextRoute[0] = new RouteSingleLane();
		nextRoute[1] = new RouteSingleLane();
		nextRoute[2] = new RouteSingleLane();
		nextRoute[3] = new RouteSingleLane();
		nextRoute[4] = new RouteSingleLane();
		nextRoute[5] = new RouteSingleLane();
		nextRoute[6] = new RouteSingleLane();
		nextRoute[7] = new RouteSingleLane();
		ampelTimer();

	}
	public boolean getAmpel(){
			return ampel;
		}
		class Task extends TimerTask
		{
		  public void run()
		  {
			if(ampel) ampel = false;
			else ampel = true;
		  }
		}
	
		public void ampelTimer(){
			Timer timer = new Timer();

				// nach 1 Sek geht's los und dann alle 5 Sekunden
			timer.schedule( new Task(), 1000, 2500 );

	
		}
	public void update() {
		
		
		for (int i = road[0].length - 1; i >= 0; i--) {
			for (int j = road.length - 1; j >= 0; j--) {
				if (road[j][i] instanceof Car) {
					getVehicle(j, i).setHandled(false);
					//getVehicle(j, i).setVelocity(1);
				}
			}
		}
		//	System.out.println("entering Route::update");

		for (int i = road[0].length - 1; i >= 0; i--) {
			for (int j = road.length - 1; j >= 0; j--) {
				if ((road[j][i] instanceof Car)
					&& (getVehicle(j, i).isHandled() == false)) {
					getVehicle(j, i).setHandled(true);
					Vehicle a = getVehicle(j, i);
					advance(j, i, a.getDirection());
				}
			}
		}
		numVehicles();
				density();
				flow();
				relFlow();
	}
	protected void advance(int x, int y, int r) {
		Vehicle a = getVehicle(x, y);
		// go straight if r = 0
		System.out.println("Kreuzung...." + x + " " + y + " r: " + r);
		if (r == 0) {
			if (x == 0 && y == 0) {
				//setVehicle(new EmptyVehicle(), x, y);
				setVehicle(a, 1, 0, x, y);

			} else if (x == 1 && y == 0) {
				///	setVehicle(new EmptyVehicle(), x, y);
				setVehicle(a, 1, 1, x, y);

			} else if (x == 0 && y == 1) {
				//setVehicle(new EmptyVehicle(), x, y);
				setVehicle(a, 0, 0, x, y);

			} else if (x == 1 && y == 1) {
				//setVehicle(new EmptyVehicle(), x, y);
				setVehicle(a, 0, 1, x, y);
			}
		}

		//		turn left if r = 1
		if (r == 1) {

			if (x == 0 && y == 0) {
				setVehicle(a, 1, 1, x, y);
			} else if (x == 1 && y == 0) {
				setVehicle(a, 0, 1, x, y);
			} else if (x == 0 && y == 1) {
				setVehicle(a, 1, 0, x, y);
			} else if (x == 1 && y == 1) {
				setVehicle(a, 0, 0, x, y);
			}
		}
		//		turn right if r = 2
		if (r == 2) {

			if (x == 0 && y == 0) {
				setVehicle(a, 0, 10, x, y);

			} else if (x == 1 && y == 0) {
				setVehicle(a, 0, 10, x, y);

			} else if (x == 0 && y == 1) {
				setVehicle(new EmptyVehicle(), x, y);
				setVehicle(a, 0, 10, x, y);

			} else if (x == 1 && y == 1) {
				setVehicle(a, 0, 10, x, y);
			}
		}
		// set car too next lane 
		if (r == -1) {
			setVehicle(a, 0, 10, x, y);
		}
	}
	protected void setVehicle(Vehicle v, int index, int oldx, int oldy) {
		int x = -1, y = -1;
		// check where car comes from.
		if (index == 1) {
			x = 1;
			y = 0;
		} else if (index == 3)
			x = y = 1;
		else if (index == 5) {
			x = 0;
			y = 1;
		} else if (index == 7)
			x = y = 0;
		
		if (road[x][y] instanceof EmptyVehicle) {
			v.setDirection(rg.getRandom3());
			road[x][y] = v;
			
			
			
		} else {
			System.out.println("Strasse nicht feei......" + x + "." + y + v);
			Object tmp [][] = ((RouteSingleLane) nextRoute[index]).getRoad();
			v.setHandled(true);
			
			((RouteSingleLane) nextRoute[index]).setVehicle(v, oldx,oldy-v.getVelocity());//tmp[0].length-1);
			
		}

	}

	protected void setVehicle(
		Vehicle v,
		int newx,
		int newy,
		int oldx,
		int oldy) {

		int i=0;
		if (oldx == 0 && oldy == 0)
			i = 0;
		else if (oldx == 1 && oldy == 1)
			i = 4;
		else if (oldx == 1 && oldy == 0)
			i = 2;
		else if (oldx == 0 && oldy == 1)
			i = 6;
		
		//System.err.println(" overflow " + overflow(newx, newy)+" i : "+i + "  "+ newx+ "  "+newy+" r "+ v.getDirection());
		if (overflow(newx, newy) > 0) {

			((RouteSingleLane) nextRoute[i]).setVehicle(v, 0,
			//newx,
			0); //overflow(newx, newy));
			setVehicle(new EmptyVehicle(), oldx, oldy);

		} else if (isFree(newx, newy,i)) {
			System.out.println("Fall isFree..." + isFree(newx, newy));
			
			setVehicle(new EmptyVehicle(), oldx, oldy);
			v.setDirection(-1);
			road[newx][newy] = v;
		
			//  if (v instanceof Car)
			//      System.out.println("set vehicle at " + x + "." + y);
		} else {
			setVehicle(v, oldx, oldy);
			System.out.println("Fall sollte nicht eintretten...");
		}
	}
	public boolean isFree(int x, int y, int i) {
		if ((overflow(x, y) > 0) && hasNextRoute()) {
			return ((RouteSingleLane) nextRoute[i]).isFree(x, overflow(x, y));
		} else if (road[x][y] instanceof EmptyVehicle) {
			return true;
		} else
			return false;
	}
}
