import java.util.ArrayList;
import java.util.Random;

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
	
	public static void addToList(ArrayList<Materialy> list) {
		Materialy.list.addAll(list);
	}

	public static ArrayList<Materialy> generate_list(int ilosc) {
		ArrayList<Materialy> list = new ArrayList<>();
		Random generator = new Random();

		for (int i = 0; i < ilosc; i++) {
			list.add(new Materialy(generator.nextInt(100), generator.nextInt(100)));
		}

		return list;
	}
	
	@Override
	public double[] use(double szczescie, double inteligencja, double studenckosc) {
		list.remove(this);

		return new double[] { 0.5 * inteligencja * this.modDoPrzygotowania,
				-0.2 * studenckosc * this.modDoZadowolenia };
	}
}
