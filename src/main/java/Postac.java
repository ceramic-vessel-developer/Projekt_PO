/**
 * Klasa abstrakcyjna postaci na planszy
 * 
 * @see Obiekt
 */
abstract class Postac extends Obiekt {
	/**
	 * Zasieg wzroku postaci
	 */
	protected int zasieg;

	/**
	 * Konstruktor postaci
	 * 
	 * @param zasieg zasieg wzroku
	 */
	protected Postac(int zasieg) {
		this.zasieg = zasieg;
	}

	/**
	 * Getter dla zasiegu
	 * 
	 * @return zasieg wzroku postaci
	 */
	public int getZasieg() {
		return this.zasieg;
	}

	/**
	 * Setter dla zasiegu wzroku
	 * 
	 * @param zasieg zasieg wzroku postaci
	 */
	public void setZasieg(int zasieg) {
		if (zasieg >= 0) {
			this.zasieg = zasieg;
		}
	}
}
