import java.util.*;
public class Odrasli extends FizickoLice {
	
	public Odrasli() {
		this.prioritet = new Random().nextInt(2) == 1;
	}
	
	public Odrasli(boolean prioritet){
		this.prioritet = prioritet;
	}
	
	@Override
	public String toString(){
		return "Odrasli" + prioritet + " " + super.toString();
	}
	
}