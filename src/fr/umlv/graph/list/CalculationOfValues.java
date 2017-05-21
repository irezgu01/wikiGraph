package fr.umlv.graph.list;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import fr.umlv.graph.AdjGraphWiki;

public class CalculationOfValues {

	public static HashMap<Integer, Double> calculator(HashMap<Integer, List<Integer>> graphNeighbors, AdjGraphWiki graphPredecessor, int k) {

		HashMap<Integer, Double> listProbas = new HashMap<>();
		HashMap<Integer, Double> probInter = new HashMap<>();
		double probaSuperNoeud = 0;
		int nb = graphNeighbors.keySet().size();

		for (Integer key : graphNeighbors.keySet()) {
			listProbas.put(key, (double) 1 / nb);
		}
		int i;
		double epsilon = 1.0 / (nb * 10.0);

		int n;
		double sumtotaleInit = 0;
		sumtotaleInit = listProbas.values().stream().mapToDouble(d -> d).sum();
		for (i = 0; i < k; i++) {
			double sumtotale = 0;
			Double[] probas = new Double[nb];
			for (Integer sommet : graphNeighbors.keySet()) {
				double sum = 0.0;
				n = 0;
				if (graphPredecessor.get(sommet) != null) {
					for (Integer pred : graphPredecessor.get(sommet)) {
						//System.out.println(graphNeighbors.get(pred));
						
						n = graphNeighbors.get(pred).size()+1;
						sum += ((1 - epsilon) / n) * listProbas.get(pred);
					}
				}
				// On rajoute le cas où le predecesseur est le super noeud

				sum += (1.0 / nb) * probaSuperNoeud;

				// On ajoute la propriété que toutes les pages pointent vers
				// elles-même
				n = graphNeighbors.get(sommet).size()+1;
				sum += ((1 - epsilon) / n) * listProbas.get(sommet);
				probInter.put(sommet, sum);
				sumtotale += sum;
			}

			probaSuperNoeud = probaSuperNoeud(graphNeighbors.keySet(), listProbas, epsilon, nb);

			System.out.println("Proba totale : " + (sumtotale + probaSuperNoeud));

			listProbas.putAll(probInter);

		}
		System.out.println("k vaut : " + i);

		return listProbas;
	}

	private static double probaSuperNoeud(Set<Integer> graph, HashMap<Integer, Double> proba, double epsilon, int nb) {

		double sum = 0;
		for (Integer v : graph) {
			sum += epsilon * proba.get(v);
		}
		return sum;
	}

}
