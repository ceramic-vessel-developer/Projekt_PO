import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Tytul {
	INZYNIER, MAGISTER_INZ, DOKTOR, DOKTOR_HAB, PROFESOR;

	private static final List<Tytul> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static Tytul randomTytul() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
