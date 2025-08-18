public class VojniAvion extends Letjelica implements Vojni{
	
	public TipNaoruzanja naoruzanje;
	
	static int c = 0;
	
	public VojniAvion(){
		super();
		setNaoruzanje(TipNaoruzanja.RAKETA);
		setTransponder(c%2==0 ? true : false);
		c++;
	}
	
	public void setNaoruzanje(TipNaoruzanja tip) {
		this.naoruzanje = tip;
	}
	
	public void setTransponder(boolean t){
		transponder = t;
	}
	
}