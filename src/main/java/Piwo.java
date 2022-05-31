import java.util.ArrayList;

public class Piwo extends NietrwalyPrzedmiot {
	static ArrayList<Piwo> list;

	public Piwo(int lifetime, int modDoZadowolenia, int modDoPrzygotowania) {
		this.setLifetime(lifetime);
		this.modDoZadowolenia = modDoZadowolenia;
		this.modDoPrzygotowania = modDoPrzygotowania;
	}

	@Override
	public double[] use(double szczescie, double inteligencja, double studenckosc) {
		list.remove(this);

		return new double[] { -0.1 * studenckosc * this.modDoPrzygotowania, 0.3 * studenckosc * this.modDoZadowolenia };
	}
}
