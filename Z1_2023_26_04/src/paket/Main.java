package paket;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;

public class Main {

	static Boolean STOP = false;
    static Scanner scan = new Scanner(System.in);
    public static PriorityQueue<Poruka> poruke = new PriorityQueue<>();
	
	public static void main(String[] args) {
		
		try {
			List<Motor> motori = new ArrayList<Motor>();
			for(int i=0;i<4;i++) {
				motori.add(new Motor());
			}
			
			Letjelica letjelica = new Letjelica(motori,0);
			
			String input = "";
			while(!"STOP".equals(input)) {
				System.out.println("");
				input = scan.nextLine();
			}
			letjelica.join();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
