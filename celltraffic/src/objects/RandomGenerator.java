/*
 * Created on 28.10.2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package objects;

import java.util.Random;

/**
 * @author Jonas Sprenger
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class RandomGenerator {
	static Random random = new Random();
	
	
		public int getRandom(){
			return random.nextInt(2);
		}

}
