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

		int leftBorder = x - zasieg;
		// rightBorder = x + zasieg
		int widthLimit = zasieg * 2 + 1;

		int topBorder = y - zasieg;
		// bottomBorder = y + zasieg;
		int heightLimit = zasieg * 2 + 1;

		if (leftBorder < 0) {
			leftBorder = 0;
		}

		if (leftBorder + widthLimit > Plansza.getDlugosc()) {
			widthLimit = Plansza.getDlugosc() - x;
		}

		if (topBorder < 0) {
			topBorder = 0;
		}

		if (topBorder + heightLimit > Plansza.getSzerokosc()) {
			heightLimit = Plansza.getSzerokosc() - y;
		}

		for (int i = leftBorder; i < widthLimit; i++) {
			for (int j = topBorder; j < heightLimit; j++) {
				if (i == x && j == y) {
					// ignore self
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
			// math magic formula
			int distance = (int) Math.sqrt(Math.pow(fromObiekt.x - obiekt.x, 2) + Math.pow(fromObiekt.y - obiekt.y, 2));

			distances[i] = distance;
			i++;
		}

		return distances;
	}

	/**
	 * @param obiekty
	 * @param distances
	 * @return [index, distance]
	 */
	public static int[] findNearestObject(ArrayList<Obiekt> obiekty, int[] distances) {
		int[] objectInfo = new int[2];

		int minIndex = 0;
		int min = distances[0];

		for (int i = 1; i < obiekty.size(); i++) {
			if (distances[i] < min) {
				minIndex = i;
				min = distances[i];
			}
		}

		objectInfo[0] = minIndex;
		objectInfo[1] = min;

		return objectInfo;
	}

	public static void visualize() {
		for (int i = 0; i < getDlugosc() + 2; i++) {
			System.out.print('-');
		}

		System.out.print('\n');

		for (int i = 0; i < getSzerokosc(); i++) {
			System.out.print('|');

			for (int j = 0; j < getDlugosc(); j++) {
				if (getPole(j, i) != null) {
					Obiekt obiekt = getPole(j, i);

					if (obiekt instanceof Student) {
						System.out.print('@');
					} else if (obiekt instanceof Prowadzacy) {
						System.out.print('§');
					} else if (obiekt instanceof Kolokwium) {
						System.out.print('&');
					} else if (obiekt instanceof Planszowka) {
						System.out.print('#');
					} else if (obiekt instanceof Piwo) {
						System.out.print('U');
					} else {
						System.out.print('O');
					}

				} else {
					System.out.print('.');
				}
			}

			System.out.print("|\n");
		}

		for (int i = 0; i < getDlugosc() + 2; i++) {
			System.out.print('-');
		}

		System.out.print('\n');
	}
}
