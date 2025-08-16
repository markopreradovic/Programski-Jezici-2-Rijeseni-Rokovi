package paket;

import java.time.*;

public class Poruka implements Comparable<Poruka> {

	public Prioritet prioritet;
	public String opis;
	public LocalDateTime vrijeme;
	
	public Poruka(Prioritet prioritet, String opis) {
        this.prioritet = prioritet;
        this.opis = opis;
        this.vrijeme = LocalDateTime.now();
    }
	
	@Override
    public String toString() {
        return prioritet + " " + opis + " " + vrijeme;
    }
	
	@Override
    public int compareTo(Poruka p) {
        if (prioritet.equals(Prioritet.KRITICNO))
            return -1;
        else if (prioritet.equals(Prioritet.UPOZORENJE))
            return 0;
        else return 1;
    }
	
}
