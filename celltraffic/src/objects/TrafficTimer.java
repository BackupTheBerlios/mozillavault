/*
 * Created on 25.10.2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package objects;

/**
 * @author Jonas Sprenger
 * is wohl besser so........
 * Thread t = new Thread();
						t.start();
						try{
				
						while(true)
						{
						System.out.println("Neuer Wert: ");
						//mach dies und das dann wieder das und nun schlaf ne runde..
						Thread.sleep(1500);
						}
						}
						catch(Exception ex){};	
 */
public class TrafficTimer extends Thread {
	int time=0;

/**
 * sets  time u want to wait...
 * 500 = 1/2 sec
 * @param t
 */
public void set(int t){
	this.time = t;
}
	public void run(){
	
	System.out.println("Timer läuft......");
	try{
		Thread.sleep(time);
		System.out.println("Timer beendet...");		
	}catch(InterruptedException e){
		interrupt();
		
	}
		
	}
	
	

}