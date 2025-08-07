package paket;

public class ProductOwner extends Clan {

	public String opis;
	
	public ProductOwner() {
		super();
		this.opis = "Opis" + redniBroj;
	}
	
	public synchronized void dodajZadatke() {
		for(int i=0;i<5;i++) {
			Main.zadaci.add(new Zadatak());
		}
		System.out.println(this + "je dodao zadatke");
	}
	
	@Override
	public void run() {
		int i=0;
		while(i<3) {
			this.dodajZadatke();
			
			synchronized (Main.lockObject) {
				try {
					Main.lockObject.wait();
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
			}
			i++;
		}
	}
	
	@Override
	public String toString() {
		return "ProductOwner{ime=" + ime + ", prezime=" + prezime + ", godineRada=" + godineRada + ", opis=" + opis + "}";
	}
	
}
