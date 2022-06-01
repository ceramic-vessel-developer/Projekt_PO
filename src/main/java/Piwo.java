import java.util.ArrayList;

public class Piwo extends NietrwalyPrzedmiot {
	private static ArrayList<Piwo> list;

	public Piwo(int lifetime, int modDoZadowolenia, int modDoPrzygotowania) {
		this.setLifetime(lifetime);
		this.setModDoZadowolenia(modDoZadowolenia);
		this.setModDoPrzygotowania(modDoPrzygotowania);
	}

	public static ArrayList<Piwo> getList() {
		return list;
	}

	public static void setList(ArrayList<Piwo> list) {
		Piwo.list = list;
	}
	
	@Override
	public double[] use(double szczescie, double inteligencja, double studenckosc) {
		list.remove(this);

		return new double[] { -0.1 * studenckosc * this.modDoPrzygotowania, 0.3 * studenckosc * this.modDoZadowolenia };
	}
}
