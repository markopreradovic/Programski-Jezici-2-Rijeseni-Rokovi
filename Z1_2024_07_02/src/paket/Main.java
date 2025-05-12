package paket;
import java.util.*;

public class Main {

	public static Random random = new Random();
	
	public static boolean cekanje = false;
	public static Object lockObject = new Object();
	
	
	public static void main(String[] args) {

		ArrayList<Robot> roboti = new ArrayList<>();
		AlarmWatcher alarmwatcher = new AlarmWatcher();
		alarmwatcher.start();
		
		
		//Dodajemo pola opticke, pola ultrazvucne
		for(int i=0;i<10;i++) {
			if (i%2==0) {
				roboti.add(new Robot("Montazni", new Senzor("Opticki")));
			}
			roboti.add(new Robot("Montazni", new Senzor("Ultrazvucni")));
		}
		
		for(int i=0;i<10;i++) {
			if (i%2==0) {
				roboti.add(new Robot("Cistacki", new Senzor("Opticki")));
			}
			roboti.add(new Robot("Cistacki", new Senzor("Ultrazvucni")));
		}
		
		for(int i=0;i<10;i++) {
			if (i%2==0) {
				roboti.add(new Robot("Istrazivacki", new Senzor("Opticki")));
			}
			roboti.add(new Robot("Istrazivacki", new Senzor("Ultrazvucni")));
		}
		
		for(Robot robot : roboti) {
			robot.start();
		}
		
		try {
			for (Robot robot : roboti) {
				robot.join();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		alarmwatcher.radi = false;
		
		
	}

}
