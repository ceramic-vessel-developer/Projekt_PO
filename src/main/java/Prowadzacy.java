public class Prowadzacy extends Postac {
	private String stopien;
	private int surowosc;
	private int respekt;

	public Prowadzacy(String stopien, int surowosc, int respekt, String imie, String nazwisko, int zasieg) {
		this.stopien = stopien;
		this.surowosc = surowosc;
		this.respekt = respekt;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.zasieg = zasieg;
	}

	@Override
	void set_cordinates(int x, int y) {

	}

	public int[] get_cordinates() {
		return new int[] { this.x, this.y };
	}
}
