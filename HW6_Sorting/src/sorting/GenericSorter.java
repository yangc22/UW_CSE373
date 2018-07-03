package sorting;

import java.util.Comparator;

	/**
	 * Class full of static sorting methods. Used to sort packets received from a
	 * server containing image metadata.
	 * 
	 * TODO: Implement mergeSort() and selectionSort().
	 * 
	 * insertionSort is implemented for you as an example.
	 * 
	 * @author Chaoyi Yang
	 *
	 */

	public class GenericSorter {
	    /**
	     * Sorts the given array of packets in ascending order according to the
	     * comparator using mergesort. You may create as many private helper
	     * functions as you wish to implement this method.
	     * 
	     * A note about ascending order:
	     * 
	     * When the method is finished, it should be true that:
	     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
	     * array.length.
	     * 
	     * @param array
	     *            the packets to sort
	     * @param comparator
	     *            The comparator the will be used to compare two packets.
	     */
	    public static <Type> void mergeSort(Type[] array, Comparator<Type> comparator) {
	    	@SuppressWarnings("unchecked")
	    	Type[] newArray = (Type[]) new Object[array.length];
	        if (array.length != 0) {
	        	mergeSort(array, newArray, 0, array.length - 1, comparator);
	        }
	    }
	    
	    /**
	     * Sorts the certain part of the given array in ascending order accoring to the 
	     * comparator using mergersort.
	     * 
	     * @param array
	     * 				the objects to sort
	     * @param newArray
	     * 				the auxiliary array used to store the sorted objects
	     * @param startIndex
	     * 				start index for the array to be sorted
	     * @param endIndex
	     * 				end index for the array to be sorted
	     * @param comparator
	     * 				comparator used to compare the order of the given object
	     */
	    private static <Type> void mergeSort(Type[] array, Type[] newArray, int startIndex,
	    		int endIndex, Comparator<Type> comparator) {
	    	if (startIndex != endIndex) {
	    		int midIndex = (startIndex + endIndex) / 2;
	    		mergeSort(array, newArray, startIndex, midIndex, comparator);
	    		mergeSort(array, newArray, midIndex + 1, endIndex, comparator);
	    		merge(array, newArray, startIndex, midIndex, endIndex, comparator);
	    	}
	    }
	    
	    /**
	     * Uses three pointers and an array to to sort the given part of the array
	     * 
	     * @param array
	     * 				the objects to sort
	     * @param newArray
	     * 				the auxiliary array used to store the sorted objects
	     * @param startIndex
	     * 				start index for the array to be sorted
	     * @param midIndex
	     * 				end index for the first array to be sorted when conquering 
	     * 				mid + 1 is  the start index for the array to be sorted
	     * @param endIndex
	     * 				end index for the second array to be sorted when conquering
	     * @param comparator
	     * 				comparator used to compare the order of the given object
	     */
	    private static <Type> void merge(Type[] array, Type[] newArray,
	    		int startIndex, int midIndex, int endIndex,  Comparator<Type> comparator) {
	    		int i = startIndex;
	    		int j = midIndex + 1;
	    		for (int k = startIndex; k<= endIndex; k++) {
	    			if (j > endIndex) {
	    				newArray[k] = array[i];
	    				i++;
	    			} else if (i > midIndex) {
	    				newArray[k] = array[j];
	    				j++;
	    			} else if (comparator.compare(array[i], array[j]) <= 0) {
	    				newArray[k] = array[i];
	    				i++;
	    			} else {
	    				newArray[k] = array[j];
	    				j++;
	    			}
	    		}
	    		for (int l = startIndex; l <= endIndex; l++) {
	    			array[l] = newArray[l];
	    		}
	    }
	    
	    /**
	     * Sort the array of packets in ascending order using selection sort.
	     * 
	     * A note about ascending order:
	     * 
	     * When the method is finished, it should be true that:
	     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
	     * array.length.
	     * 
	     * @param array
	     *            the array of packets that will be sorted.
	     * @param comparator
	     *            The comparator the will be used to compare two packets.
	     */
	    public static <Type> void selectionSort(Type[] array,
	            Comparator<Type> comparator) {
	    	for (int i = 0; i < array.length; i++) {
	        	int newIndex = findMin(array, comparator, i);
	        	swap(array, newIndex, i);
	        }
	    }
	    
	    /**
	     * finds the index of the minimum object from the unsorted part
	     * @param array
	     * 				the array of object that will be sorted
	     * @param comparator
	     * 				The comparator the will be used to compare two objects.
	     * @param i
	     * 			the start index for the unsorted array part
	     * @return
	     * 			the index of the minimum packet in the unsorted part
	     */
	    private static <Type> int findMin(Type[] array, Comparator<Type> comparator, int i) {
	    	Type min = array[i];
	    	int position = i;
	    	for (int j = i; j < array.length; j++) {
	    		if (comparator.compare(array[j], min) < 0) {
	    			min = array[j];
	    			position = j;
	    		}
	    	}
	    	return position;
	    }
	    
	    /**
	     * swap the object at the current position with the minimum object in the unsorted part
	     * @param array
	     * 				the array of objects that will be sorted
	     * @param unsortedPosition
	     * 				current position to be swapped
	     * @param sortedPosition
	     * 				position of the minimum in the unsorted part
	     */
	    private static <Type> void swap(Type[] array, int unsortedPosition, int sortedPosition) {
	    	Type temp = array[unsortedPosition];
	    	array[unsortedPosition] = array[sortedPosition];
	    	array[sortedPosition] = temp;
	    }

	    /**
	     * Sort the array of packets in ascending order using insertion sort.
	     * 
	     * A note about ascending order:
	     * 
	     * When the method is finished, it should be true that:
	     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
	     * array.length.
	     * 
	     * @param array
	     *            the array of packets that will be sorted.
	     * @param comparator
	     *            The comparator the will be used to compare two packets.
	     */
	    public static <Type> void insertionSort(Type[] array,
	            Comparator<Type> comparator) {
	        for (int outerIndex = 1; outerIndex < array.length; outerIndex++) {
	        	Type currentPacket = array[outerIndex];
	            int innerIndex = outerIndex - 1;
	            while (innerIndex >= 0
	                    && comparator.compare(currentPacket, array[innerIndex]) < 0) {
	                array[innerIndex + 1] = array[innerIndex];
	                innerIndex--;
	            }
	            array[innerIndex + 1] = currentPacket;
	        }
	    }
	}
