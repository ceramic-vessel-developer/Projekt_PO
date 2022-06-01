
abstract class NietrwalyPrzedmiot extends Przedmiot {
	protected int lifetime;

	public int getLifetime() {
		return lifetime;
	}

	public void setLifetime(int lifetime) {
		this.lifetime = lifetime;
	}
	
	public void cycleLifetime() {
		this.lifetime--;
	}
}
