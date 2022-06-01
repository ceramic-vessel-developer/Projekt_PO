import java.util.ArrayList;

public class Prowadzacy extends Postac {
	private static ArrayList<Prowadzacy> list;
	
	private String stopien;
	private int surowosc;
	private int szacunek;

	public Prowadzacy(String stopien, int surowosc, int szacunek, String imie, String nazwisko, int zasieg) {
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
	
	public String getStopien() {
		return stopien;
	}

	public void setStopien(String stopien) {
		this.stopien = stopien;
	}

	public int getSurowosc() {
		return surowosc;
	}

	public void setSurowosc(int surowosc) {
		this.surowosc = surowosc;
	}

	public int getSzacunek() {
		return szacunek;
	}

	public void setSzacunek(int szacunek) {
		this.szacunek = szacunek;
	}

}
