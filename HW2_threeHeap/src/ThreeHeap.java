// Chaoyi Yang
// 01/27/17
// CSE373
// TA: Raquel Van Hofwegen
// Assignment #2
//
// This program will implements a a min-heap data structure using a
// trinary tree structure.

import java.util.*;

public class ThreeHeap implements PriorityQueue {
	private double[] array;
	private int size;
	
	public static final int Initial_Capacity = 10;
	
	// post: constructs an empty heap of default capacity
	//		 with the initial zero size
	public ThreeHeap () {
		initial();
	}
	
	// post: constructs an empty array of default capacity
	//		 to implement the heap with the initial zero size
	private void initial() {
		array = new double[Initial_Capacity];
		size = 0;
	}

	// post: tells if this heap is empty.
	public boolean isEmpty() {
		return size == 0;
	}

	// post: returns the number of elements in this heap.
	public int size() {
		return size;
	}

	// pre: the heap is not empty (throw EmptyHeapException if not).
	// post: returns the minimum element in the heap
	public double findMin() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			throw new EmptyHeapException("The PriorityQueue is empty!");
		}
		return array[1];
	}

	// post: Inserts a new element into the priority queue.
	// 		 Duplicate values are allowed.
	public void insert(double x) {
		if (size == array.length - 1) {
			array = Arrays.copyOf(array, 2 * array.length);
		}
		size++;
		int i = percolateup(size, x);
		array[i] = x;
	}
	
	// post: percolateup the value to the corresponding position
	//		 after inserting to keep the structure property
	private int percolateup(int hole, double val) {
		while (hole > 1 && val < array[(hole + 1) / 3]) {
			array[hole] = array[(hole + 1) / 3];
			hole = (hole + 1) / 3; 
		}
		return hole;
	}
	
	// pre: the heap is not empty (throw EmptyHeapException if not).
	// post: removes and returns the minimum element from the heap.
	public double deleteMin() {
		if (isEmpty()) {
			throw new EmptyHeapException("The PriorityQueue is empty!");
		}
		double ans = array[1];
		int hole = percolateDown(1, array[size]);
		array[hole] = array[size];
		size--;
		return ans;
	}

	// post: percolatedown the value at the root to the corresponding position
	//		 after deletemin to keep the structure property
	private int percolateDown(int hole, double val) {
		// TODO Auto-generated method stub
		while (3 * hole - 1 <= size) {
			int target = hole;
			int left = 3 * hole - 1;
			int mid = left + 1;
			int right = mid + 1;
			if (mid > size ||
					(array[left] < array[mid] && array[left] < array[right])) {
				target = left;
			} else if ((right > size && array[mid] < array[left]) ||
					array[mid] < array[left] && array[mid] < array[right]) {
				target = mid;
			} else {
				target = right;
			}
			if (array[target] < val) {
				array[hole] = array[target];
				hole = target;
			} else {
				break;
			}
		}
		return hole;
	}

	// post: Inserts each element from the given List into the heap.
	//		 Duplicate values ARE allowed.
	public void buildQueue(List<Double> list) {
		makeEmpty();
		// Normal buildQueue, O(NlogN) run time
		for (double val : list) {
			insert(val);
		}
		// Floyd's Method
		/* while (array.length <= list.size()) {
			array = new double[2 * array.length];
		}
		int i = 1;
		for (double val : list) {
			array[i] = val;
			i++;
			size++;
		}
		for (i = (size + 1) / 3; i > 0; i--) {
			double val = array[i];
			int hole = percolateDown(i, val);
			array[hole] = val;
		} */
	}

	// post: Resets the heap to appear as not containing
	//		 any elements.
	public void makeEmpty() {
		// TODO Auto-generated method stub
		initial();
	}
	
	// post: creates a comma-separated, bracketed version of the heap
	public String toString() {
		if (size == 0) {
            return "[]";
        } else {
            String result = "[" + array[1];
            for (int i = 2; i <= size; i++) {
                result += ", " + array[i];
            }
            result += "]";
            return result;
        }
	}
	
	// post: prints the tree contents, one per line, following an
    //       inorder traversal and using indentation to indicate
    //       node depth; prints right to left so that it looks
    //       correct when the output is rotated.
	public void printSideways() {
        System.out.println("Tree Structure: ");
        printSideways(1, 0);
        System.out.println();
    }
	
	// post: prints in reversed pre-order the tree structure with given
    //       root, indenting each line to the given level
	private void printSideways(int index, int level) {
        if (index <= size) {
            printSideways(3 * index - 1, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("	");
            }
            System.out.println(array[index]);
            printSideways(3 * index, level + 1);
            printSideways(3 * index + 1, level + 1);
        }
    }
}
