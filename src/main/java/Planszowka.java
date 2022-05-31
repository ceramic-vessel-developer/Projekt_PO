public class Planszowka extends przedmiot{
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

    /*@Override
    double[] use(double szczescie, double inteligencja, double studenckosc) {

    }*/
}
