import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa przedmiotu znikajacego po czasie, ktory daje bonus do cech studenta
 * 
 * @see NietrwalyPrzedmiot
 */
public class Piwo extends NietrwalyPrzedmiot {
	/**
	 * Lista przedmiotow znajdujacych sie na planszy
	 */
	private static ArrayList<Piwo> list;

	/**
	 * Konstruktor klasy
	 * 
	 * @param lifetime           czas zycia przedmiotu
	 * @param modDoZadowolenia   modyfikator do zadowolenia studenta
	 * @param modDoPrzygotowania modyfikator do przygotowania do zajec studenta
	 */
	public Piwo(int lifetime, int modDoZadowolenia, int modDoPrzygotowania) {
		this.setLifetime(lifetime);
		this.setModDoZadowolenia(modDoZadowolenia);
		this.setModDoPrzygotowania(modDoPrzygotowania);
	}

	/**
	 * Getter dla listy
	 * 
	 * @return lista przedmiotow
	 */
	public static ArrayList<Piwo> getList() {
		return list;
	}

	/**
	 * Setter dla listy
	 * 
	 * @param list lista przedmiotow do ustawienia w liscie
	 */
	public static void setList(ArrayList<Piwo> list) {
		Piwo.list = list;
	}

	/**
	 * Dodaje liste przedmiotow do aktualnie istniejacej listy
	 * 
	 * @param list lista przedmiotow do dodania do aktualnej listy
	 */
	public static void addToList(ArrayList<Piwo> list) {
		Piwo.list.addAll(list);
	}

	/**
	 * Generuje liste przedmiotow o losowych wskaznikach
	 * 
	 * @param ilosc ilosc przedmiotow do wygenerowania
	 * @return lista przedmiotow
	 */
	public static ArrayList<Piwo> generate_list(int ilosc) {
		ArrayList<Piwo> list = new ArrayList<>();
		Random generator = new Random();

		for (int i = 0; i < ilosc; i++) {
			list.add(new Piwo(generator.nextInt(10), generator.nextInt(100), generator.nextInt(100)));
		}

		return list;
	}

	/**
	 * Wykonuje pojedynczy cykl zycia przedmiotow z listy i zmniejsza ich czas zycia
	 * 
	 * @see NietrwalyPrzedmiot
	 */
	public static void wykonajCykl() {
		for (Piwo piwo : list) {
			piwo.cycleLifetime();

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
		list.remove(this);

		return new double[] { -0.1 * studenckosc * this.modDoPrzygotowania, 0.3 * studenckosc * this.modDoZadowolenia };
	}
}
