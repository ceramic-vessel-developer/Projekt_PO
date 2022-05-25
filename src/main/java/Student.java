import java.util.Random;

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

    public int[] get_cordinates(){
        return new int[]{this.x,this.y};
    }

    public void move(obiekt[][] plansza,int x, int y){
        plansza[x][y]=plansza[this.x][this.y];
        plansza[this.x][this.y]=null;
        this.x=x;
        this.y=y;
    }

    public void action(obiekt[][] plansza){
        Random gen= new Random();
        for(int i=0; i<this.zasieg;i++){
            for (int j = 0; j < this.zasieg; j++) {
                if (plansza[i][j]!=null){
                    if (plansza[i][j].getClass()!=Prowadzacy.class){
                        int []koordynaty=plansza[i][j].get_cordinates();
                        if (plansza[koordynaty[0]-1][koordynaty[1]-1]==null){

                            this.move(plansza,koordynaty[0]-1,koordynaty[1]-1);

                        }else if (plansza[koordynaty[0]-1][koordynaty[1]]==null){

                            this.move(plansza,koordynaty[0]-1,koordynaty[1]);

                        }else if (plansza[koordynaty[0]][koordynaty[1]-1]==null){

                            this.move(plansza,koordynaty[0],koordynaty[1]-1);

                        }

                    }else{
                        //TODO mechanizm uciekania w drugą strone
                    }
                }else{
                    this.move(plansza, gen.nextInt(2*this.zasieg)-this.zasieg, gen.nextInt(2*this.zasieg)-this.zasieg);
                }

            }
        }

    }
}
