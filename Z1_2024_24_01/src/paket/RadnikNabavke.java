package paket;

public class RadnikNabavke extends Zaposleni{

	public RadnikNabavke() {
		super();
	}
	
	@Override
	public void run() {
		radi = true;
		System.out.println(this + "Pocinje sa radom!");
		
		int n = 1;
		int prosloVrijeme = 0;
		
		while(radi) {
			String zadatak = "Upit za nabavku #" + n;
			System.out.println(zadatak);
			uradjeniZadaci.add(zadatak);
			
			try {
				for(int i=0; i<3; i++) {
					sleep(1000);
					prosloVrijeme++;
					if(prosloVrijeme == vrijemePauze) {
						System.out.println(this + "Otisao na pauzu");
						sleep(5000);
						System.out.println(this + "Vratio se sa pauze");
					}
				}
			} catch(InterruptedException x) {
				x.printStackTrace();
			}
			n++;
			if(uradjeniZadaci.size()==10) {
				synchronized (Main.lockObject) {
					try {
						Main.lockObject.wait();
					} catch (InterruptedException x) {
						x.printStackTrace();
					}
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return "\nRadnikProdaje{ime=" + ime + ", prezime=" + prezime + ", godineRada=" + godineRada + ", cijenaRada=" + cijenaRada + ", upitiZaNabavku=" + uradjeniZadaci + "}";
	}
	
}
