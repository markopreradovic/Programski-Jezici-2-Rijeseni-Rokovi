package pack;

import java.util.*;

public abstract class Vozilo extends Thread{
	public String id;
	String konfiguracija;
	Vozac vozac;
	Motor motor;
	
	public static Object o = new Object();
	
	
	public Vozilo(){
	}
	
	public Vozilo(String conf) {
		this.konfiguracija = conf;
	}
	
	public Vozilo(String id, String conf, Vozac vozac, Motor motor) {
		this.id = id;
		this.konfiguracija = conf;
		this.vozac = vozac;
		this.motor = motor;
	}
	
	@Override
	public void run() {
		System.out.println("Vozilo:");
		for(int i=0; i<Main.map.length;i++) {
			try {
				Vozilo.ispis(id,i);
				
				if (Main.map[i].tip == TipPolja.obicna) {
				} else if(Main.map[i].tip == TipPolja.klizava) {
					Random rand = new Random();
					int value = rand.nextInt(100);
					if (value <= Main.map[i].gubitak) return;
				} else if (Main.map[i].tip == TipPolja.neravna) {
					if (this instanceof Autobus) {
						motor.snaga += motor.snaga/10;
					}
				}
				
				double value = 100 / motor.snaga;
				Thread.sleep((int)(value*1000));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void ispis(String id, int pos){
		synchronized(o){
			System.out.println("ID: " + id + ", Position: " + pos);
		}	
	}
}
