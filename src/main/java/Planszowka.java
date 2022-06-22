import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa przedmiotu, który daje bonus do cech studenta
 * 
 * @see Przedmiot
 */
public class Planszowka extends Przedmiot {
	/**
	 * Lista przedmiotów znajdujacych sie na planszy
	 */
	private static ArrayList<Planszowka> list;

	/**
	 * Konstruktor klasy
	 * 
	 * @param modDoZadowolenia   modyfikator do zadowolenia studenta
	 * @param modDoPrzygotowania modyfikator do przygotowania do zajec studenta
	 */
	public Planszowka(int modDoZadowolenia, int modDoPrzygotowania) {
		this.setModDoZadowolenia(modDoZadowolenia);
		this.setModDoPrzygotowania(modDoPrzygotowania);
	}

	/**
	 * Getter dla listy
	 * 
	 * @return lista przedmiotow
	 */
	public static ArrayList<Planszowka> getList() {
		return list;
	}

	/**
	 * Setter dla listy
	 * 
	 * @param list lista przedmiotow do ustawienia w liscie
	 */
	public static void setList(ArrayList<Planszowka> list) {
		Planszowka.list = list;
	}

	/**
	 * Dodaje liste przedmiotow do aktualnie istniejacej listy
	 * 
	 * @param list lista przedmiotow do dodania do aktualnej listy
	 */
	public static void addToList(ArrayList<Planszowka> list) {
		Planszowka.list.addAll(list);
	}

	/**
	 * Generuje liste przedmiotow o losowych wskaznikach
	 * 
	 * @param ilosc ilosc przedmiotow do wygenerowania
	 * @return lista przedmiotow
	 */
	public static ArrayList<Planszowka> generate_list(int ilosc) {
		ArrayList<Planszowka> list = new ArrayList<>();
		Random generator = new Random();

		for (int i = 0; i < ilosc; i++) {
			list.add(new Planszowka(generator.nextInt(100), generator.nextInt(100)));
		}

		return list;
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

		return new double[] { 0.1 * inteligencja * this.modDoPrzygotowania,
				-0.2 * studenckosc * this.modDoZadowolenia };
	}
}
