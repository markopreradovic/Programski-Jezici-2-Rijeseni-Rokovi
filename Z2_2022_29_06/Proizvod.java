import java.util.*;
import java.io.Serializable;
public class Proizvod implements Serializable{
	
	public String id;
	public String naziv;
	public String opis;
	public int cijena;
	public Tip tip;
	
	public static int serial = 1;
	
	public Proizvod(){
		this.id = "ID: " + serial;
		this.naziv = "Naziv " + serial;
		this.opis = "Opis" + serial;
		this.cijena = new Random().nextInt(50) + 10;
		this.tip = Tip.values()[new Random().nextInt(Tip.values().length)];
		serial++;
	}
	
	public Proizvod(String id, String naziv, String opis, int cijena, Tip tip){
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.cijena = cijena;
		this.tip = tip;
	}
	
	@Override
	public String toString(){
		return "\nID: " + id + " Naziv " + naziv + " Opis " + opis + " Cijena " + cijena + " Tip " + tip;
	}
	
	
}