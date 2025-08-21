import java.util.*;

public class Radnik extends Stanovnik {
	
	public double plata;
	private BlockingQueue<Poruka> buffer;
	
	public Radnik(String ime, String prezime, String jmb, double plata, BlockingQueue<Poruka> buffer){
		super(ime,prezime,jmb);
		this.plata = plata;
		this.buffer = buffer;
	}
	
	
	
}