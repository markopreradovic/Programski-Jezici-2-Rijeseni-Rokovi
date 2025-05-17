package paket;
import java.util.*;

public class ObjectConverter<T> {

	public T convert(HashMap<String,String> hashMap, T obj)  throws Exception {
		
		Class<?> clas = obj.getClass();
		for(String key : hashMap.keySet()) {
			try {
				var field = clas.getDeclaredField(key);
				field.setAccessible(true);
				
				if(field.getType()==int.class || field.getType()==Integer.class) 
					field.set(obj, Integer.parseInt(hashMap.get(key)));
				else if(field.getType()==double.class || field.getType()==Double.class) 
					field.set(obj, Double.parseDouble(hashMap.get(key)));
				else 
					field.set(obj, hashMap.get(key));
			} catch(NoSuchFieldException ex) {
				System.out.println("Greska nije pronadjeno trazeno polje");
			}
		}
		
		return obj;
		
	}
	
}
