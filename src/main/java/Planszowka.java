import java.util.ArrayList;

public class Planszowka extends Przedmiot {
	static ArrayList<Planszowka> list;

	public Planszowka(int modDoZadowolenia, int modDoPrzygotowania) {
		this.modDoZadowolenia = modDoZadowolenia;
		this.modDoPrzygotowania = modDoPrzygotowania;
	}

	@Override
	public double[] use(double szczescie, double inteligencja, double studenckosc) {
		list.remove(this);

		return new double[] { 0.1 * inteligencja * this.modDoPrzygotowania,
				-0.2 * studenckosc * this.modDoZadowolenia };
	}
}
