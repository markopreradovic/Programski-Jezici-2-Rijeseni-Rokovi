package paket;

public class Automobil {

	int godinaProizvodnje;
	String marka;
	String model;
	int brojVrata;
	int snagaMotora;
	Tip tip;
	
	public Automobil() {
		godinaProizvodnje = Main.random.nextInt(10) + 2015;
		marka = "Marka" + (Main.random.nextInt(10) + 1);
		model = "Model" + (Main.random.nextInt(10) + 1);
		brojVrata = (Main.random.nextInt(2) == 0) ? 2 : 4;
		snagaMotora = Main.random.nextInt(11) + 145;
		
		int randomTip = Main.random.nextInt(3);
		if (randomTip == 0) {
			tip = Tip.LIMUZINA;
		} else if (randomTip == 1) {
			tip = Tip.HATCHBACK;
		} else {
			tip = Tip.SUV;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		else if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		} else {
			Automobil automobil = (Automobil) obj;
			return (this.tip == automobil.tip && this.godinaProizvodnje == automobil.godinaProizvodnje && this.marka.equals(automobil.marka) && this.model.equals(automobil.model) && this.brojVrata == automobil.brojVrata && this.snagaMotora == automobil.snagaMotora);
		}
	}
	
	@Override
	public String toString() {
		return "Automobil{godinaProizvodnje=" + godinaProizvodnje + ", marka=" + marka + ", model=" + model + ", brojVrata=" + brojVrata + ", snagaMotora=" + snagaMotora + ", tip=" + tip + "}";
	}
	
	public enum Tip {
		LIMUZINA, HATCHBACK, SUV;
	}
}
