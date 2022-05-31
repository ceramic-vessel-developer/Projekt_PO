public class Kolokwium extends przedmiot {
    private int lifetime;

    public Kolokwium(int lifetime,int mod_do_zad,int mod_do_przy) {
        this.lifetime = lifetime;
        this.mod_do_przy=mod_do_przy;
        this.mod_do_zad=mod_do_zad;
    }

    void set_cordinates(int x, int y) {

    }

    public int[] get_cordinates(){
        return new int[]{this.x,this.y};
    }

    @Override
    double[] use(double szczescie, double inteligencja, double studenckosc) {

    }
}
