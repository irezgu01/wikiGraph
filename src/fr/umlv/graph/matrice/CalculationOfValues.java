package fr.umlv.graph.matrice;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.graph.Graph;
import fr.umlv.graph.Vertex;

public class CalculationOfValues {

	private static int goodK = -1 ;
	private static double minError = Integer.MAX_VALUE;
	private static  List<Double> givenValues = null;
	private static  List<Double> probas;

	private static void init(Graph graph){
		List<Vertex> verticles = graph.getVerticles();
		int n= graph.numberOfVertices();
		verticles.forEach(vertex ->{
			vertex.setProba(1.0/n);
			//	System.out.println(vertex);
		});
	}

	public static void calculator(Graph graph,int k){
		ArrayList<Vertex> verticles = graph.getVerticles();
		init(graph); 					//Initialisation des probas des sommets de base
		int nb = graph.numberOfVertices();
		int i ;
		probas = new ArrayList<>(nb+1);
		//Initialisation de la liste contenant les probas
		probas.add(0, 0.0);
		for(int l= 0;l < nb; l++){
			probas.add(l+1, verticles.get(l).getProba());
		}
		System.out.println("\nPROBAS INIT "+probas+"\n");
		double epsilon = graph.epsilon();
		givenValues = ExtractGraph.getValues();

		if(givenValues == null){
			throw new IllegalStateException("Une erreur s'est produite pendant la recuperation des valeurs de page rank fournies");
		}
		int n;
		double percentageError = 0.0;
		double sum = 0.0;
		for (i = 0; i < k; i++) {
	
			//Calcul des probas de chaque sommet
			for (int j = 0; j < nb; j++) {
				sum = 0.0;
				n = 0;
				List<Vertex> pred = graph.getListOfpredecessors().get(j);

				for(Vertex v : pred){
					n = v.getNeighbors();
					sum += ((1 - epsilon)/n) * v.getProba();
				}
				//On rajoute le cas où le predecesseur est le super noeud

				sum += (1.0/nb) * probas.get(0);

				//On ajoute la propriété que toutes les pages pointent vers elles-même
				n = verticles.get(j).getNeighbors();
				sum += ((1 - epsilon)/n) * verticles.get(j).getProba();

				probas.set(j+1, sum) ;
			}

			//Calcul de la proba du super noeud
			probas.set(0, probaSuperNoeud(verticles,epsilon));
			
			//Recherche de la valeur k qui est proche de la solution
			if(( percentageError= comparePageRank(probas)) < minError){
				minError = percentageError;
				goodK = i+1;
			}
			if(i == 50){
				probas.forEach(e->System.out.println(" --> "+e));
			}
			for(int ii = 0;ii <nb;ii++){

				verticles.get(ii).setProba(probas.get(ii+1));
			}

		}
		System.out.println("Le pourcentage d'erreur max observé pour cette valeur k ("+k+") est de "+ comparePageRank(probas));
		System.out.println("La valeur k assez proche de la solution est "+goodK+" et le pourcentage d'erreur est :"+minError);
	}

	private static double probaSuperNoeud(List<Vertex> verticles,double epsilon) {

		double sum = 0;
		for(Vertex v : verticles){
			sum += epsilon * v.getProba();
		}
		return sum;
	}

	private static double comparePageRank(List<Double> calculatedValues){
		int n = calculatedValues.size();
		double maxError = 0;
		double error ;
		double substraction = 0.0;
		for(int i = 0; i < n;i++){
			double exactProba = givenValues.get(i);
			substraction = exactProba - calculatedValues.get(i);
			if(substraction < 0){
				substraction = -substraction;
			}
			error = substraction /exactProba * 100.0 ;

			if(error > maxError){
				maxError = error;
			}
		}
		return maxError;
	}



}
