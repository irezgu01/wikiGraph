package fr.umlv.graph;

import java.util.Arrays;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;

public class MatGraph extends AbstractGraph implements Graph {
	private final int[][] mat;
;

	public MatGraph(int x) {
		super(x);
		mat = new int[x][x];
	}

	@Override
	public int numberOfEdges() {
		int count = 0;
		int n = this.numberOfVertices();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (mat[i][j] != 0)
					count++;
			}
		}
		return count;
	}

	@Override
	public void addEdge(int i,int j) {
		super.addEdge(i, j);
		mat[i][j] = 1;
		
	}

	@Override
	public boolean isEdge(int i,int j) {
		return mat[i][j] != 0;
	}

	@Override
	public int getWeight(int i, int j) {
		return mat[i][j];
	}


	@Override
	public Iterator<Edge> edgeIterator(int i) {
		return new Iterator<Edge>() {

			int index = findNext(0);
			int lastindex = 0;
			
			private int findNext(int dst) {
				for (int j = dst; j < n; j++) {
					if (mat[i][j] != 0)
						return j;
				}
				return -1;
			}

			@Override
			public boolean hasNext() {

				return index != -1;
			}

			@Override
			public Edge next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				lastindex = index;
				index = findNext(index + 1);

				return new Edge(i, lastindex /*, mat[i][lastindex]*/);
			}
		};
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < n; i++) {
			str.append(Arrays.toString(mat[i]));
			str.append("\n");
		}
		return str.toString();
	}

	@Override
	public void forEachEdge(int i, Consumer<Edge> consumer) {
		for (int j = 0; j < n; j++) {
			//int value = mat[i][j];
			consumer.accept(new Edge(i, j /*, value*/));
		}

	}

	public static List<Integer> DFS(Graph g) {
		LongAdder compteur = new LongAdder();
		int s = g.numberOfVertices();
		List<Integer> list = new LinkedList<Integer>();
		int[][] tab = new int[s][2];
		for(int i=0; i< s;i++) {
			tab[i][0]=-1;
			tab[i][1]=-1;
		}
		for(int i=0; i<s ; i++) {
			if(tab[i][0]==-1) {
				list.add(i);
				dfsRec(g,i,tab,compteur,list);
			}
		}
		
		for(int i=0;i<s;i++) {
			System.out.println(i+" "+Arrays.toString(tab[i]));
		}
		return list;
	}
	static void dfsRec(Graph g, int s , int[][] tab ,LongAdder cmp, List<Integer> list) {
		tab[s][0]=cmp.intValue();
		cmp.increment();
		int next;
		Iterator<Edge> it = g.edgeIterator(s);
		while(it.hasNext()) {
			next = it.next().getEnd();
			if(tab[next][0]==-1) {
				list.add(next);
				dfsRec(g,next,tab,cmp,list);
			}
		}
		tab[s][1]=cmp.intValue();
		cmp.increment();
	}
	public static List<Integer> BFS(Graph g) {

		return null;
	}

}
