import java.util.ArrayList;
import java.util.Random;

public class Plansza {
	private static Obiekt[][] plansza;
	private static int dlugosc;
	private static int szerokosc;
	
	public Plansza(int dlugosc, int szerokosc) {
		plansza = new Obiekt[dlugosc][szerokosc];
		
		Plansza.dlugosc = dlugosc;
		Plansza.szerokosc = szerokosc;
	}
	
	public static Obiekt getPole(int x, int y) {
		if (!isValidCoords(x, y)) {
			return null;
		}
		
		return plansza[x][y];
	}
	
	public static <T> void setPole(int x, int y, T obiekt) {
		if (!isValidCoords(x, y)) {
			return;
		}
		
		plansza[x][y] = (Obiekt) obiekt;
	}

	public static int getDlugosc() {
		return dlugosc;
	}
	
	public static int getSzerokosc() {
		return szerokosc;
	}
	
	public static Boolean isValidCoords(int x, int y) {
		if ((x < 0) || (x >= dlugosc) || (y < 0) || (y >= szerokosc)) {
			return false;
		}
		
		return true;
	}
	
	public static <T> void placeObjectsInRandomOrder(ArrayList<T> list) {
		Random generator = new Random();

		int x, y;

		for (T obiekt : list) {
			while (true) {
				x = generator.nextInt(Plansza.getDlugosc());
				y = generator.nextInt(Plansza.getSzerokosc());

				if (Plansza.getPole(x, y) == null) {
					Plansza.setPole(x, y, obiekt);

					((Obiekt) obiekt).setCoordinates(x, y);

					break;
				}
			}
		}
	}
}
