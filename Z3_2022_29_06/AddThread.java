public class AddThread extends Thread{
	
	public long postotak;
	
	public AddThread(long postotak){
		this.postotak = postotak;
		start();
	}
	
	@Override
	public void run(){
		try{
			while(!Simulacija.END){
				Thread.sleep(500*postotak);
				Student student = new Student();
				Simulacija.red.studenti.add(student);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}