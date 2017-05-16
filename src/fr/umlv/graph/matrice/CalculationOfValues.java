package fr.umlv.graph.matrice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.umlv.graph.Graph;
import fr.umlv.graph.Vertex;

public class CalculationOfValues {
	
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
		init(graph); 					//Initialisation des probas des sommets de base
		double probaSuperNoeud = 0;		//Initialisation de la proba du super noeud
		int nb = graph.numberOfVertices();
		ArrayList<Double> result = new ArrayList<>(nb+1);
		int i ;
		double epsilon = graph.epsilon();
		double compareValue = ExtractGraph.VALUES.get(0);
		int n;
		double sumtotaleInit = 0;
		for(int kk = 0;kk < nb;kk++){
			sumtotaleInit +=verticles.get(kk).getProba();
		}
	//	System.out.println("Proba totale : "+sumtotaleInit);

		for (i = 0; i < k; i++) {
			double sumtotale = 0;
			Double[] probas =  new Double[nb];
			for (int j = 0; j < nb; j++) {
				double sum = 0.0;
				n = 0;
				List<Vertex> pred = graph.getListOfpredecessors().get(j);
				
				for(Vertex v : pred){
					n = v.getNeighbors();
						sum += ((1 - epsilon)/n) * v.getProba();
				}
				//On rajoute le cas où le predecesseur est le super noeud
				
					sum += (1.0/nb) * probaSuperNoeud;
				
				
				//On ajoute la propriété que toutes les pages pointent vers elles-même
				n = verticles.get(j).getNeighbors();
					sum += ((1 - epsilon)/n) * verticles.get(j).getProba();
				
				//verticles.get(j).setProba(sum);
					probas[j] = sum;
				sumtotale += sum;
			}
			
			if(compareValue == probaSuperNoeud){
				System.out.println("YESSSSSSSSSSS");
				break;
			}
			probaSuperNoeud = probaSuperNoeud(verticles,epsilon,nb);
			
			System.out.println("Proba totale : "+(sumtotale+probaSuperNoeud));
			for(int ii = 0;ii <nb;ii++){
				
				verticles.get(ii).setProba(probas[ii]);
			}
			System.out.println(Arrays.toString(probas));
			System.out.println("super noeud "+probaSuperNoeud);

		}
		System.out.println("k vaut : "+i);
		result.add(probaSuperNoeud);
		for(Vertex v : verticles){
			result.add(v.getProba());
		}
		return result;
	}

	private static double probaSuperNoeud(List<Vertex> verticles,double epsilon,int nb) {
		
		double sum = 0;
		for(Vertex v : verticles){
			sum += epsilon * v.getProba();
		}
		return sum;
	}

}
