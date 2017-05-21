package fr.umlv.graph.list;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import fr.umlv.graph.AdjGraphWiki;
import fr.umlv.parsing.wiki.ParseWiki;

public class ExtractGraph {

	public static List<Double> VALUES = null;
	public static  HashMap<Integer, List<Integer>> graphNeighbors = new HashMap<>();
	public static HashMap<Integer, String> correspondances = new HashMap<>(); 
	public static AdjGraphWiki graphPredecessor = new AdjGraphWiki();

	public static AdjGraphWiki constGraph(Path path) throws IOException {

		ParseWiki.parse(path, (map, keys) -> {
			for (Integer key : map.keySet()) {
				List<Integer> list = map.get(key);
				for (Integer value : list) {
					graphPredecessor.addEdge(value, key);
				}
				
			}
			keys.forEach((k,v)->correspondances.put(v, k));
			graphNeighbors.putAll(map);
		});
		System.out.println("graphPredecessor is over");
		
		return graphPredecessor;
	}

}
