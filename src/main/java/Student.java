
import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa studenta, ktory chodzi po planszy i szuka przedmiotow. Jesli znajdzie
 * jakis przedmiot, to wybiera najblizszy i otrzymuje z tego powodu bonusy. W
 * przypadku spotkania studenta idzie dalej, a w przypadku spotkania
 * prowadzacego ucieka.
 * 
 * @see Postac
 */
public class Student extends Postac {
	/**
	 * Lista postaci znajdujacych sie na planszy
	 */
	static ArrayList<Student> list;

	/**
	 * Szczescie studenta wplywajace na jego powodzenie
	 */
	private double szczescie;
	/**
	 * Inteligencja studenta wplywajaca na jego powodzenie
	 */
	private double inteligencja;
	/**
	 * Studenckosc studenta wplywajaca na jego powodzenie
	 */
	private double studenckosc;
	/**
	 * Przygotowanie do zajec studenta wplywajace na jego powodzenie
	 */
	private double przygotowanieDoZajec;
	/**
	 * Zadowolenie studenta wplywajace na jego powodzenie
	 */
	private double zadowolenie;
	/**
	 * Punkty ects i jednoczesnie zdrowie studenta
	 */
	private int ects;

	/**
	 * Konstruktor studenta
	 * 
	 * @param szczescie            szczescie studenta
	 * @param inteligencja         inteligencja studenta
	 * @param studenckosc          studenckosc studenta
	 * @param przygotowanieDoZajec przygotowanie do zajec studenta
	 * @param zadowolenie          zadowolenie studenta
	 * @param zasieg               zasieg wzroku studenta
	 * @param ects                 punkty ects studenta
	 */
	public Student(double szczescie, double inteligencja, double studenckosc, double przygotowanieDoZajec,
			double zadowolenie, int zasieg, int ects) {
		super(zasieg);

		this.szczescie = szczescie;
		this.inteligencja = inteligencja;
		this.studenckosc = studenckosc;
		this.ects = ects;
		this.setPrzygotowanieDoZajec(przygotowanieDoZajec);
		this.setZadowolenie(zadowolenie);
	}

	/**
	 * Getter dla listy
	 * 
	 * @return lista postaci
	 */
	public static ArrayList<Student> getList() {
		return list;
	}

	/**
	 * Setter dla listy
	 * 
	 * @param list lista postaci do ustawienia w liscie
	 */
	public static void setList(ArrayList<Student> list) {
		Student.list = list;
	}

	/**
	 * Dodaje liste postaci do aktualnie istniejacej listy
	 * 
	 * @param list lista postaci do dodania do aktualnej listy
	 */
	public static void addToList(ArrayList<Student> list) {
		Student.list.addAll(list);
	}

	/**
	 * Getter dla szczescia
	 * 
	 * @return szczescie
	 */
	public double getSzczescie() {
		return szczescie;
	}

	/**
	 * Getter dla inteligencja
	 * 
	 * @return inteligencja
	 */
	public double getInteligencja() {
		return inteligencja;
	}

	/**
	 * Getter dla studenckosc
	 * 
	 * @return studenckosc
	 */
	public double getStudenckosc() {
		return studenckosc;
	}

	/**
	 * Getter dla przygotowanieDoZajec
	 * 
	 * @return przygotowanieDoZajec
	 */
	public double getPrzygotowanieDoZajec() {
		return przygotowanieDoZajec;
	}

	/**
	 * Setter dla przygotowanieDoZajec
	 * 
	 * @param przygotowanieDoZajec przygotowanie do zajec
	 */
	public void setPrzygotowanieDoZajec(double przygotowanieDoZajec) {
		this.przygotowanieDoZajec = przygotowanieDoZajec;
	}

	/**
	 * Zmienia aktualne przygotowanie do zajec o podana wartosc
	 * 
	 * @param przygotowanieDoZajec przygotowanie do zajec
	 */
	public void changePrzygotowanieDoZajec(double przygotowanieDoZajec) {
		this.przygotowanieDoZajec += przygotowanieDoZajec;
	}

	/**
	 * Getter dla zadowolenie
	 * 
	 * @return zadowolenie
	 */
	public double getZadowolenie() {
		return zadowolenie;
	}

	/**
	 * Setter dla zadowolenie
	 * 
	 * @param zadowolenie zadowolenie
	 */
	public void setZadowolenie(double zadowolenie) {
		this.zadowolenie = zadowolenie;
	}

	/**
	 * Zmienia aktualne zadowolenie o podana wartosc
	 * 
	 * @param zadowolenie zadowolenie
	 */
	public void changeZadowolenie(double zadowolenie) {
		this.zadowolenie += zadowolenie;
	}

	/**
	 * Getter dla ects
	 * 
	 * @return ects
	 */
	public int getEcts() {
		return ects;
	}

	/**
	 * Setter dla ects
	 * 
	 * @param ects punkty ects
	 */
	public void setEcts(int ects) {
		this.ects = ects;
	}

	/**
	 * Zmienia aktualne ects o podana wartosc
	 * 
	 * @param ects ects
	 */
	public void changeEcts(int ects) {
		this.ects += ects;
	}

	/**
	 * Generuje liste postaci o losowych wskaznikach
	 * 
	 * @param ilosc ilosc postaci do wygenerowania
	 * @param minimum minimalny zakres modyfikatorow
	 * @return lista postaci
	 */
	public static ArrayList<Student> generate_list(int ilosc, int minimum) {
		ArrayList<Student> list = new ArrayList<>();
		Random generator = new Random();

		for (int i = 0; i < ilosc; i++) {
			list.add(new Student(generator.nextDouble() * 10 + minimum, generator.nextDouble() * 10 + minimum,
					generator.nextDouble() * 10 + minimum, generator.nextDouble() * 100 + minimum,
					generator.nextDouble() * 100 + minimum, generator.nextInt(20) + 10 + minimum, 30));
		}

		return list;
	}

	/**
	 * Student ucieka od prowadzacego w kierunku najbardziej od niego oddalonym
	 * 
	 * @param x wspolrzedna x prowadzacego
	 * @param y wspolrzedna y prowadzacego
	 * @see Prowadzacy
	 */
	public void runaway(int x, int y) {
		int[] xy = new int[2];
		Random generator = new Random();

		boolean stop = false;
		if ((this.x == 0 && this.y == 0) || (this.x == 0 && this.y == Plansza.getSzerokosc())
				|| (this.x == Plansza.getDlugosc() && this.y == 0)
				|| (this.x == Plansza.getDlugosc() && this.y == Plansza.getSzerokosc())) {

			while (true) {
				xy[0] = this.x + generator.nextInt(this.getZasieg() * 2) - this.getZasieg();
				xy[1] = this.y + generator.nextInt(this.getZasieg() * 2) - this.getZasieg();

				if (Plansza.isValidCoords(xy[0], xy[1])) {
					if (Plansza.getPole(xy[0], xy[1]) == null) {

						break;

					}
				}
			}
		} else {
			if (x >= this.x && y >= this.y) {
				for (int i = 0; i <= this.zasieg; i++) {
					for (int j = 0; j <= this.zasieg; j++) {
						xy[0] = this.x - zasieg + i;
						xy[1] = this.y - zasieg + j;

						if (Plansza.isValidCoords(xy[0], xy[1])) {
							if (Plansza.getPole(xy[0], xy[1]) == null) {
								stop = true;

								break;
							}
						}
					}

					if (stop) {
						break;
					}
				}
			} else if (x <= this.x && y >= this.y) {
				for (int i = 0; i <= this.zasieg; i++) {
					for (int j = 0; j <= this.zasieg; j++) {
						xy[0] = this.x + zasieg - i;
						xy[1] = this.y - zasieg + j;

						if (Plansza.isValidCoords(xy[0], xy[1])) {
							if (Plansza.getPole(xy[0], xy[1]) == null) {
								stop = true;

								break;
							}
						}
					}

					if (stop) {
						break;
					}
				}
			} else if (x >= this.x && y <= this.y) {
				for (int i = 0; i <= this.zasieg; i++) {
					for (int j = 0; j <= this.zasieg; j++) {
						xy[0] = this.x - zasieg + i;
						xy[1] = this.y + zasieg - j;

						if (Plansza.isValidCoords(xy[0], xy[1])) {
							if (Plansza.getPole(xy[0], xy[1]) == null) {
								stop = true;

								break;
							}
						}
					}

					if (stop) {
						break;
					}
				}
			} else if (x <= this.x && y <= this.y) {
				for (int i = 0; i <= this.zasieg; i++) {
					for (int j = 0; j <= this.zasieg; j++) {
						xy[0] = this.x + zasieg - i;
						xy[1] = this.y + zasieg - j;

						if (Plansza.isValidCoords(xy[0], xy[1])) {
							if (Plansza.getPole(xy[0], xy[1]) == null) {
								stop = true;

								break;
							}
						}
					}

					if (stop) {
						break;
					}
				}
			}
		}
		if (!Plansza.isValidCoords(xy[0], xy[1])) {
			xy[0] = this.x;
			xy[1] = this.y;
		}
		if (Main.EXTRA_LOGS) {
			System.out.println("Student x: " + this.x + " y: " + this.y + " ran; new x: " + xy[0] + " y: " + xy[1]);
		}
		this.move(xy[0], xy[1]);
	}

	/**
	 * Wykonuje akcje jaka podejmuje student przez jeden cykl symulacji. Bada czy w
	 * zasiegu wzroku znajduje sie jakis przedmiot i jesli go znajduje, to sprawdza
	 * uzywa go i zaleznie od wyniku daje odpowiedni bonus. W przypadku
	 * niepowodzenia krazy losowo po planszy poszukujac przedmiotow. W przypadku
	 * spotkania prowadzacego ucieka, a w przypadku spotkania studenta idzie dalej.
	 * 
	 * @see Prowadzacy
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

						if (Main.EXTRA_LOGS) {
							System.out.println("Student x: " + this.x + " y: " + this.y + " not found any object; new x: "
									+ xy[0] + " y: " + xy[1]);
						}

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

		if (obiekt instanceof Prowadzacy) {
			// run for your life
			int[] xy = obiekt.getCoordinates();
			if (Main.EXTRA_LOGS) {
				System.out.println(
						"Student x: " + this.x + " y: " + this.y + " found prowadzacy on x: " + xy[0] + " y: " + xy[1]);
			}
			this.runaway(xy[0], xy[1]);

			return;
		} else if (obiekt instanceof Student) {
			while (true) {
				int[] xy = { this.x + generator.nextInt(this.getZasieg() * 2) - this.getZasieg(),
						this.y + generator.nextInt(this.getZasieg() * 2) - this.getZasieg() };

				if (Plansza.isValidCoords(xy[0], xy[1])) {
					if (Plansza.getPole(xy[0], xy[1]) == null) {
						if (Main.EXTRA_LOGS) {
							System.out.println("Student x: " + this.x + " y: " + this.y + " found student; new x: " + xy[0]
									+ " y: " + xy[1]);
						}
						this.move(xy[0], xy[1]);

						break;
					}
				}
			}

			return;
		}

		Przedmiot focusedItem = (Przedmiot) obiekt;

		int[] coords = focusedItem.getCoordinates();

		// teleport to the found object and use it
		double[] modificators = focusedItem.use(szczescie, inteligencja, studenckosc);

		if (Main.EXTRA_LOGS) {
			System.out.println("Student x: " + this.x + " y: " + this.y + " used " + obiekt.getClass().getName() + " x: "
					+ coords[0] + " y: " + coords[1]);
		}
		this.move(coords[0], coords[1]);

		this.changePrzygotowanieDoZajec(modificators[0]);
		this.changeZadowolenie(modificators[1]);
	}

	/**
	 * Sprawdza stan cech studenta, jesli jego ects lub zadowolenie spadlo ponizej
	 * zera to student umiera
	 */
	public void checkStatus() {
		if (this.ects < 0 || this.zadowolenie < 0) {
			if (Main.EXTRA_LOGS) {
				System.out.println("Student x: " + this.x + " y: " + this.y + " died");
			}
			Plansza.setPole(this.x, this.y, null);

			list.remove(this);
		}
	}
}
