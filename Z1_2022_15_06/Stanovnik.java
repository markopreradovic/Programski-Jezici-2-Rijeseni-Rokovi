public abstract class Stanovnik implements Thread{
	
	public String ime;
	public String prezime;
	public String jmb;
	
	public Stanovnik(String ime, String prezime, String jmb) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmb = jmb;

    }

    public Stanovnik(String ime, String prezime, String jmb, int plata) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmb = jmb;
    }
}