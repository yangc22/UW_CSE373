// Chaoyi Yang
// 01/27/17
// CSE373
// TA: Raquel Van Hofwegen
// Assignment #2
//
// This is a client program to test the ThreeHeap object.

import java.util.*;

public class MyClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PriorityQueue q = new ThreeHeap();
		List<Double> l = new ArrayList<Double>(10);
		l.add(12.0);
		l.add(5.0);
		l.add(11.0);
		l.add(3.0);
		l.add(10.0);
		l.add(2.0);
		l.add(9.0);
		l.add(4.0);
		l.add(8.0);
		l.add(1.0);
		l.add(7.0);
		l.add(6.0);
		System.out.println(l);
		q.buildQueue(l);
		System.out.println(q);
		System.out.println("Size of the Queue: " + q.size());
		System.out.println("Min: " + q.findMin());
		q.printSideways();
		q.insert(16);
		q.insert(32);
		System.out.println(q);
		System.out.println("Size of the Queue: " + q.size());
		System.out.println("Min: " + q.findMin());
		q.printSideways();
		q.deleteMin();
		System.out.println(q);
		System.out.println("Size of the Queue: " + q.size());
		System.out.println("Min: " + q.findMin());
		System.out.println("The queue is empty? " + q.isEmpty());
		q.printSideways();
		q.makeEmpty();
		System.out.println(q);
		System.out.println("The queue is empty? " + q.isEmpty());
	}

}
