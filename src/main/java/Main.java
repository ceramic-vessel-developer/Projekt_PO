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

	// TODO tu trzeba będzie zrobić method overload :((((
	static void place_objects(ArrayList<Student> list, Object[][] plansza) {
		Random gen = new Random();

		int x, y;

		for (Student student : list) {
			while (true) {
				x = gen.nextInt(100);
				y = gen.nextInt(100);

				if (plansza[x][y] == null) {
					plansza[x][y] = student;

					student.set_cordinates(x, y);

					break;
				}
			}
		}

	}

	public static void main(String[] args) {
		Obiekt[][] plansza = new Obiekt[100][100];
		ArrayList<Student> list = make_student_list(10);
		Piwo kustosz = new Piwo(2, 2, 2);
		
		place_objects(list, plansza);
		
		for (Student student : list) {
			System.out.println(Arrays.toString(student.get_cordinates()));
		}
		
		System.out.println(list.size());
		System.out.println(list.get(9).test(list));
		System.out.println(list.size());
	}

}
//Obiekty są w listach przez referencje