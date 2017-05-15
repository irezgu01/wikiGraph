package fr.umlv.main;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import fr.umlv.graph.Graph;
import fr.umlv.graph.matrice.CalculationOfValues;
import fr.umlv.graph.matrice.ExtractGraph;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		//choice 1 --> AdjGraph, choice 2 --> MatGraph
		Graph graph = ExtractGraph.constGraph(Paths.get("/home/hamed/Bureau/alea/alea4-6.txt"),1);
	//	System.out.println(graph);
		List<Double >values = CalculationOfValues.calculator(graph, 100);
	//	System.out.println(values);
	}

}
