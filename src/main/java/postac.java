abstract class obiekt{
    int x;
    int y;
    abstract void set_cordinates(int x,int y);
    abstract int[] get_cordinates();
}

abstract class postac extends obiekt {
    String imie;
    String nazwisko;
    int zasieg;

}
abstract class przedmiot extends obiekt{
    int mod_do_zad;
    int mod_do_przy;


    //abstract double[] use(double szczescie, double inteligencja, double studenckosc);
}

