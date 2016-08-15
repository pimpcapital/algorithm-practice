package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.DepthFirstSearch;
import com.beijunyi.leetcode.category.solution.UnionFind;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function
 * to check whether these edges make up a valid tree.
 *
 * For example:
 *   Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 *   Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 *
 * Hint:
 *   1) Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
 *   2) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are
 *   connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
 *
 * Note:
 *   you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as
 *   [1, 0] and thus will not appear together in edges.
 */
public class _261_GraphValidTree implements Medium, PremiumQuestion {

  public interface Solution {
    boolean validTree(int n, int[][] edges);
  }

  public static class Solution1 implements Solution, DepthFirstSearch {

    @Override
    public boolean validTree(int n, int[][] edges) {
      if(n < 2) return n == 1;
      boolean[][] matrix = indexEdges(n, edges);
      if(findLoop(0, matrix)) return false; // if there is a loop, this is not a tree
      for(int i = 0; i < n; i++)if(matrix[i] != null) return false; // if any node is not reached, this is not a tree
      return true;
    }

    private static boolean[][] indexEdges(int n, int[][] edges) {
      boolean[][] matrix = new boolean[n][n];
      for(int[] fromTo : edges) {
        matrix[fromTo[0]][fromTo[1]] = true;
        matrix[fromTo[1]][fromTo[0]] = true;
      }
      return matrix;
    }

    private static boolean findLoop(int from, boolean[][] matrix) {
      boolean[] to = matrix[from]; // find the destinations
      matrix[from] = null; // mark it as processed
      for(int i = 0; i < to.length; i++) {
        if(to[i]) {
          if(matrix[i] == null) return true;
          matrix[i][from] = false; // disable the return edge
          if(findLoop(i, matrix)) return true;
        }
      }
      return false;
    }

  }

  public static class Solution2 implements Solution, UnionFind {

    @Override
    public boolean validTree(int n, int[][] edges) {
      int[] root = new int[n];
      for(int i = 0; i < n; i++) root[i] = i;
      for(int[] edge : edges) {
        int root1 = find(root, edge[0]);
        int root2 = find(root, edge[1]);
        if(root1 == root2) {
          return false;
        }
        root[root1] = root2;
      }
      return edges.length == n - 1;
    }

    private int find(int[] root, int p) {
      while(root[p] != p) {
        root[p] = root[root[p]];
        p = root[p];
      }
      return p;
    }

  }

  public static void main(String args[]) {
    int n;
    int[][] edges;
    boolean result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      n = 5;
      edges = new int[][] {
        {0, 1}, {0, 2}, {0, 3}, {1, 4}
      };
      result = s.validTree(n, edges);
      System.out.println(result);

      n = 5;
      edges = new int[][] {
        {0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}
      };
      result = s.validTree(n, edges);
      System.out.println(result);
    }
  }

}
