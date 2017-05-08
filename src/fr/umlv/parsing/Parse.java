package fr.umlv.parsing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parse {
	
	public static void parse(Path path,Executor processor) throws IOException {
		HashMap<Integer, List<Integer>> graphs = new HashMap<>();
		List<Double> probas = new ArrayList<>();
		
		try(Stream<String> lines = Files.lines(path)) {
			int vertex = 1;
			boolean startProbas = false;
			String regex = " ";
			int nb = 1;
			for(String line : lines.collect(Collectors.toList())){
				int a = nb++;
				if(line.isEmpty()){
					startProbas = true;
					regex = ", ";
					continue;
				}
				String[] tokens = line.split(regex);
				if(startProbas){
					for (int i = 0; i < tokens.length; i++) {
						probas.add(Double.valueOf(tokens[i]));
					}
				}else{
					graphs.put(vertex,new ArrayList<>());
					
					for (int i = 0; i < tokens.length; i++) {
						graphs.get(vertex).add(Integer.valueOf(tokens[i]));
					}
					vertex++;
				}
			}
		}
		
		System.out.println("*********************GRAPHES*************************");
		graphs.values().forEach(e -> System.out.println(" "+e));
		System.out.println("*******************probas*************************");
		probas.forEach(e -> System.out.println(" "+e));
		
		
		processor.execute(graphs, probas);
	}
	public static void main(String[] args) throws IOException {
		parse(Paths.get("/home/hamed/git/wikiGraph/bin/alea/alea10-40.txt"),(e,f)-> System.out.println(" tout est nickel : "+e.keySet()));
	}
	

}
