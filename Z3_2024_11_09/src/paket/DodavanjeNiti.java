package paket;

public class DodavanjeNiti extends Thread{

	public DodavanjeNiti() {
		super();
	}
	
	private boolean krajVremena = false;
	
	public void setKrajVremena() {
		krajVremena = true;
	}
	
	@Override
	public void run() {
		while(!krajVremena) {
			Knjiga knjiga = new Knjiga();
			synchronized(Main.lockObject) {
				Main.stek.add(knjiga);
			}
			Main.dodanihKnjiga++;
			try {
				sleep(250);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
