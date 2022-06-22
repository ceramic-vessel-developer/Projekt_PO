/**
 * Klasa abstrakcyjna przedmiotu znikającego po czasie, ktory daje bonus do cech
 * studenta. Dziedziczy po klasie Przedmiot
 * 
 * @see Przedmiot
 */
abstract class NietrwalyPrzedmiot extends Przedmiot {
	/**
	 * Czas zycia przedmiotu
	 */
	protected int lifetime;

	/**
	 * Getter dla czasu zycia
	 * 
	 * @return czas zycia
	 */
	public int getLifetime() {
		return lifetime;
	}

	/**
	 * Setter dla czasu zycia
	 * 
	 * @param lifetime czas zycia przedmiotu
	 */
	public void setLifetime(int lifetime) {
		this.lifetime = lifetime;
	}

	/**
	 * Metoda zmniejszajaca czas zycia przedmiotu o jeden
	 * @see lifetime
	 */
	public void cycleLifetime() {
		this.lifetime--;
	}
}
