/*
 * Created on 15.10.2003 $Id: Route.java,v 1.6 2003/10/25 14:41:22 jsprenger
 * Exp $
 */
package objects;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * @author Jonas Sprenger
 *  
 */
public abstract class Route extends Observable implements ActionListener {
	double p_dec = 0.3;
	/**
	 * reference to the next route object
	 */
	Route nextRoute;

	int speedLimit = Integer.MAX_VALUE;

	public Object road[][];

	private int relFlow = 0;
	private int flow = 0;
	private double density = 0.0;
	private int numCells = 0;
	private int NumVehicles = 0;
	private double avgSpeed = 0.0;

	public Route(int length) {
		nextRoute = null;
		road = new Object[1][length];
		for (int i = 0; i <= length - 1; i++) {
			road[0][i] = new EmptyVehicle();
		}

		/*
		 * road[0][90] = new Car(); road[0][34] = new Car(); road[0][45] = new
		 * Car(); road[0][50] = new Car(); road[0][52] = new Car(); road[0][11] =
		 * new Car();
		 */
	}

	public Route(int length, int numLanes) {
		nextRoute = null;
		road = new Object[numLanes][length];
		for (int j = 0; j <= numLanes - 1; j++) {
			for (int i = 0; i <= length - 1; i++) {
				road[j][i] = new EmptyVehicle();
			}
		}
	}

	public Route() {
		nextRoute = null;
		road = new Object[1][80];
		for (int i = 0; i < 80; i++) {
			road[0][i] = new EmptyVehicle();
		}

	}
	/**
	 * iterates over the road, starts at the end
	 *  
	 */
	public void update() {

		for (int i = road[0].length - 1; i >= 0; i--) {
			for (int j = road.length - 1; j >= 0; j--) {
				if (road[j][i] instanceof Car) {
					getVehicle(j, i).setHandled(false);
				}
			}
		}
		//	System.out.println("entering Route::update");
		for (int i = road[0].length - 1; i >= 0; i--) {
			for (int j = road.length - 1; j >= 0; j--) {
				if (road[j][i] instanceof Car) {
					accelerate(j, i);
				}
			}
		}
		for (int i = road[0].length - 1; i >= 0; i--) {
			for (int j = road.length - 1; j >= 0; j--) {
				if (road[j][i] instanceof Car) {
					decelerate(j, i);
				}
			}
		}
		for (int i = road[0].length - 1; i >= 0; i--) {
			for (int j = road.length - 1; j >= 0; j--) {
				if (road[j][i] instanceof Car) {
					stochasticDecelerate(j, i);
				}
			}
		}
		for (int i = road[0].length - 1; i >= 0; i--) {
			for (int j = road.length - 1; j >= 0; j--) {
				if ((road[j][i] instanceof Car)
					&& (getVehicle(j, i).isHandled() == false)) {
					getVehicle(j, i).setHandled(true);
					advance(j, i);
				}
			}
		}

		numVehicles();
		density();
		flow();
		relFlow();

	}

	/**
	 * Returns, wether the road piece at the given coordnates is free so that a
	 * vehicle may move to that coordinates
	 * 
	 * @param x
	 *            x-coordinate of the field to test
	 * @param y
	 *            y-coordinate of the field to test
	 * @return true if the field at the coordinate x,y is empty
	 */
	public boolean isFree(int x, int y) {
		if ((overflow(x, y) > 0) && hasNextRoute()) {
			return nextRoute.isFree(x, overflow(x, y));
		}
		if (road[x][y] instanceof EmptyVehicle) {
			return true;
		}
		return false;
	}

	/**
	 * convenience function. Behaves exactly like the function above.
	 * 
	 * @param p
	 *            coordinates of the field to test
	 * @return true if the field at the coordnate is empty
	 * @see #isFree(int,int)
	 */
	public boolean isFree(Point p) {
		return isFree(p.x, p.y);
	}

	/**
	 * accelerates the given car one speed unit
	 */
	protected void accelerate(int x, int y) {
		Vehicle a = getVehicle(x, y);
		if (a.getVelocity() < Math.min(a.getMaxVelocity(), speedLimit)) {
			a.setVelocity(a.getVelocity() + 1);
		}
		// if(a.getVe)
	}

	/**
	 * decelerates the given car depending on the gap between the current
	 * vehicle and the vehicle ahead
	 * 
	 * @param x
	 *            x-coordinate of the vehicle
	 * @param y
	 *            y-coordinate of the vehicle
	 */
	protected void decelerate(int x, int y) {
		Vehicle a = getVehicle(x, y);
		//	int gap = gap(x, y + 1);
		int gap = gap(x, y);
		if (a.getVelocity() > gap) {
			a.setVelocity(gap - 1);
		}
	}

	/**
	 * Stochastic behavior. Slow down given vehicle with probability _p_dec
	 * 
	 * @param x
	 *            x-coordinate of the vehicle
	 * @param y
	 *            y-coordinate of the vehicle
	 *  
	 */
	protected void stochasticDecelerate(int x, int y) {
		Vehicle a = getVehicle(x, y);
		//	Random rand = new Random();
		//	if ((a.getVelocity() > 0) && (rand.nextFloat() <= p_dec)) {
		if ((a.getVelocity() > 0) && (Math.random() <= p_dec)) {
			a.setVelocity(a.getVelocity() - 1);
		}
	}

	protected void advance(int x, int y) {
		Vehicle a = getVehicle(x, y);
		setVehicle(new EmptyVehicle(), x, y);
		setVehicle(a, x, y + a.getVelocity());

	}

	/**
	 * gets the gap between the vehicle at position p and the vehicle in front
	 * of it
	 * 
	 * @param p
	 *            position of current vehicle
	 * @return gap
	 */
	protected int getGap(Point p) {
		return gap(p.x, p.y);
	}

	protected int gap(int x, int y) {
		int gap = 0;
		//boolean nextVehicleFound = false;
		//Vehicle a = getVehicle(x, y);

		//while (gap < a.getMaxVelocity() + 1) {
		while (gap < Math.min(speedLimit, road[0].length)) {
			if (isFree(x, y + gap + 1)) {
				gap++;
			} else {
				break;
			}
		}

		return gap;
	}

	protected Vehicle getVehicle(int x, int y) {
		if (overflow(x, y) > 0) {
			if (hasNextRoute()) {
				return nextRoute.getVehicle(x, overflow(x, y));
			}
		}
		return (Vehicle) road[x][y];
	}
	/**
	 * sets a vehicle at the given position
	 * 
	 * @param v
	 *            vehicle to set
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 */
	protected void setVehicle(Vehicle v, int x, int y) {
		if (overflow(x, y) > 0) {
			// System.out.println(overflow(x, y));
			if (hasNextRoute()) {
				if(nextRoute instanceof RouteCrossLane)
				{
					int index = ((RouteCrossLane)nextRoute).getIndexOfRoute((RouteSingleLane) this);
					
					((RouteCrossLane)nextRoute).setVehicle(v,index,x,y);
				} 
				else
				nextRoute.setVehicle(v, x, overflow(x, y));
			}
		} else {
			road[x][y] = v;
			//  if (v instanceof Car)
			//      System.out.println("set vehicle at " + x + "." + y);
		}

	}
	/**
	 * returns the Road
	 * 
	 * @return Road
	 * @deprecated
	 */
	public Object[][] getList() {
		return getRoad();
	}
	/**
	 * returns the amount of fields the current position exceeds the road
	 * length
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int overflow(int x, int y) {
		int overflow = y - (road[x].length - 1);
		if (overflow <= 0) {
			return 0;
		} else {
			return overflow;
		}
	}

	public void setNextRoute(Route r) {
		nextRoute = r;
	}

	boolean hasNextRoute() {
		return nextRoute != null;
	}

	void setDecelerationRate(double p) {
		p_dec = p;
	}

	double getDecelerationRate() {
		return p_dec;
	}
	/**
	 * @return Returns the speedLimit.
	 */
	public int getSpeedLimit() {
		return speedLimit;
	}

	/**
	 * @param speedLimit
	 *            The speedLimit to set.
	 */
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
	}

	/**
	 * @return Returns the nextRoute.
	 */
	public Route getNextRoute() {
		return nextRoute;
	}

	/**
	 * @return Returns the road.
	 */
	public Object[][] getRoad() {
		return road;
	}

	public double avgSpeed() {
		int sumSpeed = 0;
		if (numVehicles() > 0) {
			for (int j = 0; j <= road.length - 1; j++) {

				for (int i = 0; i <= road[j].length - 1; i++) {
					if (road[j][i] instanceof Car) {
						sumSpeed += getVehicle(j, i).getVelocity();
					}
				}
			}
			return sumSpeed / numVehicles();

		} else
			return 0;

	}

	public int numVehicles() {
		int num = 0;
		for (int j = 0; j <= road.length - 1; j++) {
			for (int i = 0; i <= road[j].length - 1; i++) {
				if (road[j][i] instanceof Car) {
					num++;
				}
			}
		}

		//	setChanged();
		//	notifyObservers(new Integer(num));
		return num;
	}

	public int flow() {
		int flow = (int) (numVehicles() * avgSpeed());
		if (flow != this.flow) {
			setChanged();
			notifyObservers(new Double(flow));
		}
		return flow;
	}

	public double density() {
		double density = numVehicles() / numCells();
		if (density != this.density) {
			setChanged();
			notifyObservers(new Double(density));
		}
		return density;
	}

	public int numCells() {
		int numCells = road.length * road[0].length;
		if (numCells != this.numCells) {
			setChanged();
			notifyObservers(new Integer(numCells));
		}
		return numCells;
	}

	public int relFlow() {
		int relFlow = (int) (density() * avgSpeed());
		if (relFlow != this.relFlow) {
			setChanged();
			notifyObservers(new Integer(relFlow));
		}
		return relFlow;
	}
	public void actionPerformed(ActionEvent e) {
		update();

	}
}
