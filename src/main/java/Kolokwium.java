import java.util.ArrayList;
import java.util.Random;

public class Kolokwium extends NietrwalyPrzedmiot {
	private static ArrayList<Kolokwium> list;

	public Kolokwium(int lifetime, int modDoZadowolenia, int modDoPrzygotowania) {
		this.setLifetime(lifetime);
		this.setModDoZadowolenia(modDoZadowolenia);
		this.setModDoPrzygotowania(modDoPrzygotowania);
	}

	public static ArrayList<Kolokwium> getList() {
		return list;
	}

	public static void setList(ArrayList<Kolokwium> list) {
		Kolokwium.list = list;
	}
	
	public static void addToList(ArrayList<Kolokwium> list) {
		Kolokwium.list.addAll(list);
	}

	public static ArrayList<Kolokwium> generate_list(int ilosc) {
		ArrayList<Kolokwium> list = new ArrayList<>();
		Random generator = new Random();

		for (int i = 0; i < ilosc; i++) {
			list.add(new Kolokwium(generator.nextInt(25), generator.nextInt(100), generator.nextInt(100)));
		}

		return list;
	}
	
	public static void wykonajCykl() {
		for (Kolokwium kolokwium : list) {
				kolokwium.cycleLifetime();;
			}
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
