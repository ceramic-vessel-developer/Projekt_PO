import java.util.ArrayList;
import java.util.Random;

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
	
	public static void addToList(ArrayList<Piwo> list) {
		Piwo.list.addAll(list);
	}

	public static ArrayList<Piwo> generate_list(int ilosc) {
		ArrayList<Piwo> list = new ArrayList<>();
		Random generator = new Random();

		for (int i = 0; i < ilosc; i++) {
			list.add(new Piwo(generator.nextInt(10), generator.nextInt(100), generator.nextInt(100)));
		}

		return list;
	}
	
	public static void wykonajCykl() {
		for (Piwo piwo : list) {
			piwo.cycleLifetime();;
			}
	}
	
	@Override
	public double[] use(double szczescie, double inteligencja, double studenckosc) {
		list.remove(this);

		return new double[] { -0.1 * studenckosc * this.modDoPrzygotowania, 0.3 * studenckosc * this.modDoZadowolenia };
	}
}
