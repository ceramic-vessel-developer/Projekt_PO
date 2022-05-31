
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
}
