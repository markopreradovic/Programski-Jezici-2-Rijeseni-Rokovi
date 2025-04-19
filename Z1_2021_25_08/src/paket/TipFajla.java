package paket;

public enum TipFajla {
	jpg(".jpg"),
	jpeg(".jpeg"),
	png(".png");
	
	private String tip;
	
	TipFajla(String tip){
		this.tip = tip;
	}
	
	public String getTip() {
		return tip;
	}
}
