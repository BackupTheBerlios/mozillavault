/*
 * Created on 15.10.2003
 * $Id: RouteCrossLane.java,v 1.4 2003/10/29 10:39:08 jsprenger Exp $
 */
package objects;

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
	/*
	 * 
	 * index 0 = left side
	 * index 1 = bottom
	 * index 2 = right
	 * index 3 = top
	 */
	
	Object nextRoute[] = new Object[4];

	//TODO Random generator holen 
	RandomGenerator rg;
	
	public Object[] getLane(){
		return nextRoute; 
	}
	
	public RouteCrossLane() {
		super();
		rg = new RandomGenerator();

	}
	public RouteCrossLane(int x,int y) {
		super(x,y);
		rg = new RandomGenerator();
		nextRoute[0]= new RouteSingleLane();
		nextRoute[1]= new RouteSingleLane();
		nextRoute[2]= new RouteSingleLane();
		nextRoute[3]= new RouteSingleLane();

	}
	public void update() {

		for (int i = road.length - 1; i > 0; i--) {
			for (int j = road[i].length - 1; j > 0; j--) {

				if (road[i][j] instanceof Car) {
					Vehicle v = getVehicle(i, j);
					v.setHandled(true);
					advance(0, i, rg.getRandom3());
				}
			}

		}
	}
	protected void advance(int x, int y, int r) {
		Vehicle a = getVehicle(x, y);
		// go straight if r = 0
		if (r == 0) {

			if (x == 0 && y == 0) {
				setVehicle(new EmptyVehicle(), x, y);
				setVehicle(a, 1, 0);
			} else if (x == 1 && y == 0) {
				setVehicle(new EmptyVehicle(), x, y);
				setVehicle(a, 1, 1);
			} else if (x == 0 && y == 1) {
				setVehicle(new EmptyVehicle(), x, y);
				setVehicle(a, 0, 0);
			} else if (x == 1 && y == 1) {
				setVehicle(new EmptyVehicle(), x, y);
				setVehicle(a, 0, 1);
			}
		}

		//		turn left if r = 1
		if (r == 1) {

			if (x == 0 && y == 0) {
				setVehicle(new EmptyVehicle(), x, y);
				setVehicle(a, 1, 1);
			} else if (x == 1 && y == 0) {
				setVehicle(new EmptyVehicle(), x, y);
				setVehicle(a, 0, 1);
			} else if (x == 0 && y == 1) {
				setVehicle(new EmptyVehicle(), x, y);
				setVehicle(a, 1, 0);
			} else if (x == 1 && y == 1) {
				setVehicle(new EmptyVehicle(), x, y);
				setVehicle(a, 0, 0);
			}
		}
//		turn right if r = 2
		if (r == 2) {

			if (x == 0 && y == 0) {
				setVehicle(new EmptyVehicle(), x, y);
				((Route)nextRoute[0]).setVehicle(a,x,y);
				
			} else if (x == 1 && y == 0) {
				setVehicle(new EmptyVehicle(), x, y);
				((Route)nextRoute[1]).setVehicle(a,x,y);
				
			} else if (x == 0 && y == 1) {
				setVehicle(new EmptyVehicle(), x, y);
				((Route)nextRoute[3]).setVehicle(a,x,y);
				
			} else if (x == 1 && y == 1) {
				setVehicle(new EmptyVehicle(), x, y);
				((Route)nextRoute[2]).setVehicle(a,x,y);
			}
		}
	}
}



