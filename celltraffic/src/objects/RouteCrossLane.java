/*
 * Created on 15.10.2003
 * $Id: RouteCrossLane.java,v 1.11 2003/10/31 01:56:47 moleman Exp $
 */
package objects;

import java.awt.Color;
import java.util.List;

/**
 * @author Jonas Sprenger
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class RouteCrossLane extends Route {
	int breite;
	List list;
	int timer;
	int interval = 20;
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
		timer = interval;

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
		timer = interval;

	}
	public boolean getAmpel(){
			return ampel;
		}
	
	public void update() {
		if (timer == interval) {
			timer =0;
			if(ampel) ampel = false;
			else ampel = true;
		} else{
			timer++;
		}
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
		//System.out.println("Kreuzung...." + x + " " + y + " r: " + r);
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
				//setVehicle(new EmptyVehicle(), x, y);
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
			Object tmp [][] = ((RouteSingleLane) nextRoute[index]).getRoad();
			//v.setDirection(0);
			v.setHandled(false);
			
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
		//else if (road[newx][newy] instanceof EmptyVehicle){
		
			//System.out.println("Fall isFree..." + isFree(newx, newy));
			
			v.setDirection(-1);
			road[newx][newy] = v;
			setVehicle(new EmptyVehicle(), oldx, oldy);
			
		
			//  if (v instanceof Car)
			//      System.out.println("set vehicle at " + x + "." + y);
		} else {
			//go straight if field is blocked 
			
			setVehicle(v, oldx, oldy);
			//System.err.println("Fall behoben.........."+v.getDirection());
			if(rg.getRandom2() < 0.3){
			v.setDirection(2);
			System.err.println("keinen Bock mehr, fahre rechts");
			v.setHandled(false);
			} else{
				System.err.println("!!!!!!!!!!!ich bin geduldig!");
			}
			/*for(int _i=0; _i < road.length;_i++){
				for( int _j=0; _j<road[_i].length;_j++){	
					System.out.println(getVehicle(_i,_j).toString());
				}
				}*/
		

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
