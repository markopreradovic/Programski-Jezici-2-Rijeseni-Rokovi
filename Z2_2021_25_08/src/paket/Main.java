package paket;
import java.util.*;
import java.nio.*;
import java.nio.file.*;
import java.io.*;

public class Main {
	
	public static List<File> arr = new ArrayList<File>();
	public static String seq="";
	public static A a = new A();
	public static B b = new B();
	
	public static void main(String []args){
		try {
			find(args[0]);
			System.out.println("=== Za nastavak koristiti: . ====");
			System.out.println("=================================\n");
			System.out.print("Unijeti sekvencu: ");
			Scanner scan = new Scanner(System.in);
			seq = scan.nextLine();
			
			seq = seq.toUpperCase();
			b.start();
			a.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void find(String path) {
		File f = new File(path);
		
		if(f.isDirectory()) {
			File []arrFile = f.listFiles();
			for(File i : arrFile){
				find(i.getPath());
			}
		} else if (f.isFile() && f.getPath().endsWith(".txt")) {
			arr.add(f);
		}
	}
}

class A extends Thread{
	
	@Override
	public void run() {
		try {
			for (File i : Main.arr)
			{
					List<String> a = Files.readAllLines(Paths.get(i.getPath()));
					
					boolean flag = false;
					for (int j=0;j<a.size();j++){
						String aa = a.get(j).toUpperCase();
						
						//System.out.println(aa.indexOf(Main.seq) + " " + Main.seq);
						while(aa.indexOf(Main.seq) != -1){
							if (!flag)
								System.out.println("File: " + i.getName());
							System.out.println("PATH: " + i.getPath() + ", ROW: " + j + ", COLUMN: " + aa.indexOf(Main.seq));
							flag = true;
							
							String prije = "";
							if (aa.indexOf(Main.seq) != 0)
								prije = aa.substring(0, aa.indexOf(Main.seq));
							
							String poslije = aa.substring(aa.indexOf(Main.seq) + Main.seq.length(), aa.length());
							
							aa = "";
							aa+=prije;
							aa+=poslije;
						}
					}
					
					if (flag){
						//nije podrzano ovo sto mi treba = browseFileDirectory
						java.awt.Desktop.getDesktop().open(i);
					}
					
					synchronized(Main.b){
						Main.b.notify();
					}
					
					synchronized(this){
						wait();
					}
			}
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	
}
}

class B extends Thread{
	@Override
	public void run(){
		try{
			do{
				synchronized(this){
					wait();
				}

				System.out.print("NASTAVAK: ");
				Scanner scan = new Scanner(System.in);
				String option = scan.nextLine();
				
				if (option.equals(".")){
					synchronized(Main.a){
						Main.a.notify();
					}
				}
			}while (true);
		}catch (Exception e){
		}
	}
}
