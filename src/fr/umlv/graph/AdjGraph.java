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
		for(int i=0;i<x;i++) {
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
	public void addEdge(int i,int j) {
		super.addEdge(i, j);
		adj.get(i).add(new Edge(i, j));
		
		
	}

	@Override
	public boolean isEdge(int i,int j) {
		
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
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0,n = this.numberOfVertices(); i < n; i++) {
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
	
	
	
	public static void main(String[] args) {
		AdjGraph matrice = new AdjGraph(7);
		
		matrice.addEdge(0,1);
		matrice.addEdge(0,5);
		
		matrice.addEdge(1,2);
		matrice.addEdge(1,4);
		
		matrice.addEdge(2,6);
		
		matrice.addEdge(3,2);
		matrice.addEdge(3,6);
		
		matrice.addEdge(4,3);
		
		matrice.addEdge(5,1);
		matrice.addEdge(5,2);
		
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
