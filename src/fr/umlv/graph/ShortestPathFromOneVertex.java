package fr.umlv.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class ShortestPathFromOneVertex {
	private final int source;
	private final int[] d;
	private final int[] pi;

	ShortestPathFromOneVertex(int vertex, int[] d, int[] pi) {
		this.source = vertex;
		this.d = d;
		this.pi = pi;
	}

	public void printShortestPathTo(int vertex) {
		LinkedList<Integer> path = new LinkedList<>();
		int i = vertex;
		path.addFirst(i);
		//System.out.print(i);
		while(i != source) {
			path.addFirst(pi[i]);
			//System.out.print(" <- "+pi[i]);
			i=pi[i];
		}
		System.out.println("le plus court chemin de "+source+" à "+vertex+ " est "+path);
	}

	public void printShortestPaths() {
		for (int i = 0; i < d.length; i++) {
			if (i == source) {
				continue;
			}
			printShortestPathTo(i);
		}
	}

	@Override
	public String toString() {
		return source + " " + Arrays.toString(d) + " " + Arrays.toString(pi);
	}
}
