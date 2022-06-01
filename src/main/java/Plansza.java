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

	public static ArrayList<Obiekt> searchMapWithinRange(int x, int y, int zasieg) {
		ArrayList<Obiekt> obiekty = new ArrayList<Obiekt>();

		for (int i = x; i < zasieg; i++) {
			if (i >= dlugosc) {
				continue;
			}

			for (int j = y; j < zasieg; j++) {
				if (j >= szerokosc) {
					continue;
				}

				if (Plansza.getPole(i, j) != null) {
					obiekty.add(Plansza.getPole(i, j));
				}
			}
		}

		return obiekty;
	}

	public static int[] calculateDistances(Obiekt fromObiekt, ArrayList<Obiekt> obiekty) {
		int[] distances = new int[obiekty.size()];

		int i = 0;
		
		for (Obiekt obiekt : obiekty) {
			int distance = (int) Math.sqrt(Math.pow(fromObiekt.x - obiekt.x, 2) + Math.pow(fromObiekt.y - obiekt.y, 2));
			
			distances[i] = distance;
			i++;
		}

		return distances;
	}
	
	public static int[] findNearestObject(ArrayList<Obiekt> obiekty, int[] distances) {
		int[] objectInfo = new int[2];
		
		int minIndex = 0;
		int min = distances[0];
		
		for (int i = 1; i < obiekty.size(); i++) {
			if (obiekty.get(i).getClass() != Postac.class || obiekty.get(i).getClass() != Prowadzacy.class) {
				if (min > distances[i]) {
					minIndex = i;
					min = distances[i];
				}
			}
		}

		objectInfo[0] = minIndex;
		objectInfo[1] = min;
		
		return objectInfo;
	}
}
