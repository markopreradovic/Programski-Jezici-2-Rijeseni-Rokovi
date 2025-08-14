package paket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Character extends Thread{

	public String s;
	Integer count = 0;
	static Map<String, Integer> mapa = new ConcurrentHashMap<>();
	
	public Character(String s) {
		this.s = s;
		setDaemon(true);
		start();
	}
	
	@Override
	public void run() {
		try {
			synchronized(mapa) {
				if(mapa.containsKey(s))
					mapa.put(s, mapa.get(s) + 1);
				else
					mapa.put(s, 1);
				
                System.out.println(s + " : " + mapa.get(s));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
