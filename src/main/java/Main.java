import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {
	public final static int LICZBA_DNI_SEMESTRU = 100;
	public final static int LICZBA_DNI_SESJI = 20;
	public final static int LICZBA_KOLOKWIOW = 4;
	public final static int DZIEN_SESJI = LICZBA_DNI_SEMESTRU - LICZBA_DNI_SESJI + 1;

	public static int semestr = 1;
	public static int dzien = 1;
	public static int kolokwium = 1;

	public static ArrayList<Student> make_student_list(int ilosc) {
		ArrayList<Student> list = new ArrayList<>();
		Random generator = new Random();

		for (int i = 0; i < ilosc; i++) {
			list.add(new Student(generator.nextDouble() * 10, generator.nextDouble() * 10, generator.nextDouble() * 10,
					generator.nextDouble() * 10, generator.nextDouble() * 10, "Michał", "Korczak",
					generator.nextInt()));
		}

		return list;
	}

	public static TypDnia sprawdzDzien() {
		if (dzien >= DZIEN_SESJI) {
			return TypDnia.SESJA;
		}

		int dzienPierwszegoKolowkium = (LICZBA_DNI_SEMESTRU - LICZBA_DNI_SESJI) / LICZBA_KOLOKWIOW;

		for (int i = dzienPierwszegoKolowkium; i < DZIEN_SESJI; i += dzienPierwszegoKolowkium) {
			if (dzien == i) {
				return TypDnia.KOLOKWIUM;
			}
		}
		
		return TypDnia.ZWYKLY;
	}

	public static void main(String[] args) {
		Plansza plansza = new Plansza(100, 100);

		ArrayList<Student> list = make_student_list(10);

		Plansza.placeObjectsInRandomOrder(list);

		Piwo kustosz = new Piwo(2, 2, 2);

		for (Student student : list) {
			System.out.println(Arrays.toString(student.getCoordinates()));
		}

		System.out.println(list.size());
		System.out.println(list.get(9).test(list));
		System.out.println(list.size());
	}

}
//Obiekty są w listach przez referencje