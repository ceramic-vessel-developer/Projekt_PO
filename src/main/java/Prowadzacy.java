import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa prowadzacego, ktory chodzi po planszy i sprawdza przygotowanie do zajec
 * studentow. Zaleznie od ich przygotowania odejmuje lub dodaje punkty ects oraz
 * zadowolenia.
 * 
 * @see Postac
 */
public class Prowadzacy extends Postac {
	/**
	 * Lista postaci znajdujacych sie na planszy
	 */
	private static ArrayList<Prowadzacy> list;

	/**
	 * Stopien naukowy (lub tytul) prowadzacego
	 * 
	 * @see Tytul
	 */
	private Tytul stopien;
	/**
	 * Surowosc prowadzacego wplywajaca na jego ocenianie
	 */
	private double surowosc;
	/**
	 * Szacunek prowadzacego wplywajacy na wartosc bonusu jaki daje studentom po
	 * spotkaniu
	 */
	private double szacunek;

	/**
	 * Konstruktor prowadzacego
	 * 
	 * @param stopien  stopien naukowy prowadzacego
	 * @param surowosc surowosc prowadzacego
	 * @param szacunek szacunek prowadzacego
	 * @param zasieg   zasieg wzroku prowadzacego
	 * @see Tytul
	 */
	public Prowadzacy(Tytul stopien, double surowosc, double szacunek, int zasieg) {
		super(zasieg);

		this.setStopien(stopien);
		this.setSurowosc(surowosc);
		this.setSzacunek(szacunek);
	}

	/**
	 * Getter dla listy
	 * 
	 * @return lista postaci
	 */
	public static ArrayList<Prowadzacy> getList() {
		return list;
	}

	/**
	 * Setter dla listy
	 * 
	 * @param list lista postaci do ustawienia w liscie
	 */
	public static void setList(ArrayList<Prowadzacy> list) {
		Prowadzacy.list = list;
	}

	/**
	 * Dodaje liste postaci do aktualnie istniejacej listy
	 * 
	 * @param list lista postaci do dodania do aktualnej listy
	 */
	public static void addToList(ArrayList<Prowadzacy> list) {
		Prowadzacy.list.addAll(list);
	}

	/**
	 * Getter dla stopnia
	 * 
	 * @return stopien
	 * @see Tytul
	 */
	public Tytul getStopien() {
		return stopien;
	}

	/**
	 * Setter dla stopnia
	 * 
	 * @param stopien stopien naukowy
	 */
	public void setStopien(Tytul stopien) {
		this.stopien = stopien;
	}

	/**
	 * Getter dla surowosci
	 * 
	 * @return surowosc prowadzacego
	 */
	public double getSurowosc() {
		return surowosc;
	}

	/**
	 * Setter dla surowosci
	 * 
	 * @param surowosc surowosc prowadzacego
	 */
	public void setSurowosc(double surowosc) {
		this.surowosc = surowosc;
	}

	/**
	 * Zmienia aktualna surowosc o podana wartosc
	 * 
	 * @param surowosc surowosc prowadzacego
	 */
	public void changeSurowosc(double surowosc) {
		this.surowosc += surowosc;
	}

	/**
	 * Getter dla szacunku
	 * 
	 * @return szacunek prowadzacego
	 */
	public double getSzacunek() {
		return szacunek;
	}

	/**
	 * Setter dla szacunku
	 * 
	 * @param szacunek szacunek prowadzacego
	 */
	public void setSzacunek(double szacunek) {
		this.szacunek = szacunek;
	}

	/**
	 * Generuje liste postaci o losowych wskaznikach
	 * 
	 * @param ilosc ilosc postaci do wygenerowania
	 * @return lista postaci
	 */
	public static ArrayList<Prowadzacy> generate_list(int ilosc) {
		ArrayList<Prowadzacy> list = new ArrayList<>();
		Random generator = new Random();

		for (int i = 0; i < ilosc; i++) {
			list.add(new Prowadzacy(Tytul.randomTytul(), generator.nextDouble() * 100, generator.nextDouble() * 100,
					generator.nextInt(15) + 7));
		}

		return list;
	}

	/**
	 * Wykonuje akcje jaka podejmuje prowadzacy przez jeden cykl symulacji. Bada czy
	 * w zasiegu wzroku znajduje sie jakis student i jesli go znajduje, to sprawdza
	 * jego przygotowanie do zajec i zaleznie od wyniku daje odpowiedni bonus. W
	 * przypadku niepowodzenia krazy losowo po planszy poszukujac studentow.
	 * 
	 * @see Student
	 */
	public void action() {
		Random generator = new Random();

		ArrayList<Obiekt> obiekty = Plansza.searchMapWithinRange(this.x, this.y, this.getZasieg());

		if (obiekty.isEmpty()) {
			// crawl like pathetic being in case of searching
			while (true) {
				int[] xy = { this.x + generator.nextInt(this.getZasieg() * 2) - this.getZasieg(),
						this.y + generator.nextInt(this.getZasieg() * 2) - this.getZasieg() };

				if (Plansza.isValidCoords(xy[0], xy[1])) {
					if (Plansza.getPole(xy[0], xy[1]) == null) {
						System.out.println("Prowadzacy x: " + this.x + " y: " + this.y
								+ " not found any object; new x: " + xy[0] + " y: " + xy[1]);

						this.move(xy[0], xy[1]);

						break;
					}
				}
			}

			return;
		}

		int[] odleglosci = Plansza.calculateDistances(this, obiekty);

		int[] nearestObjectInfo = Plansza.findNearestObject(obiekty, odleglosci);

		Obiekt obiekt = obiekty.get(nearestObjectInfo[0]);

		if (obiekt instanceof Student) {
			Student student = (Student) obiekt;
			int[] coords = student.getCoordinates();
			boolean moved = false;

			if ((Plansza.isValidCoords(coords[0] - 1, coords[1] - 1)
					&& Plansza.getPole(coords[0] - 1, coords[1] - 1) == null)) {

				System.out.println("Prowadzacy x: " + this.x + " y: " + this.y + " found student; new x: "
						+ (coords[0] - 1) + " y: " + (coords[1] - 1));
				this.move(coords[0] - 1, coords[1] - 1);
				moved = true;

			} else if ((Plansza.isValidCoords(coords[0] + 1, coords[1] - 1)
					&& Plansza.getPole(coords[0] + 1, coords[1] - 1) == null)) {

				System.out.println("Prowadzacy x: " + this.x + " y: " + this.y + " found student; new x: "
						+ (coords[0] + 1) + " y: " + (coords[1] - 1));
				this.move(coords[0] + 1, coords[1] - 1);
				moved = true;

			} else if ((Plansza.isValidCoords(coords[0] - 1, coords[1] + 1)
					&& Plansza.getPole(coords[0] - 1, coords[1] + 1) == null)) {

				System.out.println("Prowadzacy x: " + this.x + " y: " + this.y + " found student; new x: "
						+ (coords[0] - 1) + " y: " + (coords[1] + 1));
				this.move(coords[0] - 1, coords[1] + 1);
				moved = true;

			} else if ((Plansza.isValidCoords(coords[0] + 1, coords[1] + 1)
					&& Plansza.getPole(coords[0] + 1, coords[1] + 1) == null)) {

				System.out.println("Prowadzacy x: " + this.x + " y: " + this.y + " found student; new x: "
						+ (coords[0] + 1) + " y: " + (coords[1] + 1));
				this.move(coords[0] + 1, coords[1] + 1);
				moved = true;

			}

			if (moved) {

				int ects = 0;
				double zadowolenie;

				if (student.getPrzygotowanieDoZajec() < this.surowosc) {
					if (this.stopien == Tytul.INZYNIER || this.stopien == Tytul.MAGISTER_INZ) {
						ects = -1;
					} else if (this.stopien == Tytul.DOKTOR || this.stopien == Tytul.DOKTOR_HAB) {
						ects = -2;
					} else if (this.stopien == Tytul.PROFESOR) {
						ects = -3;
					}

					zadowolenie = -(this.szacunek - student.getSzczescie()) * 0.5;
				} else {
					zadowolenie = this.szacunek * 0.3;
				}

				student.changeEcts(ects);
				student.changeZadowolenie(zadowolenie);

				System.out.println("Prowadzacy x: " + this.x + " y: " + this.y + " found student " + " x: " + coords[0]
						+ " y: " + coords[1]);

				System.out.println("Prowadzacy x: " + this.x + " y: " + this.y + "; change ects: " + ects
						+ " zadowolenie: " + zadowolenie);

				System.out.println("Prowadzacy x: " + this.x + " y: " + this.y + "; student ects: " + student.getEcts()
						+ " zadowolenie: " + student.getZadowolenie());

				return;
			}
		} else {

			// crawl like pathetic being in case of searching
			while (true) {
				int[] xy = { this.x + generator.nextInt(this.getZasieg() * 2) - this.getZasieg(),
						this.y + generator.nextInt(this.getZasieg() * 2) - this.getZasieg() };

				if (Plansza.isValidCoords(xy[0], xy[1])) {
					if (Plansza.getPole(xy[0], xy[1]) == null) {
						System.out.println("Prowadzacy x: " + this.x + " y: " + this.y
								+ " not found any student; new x: " + xy[0] + " y: " + xy[1]);

						this.move(xy[0], xy[1]);

						break;
					}
				}
			}

		}
	}
}
