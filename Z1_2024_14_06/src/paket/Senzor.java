package paket;

public abstract class Senzor extends Thread{

	public String naziv;
	public boolean status;
	
	public static int redniBroj = 1;
	
	public Senzor() {
		this.naziv = "Naziv" + redniBroj++;
		this.status = false;
	}
	
	public void upali() {
		this.status = true;
	}
	
	public void ugasi() {
		this.status = false;
	}
	
	public abstract void mjeri();

	@Override
	public void run() {
		System.out.println(this + " poceo sa radom");
		
		while(this.status) {
			this.mjeri();
			try {
				sleep(500);
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		
		System.out.println(this + " je zavrsio sa radom"); 
	}
}
