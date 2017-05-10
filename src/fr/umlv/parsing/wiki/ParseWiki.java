package fr.umlv.parsing.wiki;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParseWiki {
	public static void parse(Path path, Executor processor) throws IOException {
		HashMap<String, List<String>> graphs = new HashMap<>();

		try (Stream<String> lines = Files.lines(path)) {
			String vertex;
			String regex = "\\|";
			for (String line : lines.collect(Collectors.toList())) {

				String[] tokens = line.split(regex);
				vertex = tokens[0];
				graphs.put(vertex, new ArrayList<>());
				for (int i = 1; i < tokens.length; i++) {
					graphs.get(vertex).add(tokens[i]);
				}
			}
		}

		System.out.println("*********************GRAPHES*************************");
		graphs.keySet().forEach(e -> System.out.println(e + " -> "+ graphs.get(e)));
		//graphs.values().forEach(e -> System.out.println(" " + e));

		processor.execute(graphs);

	}

	public static void main(String[] args) throws IOException {
		parse(Paths.get("/home/cho/wikis/wiki-cree.txt"), e -> System.out.println(" tout est nickel : " + e.keySet()));
	}

}
