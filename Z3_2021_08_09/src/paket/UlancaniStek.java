package paket;

public class  UlancaniStek<T> {
	public T vrijednost;
	public UlancaniStek<T> tos;
	public UlancaniStek<T> referenceNext;
	
	public UlancaniStek(){
		vrijednost = null;
		tos = null;
		referenceNext = null;
	}
	
	public UlancaniStek(T vrijednost) {
		this.vrijednost = vrijednost;
		tos = this;
		referenceNext = null;
	}
	
	public void push(T vrijednost) {
		UlancaniStek<T> novi = new UlancaniStek<>(vrijednost);
		novi.referenceNext = this.tos;
		tos = novi;
	}
	
	public T pop() throws StackEmptyException{
		if(tos == null) throw new StackEmptyException();
		T vrijednost = tos.vrijednost;
		tos = tos.referenceNext;
		return vrijednost;
	}
}
