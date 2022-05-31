import java.util.ArrayList;
import java.util.Random;

public class Kolokwium extends NietrwalyPrzedmiot {
	public static ArrayList<Kolokwium> list;

	public Kolokwium(int lifetime, int modDoZadowolenia, int modDoPrzygotowania) {
		this.setLifetime(lifetime);
		this.modDoPrzygotowania = modDoPrzygotowania;
		this.modDoZadowolenia = modDoZadowolenia;
	}

	@Override
	public double[] use(double szczescie, double inteligencja, double studenckosc) {
		Random generator = new Random();

		list.remove(this);

		int znak;

		if (generator.nextInt(100) <= szczescie) {
			znak = 1;
		} else {
			znak = -1;
		}

		return new double[] {
				0.1 * inteligencja * this.modDoPrzygotowania + znak * 0.1 * szczescie * this.modDoPrzygotowania,
				znak * 0.05 * studenckosc * this.modDoZadowolenia };
	}
}
