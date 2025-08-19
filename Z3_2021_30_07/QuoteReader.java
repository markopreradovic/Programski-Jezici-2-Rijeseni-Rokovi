public class QuoteReader extends Thread{
	
	public QuoteReader(){
		setDeamon(true);
	}
	
	@Override
	public void run(){
		while(true){
			for(String s : QuoteStorage.quotes){
				System.out.println(s);
			}
			try{
				sleep(2000);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}