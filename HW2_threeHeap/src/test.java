import java.util.*;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] array = new double[10];
		for (int i = 0; i < 10; i++) {
			array[i] = 1.0 * i / 10;
		}
		System.out.println(Arrays.toString(array));
		array = Arrays.copyOf(array, 2 * array.length);
		System.out.println(Arrays.toString(array));
	}

}
