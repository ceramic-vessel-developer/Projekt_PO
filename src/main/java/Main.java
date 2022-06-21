import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public final static int LICZBA_SEMESTROW = 6;
	public final static int LICZBA_DNI_SEMESTRU = 100;
	public final static int LICZBA_DNI_SESJI = 20;
	public final static int LICZBA_KOLOKWIOW = 4;
	public final static int DZIEN_SESJI = LICZBA_DNI_SEMESTRU - LICZBA_DNI_SESJI + 1;

	public static TypDnia typDnia = TypDnia.ZWYKLY;

	public static int semestr = 1;
	public static int dzien = 1;
	public static int kolokwium = 1;

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

		System.out.println("Podaj poczatkowa ilosc piw: ");
		int piwaCount = scan.nextInt();
		
		System.out.println("Podaj ilosc semestrow: ");
		int semestrCount = scan.nextInt();

		Plansza plansza = new Plansza(dlugosc, szerokosc);
		
		// random generation of actors
		Student.setList(Student.generate_list(studenciCount, minimum));
		Prowadzacy.setList(Prowadzacy.generate_list(prowadzacyCount));
		Kolokwium.setList(Kolokwium.generate_list(kolokwiaCount));
		Piwo.setList(Piwo.generate_list(piwaCount));

		// random displacement of actors
		Plansza.placeObjectsInRandomOrder(Student.getList());
		Plansza.placeObjectsInRandomOrder(Prowadzacy.getList());
		Plansza.placeObjectsInRandomOrder(Kolokwium.getList());
		Plansza.placeObjectsInRandomOrder(Piwo.getList());

		for (int i = 1; i <= LICZBA_DNI_SEMESTRU * semestrCount; i++) {
			System.out.println("\n\nDzien: " + i);

			Plansza.visualize();

			System.out.println("Student: " + Student.getList().size());
			System.out.println("Prowadzacy: " + Prowadzacy.getList().size());
			System.out.println("Kolokwium: " + Kolokwium.getList().size());
			System.out.println("p: " + Piwo.getList().size());

			System.out.println("\nAt the end of the day");

			wykonajCykl();
		}

		Plansza.visualize();

		System.out.println(Student.getList().size());
		System.out.println("Koniec dzialania programu");
	}
}