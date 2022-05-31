
import java.util.ArrayList;
import java.util.Random;

public class Student extends Postac {
	private double szczescie;
	private double inteligencja;
	private double studenckosc;
	private double przygotowanieDoZajec;
	private double zadowolenie;

	public Student(double szczescie, double inteligencja, double studenckosc, double przygotowanieDoZajec, double zadowolenie,
			String imie, String nazwisko, int zasieg) {
		super(imie, nazwisko, zasieg);

		this.setSzczescie(szczescie);
		this.setInteligencja(inteligencja);
		this.setStudenckosc(studenckosc);
		this.setPrzygotowanieDoZajec(przygotowanieDoZajec);
		this.setZadowolenie(zadowolenie);
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

	public void setPrzygotowanieDoZajec(double p_d_z) {
		this.przygotowanieDoZajec = p_d_z;
	}

	public double getZadowolenie() {
		return zadowolenie;
	}

	public void setZadowolenie(double zadowolenie) {
		this.zadowolenie = zadowolenie;
	}

	public void move(Obiekt[][] plansza, int x, int y) {
		plansza[x][y] = plansza[this.x][this.y];
		plansza[this.x][this.y] = null;

		this.x = x;
		this.y = y;
	}

	public void action(Obiekt[][] plansza) {
		Random gen = new Random();

		boolean stop = false;

		for (int i = 0; i < this.getZasieg(); i++) {
			if (stop) {
				break;
			}

			for (int j = 0; j < this.getZasieg(); j++) {
				if (stop) {
					break;
				}

				if (plansza[i][j] != null) {
					int[] koordynaty = plansza[i][j].getCoordinates();

					if (plansza[i][j].getClass() != Prowadzacy.class) {
						if (plansza[koordynaty[0] - 1][koordynaty[1] - 1] == null) {
							this.move(plansza, koordynaty[0] - 1, koordynaty[1] - 1);
							stop = true;
						} else if (plansza[koordynaty[0] - 1][koordynaty[1]] == null) {
							this.move(plansza, koordynaty[0] - 1, koordynaty[1]);
							stop = true;
						} else if (plansza[koordynaty[0]][koordynaty[1] - 1] == null) {
							this.move(plansza, koordynaty[0], koordynaty[1] - 1);
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
				int[] xy = { this.x + gen.nextInt(this.getZasieg() * 2) - this.getZasieg(),
						this.y + gen.nextInt(this.getZasieg() * 2) - this.getZasieg() };

				if (plansza[xy[0]][xy[1]] == null) {
					this.move(plansza, xy[0], xy[1]);
				}
			}
		}

	}

	public String test(ArrayList<Student> list) {
		list.remove(this);
		return "DUPA";
	}
}
