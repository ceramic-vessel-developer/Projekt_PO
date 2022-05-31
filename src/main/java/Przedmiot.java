
abstract class Przedmiot extends Obiekt {
	protected int modDoZadowolenia;
	protected int modDoPrzygotowania;

	abstract double[] use(double szczescie, double inteligencja, double studenckosc);
}
