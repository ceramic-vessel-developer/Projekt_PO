
abstract class Przedmiot extends Obiekt {
	protected int modDoZadowolenia;
	protected int modDoPrzygotowania;

	public int getModDoZadowolenia() {
		return modDoZadowolenia;
	}

	public void setModDoZadowolenia(int modDoZadowolenia) {
		this.modDoZadowolenia = modDoZadowolenia;
	}
	
	public int getModDoPrzygotowania() {
		return modDoPrzygotowania;
	}

	public void setModDoPrzygotowania(int modDoPrzygotowania) {
		this.modDoPrzygotowania = modDoPrzygotowania;
	}
	
	abstract double[] use(double szczescie, double inteligencja, double studenckosc);
}
