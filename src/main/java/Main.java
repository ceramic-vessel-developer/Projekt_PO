import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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

	public static ArrayList<Student> make_student_list(int ilosc) {
		ArrayList<Student> list = new ArrayList<>();
		Random generator = new Random();

		for (int i = 0; i < ilosc; i++) {
			list.add(new Student(generator.nextDouble() * 10, generator.nextDouble() * 10, generator.nextDouble() * 10,
					generator.nextDouble() * 10, generator.nextDouble() * 10, "Jan", "Najemnik",
					generator.nextInt()));
		}

		return list;
	}

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
			}
		}
	}

	public static void wykonajCykl() {
		sprawdzDzien();
	}

	public static void main(String[] args) {
		Plansza plansza = new Plansza(100, 100);

		// mock
		Student.setList(Student.generate_list(10));

		Plansza.placeObjectsInRandomOrder(Student.getList());

		Piwo kustosz = new Piwo(2, 2, 2);

		for (Student student : Student.getList()) {
			System.out.println(Arrays.toString(student.getCoordinates()));
		}

		for (int i = 1; i <= LICZBA_DNI_SEMESTRU /* * LICZBA_SEMESTROW */; i++) {
			wykonajCykl();
		}

		System.out.println(Student.getList().size());
		System.out.println(Student.getList().get(9).test(Student.getList()));
		System.out.println(Student.getList().size());
	}

}
//Obiekty są w listach przez referencje