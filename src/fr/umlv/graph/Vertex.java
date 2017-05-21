package fr.umlv.graph;

public class Vertex {
	private final int vertex;
	private int neighbors;
	private double proba;
	
	public Vertex(int vertex) {
		this.vertex = vertex;
		neighbors = 0;
		proba = 1;
	}

	public int getVertex() {
		return vertex;
	}

	public int getNeighbors() {
		return neighbors + 1;
	}

	public double getProba() {
		return proba;
	}

	public void setNeighbors() {
		this.neighbors++;
	}

	public void setProba(double proba) {
		this.proba = proba;
	}
	@Override
	public boolean equals(Object arg0) {
		if(!(arg0 instanceof Vertex)) {
			return false;
		}
		Vertex v = (Vertex)arg0;
		return v.getVertex() == this.vertex;
	}
	@Override
	public String toString() {
		
		return "Je suis le sommet : "+vertex+", j'ai : "+neighbors+" voisin(s) et ma proba vaut : "+proba;
	}
	

}
