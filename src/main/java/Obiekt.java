/**
 * Podstawowa klasa abstrakcyjna obiektow znajdujacych sie na planszy
 */
abstract class Obiekt {
	/**
	 * Wspolrzedna x
	 */
	protected int x;
	/**
	 * Wspolrzedna y
	 */
	protected int y;

	/**
	 * Getter dla wspolrzednych obiektu
	 * 
	 * @return wspolrzedne obiektu w formie tablicy int
	 */
	public int[] getCoordinates() {
		return new int[] { this.x, this.y };
	}

	/**
	 * Setter dla wspolrzednych obiektu
	 * 
	 * @param x wspolrzedna x
	 * @param y wspolrzedna y
	 */
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Przesuwa obiekt na planszy na podane wspolrzedne
	 * 
	 * @param x wspolrzedna x
	 * @param y wspolrzedna y
	 */
	public void move(int x, int y) {
		Plansza.setPole(x, y, Plansza.getPole(this.x, this.y));
		Plansza.setPole(this.x, this.y, null);

		this.setCoordinates(x, y);
	}
}
