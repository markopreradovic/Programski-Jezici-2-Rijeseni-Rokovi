package paket;
import java.util.Random;
public class Student {

	public String ime;
	public String prezime;
	public String brIndeksa;
	public int godRodjenja;
	public int godStudija;
	public double prosjek;
	
	
	public Student() {
		Random random = new Random();
		
		this.ime = ('A' + random.nextInt(26)) + "_ime";
		this.prezime = ('A' + random.nextInt(26) + "_prezime");
		this.brIndeksa = String.valueOf(random.nextInt(2000));
		this.godRodjenja = random.nextInt(15) + 1990;
		this.godStudija = random.nextInt(4) + 1;
		this.prosjek = random.nextDouble(4) + 6.0;
	}
	
}
