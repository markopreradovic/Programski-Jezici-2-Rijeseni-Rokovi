package paket;

import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Paket<T extends Posiljka> implements Serializable {
	
	public List<T> arr = new ArrayList<T>();
	public double tezina = 0.0;
	
	public void slanje(){
		synchronized(TrecaNit.o){
			System.out.println("==================================");
			System.out.println("SLANJE POSILJKI: ");
			arr.stream().forEach(System.out::println);
			System.out.println("==================================");
		}
	}
	
	public void addPosiljka(T posiljka) {
		arr.add(posiljka);
		tezina+=posiljka.tezina;
	}
	
}
