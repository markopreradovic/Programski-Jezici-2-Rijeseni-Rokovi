import java.util.*;


public class Student{
	public String ime;
	public String prezime;
	public int indeks;
	public double prosjek;
	
	public static int serial = 1;
	
	public Student(){
		this.ime = "Ime" + serial;
		this.prezime = "Prezime" + serial;
		this.indeks = serial;
		this.prosjek = new Random().nextDouble(4.0) + 6;
		serial ++;
	}
	
	public Student(String ime, String prezime, int indeks, double prosjek){
		this.ime = ime;
		this.prezime = prezime;
		this.indeks = indeks;
		this.prosjek = prosjek;
	}
	
	@Override
	public String toString(){
		return "Ime" + ime + " Prezime" + prezime + " Indeks" + indeks + " Prosjek" + prosjek;
	}
}