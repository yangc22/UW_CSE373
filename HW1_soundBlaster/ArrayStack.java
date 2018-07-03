// Riley Porter
// 17/1/17
// CSE373
// TA: Raquel Van Hofwegen
// Assignment #1
//
// Class ArrayStack can be used to store a list of doubles as a last in first out stack.

import java.util.EmptyStackException;

public class ArrayStack implements DStack {
	private double[] data;
	private int size;
	
	public static final int DEFAULT_CAPACITY = 10;
	
	// post: constructs an empty array of default capacity
	public ArrayStack() {
		data = new double[DEFAULT_CAPACITY];
		size = 0;
	}
	
	// post: tells if this stack is empty 
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (size == 0) {
			return true;
		} else{
			return false;
		}
	}

	// post: appends the value to the end of the array, resize to use an array twice as large
	//		 whenever the array becomes full and resizes to use an array of half the size when
	//		 the array is 3/4 empty
	public void push(double d) {
		// TODO Auto-generated method stub
		data[size] = d;
		size++;
		if (size == data.length) {
			double[] newData = new double[2 * size];
			for (int i = 0; i < size; i++){
				newData[i] = data[i];
			}
			data = newData;
		} else if(size < data.length / 4) {
			double[] newData = new double[data.length / 2];
			for (int i = 0; i < size; i++){
				newData[i] = data[i];
			}
			data = newData;
		}
	}

	// pre : stack is not empty (throws EmptyStackException if not)
    // post: returns the value stored at the end and removes value at that position
	public double pop() {
		// TODO Auto-generated method stub
		if (isEmpty()){
			throw new EmptyStackException();
		}
		double num = data[size - 1];
		size--;
		return num;
	}

	// pre : stack is not empty (throws EmptyStackException if not)
    // post: returns the value stored at the end
	public double peek() {
		// TODO Auto-generated method stub
		if (isEmpty()){
			throw new EmptyStackException();
		}
		double num = data[size - 1];
		return num;
	}
	
	// post: creates a comma-separated, bracketed version of the array
	public String toString() {
		if (size == 0) {
            return "[]";
        } else {
            String result = "[" + data[0];
            for (int i = 1; i < size; i++) {
                result += ", " + data[i];
            }
            result += "]";
            return result;
        }
	}
}
