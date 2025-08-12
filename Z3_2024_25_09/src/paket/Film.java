package paket;

import java.util.Objects;

public class Film {

	public String naziv;
	public String reziser;
	public int godinaIzdavanja;
	public Zanr zanr;
	
	public static int redniBroj = 1;
	
	public Film() {
		this.naziv = "Naziv " + redniBroj;
		this.reziser = "Reziser " + redniBroj;
		this.godinaIzdavanja = Main.random.nextInt(20) + 2000;
		int rand = Main.random.nextInt(4);
		
		switch(rand) {
		case 1:
			this.zanr = Zanr.AKCIJA;
		case 2: 
			this.zanr = Zanr.DOKUMENTARAC;
		case 3: 
			this.zanr = Zanr.DRAMA;
		case 4:
			this.zanr = Zanr.KOMEDIJA;
		}
	}
	
	public enum Zanr{
		AKCIJA,DRAMA,KOMEDIJA,DOKUMENTARAC
	}
	
	@Override
	public boolean equals(Object object) {
		
		if(this == object) {
			return true;
		}
		
		if(object == null || this.getClass() != object.getClass()) {
			return false;
		}
		
		Film film = (Film) object;
		return this.naziv.equals(film.naziv) && this.godinaIzdavanja == film.godinaIzdavanja;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.naziv, this.godinaIzdavanja);
	}
	
	@Override
	public String toString() {
		return "Film{naziv=" + this.naziv + ", reziser=" + this.reziser + ", godinaIzdavanja=" + this.godinaIzdavanja + ", zanr=" + this.zanr + "}";
	}
	
}
