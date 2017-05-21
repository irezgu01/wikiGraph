package fr.umlv.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class AdjGraphWiki  {

	private final HashMap<Integer, LinkedList<Integer>> adj;

	public AdjGraphWiki() {
		adj = new HashMap<>();
	}
	
	public Set<Integer> getSommet() {
		return adj.keySet();
	}
	public LinkedList<Integer> get(int i) {
		return adj.get(i);
	}

	public void addEdge(int i, int j) {

		if (adj.get(i) == null) {
			adj.put(i, new LinkedList<>());
		}
		adj.get(i).add(j);

	}
	
	public int numberOfNeighbors(int key) {
		return adj.get(key).size();
	}
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (Integer key : adj.keySet()) {
			str.append(key).append(" -> ");
			str.append(adj.get(key));
			str.append("\n");
		}
		return str.toString();
	}
}
