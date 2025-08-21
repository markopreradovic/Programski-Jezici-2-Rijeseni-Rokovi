import java.text.SimpleDateFormat;
import java.util.Date;
public class Poruka {

	public String jmbPosiljaoca;
	public String jmbPrimaoca; 
	public String vrijemeSlanja;
	public String tekst;
	
	public Poruka(String jmbPosiljaoca, String jmbPrimaoca, String tekst){
		this.jmbPosiljaoca=jmbPosiljaoca;
		this.jmbPrimaoca=jmbPrimaoca;
		this.vrijemeSlanja = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
		this.tekst=tekst;
	}

}