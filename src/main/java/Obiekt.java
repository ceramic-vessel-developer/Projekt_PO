
abstract class Obiekt {
	protected int x;
	protected int y;

	public int[] getCoordinates() {
		return new int[] { this.x, this.y };
	}

	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void move(int x, int y) {
		Plansza.setPole(x, y, Plansza.getPole(this.x, this.y));
		Plansza.setPole(this.x, this.y, null);

		this.setCoordinates(x, y);
	}
}
