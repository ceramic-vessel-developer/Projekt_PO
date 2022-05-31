import java.util.ArrayList;

public class Materialy extends Przedmiot {
	public static ArrayList<Materialy> list;

	public Materialy(int modDoZadowolenia, int modDoPrzygotowania) {
		this.modDoZadowolenia = modDoZadowolenia;
		this.modDoPrzygotowania = modDoPrzygotowania;
	}

	@Override
	public double[] use(double szczescie, double inteligencja, double studenckosc) {
		list.remove(this);

		return new double[] { 0.5 * inteligencja * this.modDoPrzygotowania,
				-0.2 * studenckosc * this.modDoZadowolenia };
	}
}
