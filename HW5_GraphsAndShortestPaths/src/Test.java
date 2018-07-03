import java.util.*;

public class Test {

	public static void main(String[] args) {
		Map<String, Integer> m = new HashMap<>();
		m.put("Yang", 1);
		System.out.println(m);
		m.put("Yang", 2);
		System.out.println(m);
		int x = Integer.MAX_VALUE;
		System.out.println(x);
		Vertex[] vertices = new Vertex[10];
		vertices[1] = new Vertex("Seattle");
		System.out.println(vertices[1]);
		Collection<Vertex> v = new HashSet<>();
		v.add(new Vertex("Seattle"));
		v.add(new Vertex("shanghai"));
		Graph g = new MyGraph(v, new HashSet<Edge>());
		System.out.println(g.vertices().size());
	}

}
