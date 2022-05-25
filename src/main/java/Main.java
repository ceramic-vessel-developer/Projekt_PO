import java.util.ArrayList;
import java.util.Random;

public class Main {
    static  ArrayList<Student> make_student_list(int ilosc){
        ArrayList<Student> list=new ArrayList<>();
        Random generator=new Random();
        for (int i = 0; i < ilosc; i++) {
            list.add(new Student(generator.nextDouble()*10, generator.nextDouble()*10, generator.nextDouble()*10, generator.nextDouble()*10,"Michał","Korczak",generator.nextInt()));
        }
        return list;
    }


    public static void main(String[] args) {
        Object [][] plansza=new Object[100][100];
        Piwo kustosz=new Piwo(2,2,2);
        System.out.println(kustosz.getClass());
        ArrayList<Student> list=make_student_list(10);
        for (Student student:list) {
            System.out.println(student);
        }
        plansza[33][22]=list.get(0);
        list.get(0).set_cordinates(33,22);
        list.get(0).move(plansza,44,33);
        System.out.println(plansza[33][22]);
        System.out.println(plansza[44][33]);

    }

}
//Obiekty są w listach przez referencje