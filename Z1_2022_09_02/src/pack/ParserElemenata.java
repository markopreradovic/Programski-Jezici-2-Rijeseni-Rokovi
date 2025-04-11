package pack;

import java.util.*;
import java.nio.file.*;
import java.io.*;
import java.util.stream.*;

public class ParserElemenata {
	
	public List<Vozilo> arrVozilo = new ArrayList<Vozilo>();
	
	public void getElements() {
		try {
			List<String> lines = Files.readAllLines(Paths.get(System.getProperty("user.dir") + File.separator + "vozila.csv"));
			
			List<String> id = new ArrayList<String>();
			for(String i : lines) {
				if(!id.contains(i.split(";")[1])) {
					id.add(i.split(";")[1]);
				}
			}
			
			for(String i : id) {
				//Stream API za filtriranje liste stringova (lines) i kreiranje nove liste (arr) na osnovu uslova
				List<String> arr = lines.stream().filter(t -> i.equals(t.split(";")[1])).collect(Collectors.toList());
				
				Motor motor = null;
				Vozac vozac = null;
				String superMoc = "";
				Vozilo vozilo = null;
				
				for(String j : arr) {
					String []sp = j.split(";");
					if("MOTOR".equals(sp[0])) {
						motor = new Motor(Integer.parseInt(sp[2]), getTipMotora(sp[3]));
					}
					else if("SUPER_MOC".equals(sp[0])) {
						superMoc = sp[2];
					}
					else if("AUTOMOBIL".equals(sp[0])) {
						vozilo = new Automobil(sp[2]);
					}
					else if("KAMION".equals(sp[0])) {
						vozilo = new Kamion(sp[2]);
					}
					else if("AUTOBUS".equals(sp[0])) {
						vozilo = new Autobus(sp[2],Integer.parseInt(sp[3]));
					}
					else if("Vozac".equals(sp[0])) {
						vozac = new Vozac(sp[2], sp[3]);
					}
				}
				
				vozilo.vozac = vozac;
				vozilo.motor = motor;
				if(vozilo instanceof Automobil) {
					Automobil a = (Automobil)vozilo;
					a.supermoci = superMoc;
					vozilo = a;
				}
				
				vozilo.id = i;
				arrVozilo.add(vozilo);
				
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private TipMotora getTipMotora(String tip) {
		if("benzinski".equals(tip)) return TipMotora.benzinski;
		return TipMotora.dizelski;
	}
	
	public void startAll() {
		System.out.println("BROJ: " + arrVozilo.size());
		for (Vozilo i : arrVozilo) {
			i.start();
		}
	}
	
	
}	
