package fr.umlv.graph;

import java.util.ArrayList;
import java.util.LinkedList;

 abstract class AbstractGraph {
	
	private final ArrayList<Vertex> verticles;
	private final ArrayList<LinkedList<Vertex>> predecessors;
	protected final int n;

	
	public AbstractGraph(int n) {
		this.n = n;
		verticles = new ArrayList<>(n);
		predecessors = new ArrayList<>();
		
		for(int i = 0;i <n;i++){
			predecessors.add(new LinkedList<Vertex>());
			verticles.add(new Vertex(i));
		}
	}
	
	public int numberOfVertices() {
		return n;
	}

	
	public void addEdge(int i,int j) {
		verticles.get(i).setNeighbors(); //On met à jour le nombre de voisins de i
		predecessors.get(j).add(verticles.get(i)); //On ajoute i dans la liste des predecesseurs de j

	}
	
	public ArrayList<Vertex> getVerticles() {
		return verticles;
	}
	
	
	public ArrayList<LinkedList<Vertex>> getListOfpredecessors() {
		return predecessors;
	}
	
	public  double epsilon() {
		return 1.0 /( numberOfVertices() * 10.0);
	}
}

	
		


