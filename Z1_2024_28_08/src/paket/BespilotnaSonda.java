package paket;

public class BespilotnaSonda extends SvemirskaLetjelica implements RaketaStit{

	private String senzor;
	private Laser laser;
	
	public BespilotnaSonda(int id, int kolona, String senzor, Laser laser) {
		super(id,kolona);
		this.senzor = senzor;
		this.laser = laser;
	}
	
	public synchronized void detektujLetjelice() {
	    for (int i = this.red; i < this.red + 3 && i < 20; i++) {
	        if (i < 0) continue; //  negative indices

	        // iteracija kroz kolone (0, 1, 2)
	        for (int j = 0; j < 3; j++) {
	            if (Main.MAPA[i][j] != null && !(Main.MAPA[i][j] instanceof LaserStit)) {
	                if (Main.MAPA[i][j] instanceof SvemirskiBrod) {
	                    System.out.println("BespilotnaSonda " + this.id + " je unistila SvemirskiBrod na lokaciji [" + i + "][" + j + "]");
	                } else if (Main.MAPA[i][j] instanceof MatricnaStanica) {
	                    System.out.println("BespilotnaSonda " + this.id + " je unistila MatricnuStanicu na lokaciji [" + i + "][" + j + "]");
	                } else if (Main.MAPA[i][j] instanceof PilotnaLetjelica) {
	                    PilotnaLetjelica pilotnaLetjelica = (PilotnaLetjelica) Main.MAPA[i][j];
	                    pilotnaLetjelica.ugasiLetjelicu();
	                }
	            }
	        }
	    }
	}
	
	@Override
    public void run() {
        int i;

        synchronized(Main.lock) {
            i = Main.DUZINA_MAPE - 1;
        }

        for (; i >= 0; i--) {
            if (!this.ziva) {
                break;
            }

            synchronized(Main.lock) {
                if (Main.cekanje) {
                    try {
                        Main.lock.wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            this.red = i;

            Main.MAPA[this.red][this.kolona] = this;
            if (this.red != 0) {
                Main.MAPA[this.red][this.kolona] = null;
            }

            System.out.println(this);

            try {
                sleep(1000);
                this.vrijeme += 1000;
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            //this.detektujLetjelice();
        }

        this.ziva = false;

        System.out.println(this + " JE ZAVRSILA KRETANJE!");
    }

    @Override
    public String toString() {
        return "BespilotnaSonda{id=" + this.id + ", red=" + this.red + ", kolona=" + this.kolona + ", senzor=" + this.senzor + ", laser=" + this.laser + "}";
    }
	
}
