import java.util.ArrayList;

public class Planszowka extends przedmiot{
    static ArrayList<Planszowka> list;
    public Planszowka(int mod_do_zad,int mod_do_przy) {
        this.mod_do_przy=mod_do_przy;
        this.mod_do_zad=mod_do_zad;
    }

    @Override
    void set_cordinates(int x, int y) {

    }

    public int[] get_cordinates(){
        return new int[]{this.x,this.y};
    }

    @Override
    double[] use(double szczescie, double inteligencja, double studenckosc) {
        list.remove(this);
        return new double[]{0.1*inteligencja*this.mod_do_przy,-0.2*studenckosc*this.mod_do_zad};

    }
}
