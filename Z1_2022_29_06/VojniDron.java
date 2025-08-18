import java.util.*;

public class VojniDron extends Dron	implements Vojni{
	public TipNaoruzanja naoruzanje;
	public String kamera;
	
	public VojniDron(){
		super();
	}
	
	public void setNaoruzanje(TipNaoruzanja tip){
		this.naoruzanje = tip;
	}
	
	public void setTransponder(boolean t){
		transponder = t;
	}
}