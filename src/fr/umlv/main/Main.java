package fr.umlv.main;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import fr.umlv.calc.CalculationOfValues;
import fr.umlv.graph.ExtractGraph;
import fr.umlv.graph.Graph;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		//choice 1 --> AdjGraph, choice 2 --> MatGraph
		Graph graph = ExtractGraph.constGraph(Paths.get("/home/hamed/git/wikiGraph/bin/alea/alea10-40.txt"),1);
		System.out.println(graph);
		List<Double >values = CalculationOfValues.calculator(graph, 5);
		System.out.println(values);
	}

}
