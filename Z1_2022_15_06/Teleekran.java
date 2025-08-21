public class Teleekran implements Runnable{
	
	public BlockingQueue<Poruka> poruke = new LinkedBlockingQueue<>();
	public boolean aktivni = true;
	
	public void posaljiPoruku(String msg) {
		poruke.offer(msg);
	}
	
	public void zaustavi(){
		aktivni = false;
	}
	
	@Override
    public void run() {
        while (aktivni) {
            try {
                String poruka = poruke.poll(100, TimeUnit.MILLISECONDS);
                if (poruka != null) {
                    System.out.println("*** TELEEKRAN *** " + poruka);
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }
	
	
}