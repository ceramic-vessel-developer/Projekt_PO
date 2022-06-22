import java.util.ArrayList;
import java.util.Random;

public class Planszowka extends Przedmiot {
	private static ArrayList<Planszowka> list;

	public Planszowka(int modDoZadowolenia, int modDoPrzygotowania) {
		this.setModDoZadowolenia(modDoZadowolenia);
		this.setModDoPrzygotowania(modDoPrzygotowania);
	}

	public static ArrayList<Planszowka> getList() {
		return list;
	}

	public static void setList(ArrayList<Planszowka> list) {
		Planszowka.list = list;
	}
	
	public static void addToList(ArrayList<Planszowka> list) {
		Planszowka.list.addAll(list);
	}
	public static ArrayList<Planszowka> generate_list(int ilosc) {
		ArrayList<Planszowka> list = new ArrayList<>();
		Random generator = new Random();

		for (int i = 0; i < ilosc; i++) {
			list.add(new Planszowka(generator.nextInt(100), generator.nextInt(100)));
		}

		return list;
	}
	
	@Override
	public double[] use(double szczescie, double inteligencja, double studenckosc) {
		list.remove(this);

		return new double[] { 0.1 * inteligencja * this.modDoPrzygotowania,
				-0.2 * studenckosc * this.modDoZadowolenia };
	}
}
