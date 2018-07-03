// Chaoyi Yang
// 17/03/03
// CSE373
// TA: Raquel Van Hofwegen
// Assignment #5

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
     * Returns the shortest path from a to b in the graph, or null if there is
     * no such path.  Assumes all edge weights are nonnegative.
     * Uses Dijkstra's algorithm.
     * @param a the starting vertex
     * @param b the destination vertex
     * @return a Path where the vertices indicate the path from a to b in order
     *   and contains a (first) and b (last) and the cost is the cost of 
     *   the path. Returns null if b is not reachable from a.
     * @throws IllegalArgumentException if a or b does not exist.
     */
    public Path shortestPath(Vertex a, Vertex b) {
    	if (!myGraph.containsKey(a)) {
    		throw new IllegalArgumentException("The vertex " + a + " does not exist!");
    	} else if (!myGraph.containsKey(b)) {
    		throw new IllegalArgumentException("The vertex " + b + " does not exist!");
    	}
    	if (a.equals(b)) {
    		List<Vertex> p = new ArrayList<>();
    		p.add(a);
    		return new Path(p, 0);
    	}
    	Set<Vertex> knownVertices = new HashSet<>();
    	Set<Vertex> unknownVertices = new HashSet<>();
    	Map<Vertex, Vertex> predecessor = new HashMap<>();
    	Map<Vertex, Integer> position = new HashMap<>();
    	Vertex[] heap = new Vertex[this.vertices().size() + 1];
    	List<Vertex> allVertices = new ArrayList<>();
    	buildVertices(allVertices, a);
    	unknownVertices.addAll(allVertices);
    	buildHeap(allVertices, heap, position);
    	int size = this.vertices().size();
    	while (size != 0 || !knownVertices.contains(b)) {
    		Vertex c = deleteMin(heap, position, size);
    		size--;
    		knownVertices.add(c);
    		unknownVertices.remove(c);
    		for (Vertex v : this.adjacentVertices(c)) {
    			if (!knownVertices.contains(v)) {
    				if (c.getDistance() + this.edgeCost(c, v) < v.getDistance()) {
    					decreaseKey(v, c.getDistance() + this.edgeCost(c, v), position, heap, size);
    					predecessor.put(v, c);
    				}
    			}
    		}
    	}
    	return getPath(predecessor, knownVertices, b);
    }
    
    private Path getPath(Map<Vertex, Vertex> predecessor, Set<Vertex> knownVertices, Vertex b) {
    	List<Vertex> finalPath = new ArrayList<>();
		Vertex interPath = b;
		if (predecessor.get(interPath) == null) {
            return null;
	    }
	    finalPath.add(interPath);
	    while (predecessor.get(interPath) != null) {
	            interPath = predecessor.get(interPath);
	            finalPath.add(interPath);
	    }
	    Collections.reverse(finalPath);
	    int finalCost = 0;
	    for (Vertex v : knownVertices) {
	    	if (v.equals(b)) {
	    		finalCost = v.getDistance();
	    	}
	    }
	    return new Path(finalPath, finalCost);
    }
    
    /** 
     * Makes deep copy and adds all the vertices in the graph to the list
     * which will be used in the heap later
     * @param l list to hold all the bertices
     * @param a starting vertex of the shortest path
     */
    private void buildVertices (List<Vertex> l, Vertex a) {
    	for (Vertex v : this.vertices()) {
    		if (!v.equals(a)) {
    			l.add(new Vertex(v.getLabel()));
    		} else {
    			l.add(new Vertex(a.getLabel(), 0));
    		}
    	}
    }
    
    /**
     * builds the heap structure using all the vertices and records the position
     * of each vertex using map
     * @param l list of all vertices
     * @param array used to implement heap
     * @param m map to record the position
     */
    private void buildHeap(List<Vertex> l, Vertex[] array, Map<Vertex, Integer> m) {
    	int i = 2;
    	for (Vertex v : l) {
    		if (v.getDistance() == 0) {
    			array[1] = v;
    			m.put(v, 1);
    		} else {
    			array[i] = v;
    			m.put(v, i);
    			i++;
    		}
    	}
    }
    
    /**
     * removes and returns the element with the minimum distance cost in the heap
     * @param array heap structure
     * @param p map which holds the position of all vertices
     * @param size size of the heap
     * @return the vertex with the minimum distance cost
     */
    private Vertex deleteMin(Vertex[] array, Map<Vertex, Integer> p, int size) {
		Vertex ans = array[1];
		int hole = percolateDown(array, 1, array[size], size, p);
		array[hole] = array[size];
		p.put(array[hole], hole);
		return ans;
	}
    
    /**
     * percolatedown the value at the root to the corresponding position
	 * after deletemin to keep the structure property
     * @param array heap structure
     * @param hole position to be filled
     * @param val	the distance of the last vertex in the heap
     * @param size size of the heap
     * @param p map which holds the position of all vertices
     * @return the position the last element should stay in the heap after deleteMin
     */
    private int percolateDown(Vertex[] array, int hole, Vertex val, int size, Map<Vertex, Integer> p) {
		while (2 * hole <= size) {
			int target = hole;
			int left = 2 * hole;
			int right = left + 1;
			if (right > size || array[left].getDistance() < array[right].getDistance()) {
				target = left;
			} else {
				target = right;
			}
			if (array[target].getDistance() < val.getDistance()) {
				array[hole] = array[target];
				p.put(array[hole], hole);
				hole = target;
			} else {
				break;
			}
		}
		return hole;
	}
    
    /**
     * decrease the distance of the certain vertex and put it into the right position
     * in the heap
     * @param v vertex to be changed
     * @param d	new distance value of the vertex
     * @param p map which holds the position of all vertices
     * @param array heap structure
     * @param size size of the heap
     */
    private void decreaseKey(Vertex v, int d, Map<Vertex, Integer> p, Vertex[] array, int size) {
    	Vertex vertexToChange = array[p.get(v)];
    	vertexToChange.setDistance(d);
    	int i = percolateup(array, p.get(v), vertexToChange, p);
    	array[i] = vertexToChange;
    	p.put(vertexToChange, i);
    }
    
    /**
     * percolateup the value at the last position of the heap to the corresponding position
	 * after insertion to keep the structure property
     * @param array heap structure 
     * @param hole position to be filled
     * @param v vertex to be filed
     * @param p map which holds the position of all vertices
     * @return
     */
    private int percolateup(Vertex[] array, int hole, Vertex v, Map<Vertex, Integer> p) {
		while (hole > 1 && v.getDistance() < array[hole / 2].getDistance()) {
			array[hole] = array[hole / 2];
			p.put(array[hole / 2], hole);
			hole = hole / 2; 
		}
		return hole;
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