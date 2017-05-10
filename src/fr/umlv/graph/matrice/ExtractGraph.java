package fr.umlv.graph.matrice;

import fr.umlv.graph.*;
import fr.umlv.parsing.matrice.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;



public class ExtractGraph {

	public static  List<Double> VALUES = null;
	private static Graph graph = null;
	
	public static Graph constGraph(Path path,int type) throws IOException{
		
		
		Parse.parse(path, (map,probas)-> {
			Graph g;
			int nb = map.size();
			switch (type) {
			case 1:
				g = new AdjGraph(nb);
				break;
			case 2:
				g = new MatGraph(nb);
				break;
			default:
				throw new IllegalArgumentException("Unknown type of graph");
			}
			for(int start : map.keySet()){
				int end = 0;
				for(int i : map.get(start)){
					if(i == 1){
						g.addEdge(start,end);
					}
					end++;
				}
			}
			graph = g;
			VALUES = probas;
			
		});
		
		return graph;
	}

}
