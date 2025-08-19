import java.util.*;
public class FizickoLice extends Kupac {
	
	public FizickoLice(){
		super(new Random().nextDouble()*30 + 20);
	}
	
	
	@Override
	public boolean isFizickoLice(){
		return true;
	}
	
	public String toString(){
		return "Fizicko" + super.toString();
	}
}