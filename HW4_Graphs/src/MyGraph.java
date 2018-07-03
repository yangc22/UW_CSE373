// Chaoyi Yang
// 17/2/23
// CSE373
// TA: Raquel Van Hofwegen
// Assignment #4

import java.util.*;

/**
 * A representation of a graph.
 * Assumes that we do not have negative cost edges in the graph.
 */
public class MyGraph implements Graph {
	private Map<Vertex, Set<Edge>> myGraph;

    /**
     * Creates a MyGraph object with the given collection of vertices
     * and the given collection of edges.
     * @param v a collection of the vertices in this graph
     * @param e a collection of the edges in this graph
     */
    public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
    	myGraph = new HashMap<>();
    	for (Vertex vertex : v) {
    		if (!myGraph.containsKey(vertex)) {
    			myGraph.put(copyOfVertex(vertex), new HashSet<Edge>());
    		}
    	}
    	for (Edge edge : e) {
    		if (edge.getWeight() < 0) {
    			throw new IllegalArgumentException("Edge: " + edge + " has negative weight!");
    		}
    		Edge sameEdge = findEdge(edge.getSource(), edge.getDestination());
    		if (sameEdge != null) {
    			if (edge.getWeight() != sameEdge.getWeight()) {
    				throw new IllegalArgumentException("Edge: " + edge + " " + sameEdge + " are from the same source"
							+ " and destination but have different weights!");
    			}
    		}
    		myGraph.get(edge.getSource()).add(new Edge(edge.getSource(), edge.getDestination(), edge.getWeight()));
    	}
    }

    /** 
     * Return the collection of vertices of this graph
     * @return the vertices as a collection (which is anything iterable)
     */
    public Collection<Vertex> vertices() {
    	Collection<Vertex> allVertices = new HashSet<>();
    	for (Vertex vertex : myGraph.keySet()) {
    		allVertices.add(copyOfVertex(vertex));
    	}
    	return allVertices;
    }

    /** 
     * Return the collection of edges of this graph
     * @return the edges as a collection (which is anything iterable)
     */
    public Collection<Edge> edges() {
    	Collection<Edge> allEdges = new HashSet<>();
    	for (Set<Edge> edges : myGraph.values()) {
    		for (Edge edge : edges) {
    			allEdges.add(copyOfEdge(edge));
    		}
    	}
    	return allEdges;
    }

    /**
     * Return a collection of vertices adjacent to a given vertex v.
     *   i.e., the set of all vertices w where edges v -> w exist in the graph.
     * Return an empty collection if there are no adjacent vertices.
     * @param v one of the vertices in the graph
     * @return an iterable collection of vertices adjacent to v in the graph
     * @throws IllegalArgumentException if v does not exist.
     */
    public Collection<Vertex> adjacentVertices(Vertex v) {
    	if (!myGraph.containsKey(v)) {
    		throw new IllegalArgumentException("The vertex " + v + " does not exist in our graph!");
    	}
    	Collection<Vertex> vertexSet = new HashSet<>();
    	for (Edge edgeOfV : myGraph.get(v)) {
    		vertexSet.add(copyOfVertex(edgeOfV.getDestination()));
    	}
    	return vertexSet;
    }

    /**
     * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed graph.
     * Assumes that we do not have negative cost edges in the graph.
     * @param a one vertex
     * @param b another vertex
     * @return cost of edge if there is a directed edge from a to b in the graph, 
     * return -1 otherwise.
     * @throws IllegalArgumentException if a or b do not exist.
     */
    public int edgeCost(Vertex a, Vertex b) throws IllegalArgumentException {
	    Edge edge = findEdge(a, b);
	    if (edge != null) {
	    	return edge.getWeight();
	    }
	    return -1;
    }
    
    /**
     *  find the certain edge with the given source and destination
     * @param a source vertex
     * @param b destination vertex
     * @return edge with the given source and destination
     */
    private Edge findEdge(Vertex a, Vertex b) {
    	if (!myGraph.containsKey(a) || !myGraph.containsKey(b)) {
			throw new IllegalArgumentException("The vertices " + "\"" + a + "\"" + " or " + "\"" + b + "\" do not exit in the graph!");
	    }
	    for (Edge edge : myGraph.get(a)) {
	    	if (edge.getDestination().equals(b)) {
	    		return edge;
	    	}
	    }
	    return null;
    }
    
    // Make a deep copy for the given vertex which copies the given label of the vertex
    private Vertex copyOfVertex (Vertex v) {
    	Vertex newV = new Vertex(v.getLabel());
    	return newV;
    }
    
    // Make a deep copy for the given edge which also deeply copies the source 
    // and destination vertice
    private Edge copyOfEdge (Edge e) {
    	Edge newE = new Edge(e.getSource(), e.getDestination(), e.getWeight());
    	return newE;
    }
    
    /**
	 * A string representation of this object
	 * @return the label attached to this graph
	 */
    public String toString () {
    	return myGraph.toString();
    }
}