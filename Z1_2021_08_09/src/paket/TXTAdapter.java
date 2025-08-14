package paket;
import java.io.*;

public class TXTAdapter extends Adapter {

	@Override
	public void importData(String fileName) {
		 try (BufferedReader br = new BufferedReader(new FileReader(fileName));
	             PrintWriter pw = new PrintWriter(Adapter.PATH + File.separator + new File(fileName).getName())) {
				String linija;
				while( (linija=br.readLine()) != null ) {
					pw.println(linija);
					linija = linija.substring(2, linija.length()-2);
					String[] elements = linija.split("\\|\\|");
					addObject(elements);
				}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
