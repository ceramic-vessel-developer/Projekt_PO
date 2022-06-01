
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

		this.setSzczescie(szczescie);
		this.setInteligencja(inteligencja);
		this.setStudenckosc(studenckosc);
		this.setPrzygotowanieDoZajec(przygotowanieDoZajec);
		this.setZadowolenie(zadowolenie);
	}

	public static ArrayList<Student> getList() {
		return list;
	}

	public static void setList(ArrayList<Student> list) {
		Student.list = list;
	}

	public double getSzczescie() {
		return szczescie;
	}

	public void setSzczescie(double szczescie) {
		this.szczescie = szczescie;
	}

	public double getInteligencja() {
		return inteligencja;
	}

	public void setInteligencja(double inteligencja) {
		this.inteligencja = inteligencja;
	}

	public double getStudenckosc() {
		return studenckosc;
	}

	public void setStudenckosc(double studenckosc) {
		this.studenckosc = studenckosc;
	}

	public double getPrzygotowanieDoZajec() {
		return przygotowanieDoZajec;
	}

	public void setPrzygotowanieDoZajec(double przygotowanieDoZajec) {
		this.przygotowanieDoZajec = przygotowanieDoZajec;
	}

	public double getZadowolenie() {
		return zadowolenie;
	}

	public void setZadowolenie(double zadowolenie) {
		this.zadowolenie = zadowolenie;
	}

	public static ArrayList<Student> generate_list(int ilosc) {
		ArrayList<Student> list = new ArrayList<>();
		Random generator = new Random();

		for (int i = 0; i < ilosc; i++) {
			list.add(new Student(generator.nextDouble() * 10, generator.nextDouble() * 10, generator.nextDouble() * 10,
					generator.nextDouble() * 10, generator.nextDouble() * 10, "Michał", "Korczak",
					generator.nextInt()));
		}

		return list;
	}

	public void action() {
		Random generator = new Random();

		boolean stop = false;

		for (int i = 0; i < this.getZasieg(); i++) {
			if (stop) {
				break;
			}

			for (int j = 0; j < this.getZasieg(); j++) {
				if (stop) {
					break;
				}

				if (Plansza.getPole(i, j) != null) {
					int[] koordynaty = Plansza.getPole(i, j).getCoordinates();

					if (Plansza.getPole(i, j).getClass() != Prowadzacy.class) {
						if (Plansza.getPole(koordynaty[0] - 1, koordynaty[1] - 1) == null) {
							this.move(koordynaty[0] - 1, koordynaty[1] - 1);
							stop = true;
						} else if (Plansza.getPole(koordynaty[0] - 1, koordynaty[1]) == null) {
							this.move(koordynaty[0] - 1, koordynaty[1]);
							stop = true;
						} else if (Plansza.getPole(koordynaty[0], koordynaty[1] - 1) == null) {
							this.move(koordynaty[0], koordynaty[1] - 1);
							stop = true;
						}

					} else {
						// TODO mechanizm uciekania w drugą strone
					}
				}
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
