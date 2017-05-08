package fr.umlv.graph;

public class Vertex {
	private final int vertex;
	private int neighbors;
	private double proba;
	
	public Vertex(int vertex) {
		this.vertex = vertex;
		neighbors = 1;
		proba = 1;
	}

	public int getVertex() {
		return vertex;
	}

	public int getNeighbors() {
		return neighbors;
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
	

}
