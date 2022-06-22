abstract class Postac extends Obiekt {
	protected int zasieg;

	protected Postac(int zasieg) {
		this.zasieg = zasieg;
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
