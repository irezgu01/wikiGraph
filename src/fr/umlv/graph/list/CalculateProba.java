package fr.umlv.graph.list;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.graph.Graph;
import fr.umlv.graph.Vertex;

public class CalculateProba {
	
	private static void init(Graph graph){
		List<Vertex> verticles = graph.getVerticles();
		int n= graph.numberOfVertices();
		verticles.forEach(vertex ->{
			vertex.setProba(1.0/n);
		//	System.out.println(vertex);
		});
	}
	public static List<Double> calculator(Graph graph,int k){
		ArrayList<Vertex> verticles = graph.getVerticles();
		init(graph);
		double probaSuperNoeud = 0;
		int nb = graph.numberOfVertices();
		System.out.println(nb);
		ArrayList<Double> result = new ArrayList<>(nb+1);
		int i ;
		double epsilon = graph.epsilon();

		for (i = 0; i < k; i++) {
			for (int j = 0; j < nb; j++) {
				double sum = 0.0;
				int n = 0;
				List<Vertex> pred = graph.getListOfpredecessors().get(j);
				for(Vertex v : pred){
					n = v.getNeighbors();
					if(n!=0){
						sum += ((1 - epsilon)/n) * v.getProba();
					}
				}
				if(n!=0){
					sum += (1.0 / n) * probaSuperNoeud;
				}
				verticles.get(j).setProba(sum);
			}
			probaSuperNoeud = probaSuperNoeud(verticles,epsilon);
			
		}
		System.out.println("k vaut : "+i);
		result.add(probaSuperNoeud);
		for(Vertex v : verticles){
			result.add(v.getProba());
		}
		return result;
	}

	private static double probaSuperNoeud(List<Vertex> verticles,double epsilon) {
		
		double sum = 0;
		for(Vertex v : verticles){
			sum += epsilon * v.getProba();
		}
		return sum;
	}
}
