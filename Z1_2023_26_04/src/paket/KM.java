package paket;

public class KM extends Modul{

	public KM() {
        setDaemon(true);
    }
	
	@Override
    public void run() {
        try {

            while (!Main.STOP) {
                synchronized (Main.poruke) {
                    Poruka poruka = Main.poruke.poll();
                    Main.poruke.remove(poruka);
                    System.out.println(poruka);
                }

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
