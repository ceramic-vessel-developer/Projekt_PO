import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {
	static ArrayList<Student> make_student_list(int ilosc) {
		ArrayList<Student> list = new ArrayList<>();
		Random generator = new Random();

		for (int i = 0; i < ilosc; i++) {
			list.add(new Student(generator.nextDouble() * 10, generator.nextDouble() * 10, generator.nextDouble() * 10,
					generator.nextDouble() * 10, generator.nextDouble() * 10, "Michał", "Korczak",
					generator.nextInt()));
		}

		return list;
	}

	public static void main(String[] args) {
		Plansza plansza = new Plansza(100, 100);
	
		ArrayList<Student> list = make_student_list(10);
		
		Plansza.placeObjectsInRandomOrder(list);
		
		Piwo kustosz = new Piwo(2, 2, 2);
		
		for (Student student : list) {
			System.out.println(Arrays.toString(student.getCoordinates()));
		}

		System.out.println(list.size());
		System.out.println(list.get(9).test(list));
		System.out.println(list.size());
	}

}
//Obiekty są w listach przez referencje