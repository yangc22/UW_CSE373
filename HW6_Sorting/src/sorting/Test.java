package sorting;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] a = {4,6,7,349,4,223,4,68756,8,3,245,7,8,64,9};
		// Integer[] majorIndexes = {4,6,7,9,4,2,4,6,8,3,2,7,8,6,9};
		// Integer[] minorIndexes = {4,6,7,9,4,2,5,4,6,34,2,7,8,2,9};
		IntegerSorter.mergeSort(a, new IntegerComparator());
		System.out.println(Arrays.toString(a));
        /* Packet[] testArray = new Packet[majorIndexes.length];
        for (int i = 0; i < majorIndexes.length; i++) {
            testArray[i] = new Packet(majorIndexes[i], minorIndexes[i]);
        }
		GenericSorter.mergeSort(testArray, new PacketComparator()); */
	}

}
