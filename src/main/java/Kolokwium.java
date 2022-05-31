import java.util.ArrayList;
import java.util.Random;

public class Kolokwium extends Przedmiot {
	private int lifetime;
	static ArrayList<Kolokwium> list;

	public Kolokwium(int lifetime, int mod_do_zad, int mod_do_przy) {
		this.lifetime = lifetime;
		this.mod_do_przy = mod_do_przy;
		this.mod_do_zad = mod_do_zad;
	}

	void set_cordinates(int x, int y) {

	}

	public int[] get_cordinates() {
		return new int[] { this.x, this.y };
	}

	@Override
	double[] use(double szczescie, double inteligencja, double studenckosc) {
		Random gen = new Random();

		list.remove(this);

		int znak;

		if (gen.nextInt(100) <= szczescie) {
			znak = 1;
		} else {
			znak = -1;
		}

		return new double[] { 0.1 * inteligencja * this.mod_do_przy + znak * 0.1 * szczescie * this.mod_do_przy,
				znak * 0.05 * studenckosc * this.mod_do_zad };
	}
}
