package paket;
import java.util.*;
import java.io.*;

public class Posiljka implements Serializable {
	public String aPrimaoca;
	public String aPosiljaoca;
	public double tezina;
	
	public static Random rand = new Random();
	public Posiljka(String aPrimaoca, String aPosiljaoca, int l, int r) {
		this.aPosiljaoca = aPosiljaoca;
		this.aPrimaoca = aPrimaoca;
		
		tezina = (rand.nextInt((r-l+1)+1)) * 1.0;
	}
	
	@Override
	public String toString() {
		return "Adresa primaoca: " + aPrimaoca + " Adresa posiljaoca: " + aPosiljaoca + " Tezina: " + tezina;
	}
	
	public static TipFajla getRandomTipFajla() {
		int val = rand.nextInt(3);
		if (val==0) return TipFajla.jpg;
		else if (val==1) return TipFajla.jpeg;
		return TipFajla.png;
	}
	
	public static Valuta getRandomValuta(){
		int value = rand.nextInt(3);
		
		if (value == 0)
			return Valuta.km;
		else if (value == 1)
			return Valuta.evro;
		
		return Valuta.dolar;
	}
	
}
