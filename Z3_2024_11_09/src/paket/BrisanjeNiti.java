package paket;

public class BrisanjeNiti extends Thread {

	public BrisanjeNiti() {
		super();
	}
	
    private boolean krajVremena = false;

    public void setKrajVremena() {
    	this.krajVremena = true;
    }
	
    @Override 
    public void run() {
    	while(!krajVremena) {
    		Knjiga knjiga;
    		synchronized(Main.lockObject) {
    			if(!Main.stek.isEmpty()) {
    				knjiga = Main.stek.pop();
    				Main.uklonjenihKnjiga++;
    			}
    		}
    		try {
    			sleep(400);
    		}catch (InterruptedException ex) {
    			ex.printStackTrace();
    		}
    	}
    }
}
