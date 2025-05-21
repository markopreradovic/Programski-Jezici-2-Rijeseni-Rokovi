package paket;
import java.util.*;


public class RadnaMasina extends Thread {
	
	public int serijskiBroj;
	public String model;
	public boolean status;
	public ArrayList<Senzor> senzori = new ArrayList<>();
	
	public static int redniBroj = 1;
	
	public RadnaMasina() {
		this.serijskiBroj = redniBroj;
		this.model = "Model" + redniBroj;
		this.status = false;
		redniBroj++;
	}
	
	public void upali() {
		this.status = true;
	}
	
	public void ugasi() {
		this.status = false;
	}
	
	@Override
	public void run() {
		this.upali();
		System.out.println(this + " je pocela sa radom!");
		
		
	}
	
	@Override
	public String toString() {
		return "RadnaMasina{serijskiBroj=" + this.serijskiBroj + ", model=" + this.model + ", status=" + this.status + ", senzori=" + this.senzori + "}";
	}
	
	
	
	
	
	
	
}
