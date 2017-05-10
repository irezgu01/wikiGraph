package fr.umlv.parsing.matrice;

import java.util.List;
import java.util.Map;

public interface Executor {
	void execute(Map<Integer, List<Integer>> graphs,List<Double> probas);
}
