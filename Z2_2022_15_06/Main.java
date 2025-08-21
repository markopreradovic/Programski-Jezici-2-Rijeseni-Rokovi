import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class Main {
    public static final String PREDMET = "predmet";
    public static final String PROFESOR = "profesor";
    public static final String STUDENT = "student";
    public static final String DEST_FILE = "C:\\Users\\Administrator\\Desktop\\Fakultet\\Programski jezici 2\\Rokovi Marko\\Z2_2022_15_06\\Solution\\";

    private static List<Predmet> predmeti = new ArrayList<>();
    private static List<Profesor> profesori = new ArrayList<>();
    private static List<Student> studenti = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Nedovoljno argumenata. Koristi se podrazumevana putanja.");
            args = new String[]{System.getProperty("user.home") + "\\WatchFolder"};
        }

        try (WatchService service = FileSystems.getDefault().newWatchService()) {
            Path dir = Path.of(args[0]);
            if (!Files.exists(dir) || !Files.isDirectory(dir)) {
                System.out.println("Direktorijum " + dir + " ne postoji. Kreiram ga...");
                Files.createDirectories(dir);
            }
            dir.register(service, ENTRY_CREATE);
            // Provera DEST_FILE direktorijuma
            Path destPath = Path.of(DEST_FILE);
            if (!Files.exists(destPath)) {
                Files.createDirectories(destPath);
            }
            while (true) {
                WatchKey key = null;
                try {
                    key = service.take();
                } catch (InterruptedException e) {
                    System.err.println("Prekinuto čekanje na događaje: " + e.getMessage());
                    break;
                }
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();
                    if (kind.equals(ENTRY_CREATE)) {
                        System.out.println("Kreiran novi fajl: " + fileName);
                        glavnaMetoda(args[0], fileName.toString());
                    }
                }
                boolean valid = key.reset();
                if (!valid) {
                    System.out.println("WatchKey više nije validan.");
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Greška pri pokretanju WatchService: " + e.getMessage());
            return;
        }
    }

    // Ostale metode ostaju iste, osim ispravki za Path.of i rukovanje direktorijumima
    public static void glavnaMetoda(String folderPutanja, String nazivFajla) {
        if (nazivFajla.startsWith(PREDMET)) {
            Predmet p = procitajPredmet(Path.of(folderPutanja, nazivFajla).toString());
            if (p == null) return;
            predmeti.add(p);

            Path predmetPath = Path.of(DEST_FILE, p.naziv);
            try {
                Files.createDirectories(Path.of(predmetPath.toString(), "studenti"));
                Files.createDirectories(Path.of(predmetPath.toString(), "profesori"));
            } catch (IOException e) {
                System.err.println("Greška pri kreiranju direktorijuma za predmet " + p.naziv + ": " + e.getMessage());
                return;
            }

            for (Student s : studenti) {
                for (String sId : s.lista) {
                    if (sId.equals(p.id)) {
                        Path studentPath = Path.of(predmetPath.toString(), "studenti", s.indeks);
                        serialize(studentPath.toString(), s);
                    }
                }
            }
            for (Profesor prof : profesori) {
                for (String profId : prof.lista) {
                    if (profId.equals(p.id)) {
                        Path profPath = Path.of(predmetPath.toString(), "profesori", prof.jmb);
                        serialize(profPath.toString(), prof);
                    }
                }
            }
        } else if (nazivFajla.startsWith(PROFESOR)) {
            Profesor profesor = procitajProfesora(Path.of(folderPutanja, nazivFajla).toString());
            if (profesor == null) return;
            profesori.add(profesor);
            for (Predmet p : predmeti) {
                for (String id : profesor.lista) {
                    if (id.equals(p.id)) {
                        Path predmetPath = Path.of(DEST_FILE, p.naziv);
                        Path profPath = Path.of(predmetPath.toString(), "profesori", profesor.jmb);
                        serialize(profPath.toString(), profesor);
                    }
                }
            }
        } else if (nazivFajla.startsWith(STUDENT)) {
            Student s = procitajStudenta(Path.of(folderPutanja, nazivFajla).toString());
            if (s == null) return;
            studenti.add(s);
            for (Predmet p : predmeti) {
                for (String id : s.lista) {
                    if (id.equals(p.id)) {
                        Path predmetPath = Path.of(DEST_FILE, p.naziv);
                        Path studentPath = Path.of(predmetPath.toString(), "studenti", s.indeks);
                        serialize(studentPath.toString(), s);
                    }
                }
            }
        }
    }

    // Ostale metode sa proverenim rukovanjem izuzecima
    public static Predmet procitajPredmet(String nazivFajla) {
        try {
            List<String> linije = Files.readAllLines(Path.of(nazivFajla));
            if (linije.size() < 3) {
                System.err.println("Nepotpuni podaci u fajlu: " + nazivFajla);
                return null;
            }
            return new Predmet(linije.get(1), linije.get(2));
        } catch (IOException e) {
            System.err.println("Greška pri čitanju fajla " + nazivFajla + ": " + e.getMessage());
            return null;
        }
    }

    public static Profesor procitajProfesora(String nazivFajla) {
        try {
            List<String> linije = Files.readAllLines(Path.of(nazivFajla));
            if (linije.size() < 5) {
                System.err.println("Nepotpuni podaci u fajlu: " + nazivFajla);
                return null;
            }
            String predmetId = linije.get(4);
            String[] ids = predmetId.split(",");
            if (ids.length == 0 || ids[0].isEmpty()) {
                System.err.println("Nema validnih ID-ova predmeta u fajlu: " + nazivFajla);
                return null;
            }
            return new Profesor(linije.get(1), linije.get(2), linije.get(3), new ArrayList<>(Arrays.asList(ids)));
        } catch (IOException e) {
            System.err.println("Greška pri čitanju fajla " + nazivFajla + ": " + e.getMessage());
            return null;
        }
    }

    public static Student procitajStudenta(String nazivFajla) {
        try {
            List<String> linije = Files.readAllLines(Path.of(nazivFajla));
            if (linije.size() < 5) {
                System.err.println("Nepotpuni podaci u fajlu: " + nazivFajla);
                return null;
            }
            String predmetId = linije.get(4);
            String[] ids = predmetId.split(",");
            if (ids.length == 0 || ids[0].isEmpty()) {
                System.err.println("Nema validnih ID-ova predmeta u fajlu: " + nazivFajla);
                return null;
            }
            return new Student(linije.get(1), linije.get(2), linije.get(3), new ArrayList<>(Arrays.asList(ids)));
        } catch (IOException e) {
            System.err.println("Greška pri čitanju fajla " + nazivFajla + ": " + e.getMessage());
            return null;
        }
    }

    private static void serialize(String path, Student s) {
        try {
            Files.createDirectories(Path.of(path));
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + File.separator + s.indeks + ".ser"))) {
                oos.writeObject(s);
            }
        } catch (IOException e) {
            System.err.println("Greška pri serijalizaciji studenta " + s.indeks + ": " + e.getMessage());
        }
    }

    private static void serialize(String path, Profesor p) {
        try {
            Files.createDirectories(Path.of(path));
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + File.separator + p.jmb + ".ser"))) {
                oos.writeObject(p);
            }
        } catch (IOException e) {
            System.err.println("Greška pri serijalizaciji profesora " + p.jmb + ": " + e.getMessage());
        }
    }
}