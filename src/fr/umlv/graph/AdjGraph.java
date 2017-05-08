package fr.umlv.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Consumer;

public class AdjGraph implements Graph {
	
	private final ArrayList<LinkedList<Edge>> adj;
	private final int n;
	
	public AdjGraph(int x) {
		this.n =x;
		adj = new ArrayList<>();
		for(int i=0;i<n;i++) {
			adj.add(new LinkedList<Edge>());
		}
	}

	@Override
	public int numberOfEdges() {
		int count =0;
		for (LinkedList<Edge> linkedList : adj) {
			count = count + linkedList.size();
		}
		return count;
	}

	@Override
	public int numberOfVertices() {
		return n;
	}

	@Override
	public void addEdge(Vertex start, Vertex end) {
		int i = start.getVertex();
		adj.get(i).add(new Edge(i, end.getVertex()));
	}

	@Override
	public boolean isEdge(Vertex start, Vertex end) {
		int i = start.getVertex();
		int j = end.getVertex();
		LinkedList<Edge> list = adj.get(i);
		for (Edge edge : list) {
			if(edge.getEnd() == j)
				return true;
		}
		return false;
	}

	@Override
	public int getWeight(int i, int j) {
		LinkedList<Edge> list = adj.get(i);
		for (Edge edge : list) {
			if(edge.getEnd() == j)
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
	public void forEachEdge(int i, Consumer<Edge> consumer) {
		LinkedList<Edge> list = adj.get(i);
		for (Edge edge : list) {
			consumer.accept(edge);
		}
	}
	
	
	
	public static void main(String[] args) {
		AdjGraph matrice = new AdjGraph(7);
		
		matrice.addEdge(new Vertex(0), new Vertex(1));
		matrice.addEdge(new Vertex(0), new Vertex(5));
		
		matrice.addEdge(new Vertex(1), new Vertex(2));
		matrice.addEdge(new Vertex(1), new Vertex(4));
		
		matrice.addEdge(new Vertex(2), new Vertex(6));
		
		matrice.addEdge(new Vertex(3), new Vertex(2));
		matrice.addEdge(new Vertex(3), new Vertex(6));
		
		matrice.addEdge(new Vertex(4), new Vertex(3));
		
		matrice.addEdge(new Vertex(5), new Vertex(1));
		matrice.addEdge(new Vertex(5), new Vertex(2));
		
		System.out.println(matrice.toString());
		System.out.println(Graph.dijkstra(matrice, 0));
		
		/*
		matrice.addEdge(2, 3, 1);
		matrice.addEdge(2, 2, 1);
		matrice.addEdge(1, 0, 3);
		*/
		Iterator<Edge> it = matrice.edgeIterator(0);
		while(it.hasNext()) {
			System.out.println(it.next());
			
		}
		/*
		System.out.println(matrice.isEdge(0, 1));
		System.out.println(matrice.isEdge(2, 2));
		System.out.println(matrice.getWeight(1, 0));
		System.out.println(matrice.numberOfEdges());
		*/
	}

}
