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
		HashMap<Integer, List<Integer>> graphs = new HashMap<>();
		HashMap<String, Integer> keys = new HashMap<>();
		HashMap<Integer, Boolean> redondance = new HashMap<>();
		try (Stream<String> lines = Files.lines(path)) {
			String regex = "\\|";
			int sommet = 0;
			boolean added = true;
			for (String line : lines.collect(Collectors.toList())) {
				String[] tokens = line.split(regex);

				if (keys.get(tokens[0]) == null) {
					keys.put(tokens[0], sommet);
					added = true;
					graphs.put(sommet, new ArrayList<>());
				} else {
					int j = keys.get(tokens[0]);
					graphs.put(j, new ArrayList<>());
					added = false;
				}

				for (int i = 1; i < tokens.length; i++) {
					if (added) {
						sommet++;
					}
					if (keys.get(tokens[i]) == null) {
						keys.put(tokens[i], sommet);
						added = true;
						if (redondance.get(sommet) == null) {
							graphs.get(keys.get(tokens[0])).add(sommet);
							redondance.put(sommet, true);
						}
					} else {
						int j = keys.get(tokens[i]);
						added = false;
						if (redondance.get(j) == null) {
							graphs.get(keys.get(tokens[0])).add(j);
							redondance.put(j, true);
						}
					}
				}
				redondance.clear();
				sommet++;
			}
		}

		System.out.println("*********************GRAPHES*************************");
		graphs.keySet().forEach(e -> System.out.println(e + " -> " + graphs.get(e)));
		// System.out.println("*******************map*************************");
		// keys.keySet().forEach(e -> System.out.println(e + "-> " +
		// keys.get(e)));
		processor.execute(graphs, keys);

	}

	public static void main(String[] args) throws IOException {
		parse(Paths.get("/home/cho/wikis/wiki-cree.txt"), (e, f) -> System.out.println("done"));
	}

}
