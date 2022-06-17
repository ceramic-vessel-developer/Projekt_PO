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
		dzien+=1;
	}

	public static void wykonajCykl() {
		Random generator = new Random();

		sprawdzDzien();
		/*
		// random daily event
		for (Student student : Student.getList()) {
			// 33% probability of being happier today
			if (generator.nextInt(2) == 0) {
				student.changeZadowolenie(generator.nextDouble() * 25);
			}
			// 25% probability of being more sad today
			else if (generator.nextInt(3) == 0) {
				student.changeZadowolenie(generator.nextDouble() * -15);
			}
		}
		*/
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
		int i = 0;
		while (true){
			if(i>=Student.getList().size()){
				break;
			}
			Student.getList().get(i).action();
			Student.getList().get(i).checkStatus();
			i++;
		}
	}

	public static void visualization(Plansza plansza){

		for (int i = 0; i < Plansza.getSzerokosc()+2; i++) {
			System.out.print('-');
		}

		System.out.print('\n');

		for (int i = 0; i < Plansza.getDlugosc(); i++) {
			System.out.print('|');

			for (int j = 0; j < Plansza.getSzerokosc(); j++) {
				if(Plansza.getPole(i,j)!=null) {
					String classname = Plansza.getPole(i, j).getClass().getName();

					if(classname.equals("Student")){
						System.out.print('S');
					}else if(classname.equals("Prowadzacy")){
						System.out.print('P');
					}else{
						System.out.print('O');
					}

				}else{
					System.out.print(' ');
				}
			}
			System.out.print("|\n");
		}
		for (int i = 0; i < Plansza.getSzerokosc()+2; i++) {
			System.out.print('-');
		}
		System.out.print('\n');
	}

	public static void main(String[] args) {
		System.out.println("Program zostal uruchomiony poprawnie\nTest rozmieszczenia obiektow:");
		Plansza plansza = new Plansza(25, 25);

		// random generation of actors
		Student.setList(Student.generate_list(10));
		Prowadzacy.setList(Prowadzacy.generate_list(0));
		Kolokwium.setList(Kolokwium.generate_list(5));
		Piwo.setList(Piwo.generate_list(5));

		// random displacement of actors
		Plansza.placeObjectsInRandomOrder(Student.getList());
		Plansza.placeObjectsInRandomOrder(Prowadzacy.getList());
		Plansza.placeObjectsInRandomOrder(Kolokwium.getList());
		Plansza.placeObjectsInRandomOrder(Piwo.getList());

		for (Student student : Student.getList()) {
			System.out.println(Arrays.toString(student.getCoordinates()));
		}
		visualization(plansza);
		for (int i = 1; i <= LICZBA_DNI_SEMESTRU /* * LICZBA_SEMESTROW */; i++) {
			wykonajCykl();
			visualization(plansza);
			System.out.println(Student.getList().size());
			System.out.println(Kolokwium.getList().size());
			System.out.println(Piwo.getList().size());
		}
		visualization(plansza);
		System.out.println(Student.getList().size());
		System.out.println("Koniec dzialania programu");
	}

}
//Obiekty są w listach przez referencje