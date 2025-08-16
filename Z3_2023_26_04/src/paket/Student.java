package paket;
import java.util.*;
public class Student implements Comparable<Student>{
	
	public String ime;
	public String prezime;
	public String indeks;
	public double prosjek;
	
	public static int serial = 1;
	
	public Student() {
		this.ime = "Ime" + serial;
		this.prezime = "Prezime" + serial;
		this.indeks = String.valueOf(new Random().nextInt(2023) + serial);
		this.prosjek = new Random().nextDouble(4.0) + 6.0;
		serial ++;
	}
	
	 @Override
	    public int compareTo(Student s) {
	        return Double.compare(this.prosjek, s.prosjek);
	    }

	    public boolean equals(Student s) {
	        return this.equals(s);
	    }


	    @Override
	    public String toString() {
	        return ime + " " + prezime + " " + indeks + " " + prosjek;
	    }
	
}
