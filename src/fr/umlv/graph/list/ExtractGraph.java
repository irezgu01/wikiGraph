package fr.umlv.graph.list;

import fr.umlv.graph.*;
import fr.umlv.parsing.wiki.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;



public class ExtractGraph {

	public static  List<Double> VALUES = null;
	private static Graph graph = null;
	
	public static Graph constGraph(Path path,int type) throws IOException{
		
		
		ParseWiki.parse(path , (map,keys) -> {
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
			for (Integer key : map.keySet()) {
				for (Integer value : map.get(key)) {
					g.addEdge(key, value);
				}
			}
			graph = g;			
		});
		
		return graph;
	}

}
