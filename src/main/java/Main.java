import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import com.opencsv.CSVWriter;

/**
 * Klasa glowna symulacji
 */
public class Main {
	/**
	 * Liczba semestrow
	 */
	public final static int LICZBA_SEMESTROW = 6;
	/**
	 * Liczba dni w semestrze
	 */
	public final static int LICZBA_DNI_SEMESTRU = 100;
	/**
	 * Liczba dni sesji
	 */
	public final static int LICZBA_DNI_SESJI = 20;
	/**
	 * Liczba kolokwiow w semestrze
	 */
	public final static int LICZBA_KOLOKWIOW = 4;
	/**
	 * Dzien poczatku sesji
	 */
	public final static int DZIEN_SESJI = LICZBA_DNI_SEMESTRU - LICZBA_DNI_SESJI + 1;

	/**
	 * Rodzaj dnia symulacji
	 * 
	 * @see TypDnia
	 */
	public static TypDnia typDnia = TypDnia.ZWYKLY;

	/**
	 * Aktualny semestr
	 */
	public static int semestr = 1;
	/**
	 * Aktualny dzien
	 */
	public static int dzien = 1;

	/**
	 * Numer przyszlego kolokwium
	 */
	public static int kolokwium = 1;

	/**
	 * Sprawdza rodzaj dnia symulacji
	 */
	public static void sprawdzDzien() {
		typDnia = TypDnia.ZWYKLY;

		if (dzien >= DZIEN_SESJI) {
			typDnia = TypDnia.SESJA;

			return;
		}

		int dzienPierwszegoKolowkium = (LICZBA_DNI_SEMESTRU - LICZBA_DNI_SESJI) / LICZBA_KOLOKWIOW;

		for (int i = dzienPierwszegoKolowkium; i < DZIEN_SESJI; i += dzienPierwszegoKolowkium) {
			if (dzien == i) {
				typDnia = TypDnia.KOLOKWIUM;
				break;
			}
		}

		dzien += 1;
	}

	/**
	 * Zapisuje dane symulacji do pliku csv
	 * 
	 * @param filePath sciezka do pliku
	 * @param data     dane do zapisania
	 */
	public static void writeData(String filePath, String[] data) {
		File file = new File(filePath);
		try {
			FileWriter outputfile = new FileWriter(file, true);

			CSVWriter writer = new CSVWriter(outputfile);

//			String[] header = { "Dzien", "Liczba studentow" };
//			writer.writeNext(header);

			writer.writeNext(data, false);

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Wykonuje pojedynczy cykl symulacji zaczynajac od sprawdzenia dnia,
	 * wylosowania nowych obiektow na planszy i wykonania zdarzen odpowiednich dla
	 * danego typu dnia.
	 */
	public static void wykonajCykl() {
		Random generator = new Random();

		sprawdzDzien();

		// random daily event
//		for (Student student : Student.getList()) {
//			// 33% probability of being happier today
//			if (generator.nextInt(2) == 0) {
//				student.changeZadowolenie(generator.nextDouble() * 25);
//			}
//			// 25% probability of being more sad today
//			else if (generator.nextInt(3) == 0) {
//				student.changeZadowolenie(generator.nextDouble() * -15);
//			}
//		}

		// 20% probability of generating new kolokwium on map
		if (generator.nextInt(4) == 0) {
			ArrayList<Kolokwium> kolokwia = Kolokwium.generate_list(1);

			Plansza.placeObjectsInRandomOrder(kolokwia);
			Kolokwium.addToList(kolokwia);
		}

		// 25% probability of generating new uzywka on map
		if (generator.nextInt(3) == 0) {
			ArrayList<Piwo> piwa = Piwo.generate_list(1);

			Plansza.placeObjectsInRandomOrder(piwa);
			Piwo.addToList(piwa);
		}

		// kolokwium event
		if (typDnia == TypDnia.KOLOKWIUM) {
			for (Prowadzacy prowadzacy : Prowadzacy.getList()) {
				prowadzacy.changeSurowosc(15);
			}
		}

		// sesja event
		if (typDnia == TypDnia.SESJA) {
			for (Prowadzacy prowadzacy : Prowadzacy.getList()) {
				prowadzacy.changeSurowosc(25);
			}
		}

		for (int i = 0; i < Student.getList().size(); i++) {
			Student.getList().get(i).action();
			Student.getList().get(i).checkStatus();
		}

		for (int i = 0; i < Prowadzacy.getList().size(); i++) {
			Prowadzacy.getList().get(i).action();
		}

		// kolokwium event
		if (typDnia == TypDnia.KOLOKWIUM) {
			for (Prowadzacy prowadzacy : Prowadzacy.getList()) {
				prowadzacy.changeSurowosc(-15);
			}
		}

		// sesja event
		if (typDnia == TypDnia.SESJA) {
			for (Prowadzacy prowadzacy : Prowadzacy.getList()) {
				prowadzacy.changeSurowosc(-25);
			}
		}
	}

	/**
	 * Metoda glowna
	 * 
	 * @param args nieuzywane
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Podaj dlugosc planszy: ");
		int dlugosc = scan.nextInt();

		System.out.println("Podaj szerokosc planszy: ");
		int szerokosc = scan.nextInt();

		System.out.println("Podaj minimalna wartosc cech: ");
		int minimum = scan.nextInt();

		System.out.println("Podaj poczatkowa ilosc studentow: ");
		int studenciCount = scan.nextInt();

		System.out.println("Podaj poczatkowa ilosc prowadzacych: ");
		int prowadzacyCount = scan.nextInt();

		System.out.println("Podaj poczatkowa ilosc kolokwiow: ");
		int kolokwiaCount = scan.nextInt();

		System.out.println("Podaj poczatkowa ilosc materialow: ");
		int materialyCount = scan.nextInt();

		System.out.println("Podaj poczatkowa ilosc planszowek: ");
		int planszowkiCount = scan.nextInt();

		System.out.println("Podaj poczatkowa ilosc piw: ");
		int piwaCount = scan.nextInt();

		System.out.println("Podaj ilosc semestrow: ");
		int semestrCount = scan.nextInt();

		Plansza plansza = new Plansza(dlugosc, szerokosc);

		// random generation of actors
		Student.setList(Student.generate_list(studenciCount, minimum));
		Prowadzacy.setList(Prowadzacy.generate_list(prowadzacyCount));
		Kolokwium.setList(Kolokwium.generate_list(kolokwiaCount));
		Materialy.setList(Materialy.generate_list(materialyCount));
		Planszowka.setList(Planszowka.generate_list(planszowkiCount));
		Piwo.setList(Piwo.generate_list(piwaCount));

		// random displacement of actors
		Plansza.placeObjectsInRandomOrder(Student.getList());
		Plansza.placeObjectsInRandomOrder(Prowadzacy.getList());
		Plansza.placeObjectsInRandomOrder(Kolokwium.getList());
		Plansza.placeObjectsInRandomOrder(Materialy.getList());
		Plansza.placeObjectsInRandomOrder(Planszowka.getList());
		Plansza.placeObjectsInRandomOrder(Piwo.getList());

		for (int j = 1; j < semestrCount+1; j++) {
			for (int i = 1; i <= LICZBA_DNI_SEMESTRU; i++) {
				System.out.println("\n\nSemestr: " + j + " Dzien: " + i);

				Plansza.visualize();

				System.out.println("Student: " + Student.getList().size());
				System.out.println("Prowadzacy: " + Prowadzacy.getList().size());
				System.out.println("Kolokwium: " + Kolokwium.getList().size());
				System.out.println("Materialy: " + Materialy.getList().size());
				System.out.println("Planszowka: " + Planszowka.getList().size());
				System.out.println("p: " + Piwo.getList().size());

				writeData("data.csv",
						new String[] { String.valueOf(i+((j-1)*100)), String.valueOf(Student.getList().size()),
								String.valueOf(Kolokwium.getList().size()), String.valueOf(Materialy.getList().size()),
								String.valueOf(Planszowka.getList().size()), String.valueOf(Piwo.getList().size()) });

				System.out.println("\nAt the end of the day");

				wykonajCykl();
			}
		}

		Plansza.visualize();

		System.out.println(Student.getList().size());
		System.out.println("Koniec dzialania programu");
	}
}