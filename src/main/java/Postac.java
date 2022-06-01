abstract class Postac extends Obiekt {
	protected String imie;
	protected String nazwisko;
	protected int zasieg;
	
	protected Przedmiot focusedItem;

	protected Postac(String imie, String nazwisko, int zasieg) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.zasieg = zasieg;
	}

	public String getImie() {
		return this.imie;
	}

	public String getNazwisko() {
		return this.nazwisko;
	}

	public int getZasieg() {
		return this.zasieg;
	}

	public void setZasieg(int zasieg) {
		if (zasieg >= 0) {
			this.zasieg = zasieg;
		}
	}
}
