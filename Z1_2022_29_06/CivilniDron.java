import java.util.*;

public class CivilniDron extends Dron{
	public int domet;
	
	public CivilniDron(){
		super();
		domet = new Random().nextInt(Simulacija.DIM);
	}
}