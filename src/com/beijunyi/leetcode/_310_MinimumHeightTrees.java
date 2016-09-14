package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.BreadthFirstSearch;

/**
 * For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a
 * rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given
 * such a graph, write a function to find all the MHTs and return a list of their root labels.
 *
 * Format
 *   The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of
 *   undirected edges (each edge is a pair of labels).
 *
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as
 * [1, 0] and thus will not appear together in edges.
 *
 * Example 1:
 * Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 *       0
 *       |
 *       1
 *      / \
 *     2   3
 * return [1]
 *
 * Example 2:
 * Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 *    0  1  2
 *     \ | /
 *       3
 *       |
 *       4
 *       |
 *       5
 * return [3, 4]
 *
 * Hint:
 *   How many MHTs can a graph have at most?
 * Note:
 *   (1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are
 *       connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
 *   (2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 */
public class _310_MinimumHeightTrees implements Medium {

  public interface Solution {
    List<Integer> findMinHeightTrees(int n, int[][] edges);
  }

  /**
   * It is easy to see that the root of an MHT has to be the middle point (or two middle points) of the longest path of
   * the tree.
   * Though multiple longest paths can appear in an unrooted tree, they must share the same middle point(s).
   *
   * Computing the longest path of a unrooted tree can be done, in O(n) time, by tree dp, or simply 2 tree traversals
   * (dfs or bfs).
   *
   * Randomly select a node x as the root, do a dfs/bfs to find the node y that has the longest distance from x.
   * Then y must be one of the endpoints on some longest path.
   * Let y the new root, and do another dfs/bfs. Find the node z that has the longest distance from y.
   *
   * Now, the path from y to z is the longest one, and thus its middle point(s) is the answer.
   */
  public static class Solution1 implements Solution, BreadthFirstSearch {

    @Override
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
      Map<Integer, Set<Integer>> graph = indexEdges(n, edges);
      List<Integer> zeroToA = toFurthestPoint(0, graph);
      List<Integer> aToB = toFurthestPoint(zeroToA.get(zeroToA.size() - 1), graph);
      return findMidPoints(aToB);
    }

    private static Map<Integer, Set<Integer>> indexEdges(int n, int[][] edges) {
      Map<Integer, Set<Integer>> ret = new HashMap<>();
      for(int i = 0; i < n; i++) ret.put(i, new HashSet<>());
      for(int[] edge : edges) {
        ret.get(edge[0]).add(edge[1]);
        ret.get(edge[1]).add(edge[0]);
      }
      return ret;
    }

    private static List<Integer> toFurthestPoint(int start,  Map<Integer, Set<Integer>> graph) {
      Map<Integer, Integer> predecessors = new HashMap<>();
      Queue<Integer> q = new LinkedList<>();
      q.offer(start);
      predecessors.put(start, null);
      int furthest;
      while(true) {
        int current  = q.poll();
        Set<Integer> successors = graph.get(current);
        for(int successor : successors) {
          if(predecessors.containsKey(successor)) continue;
          q.offer(successor);
          predecessors.put(successor, current);
        }
        if(q.isEmpty()) {
          furthest = current;
          break;
        }
      }
      List<Integer> path = new ArrayList<>();
      Integer current = furthest;
      while(current != null) {
        path.add(current);
        current = predecessors.get(current);
      }
      Collections.reverse(path);
      return path;
    }

    private static List<Integer> findMidPoints(List<Integer> path) {
      int mid = path.size() / 2;
      if(path.size() % 2 == 0) return path.subList(mid - 1, mid + 1);
      return path.subList(mid, mid + 1);
    }
  }

  /**
   * Every iteration, eliminate node whose degrees are 1. They are the leaves.
   * Repeat the process until 2 or 1 node(s) left. They are roots.
   */
  public static class Solution2 implements Solution {

    @Override
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
      Map<Integer, Set<Integer>> graph = computeGraph(n, edges);

      Set<Integer> roots = new HashSet<>();
      for(Map.Entry<Integer, Set<Integer>> node : graph.entrySet()) {
        if(node.getValue().size() == 1) {
          roots.add(node.getKey());
        }
      }

      while(graph.size() > 2) {
        Set<Integer> newRoots = new HashSet<>();
        for(int root : roots) {
          for(int adj : graph.get(root)) {
            graph.get(adj).remove(root);
            if(graph.get(adj).size() == 1) {
              newRoots.add(adj);
            }
          }
        }
        graph.keySet().removeAll(roots);
        roots = newRoots;
      }
      return new ArrayList<>(graph.keySet());
    }

    private static Map<Integer, Set<Integer>> computeGraph(int n, int[][] edges) {
      Map<Integer, Set<Integer>> ret = new HashMap<>();
      for(int i = 0; i < n; i++) ret.put(i, new HashSet<>());
      for(int[] edge : edges) {
        ret.get(edge[0]).add(edge[1]);
        ret.get(edge[1]).add(edge[0]);
      }
      return ret;
    }

  }

  public static void main(String args[]) {
    int n;
    int[][] edges;
    List<Integer> result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      n = 1;
      edges = new int[][] {};
      result = s.findMinHeightTrees(n, edges);
      System.out.println(result);

      n = 6;
      edges = new int[][] {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
      result = s.findMinHeightTrees(n, edges);
      System.out.println(result);
    }
  }

}
