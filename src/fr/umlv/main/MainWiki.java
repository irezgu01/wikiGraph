package fr.umlv.main;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

import fr.umlv.graph.AdjGraphWiki;
//import fr.umlv.graph.matrice.CalculationOfValues;
import fr.umlv.graph.list.CalculationOfValues;
import fr.umlv.graph.list.ExtractGraph;

public class MainWiki {
	public static void main(String[] args) throws IOException {
		
		AdjGraphWiki graph = ExtractGraph.constGraph(Paths.get("/home/cho/wikis/wiki-simple.txt"));
		HashMap<Integer, String> correspondance = ExtractGraph.correspondances;
		HashMap<Integer, Double> probas = CalculationOfValues.calculator(ExtractGraph.graphNeighbors, graph, 2);
		probas.keySet().forEach(e-> System.out.println(correspondance.get(e)+ " -> " +probas.get(e)));	
		System.err.println("*******Done*********");
		}
}
