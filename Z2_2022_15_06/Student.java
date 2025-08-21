import java.util.*;
public class Student {
	public String ime;
	public String prezime;
	public String indeks;
	public List<String> lista;
	
	public Student(String ime, String prezime, String indeks, List<String> lista){
		this.ime = ime;
		this.prezime = prezime;
		this.indeks = indeks;
		this.lista = lista;
	}
}