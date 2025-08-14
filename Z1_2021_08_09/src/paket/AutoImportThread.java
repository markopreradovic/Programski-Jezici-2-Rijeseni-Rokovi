package paket;
import java.io.*;
import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;

public class AutoImportThread extends Thread{
	
	String folder;
	
	public AutoImportThread(String folder) {
		this.folder = folder;
		start();
	}
	
	@Override
	public void run() {
		try {
			WatchService service = FileSystems.getDefault().newWatchService();
			Path dir = Paths.get(folder);
			dir.register(service, ENTRY_CREATE);
			while(true) {
				WatchKey key = null;
				try {
					key=service.take();
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				for(WatchEvent<?> event : key.pollEvents()) {
					WatchEvent.Kind<?> kind = event.kind();
					WatchEvent<Path> ev = (WatchEvent<Path>) event;
					if(kind.equals(ENTRY_CREATE)) {
						String fileName = new File(folder).getAbsolutePath() + File.separator + ev.context();
						System.out.println("CONTEXT ABSOLUTE " + fileName);
                        Main.processFile(fileName);
					}
				}
				boolean valid = key.reset();
				if(!valid) 
					break;
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
