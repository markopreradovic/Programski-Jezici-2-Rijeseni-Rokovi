public class RemoveThread extends Thread{
	
	public long postotak;
	
	public RemoveThread(long postotak){
		this.postotak = postotak;
		start();	
	}
	
	@Override
	public void run(){
		try{
			while(!Simulacija.END){
				Thread.sleep(500*postotak);
				Student student = Simulacija.red.studenti.take();
				System.out.println("Uklonjen je: " + student);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}