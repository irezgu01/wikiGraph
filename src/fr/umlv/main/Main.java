package fr.umlv.main;

import java.io.IOException;

import java.nio.file.Paths;
import fr.umlv.graph.Graph;
import fr.umlv.graph.matrice.CalculationOfValues;
import fr.umlv.graph.matrice.ExtractGraph;

public class Main {
	
	private static void usage(){
		throw new IllegalArgumentException("\nUsage : Enter x and  k (x = 1 --> AdjGraph, 2 --> MatGraph; 0<k<10_001) \n");
	}
	
	public static void main(String[] args) throws IOException {
		
		if(args.length != 2){
			usage();
		}
		
		int type = Integer.parseInt(args[0]);
		int k = Integer.parseInt(args[1]);
		if((type != 1 && type !=2) || (k <=0 || k>10_000)){
			usage();
		}
		
		//choice 1 --> AdjGraph, choice 2 --> MatGraph
		Graph graph = ExtractGraph.constGraph(Paths.get("/home/hamed/Bureau/alea/alea1000-10000.txt"),type);
		CalculationOfValues.calculator(graph, k); 
	}

}
