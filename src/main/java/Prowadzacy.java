import java.util.ArrayList;
import java.util.Random;

public class Prowadzacy extends Postac {
	private static ArrayList<Prowadzacy> list;

	private Tytul stopien;
	private double surowosc;
	private double szacunek;

	public Prowadzacy(Tytul stopien, double surowosc, double szacunek, String imie, String nazwisko, int zasieg) {
		super(imie, nazwisko, zasieg);

		this.setStopien(stopien);
		this.setSurowosc(surowosc);
		this.setSzacunek(szacunek);
	}

	public static ArrayList<Prowadzacy> getList() {
		return list;
	}

	public static void setList(ArrayList<Prowadzacy> list) {
		Prowadzacy.list = list;
	}

	public static void addToList(ArrayList<Prowadzacy> list) {
		Prowadzacy.list.addAll(list);
	}

	public Tytul getStopien() {
		return stopien;
	}

	public void setStopien(Tytul stopien) {
		this.stopien = stopien;
	}

	public double getSurowosc() {
		return surowosc;
	}

	public void setSurowosc(double surowosc) {
		this.surowosc = surowosc;
	}

	public void changeSurowosc(double surowosc) {
		this.surowosc += surowosc;
	}

	public double getSzacunek() {
		return szacunek;
	}

	public void setSzacunek(double szacunek) {
		this.szacunek = szacunek;
	}

	public static ArrayList<Prowadzacy> generate_list(int ilosc) {
		ArrayList<Prowadzacy> list = new ArrayList<>();
		Random generator = new Random();

		for (int i = 0; i < ilosc; i++) {
			list.add(new Prowadzacy(Tytul.DOKTOR_HAB, generator.nextDouble() * 100, generator.nextDouble() * 100, "Damian",
					"Mrozo", generator.nextInt(15) + 7));
		}

		return list;
	}

	public void action() {
		Random generator = new Random();
		
		ArrayList<Obiekt> obiekty = Plansza.searchMapWithinRange(this.x, this.y, this.getZasieg());

		if (obiekty.isEmpty()) {
			// crawl like pathetic being in case of searching
			while (true) {
				int[] xy = { this.x + generator.nextInt(this.getZasieg() * 2) - this.getZasieg(),
						this.y + generator.nextInt(this.getZasieg() * 2) - this.getZasieg() };

				if (Plansza.isValidCoords(xy[0], xy[1])) {
					if (Plansza.getPole(xy[0], xy[1]) == null) {
						System.out.println("Prowadzacy x: " + this.x + " y: " + this.y
								+ " not found any object; new x: " + xy[0] + " y: " + xy[1]);

						this.move(xy[0], xy[1]);

						break;
					}
				}
			}

			return;
		}

		int[] odleglosci = Plansza.calculateDistances(this, obiekty);

		int[] nearestObjectInfo = Plansza.findNearestObject(obiekty, odleglosci);

		Obiekt obiekt = obiekty.get(nearestObjectInfo[0]);

		if (obiekt instanceof Student) {
			Student student = (Student) obiekt;
			int[] coords = student.getCoordinates();
			boolean moved = false;

			if ((Plansza.isValidCoords(coords[0]-1,coords[1]-1) && Plansza.getPole(coords[0]-1,coords[1]-1)==null)){

				System.out.println("Prowadzacy x: " + this.x + " y: " + this.y + " found student; new x: "
						+ (coords[0]-1) + " y: " + (coords[1]-1));
				this.move(coords[0]-1,coords[1]-1);
				moved = true;

			}else if ((Plansza.isValidCoords(coords[0]+1,coords[1]-1) && Plansza.getPole(coords[0]+1,coords[1]-1)==null)){

				System.out.println("Prowadzacy x: " + this.x + " y: " + this.y + " found student; new x: "
						+ (coords[0]+1) + " y: " + (coords[1]-1));
				this.move(coords[0]+1,coords[1]-1);
				moved = true;


			}else if ((Plansza.isValidCoords(coords[0]-1,coords[1]+1) && Plansza.getPole(coords[0]-1,coords[1]+1)==null)){

				System.out.println("Prowadzacy x: " + this.x + " y: " + this.y + " found student; new x: "
						+ (coords[0]-1) + " y: " + (coords[1]+1));
				this.move(coords[0]-1,coords[1]+1);
				moved = true;


			}else if ((Plansza.isValidCoords(coords[0]+1,coords[1]+1)&& Plansza.getPole(coords[0]+1,coords[1]+1)==null)){

				System.out.println("Prowadzacy x: " + this.x + " y: " + this.y + " found student; new x: "
						+ (coords[0]+1) + " y: " + (coords[1]+1));
				this.move(coords[0]+1,coords[1]+1);
				moved = true;

			}

			if(moved) {

				int ects = 0;
				double zadowolenie;

				if (student.getPrzygotowanieDoZajec() < this.surowosc) {
					if (this.stopien == Tytul.INZYNIER || this.stopien == Tytul.MAGISTER_INZ) {
						ects = -1;
					} else if (this.stopien == Tytul.DOKTOR || this.stopien == Tytul.DOKTOR_HAB) {
						ects = -2;
					} else if (this.stopien == Tytul.PROFESOR) {
						ects = -3;
					}

					zadowolenie = -(this.szacunek - student.getSzczescie()) * 0.5;
				} else {
					zadowolenie = this.szacunek * 0.3;
				}

				student.changeEcts(ects);
				student.changeZadowolenie(zadowolenie);

				System.out.println("Prowadzacy x: " + this.x + " y: " + this.y + " found student " + " x: " + coords[0]
						+ " y: " + coords[1]);

				System.out.println("Prowadzacy x: " + this.x + " y: " + this.y + "; change ects: " + ects + " zadowolenie: "
						+ zadowolenie);

				System.out.println("Prowadzacy x: " + this.x + " y: " + this.y + "; student ects: " + student.getEcts() + " zadowolenie: "
						+ student.getZadowolenie());

				return;
			}
		}else{

			// crawl like pathetic being in case of searching
			while (true) {
				int[] xy = { this.x + generator.nextInt(this.getZasieg() * 2) - this.getZasieg(),
						this.y + generator.nextInt(this.getZasieg() * 2) - this.getZasieg() };

				if (Plansza.isValidCoords(xy[0], xy[1])) {
					if (Plansza.getPole(xy[0], xy[1]) == null) {
						System.out.println("Prowadzacy x: " + this.x + " y: " + this.y
								+ " not found any student; new x: " + xy[0] + " y: " + xy[1]);

						this.move(xy[0], xy[1]);

						break;
					}
				}
			}


		}
	}
}
