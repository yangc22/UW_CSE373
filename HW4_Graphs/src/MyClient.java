// Chaoyi Yang
// 17/2/23
// CSE373
// TA: Raquel Van Hofwegen
// Assignment #4
//
// Client code testing the MyGraph class. Where the graph represents the flight route I can
// choose to fly to home.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

public class MyClient {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner inputVertices = new Scanner(new File("my_vertices.txt"));
		Collection<Vertex> v = new HashSet<>();
		while (inputVertices.hasNextLine()) {
			String line = inputVertices.nextLine();
			Scanner data = new Scanner(line);
			while (data.hasNext()) {
				String ver = data.next();
				v.add(new Vertex(ver));
			}
			data.close();
		}
		inputVertices.close();
		Scanner inputEdges = new Scanner(new File("my_edges.txt"));
		Collection<Edge> e = new HashSet<>();
		while (inputEdges.hasNextLine()) {
			String line = inputEdges.nextLine();
			Scanner data = new Scanner(line);
			String source = data.next();
			String destination = data.next();
			int weight = data.nextInt();
			e.add(new Edge(new Vertex(source), new Vertex(destination), weight));
			data.close();
		}
		inputEdges.close();
		
		MyGraph myGraph = new MyGraph(v, e);
		System.out.println(myGraph);
		System.out.println(myGraph.edges());
		System.out.println(myGraph.vertices());
		Vertex seattle = new Vertex("Seattle");
		Vertex beijing = new Vertex("Beijing");
		System.out.println(myGraph.adjacentVertices(seattle));
		System.out.println(myGraph.adjacentVertices(beijing));
		System.out.println(myGraph.edgeCost(seattle, beijing));
	}

}