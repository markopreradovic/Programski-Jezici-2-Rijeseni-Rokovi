package paket;

public class Podmornica extends Plovilo{
	public Sonar sonar;
	public Torpedo torpedo;
	public boolean imaTorpedoStit;
	
	public Podmornica(String id, int x, int y, boolean ImaTorpedoStit) {
		super(id,x,y);
		this.imaTorpedoStit = ImaTorpedoStit;
	}
	
	@Override
    public void run() {
        long pocetak = System.currentTimeMillis();
        try {
            while (true) {
                //synchronized(Mapa.mapa)
                //{
                if (x - 3 >= 0 && Mapa.mapa[x - 3][y] != null) {
                    if (Mapa.mapa[x - 3][y] instanceof Razarac) {
                        Mapa.mapa[x - 3][y].interrupt();
                        Mapa.mapa[x - 3][y] = null;
                        System.out.println("Razarac potopljen!");
                    } else if (Mapa.mapa[x - 3][y] instanceof NosacAviona) {
                        if (((NosacAviona) Mapa.mapa[x - 3][y]).imaStitTorpedo) {
                            Mapa.mapa[x][y] = null;
                            System.out.println("Podmornica potopljena!");
                            this.interrupt();
                        } else {
                            Mapa.mapa[x - 3][y].interrupt();
                            Mapa.mapa[x - 3][y] = null;
                            System.out.println("Nosac aviona potopljen!");
                        }
                    }
                }
                int newX = x - 1;
                if (newX == -1)
                    return;
                Mapa.mapa[x][y] = null;
                Mapa.mapa[newX][y] = this;
                x = newX;
                System.out.println("Podmornica " + x + " " + y);
                sleep(1000);
                //}
            }
        } catch (InterruptedException e) {
        }
    }
}
