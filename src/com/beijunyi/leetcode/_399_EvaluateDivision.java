package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real
 * number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 *
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries,
 * where equations.size() == values.size(), and the values are positive. This represents the equations. Return
 * vector<double>.
 *
 * According to the example above:
 *   equations = [ ["a", "b"], ["b", "c"] ],
 *   values = [2.0, 3.0],
 *   queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 *
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is
 * no contradiction.
 */
public class _399_EvaluateDivision implements Medium {

  public interface Solution {
    double[] calcEquation(String[][] equations, double[] values, String[][] queries);
  }

  public static class Solution1 implements Solution {

    @Override
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
      Map<String, Map<String, Double>> graph = makeGraph(equations, values);

      double[] ret = new double[queries.length];
      for(int i = 0; i < queries.length; i++) {
        Double value = searchGraph(queries[i][0], queries[i][1], graph);
        ret[i] = value != null ? value : -1D;
      }
      return ret;
    }

    private static Double searchGraph(String from, String to, Map<String, Map<String, Double>> graph) {
      Set<String> visited = new HashSet<>();
      visited.add(from);
      return searchGraph(from , to, graph, 1, visited);
    }

    private static Double searchGraph(String from, String to, Map<String, Map<String, Double>> graph, double current, Set<String> visited) {
      if(from.equals(to)) return graph.containsKey(from) ? current : -1D;
      Map<String, Double> reachables = graph.get(from);
      if(reachables == null) return null;
      for(String next : reachables.keySet()) {
        if(!visited.add(next)) continue;
        double value = reachables.get(next);
        Double result = searchGraph(next, to, graph, current * value, visited);
        if(result != null) return result;
      }
      return null;
    }

    private static Map<String, Map<String, Double>> makeGraph(String[][] equations, double[] values) {
      Map<String, Map<String, Double>> ret = new HashMap<>();
      int max = equations.length;
      for(int i = 0; i < max; i++) {
        String left = equations[i][0];
        String right = equations[i][1];
        double value = values[i];
        ret.computeIfAbsent(left, (key) -> new HashMap<>()).put(right, value);
        ret.computeIfAbsent(right, (key) -> new HashMap<>()).put(left, 1D / value);
      }
      return ret;
    }

  }

  public static void main(String args[]) {
    String[][] equations;
    double[] values;
    String[][] queries;
    double[] result;

    for(Solution s : Arrays.asList(new Solution1())) {
      equations = new String[][]{{"a", "b"}, {"b", "c"}};
      values = new double[]{2, 3};
      queries = new String[][]{{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
      result = s.calcEquation(equations, values, queries);
      System.out.println(Arrays.toString(result));
    }
  }

}
