package paket;

public class Knjiga {

	public enum Zanr{
		BELETRISTIKA, PUTOPIS, TRILER, POEZIJA
	}
	
	public String naslov;
	public String pisac;
	public int godinaIzdavanja;
	public Zanr zanr;
	
	public Knjiga(String naslov, String pisac, int godina, Zanr zanr) {
		this.naslov = naslov;
		this.pisac = pisac;
		this.godinaIzdavanja = godina;
		this.zanr = zanr;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		else if(o == null || this.getClass() != o.getClass()) return false;
		Knjiga knjiga = (Knjiga) o;
		return (this.naslov.equals(knjiga.naslov) && this.godinaIzdavanja == knjiga.godinaIzdavanja);
	}
	
	@Override 
	public int hashCode() {
		int hash = 3;
		hash = hash * 7 * naslov.hashCode();
		hash = hash * 7 * Integer.hashCode(godinaIzdavanja);
		return hash;
	}
	

    @Override
    public String toString() {
        return naslov + " " + pisac + " " + godinaIzdavanja + " " + zanr.toString();
    }
}
