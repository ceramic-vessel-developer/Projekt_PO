import java.util.ArrayList;

public class Materialy extends Przedmiot {
	private static ArrayList<Materialy> list;

	public Materialy(int modDoZadowolenia, int modDoPrzygotowania) {
		this.setModDoZadowolenia(modDoZadowolenia);
		this.setModDoPrzygotowania(modDoPrzygotowania);
	}

	public static ArrayList<Materialy> getList() {
		return list;
	}

	public static void setList(ArrayList<Materialy> list) {
		Materialy.list = list;
	}
	
	@Override
	public double[] use(double szczescie, double inteligencja, double studenckosc) {
		list.remove(this);

		return new double[] { 0.5 * inteligencja * this.modDoPrzygotowania,
				-0.2 * studenckosc * this.modDoZadowolenia };
	}
}
