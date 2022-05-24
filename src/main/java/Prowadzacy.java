public class Prowadzacy extends postac{
    private String stopien;
    private int surowosc;
    private int respekt;

    public Prowadzacy(String stopien, int surowosc, int respekt, String imie, String nazwisko, int zasieg) {
        this.stopien = stopien;
        this.surowosc = surowosc;
        this.respekt = respekt;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.zasieg=zasieg;
    }
}
