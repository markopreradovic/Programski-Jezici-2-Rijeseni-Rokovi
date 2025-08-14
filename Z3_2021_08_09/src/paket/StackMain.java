package paket;

public class StackMain {

    static boolean END = false;

	
	public static void main(String[] args) throws StackEmptyException, InterruptedException{
		
		UlancaniStek<Integer> stek = new UlancaniStek<>(1);
		AddThread addThread = new AddThread(stek,200);
		RemoveThread removeThread = new RemoveThread(stek,200);
		
		addThread.start();
		removeThread.start();
		
		long start = System.currentTimeMillis();
		long end;
		do {
			end = System.currentTimeMillis();
		} while(end - start < 30000);
		
		addThread.join();
		removeThread.join();
		while(stek.tos != null) {
            System.out.println("Preostala vrijednost " + stek.pop());

		}

	}

}
