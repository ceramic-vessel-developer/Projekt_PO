import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa przedmiotu znikającego po czasie, który daje bonus do cech studenta
 * 
 * @see NietrwalyPrzedmiot
 */
public class Kolokwium extends NietrwalyPrzedmiot {
	/**
	 * Lista przedmiotów znajdujacych sie na planszy
	 */
	private static ArrayList<Kolokwium> list;

	/**
	 * Konstruktor klasy
	 * @param lifetime           czas zycia przedmiotu
	 * @param modDoZadowolenia   modyfikator do zadowolenia studenta
	 * @param modDoPrzygotowania modyfikator do przygotowania do zajec studenta
	 */
	public Kolokwium(int lifetime, int modDoZadowolenia, int modDoPrzygotowania) {
		this.setLifetime(lifetime);
		this.setModDoZadowolenia(modDoZadowolenia);
		this.setModDoPrzygotowania(modDoPrzygotowania);
	}

	/**
	 * Getter dla listy
	 * 
	 * @return lista przedmiotow
	 */
	public static ArrayList<Kolokwium> getList() {
		return list;
	}

	/**
	 * Setter dla listy
	 * 
	 * @param list lista przedmiotow do ustawienia w liscie
	 */
	public static void setList(ArrayList<Kolokwium> list) {
		Kolokwium.list = list;
	}

	/**
	 * Dodaje liste przedmiotow do aktualnie istniejacej listy
	 * 
	 * @param list lista przedmiotow do dodania do aktualnej listy
	 */
	public static void addToList(ArrayList<Kolokwium> list) {
		Kolokwium.list.addAll(list);
	}

	/**
	 * Generuje liste przedmiotow o losowych wskaznikach
	 * 
	 * @param ilosc ilosc przedmiotow do wygenerowania
	 * @return lista przedmiotow
	 */
	public static ArrayList<Kolokwium> generate_list(int ilosc) {
		ArrayList<Kolokwium> list = new ArrayList<>();
		Random generator = new Random();

		for (int i = 0; i < ilosc; i++) {
			list.add(new Kolokwium(generator.nextInt(25), generator.nextInt(100), generator.nextInt(100)));
		}

		return list;
	}

	/**
	 * Wykonuje pojedynczy cykl zycia przedmiotow z listy i zmniejsza ich czas zycia
	 * 
	 * @see NietrwalyPrzedmiot
	 */
	public static void wykonajCykl() {
		for (Kolokwium kolokwium : list) {
			kolokwium.cycleLifetime();
			;
		}
	}

	/**
	 * Uzywa przedmiotu, po czym go usuwa z listy i zwraca obliczone modyfikatory
	 * 
	 * @param szczescie    szczescie studenta
	 * @param inteligencja inteligencja studenta
	 * @param studenckosc  studenckosc studenta
	 * @return obliczone modyfikatory (bonus dodatni lub ujemny) dla studenta
	 * @see Student
	 */
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
