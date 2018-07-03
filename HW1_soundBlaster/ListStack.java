// Riley Porter
// 17/1/17
// CSE373
// TA: Raquel Van Hofwegen
// Assignment #1
//
// Class ListStack can be used to store a list of doubles as a last in first out stack.

import java.util.EmptyStackException;

public class ListStack implements DStack {
	private ListStackNode front;

	// post: tells if this stack is empty 
	public boolean isEmpty() {
		if (front == null) {
			return true;
		} else {
			return false;
		}
	}
	
	// post: appends the value to the front of the list
	public void push(double d) {
		if (isEmpty()) {
            front = new ListStackNode(d);
		} else {
            front = new ListStackNode(d, front);
        }
	}

	// pre : stack is not empty (throws EmptyStackException if not)
    // post: returns the value stored at the front and removes value at the front of the list
	//		 , shifting subsequent values left
	public double pop() {
		if (isEmpty()){
			throw new EmptyStackException();
		}
        double num = front.data;
        front = front.next;
		return num;
	}

	// pre : stack is not empty (throws EmptyStackException if not)
    // post: returns the value stored at the front
	public double peek() {
		if (isEmpty()){
			throw new EmptyStackException();
		}
		return front.data;
	}
	
	// post: creates a comma-separated, bracketed version of the list
		public String toString() {
			if (isEmpty()) {
	            return "[]";
	        } else {
	            String result = "]";
	            ListStackNode current = front;
	            while (current.next != null) {
	                result = ", " + current.data + result;
	                current = current.next;
	            }
	            result = "[" + current.data + result;
	            return result;
	        }
		}

	class ListStackNode {
		public double data;       // data stored in this node
	    public ListStackNode next;  // link to next node in the list

	    // post: constructs a node with data 0 and null link
	    public ListStackNode() {
	        this(0, null);
	    }

	    // post: constructs a node with given data and null link
	    public ListStackNode(double data) {
	        this(data, null);
	    }

	    // post: constructs a node with given data and given link
	    public ListStackNode(double data, ListStackNode next) {
	        this.data = data;
	        this.next = next;
	    }
	}
}
