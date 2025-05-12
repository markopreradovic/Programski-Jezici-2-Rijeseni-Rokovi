package paket;
import java.io.*;

public class Robot extends Thread{

	String serijskiBroj;
	
	String tip;
	
	int snaga;
	int autonomija;
	int visina;
	
	Senzor senzor;
	Motor motor;
	Baterija baterija;
	
	int vrijemeRada;
	
	public static int redniBroj;
	
	public Robot(String tip, Senzor senzor) {
		this.serijskiBroj = "Serijski broj " + redniBroj;
		this.tip = tip;
		this.snaga = Main.random.nextInt(10) + 1;
		this.autonomija = 100;
		this.visina = Main.random.nextInt(3) + 1;
		this.motor = new Motor();
		this.baterija = new Baterija();
		
		this.vrijemeRada = Main.random.nextInt(21) + 10;
		
		redniBroj++;
	}
	
	private void upisiUTxtDatoteku() {
		try (PrintWriter pw = new PrintWriter(new FileWriter("ostecenja.txt", true))) {
			pw.println(this + " OSTECEN");
		} catch (IOException ex) {
			System.out.println("Greska prilikom upisa u file!");
		}
	}
	
	@Override
	public void run() {
		int prosloVrijeme = 0;
		while(prosloVrijeme <= vrijemeRada) {
			if(this.tip.equals("Montazni")) {
				System.out.println("\n" + this + " je u fazi montaze");
			} else if(this.tip.equals("Cistacki")) {
				System.out.println("\n" + this + " je u fazi ciscenja");
			} else {
				System.out.println("\n" + this + " je u fazi istrazivanja");
			}
			
			try {
				sleep(1000);
			}catch (InterruptedException ex) {
				System.out.println("Greska prilikom spavanja niti");
			}
			
			prosloVrijeme++;
			
			synchronized (Main.random) {
				if (Main.random.nextInt(100) < 5) {
					upisiUTxtDatoteku();
				}
			}
			
			synchronized (Main.lockObject) {
				if (Main.cekanje == true) {
					try {
						Main.lockObject.wait();
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return "Robot{serijskiBroj=" + serijskiBroj + ", tip=" + tip + ", snaga=" + snaga + ", autonomija=" + autonomija + ", visina=" + visina + ", senzor=" + senzor + ", motor=" + motor + ", baterija=" + baterija + "}";
	}
	
	
}
