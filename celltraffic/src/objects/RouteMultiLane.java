/*
 * $Id: RouteMultiLane.java,v 1.2 2003/10/29 17:58:19 moleman Exp $
 */
package objects;

public class RouteMultiLane extends Route {

	public RouteMultiLane() {
		super(100, 2);
	}

	public RouteMultiLane(int length) {
		super(length, 2);
	}

	public RouteMultiLane(int length, int numLanes) {
		super(length, numLanes);
	}

	double pOverhaul() {
		return 0.4;
	}

	boolean moveToLeft(int x, int y) {
		if (x >= road.length - 1) {
			return false;
		}
		Vehicle v = getVehicle(x, y);
		if (gap(x, y) < v.getVelocity()) {
			if (isFree(x + 1, y)) {
				if (gap(x + 1, Math.max(0, y - v.getMaxVelocity()))
					>= v.getMaxVelocity()) {
					if (gap(x + 1, y) >= v.getVelocity()) {
						if (Math.random() < pOverhaul()) {
							Vehicle vordermann =
								getVehicle(x, y + gap(x, y) + 1);
							if (v.getVelocity() > vordermann.getVelocity()) {
								setVehicle(new EmptyVehicle(), x, y);
								setVehicle(v, x + 1, y);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	boolean moveToRight(int x, int y) {
		if (x == 0) {
			return false;
		}
		Vehicle v = getVehicle(x, y);
		if ((isFree(x - 1, y)) && gap(x - 1, y) >= v.getVelocity()) {
			setVehicle(new EmptyVehicle(), x, y);
			setVehicle(v, x - 1, y);
			return true;
		}
		return false;
	}

	public void decelerate(int x, int y) {
		if (!(moveToRight(x, y) || moveToLeft(x, y))) {
			super.decelerate(x, y);
		}

	}
}
