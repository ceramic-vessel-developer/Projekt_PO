import java.util.ArrayList;
import java.util.Random;

public class Prowadzacy extends Postac {
	private static ArrayList<Prowadzacy> list;

	private Tytul stopien;
	private int surowosc;
	private int szacunek;

	public Prowadzacy(Tytul stopien, int surowosc, int szacunek, String imie, String nazwisko, int zasieg) {
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

	public Tytul getStopien() {
		return stopien;
	}

	public void setStopien(Tytul stopien) {
		this.stopien = stopien;
	}

	public int getSurowosc() {
		return surowosc;
	}

	public void setSurowosc(int surowosc) {
		this.surowosc = surowosc;
	}

	public void changeSurowosc(int surowosc) {
		this.surowosc += surowosc;
	}

	public int getSzacunek() {
		return szacunek;
	}

	public void setSzacunek(int szacunek) {
		this.szacunek = szacunek;
	}

	public static ArrayList<Prowadzacy> generate_list(int ilosc) {
		ArrayList<Prowadzacy> list = new ArrayList<>();
		Random generator = new Random();

		for (int i = 0; i < ilosc; i++) {
			list.add(new Prowadzacy(Tytul.DOKTOR_HAB, generator.nextInt() * 10, generator.nextInt() * 10, "Damian",
					"Mrozo", generator.nextInt(15) + 7));
		}

		return list;
	}
}
