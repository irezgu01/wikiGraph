package fr.umlv.main;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import fr.umlv.graph.AdjGraphWiki;
//import fr.umlv.graph.matrice.CalculationOfValues;
import fr.umlv.graph.list.CalculationOfValues;
import fr.umlv.graph.list.ExtractGraph;

public class MainWiki {
	public static void main(String[] args) throws IOException {
		if(args.length!=3) {
			
			System.out.println("usage : ./main path | k | x valeurs à afficher");
		}
		int k = Integer.valueOf(args[1]);
		int limit = Integer.valueOf(args[2]);
		String path = args[0];
		AdjGraphWiki graph = ExtractGraph.constGraph(Paths.get(path));
		HashMap<Integer, String> correspondance = ExtractGraph.correspondances;
		HashMap<Integer, Double> probas = CalculationOfValues.calculator(ExtractGraph.graphNeighbors, graph, k);
		probas.keySet().forEach(e-> System.out.println(correspondance.get(e)+ " -> " +probas.get(e)));
		
		System.err.println("*******Done*********");
		probas.entrySet().stream().sorted(Map.Entry.comparingByValue((a,b)-> Double.compare(b,a))).limit(limit).forEach(entry->{
			System.out.println(correspondance.get(entry.getKey()) + " : " + entry.getValue());
		});
		
		}
}
