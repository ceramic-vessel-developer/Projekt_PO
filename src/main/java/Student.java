
import java.util.ArrayList;
import java.util.Random;

public class Student extends Postac {
	private static ArrayList<Student> list;

	private double szczescie;
	private double inteligencja;
	private double studenckosc;
	private double przygotowanieDoZajec;
	private double zadowolenie;

	public Student(double szczescie, double inteligencja, double studenckosc, double przygotowanieDoZajec,
			double zadowolenie, String imie, String nazwisko, int zasieg) {
		super(imie, nazwisko, zasieg);

		this.szczescie = szczescie;
		this.inteligencja = inteligencja;
		this.studenckosc = studenckosc;
		this.setPrzygotowanieDoZajec(przygotowanieDoZajec);
		this.setZadowolenie(zadowolenie);
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
					generator.nextDouble() * 10, generator.nextDouble() * 10, "Jan", "Najemnik",
					generator.nextInt(20) + 10));
		}

		return list;
	}

	public void action() {
		Random generator = new Random();

		if (this.focusedObject == null) {
			ArrayList<Obiekt> obiekty = Plansza.searchMapWithinRange(this.x, this.y, this.getZasieg());

			if (obiekty.isEmpty()) {
				// cmon do something
				return;
			}

			int[] odleglosci = Plansza.calculateDistances(this, obiekty);

			int[] nearestObjectInfo = Plansza.findNearestObject(obiekty, odleglosci);

			this.focusedObject = obiekty.get(nearestObjectInfo[0]);
		}

		boolean stop = false;

		int[] koordynaty = this.focusedObject.getCoordinates();

		if (!stop && Plansza.isValidCoords(koordynaty[0] - 1, koordynaty[1] - 1)) {
			if (Plansza.getPole(koordynaty[0] - 1, koordynaty[1] - 1) == null) {
				this.move(koordynaty[0] - 1, koordynaty[1] - 1);
				stop = true;
			}
		} else if (!stop && Plansza.isValidCoords(koordynaty[0] - 1, koordynaty[1])) {
			if (Plansza.getPole(koordynaty[0] - 1, koordynaty[1]) == null) {
				this.move(koordynaty[0] - 1, koordynaty[1]);
				stop = true;
			}
		} else if (!stop && Plansza.isValidCoords(koordynaty[0], koordynaty[1] - 1)) {
			if (Plansza.getPole(koordynaty[0], koordynaty[1] - 1) == null) {
				this.move(koordynaty[0], koordynaty[1] - 1);
				stop = true;
			}
		}

		if (!stop) {
			for (int i = 0; i < 10; i++) {
				int[] xy = { this.x + generator.nextInt(this.getZasieg() * 2) - this.getZasieg(),
						this.y + generator.nextInt(this.getZasieg() * 2) - this.getZasieg() };

				if (Plansza.getPole(xy[0], xy[1]) == null) {
					this.move(xy[0], xy[1]);
				}
			}
		}

	}

	public String test(ArrayList<Student> list) {
		list.remove(this);
		return "DUPA";
	}
}
