/**
 * Klasa abstrakcyjna przedmiotu znikającego po czasie, który daje bonus do cech
 * studenta. Dziedziczy po klasie Przedmiot
 * 
 * @see Obiekt
 */
abstract class Przedmiot extends Obiekt {
	/**
	 * Modyfikator do zadowolenia studenta
	 */
	protected int modDoZadowolenia;
	/**
	 * Modyfikator do przygotowania studenta
	 */
	protected int modDoPrzygotowania;

	/**
	 * Getter dla modyfikatora do zadowolenia
	 * 
	 * @return modyfikator do zadowolenia
	 */
	public int getModDoZadowolenia() {
		return modDoZadowolenia;
	}

	/**
	 * Setter dla modyfikatora do zadowolenia
	 * 
	 * @param modDoZadowolenia modyfikator do zadowolenia
	 */
	public void setModDoZadowolenia(int modDoZadowolenia) {
		this.modDoZadowolenia = modDoZadowolenia;
	}

	/**
	 * Getter dla modyfikatora do przygotowania
	 * 
	 * @return modyfikator do przygotowania
	 */
	public int getModDoPrzygotowania() {
		return modDoPrzygotowania;
	}

	/**
	 * Setter dla modyfikatora do przygotowania
	 * 
	 * @param modDoZadowolenia modyfikator do przygotowania
	 */
	public void setModDoPrzygotowania(int modDoPrzygotowania) {
		this.modDoPrzygotowania = modDoPrzygotowania;
	}

	/**
	 * Metoda abstrakcyjna obliczajaca i zwracajaca odpowiednie bonusy dla studenta
	 * zaleznie od jego wskaznikow i modyfikatorow przedmiotu
	 * 
	 * @param szczescie    szczescie studenta
	 * @param inteligencja inteligencja studenta
	 * @param studenckosc  studenckosc studenta
	 * @return tablica zawierajaca modyfikatory dla studenta
	 */
	abstract double[] use(double szczescie, double inteligencja, double studenckosc);
}
