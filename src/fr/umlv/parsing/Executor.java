package fr.umlv.parsing;

import java.util.List;
import java.util.Map;

public interface Executor {
	void execute(Map<Integer, List<Integer>> graphs,List<Double> probas);

}
