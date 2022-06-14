
import java.util.ArrayList;
import java.util.Random;

public class Student extends Postac {
	private static ArrayList<Student> list;

	private double szczescie;
	private double inteligencja;
	private double studenckosc;
	private double przygotowanieDoZajec;
	private double zadowolenie;
	private int ects;

	public Student(double szczescie, double inteligencja, double studenckosc, double przygotowanieDoZajec,
				   double zadowolenie, String imie, String nazwisko, int zasieg, int ects) {
		super(imie, nazwisko, zasieg);

		this.szczescie = szczescie;
		this.inteligencja = inteligencja;
		this.studenckosc = studenckosc;
		this.ects = ects;
		this.setPrzygotowanieDoZajec(przygotowanieDoZajec);
		this.setZadowolenie(zadowolenie);
	}

	public void changeEcts(int modEcts){
		this.ects += modEcts;
	}

	public static ArrayList<Student> getList() {
		return list;
	}

	public static void setList(ArrayList<Student> list) {
		Student.list = list;
	}

	public static void addToList(ArrayList<Student> list) {
		Student.list.addAll(list);
	}

	public double getSzczescie() {
		return szczescie;
	}

	public double getInteligencja() {
		return inteligencja;
	}

	public double getStudenckosc() {
		return studenckosc;
	}

	public double getPrzygotowanieDoZajec() {
		return przygotowanieDoZajec;
	}

	public void setPrzygotowanieDoZajec(double przygotowanieDoZajec) {
		this.przygotowanieDoZajec = przygotowanieDoZajec;
	}

	public void changePrzygotowanieDoZajec(double przygotowanieDoZajec) {
		this.przygotowanieDoZajec += przygotowanieDoZajec;
	}

	public double getZadowolenie() {
		return zadowolenie;
	}

	public void setZadowolenie(double zadowolenie) {
		this.zadowolenie = zadowolenie;
	}

	public void changeZadowolenie(double zadowolenie) {
		this.zadowolenie += zadowolenie;
	}

	public static ArrayList<Student> generate_list(int ilosc) {
		ArrayList<Student> list = new ArrayList<>();
		Random generator = new Random();

		for (int i = 0; i < ilosc; i++) {
			list.add(new Student(generator.nextDouble() * 10, generator.nextDouble() * 10, generator.nextDouble() * 10,
					generator.nextDouble() * 10, generator.nextDouble() * 10, "Jan", "Najemnik",
					generator.nextInt(20) + 10, 30));
		}

		return list;
	}

	public void runaway(int x, int y){
		boolean stop=false;
		if (x>this.x && y>this.y){
			for (int i = 0; i < this.zasieg; i++) {
				if (stop){
					break;
				}
				for (int j = 0; j <this.zasieg ; j++) {
					if (Plansza.getPole(this.x-zasieg+i,this.y-zasieg+j)==null && Plansza.isValidCoords(this.x-zasieg+i,this.y-zasieg+j)){
						this.move(this.x-zasieg+i,this.y-zasieg+j);
						stop=true;
						break;
					}
				}

			}

		}else if (x<this.x && y>this.y){
			for (int i = 0; i < this.zasieg; i++) {
				if (stop){
					break;
				}
				for (int j = 0; j <this.zasieg ; j++) {
					if (Plansza.getPole(this.x+zasieg-i,this.y-zasieg+j)==null && Plansza.isValidCoords(this.x+zasieg-i,this.y-zasieg+j)){
						this.move(this.x+zasieg-i,this.y-zasieg+j);
						stop=true;
						break;
					}
				}

			}
		}if (x>this.x && y<this.y){
			for (int i = 0; i < this.zasieg; i++) {
				if (stop){
					break;
				}
				for (int j = 0; j <this.zasieg ; j++) {
					if (Plansza.getPole(this.x-zasieg+i,this.y+zasieg-j)==null && Plansza.isValidCoords(this.x-zasieg+i,this.y+zasieg-j)){
						this.move(this.x-zasieg+i,this.y+zasieg-j);
						stop=true;
						break;
					}
				}

			}

		}else if (x<this.x && y<this.y){
			for (int i = 0; i < this.zasieg; i++) {
				if (stop){
					break;
				}
				for (int j = 0; j <this.zasieg ; j++) {
					if (Plansza.getPole(this.x+zasieg-i,this.y+zasieg-j)==null && Plansza.isValidCoords(this.x+zasieg-i,this.y+zasieg-j)){
						this.move(this.x+zasieg-i,this.y+zasieg-j);
						stop=true;
						break;
					}
				}
			}

		}
	}

	public void action() {
		Random generator = new Random();


		ArrayList<Obiekt> obiekty = Plansza.searchMapWithinRange(this.x, this.y, this.getZasieg());

		if (obiekty.isEmpty()) {
			// crawl like pathetic being in case of searching
			for (int i = 0; i < 10; i++) {
				int[] xy = {this.x + generator.nextInt(this.getZasieg() * 2) - this.getZasieg(),
						this.y + generator.nextInt(this.getZasieg() * 2) - this.getZasieg()};

				if (Plansza.getPole(xy[0], xy[1]) == null) {
					this.move(xy[0], xy[1]);
					break;
				}
			}
			return;
		}

		int[] odleglosci = Plansza.calculateDistances(this, obiekty);

		int[] nearestObjectInfo = Plansza.findNearestObject(obiekty, odleglosci);

		if(obiekty.get(nearestObjectInfo[0]).getClass().getName().equals("Prowadzacy")){
			// run for your life
			int []kor = obiekty.get(nearestObjectInfo[0]).getCoordinates();
			this.runaway(kor[0],kor[1]);
			return;
		}

		this.focusedItem = (Przedmiot) obiekty.get(nearestObjectInfo[0]);



		int[] koordynaty = this.focusedItem.getCoordinates();

		// teleport to the found object, and use it
		double[] mods=this.focusedItem.use(szczescie, inteligencja, studenckosc);
		this.move(koordynaty[0],koordynaty[1]);
		this.changePrzygotowanieDoZajec(mods[0]);
		this.changeZadowolenie(mods[1]);




	}
}
