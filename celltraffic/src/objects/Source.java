/*
 * Created on 15.10.2003 $Id: Source.java,v 1.3 2003/10/25 12:46:25 moleman Exp $
 */
package objects;


public class Source extends Route {
    public Source() {
        super();
    }
    public void advance(int x, int y) {
        Vehicle a = getVehicle(x, y);
        if (y + a.getVelocity() > road[x].length) {
            //TODO advance for position outside current road
            System.out.println(
                "TODO: Source: New Position of Vehicle exceeds current road");
        } else {
            road[x][y + a.getVelocity()] = road[x][y];
        }
    }
}
