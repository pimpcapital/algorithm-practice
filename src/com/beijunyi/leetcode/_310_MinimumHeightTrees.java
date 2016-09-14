package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.beijunyi.leetcode.category.difficulty.Medium;

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

  public static class Solution1 implements Solution {
    @Override
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
      boolean[][] graph = indexEdges(n, edges);
      Stack<Integer> a = findFurthestGraph(0, graph);
      Stack<Integer> b = findFurthestGraph(a.get(a.size() - 1), graph);
      return findMidPoints(b);
    }

    private static boolean[][] indexEdges(int n, int[][] edges) {
      boolean[][] ret = new boolean[n][n];
      for(int[] edge : edges) {
        ret[edge[0]][edge[1]] = true;
        ret[edge[1]][edge[0]] = true;
      }
      return ret;
    }

    private static Stack<Integer> findFurthestGraph(int i, boolean[][] graph) {

      return null;
    }

    private static Stack<Integer> findMidPoints(Stack<Integer> b) {
      return null;
    }
  }

  public static void main(String args[]) {
    int n;
    int[][] edges;
    List<Integer> result;

    for(Solution s : Arrays.asList(new Solution1())) {

    }
  }

}
