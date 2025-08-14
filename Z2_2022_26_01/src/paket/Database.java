package paket;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.List;


public class Database extends Thread{

	public String putanja;
	
	public Database(String putanja) {
		this.putanja = putanja;
		start();
	}
	
	@Override
	public void run() {
		String date = LocalDate.now().toString();
		String bin = "." + File.separator + date 
				+ File.separator + new File(putanja).getName().split("\\.")[0] + ".bin";
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(bin))){
			List<String> lines = (List<String>) ois.readObject();
			for(String line : lines) {
				String[] parts = line.split("");
				for(String znak : parts)
					new Character(znak).join();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
