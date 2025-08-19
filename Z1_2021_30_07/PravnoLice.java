import java.util.*;

public class PravnoLice extends Kupac {
	
	public PravnoLice(){
		super(new Random().nextDouble()*50 + 50);
	}
	
	@Override
	public boolean isFizickoLice(){
		return false;
	}
	
	@Override
	public String toString() {
		return "Pravno" + super.toString();
	}
	
}