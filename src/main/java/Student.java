public class Student extends postac {
    private double szczescie;
    private double inteligencja;
    private double p_d_s;
    private double zad;

    public Student(double szczescie, double inteligencja, double p_d_s, double zad, String imie, String nazwisko, int zasieg) {
        this.szczescie = szczescie;
        this.inteligencja = inteligencja;
        this.p_d_s = p_d_s;
        this.zad = zad;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.zasieg=zasieg;
    }
}
