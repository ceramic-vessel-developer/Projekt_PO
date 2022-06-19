
import java.util.ArrayList;
import java.util.Random;

public class Student extends Postac {
	static ArrayList<Student> list;

	private double szczescie;
	private double inteligencja;
	private double studenckosc;
	private double przygotowanieDoZajec;
	private double zadowolenie;
	private int ects;

	public Student(double szczescie, double inteligencja, double studenckosc, double przygotowanieDoZajec,
			double zadowolenie, String imie, String nazwisko, int zasieg, int ects) {
		super(imie, nazwisko, zasieg);

		this.szczescie = szczescie;
		this.inteligencja = inteligencja;
		this.studenckosc = studenckosc;
		this.ects = ects;
		this.setPrzygotowanieDoZajec(przygotowanieDoZajec);
		this.setZadowolenie(zadowolenie);
	}

	public void changeEcts(int modEcts) {
		this.ects += modEcts;
	}

	public static ArrayList<Student> getList() {
		return list;
	}

	public static void setList(ArrayList<Student> list) {
		Student.list = list;
	}

	public static void addToList(ArrayList<Student> list) {
		Student.list.addAll(list);
	}

	public double getSzczescie() {
		return szczescie;
	}

	public double getInteligencja() {
		return inteligencja;
	}

	public double getStudenckosc() {
		return studenckosc;
	}

	public double getPrzygotowanieDoZajec() {
		return przygotowanieDoZajec;
	}

	public void setPrzygotowanieDoZajec(double przygotowanieDoZajec) {
		this.przygotowanieDoZajec = przygotowanieDoZajec;
	}

	public void changePrzygotowanieDoZajec(double przygotowanieDoZajec) {
		this.przygotowanieDoZajec += przygotowanieDoZajec;
	}

	public double getZadowolenie() {
		return zadowolenie;
	}

	public void setZadowolenie(double zadowolenie) {
		this.zadowolenie = zadowolenie;
	}

	public void changeZadowolenie(double zadowolenie) {
		this.zadowolenie += zadowolenie;
	}

	public static ArrayList<Student> generate_list(int ilosc) {
		ArrayList<Student> list = new ArrayList<>();
		Random generator = new Random();

		for (int i = 0; i < ilosc; i++) {
			list.add(new Student(generator.nextDouble() * 10, generator.nextDouble() * 10, generator.nextDouble() * 10,
					generator.nextDouble() * 100, generator.nextDouble() * 100, "Jan", "Najemnik",
					generator.nextInt(20) + 10, 30));
		}

		return list;
	}

	public void runaway(int x, int y) {
		int[] xy = new int[2];

		boolean stop = false;

		if (x > this.x && y > this.y) {
			for (int i = 0; i < this.zasieg; i++) {
				for (int j = 0; j < this.zasieg; j++) {
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
		} else if (x < this.x && y > this.y) {
			for (int i = 0; i < this.zasieg; i++) {
				for (int j = 0; j < this.zasieg; j++) {
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
		} else if (x > this.x && y < this.y) {
			for (int i = 0; i < this.zasieg; i++) {
				for (int j = 0; j < this.zasieg; j++) {
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
		} else if (x < this.x && y < this.y) {
			for (int i = 0; i < this.zasieg; i++) {
				for (int j = 0; j < this.zasieg; j++) {
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

		System.out.println("Student x: " + this.x + " y: " + this.y + " ran; new x: " + xy[0] + " y: " + xy[1]);
	}

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
						System.out.println("Student x: " + this.x + " y: " + this.y + " not found any object; new x: "
								+ xy[0] + " y: " + xy[1]);

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
			System.out.println(
					"Student x: " + this.x + " y: " + this.y + " found prowadzacy on x: " + xy[0] + " y: " + xy[1]);

			this.runaway(xy[0], xy[1]);

			return;
		} else if (obiekt instanceof Student) {
			while (true) {
				int[] xy = { this.x + generator.nextInt(this.getZasieg() * 2) - this.getZasieg(),
						this.y + generator.nextInt(this.getZasieg() * 2) - this.getZasieg() };

				if (Plansza.isValidCoords(xy[0], xy[1])) {
					if (Plansza.getPole(xy[0], xy[1]) == null) {
						System.out.println("Student x: " + this.x + " y: " + this.y + " found student; new x: " + xy[0]
								+ " y: " + xy[1]);

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

		System.out.println("Student x: " + this.x + " y: " + this.y + " used " + obiekt.getClass().getName() + " x: "
				+ coords[0] + " y: " + coords[1]);

		this.move(coords[0], coords[1]);

		this.changePrzygotowanieDoZajec(modificators[0]);
		this.changeZadowolenie(modificators[1]);
	}

	public void checkStatus() {
		if (this.ects < 0 || this.zadowolenie < 0) {
			System.out.println("Student x: " + this.x + " y: " + this.y + " died");

			list.remove(this);
		}
	}
}
