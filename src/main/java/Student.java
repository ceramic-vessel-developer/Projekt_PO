import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Student extends Postac {
	private double szczescie;
	private double inteligencja;
	private double studenckosc;
	private double p_d_z;
	private double zad;

	public Student(double szczescie, double inteligencja, double studenckosc, double p_d_z, double zad, String imie,
			String nazwisko, int zasieg) {
		this.szczescie = szczescie;
		this.inteligencja = inteligencja;
		this.studenckosc = studenckosc;
		this.p_d_z = p_d_z;
		this.zad = zad;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.zasieg = zasieg;
	}

	public void set_cordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int[] get_cordinates() {
		return new int[] { this.x, this.y };
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

		for (int i = 0; i < this.zasieg; i++) {
			if (stop) {
				break;
			}

			for (int j = 0; j < this.zasieg; j++) {
				if (stop) {
					break;
				}

				if (plansza[i][j] != null) {
					int[] koordynaty = plansza[i][j].get_cordinates();

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
				int[] xy = { this.x + gen.nextInt(this.zasieg * 2) - this.zasieg,
						this.y + gen.nextInt(this.zasieg * 2) - this.zasieg };

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
