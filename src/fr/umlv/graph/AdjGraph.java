package fr.umlv.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Consumer;

public class AdjGraph extends AbstractGraph implements Graph {

	private final ArrayList<LinkedList<Edge>> adj;

	public AdjGraph(int x) {
		super(x);
		adj = new ArrayList<>(x);
		for (int i = 0; i < x; i++) {
			adj.add(new LinkedList<Edge>());
		}
	}

	@Override
	public int numberOfEdges() {
		int count = 0;
		for (LinkedList<Edge> linkedList : adj) {
			count = count + linkedList.size();
		}
		return count;
	}

	@Override
	public void addEdge(int i, int j) {
		// super.addEdge(i, j);
		adj.get(i).add(new Edge(i, j));

	}

	@Override
	public boolean isEdge(int i, int j) {

		LinkedList<Edge> list = adj.get(i);
		for (Edge edge : list) {
			if (edge.getEnd() == j)
				return true;
		}
		return false;
	}

	@Override
	public int getWeight(int i, int j) {
		LinkedList<Edge> list = adj.get(i);
		for (Edge edge : list) {
			if (edge.getEnd() == j)
				return edge.getValue();
		}
		return 0;
	}

	@Override
	public Iterator<Edge> edgeIterator(int i) {
		return new Iterator<Edge>() {
			Iterator<Edge> iterateur = adj.get(i).iterator();

			@Override
			public boolean hasNext() {
				return iterateur.hasNext();
			}

			@Override
			public Edge next() {
				return iterateur.next();
			}
		};
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0, n = this.numberOfVertices(); i < n; i++) {
			str.append(adj.get(i));
			str.append("\n");
		}
		return str.toString();
	}

	@Override
	public void forEachEdge(int i, Consumer<Edge> consumer) {
		LinkedList<Edge> list = adj.get(i);
		for (Edge edge : list) {
			consumer.accept(edge);
		}
	}

}
