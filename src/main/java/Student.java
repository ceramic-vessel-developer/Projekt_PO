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
    public void set_cordinates(int x,int y){
        this.x=x;
        this.y=y;
    }

    public double getP_d_s() {
        return p_d_s;
    }

    public void setP_d_s(double p_d_s) {
        this.p_d_s = p_d_s;
    }

    public void move(Object[][] plansza,int x, int y){
        plansza[x][y]=plansza[this.x][this.y];
        plansza[this.x][this.y]=0;
        this.x=x;
        this.y=y;
    }
}
