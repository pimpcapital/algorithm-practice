package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.BreadthFirstSearch;
import com.beijunyi.leetcode.category.solution.DepthFirstSearch;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed
 * as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 * For example:
 *   2, [[1,0]]
 *   There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
 *
 *   2, [[1,0],[0,1]]
 *   There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0
 *   you should also have finished course 1. So it is impossible.
 *
 * Note:
 *   The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a
 *   graph is represented.
 */
public class _207_CourseSchedule implements Medium {

  public interface Solution {
    boolean canFinish(int numCourses, int[][] prerequisites);
  }

  /**
   * Time: O(n*p), Space: O(n) where:
   *   n is the number of courses
   *   p is the number of prerequisites
   *
   * A BFS solution that starts with the nodes that have 0 inward degree.
   * For every iteration, the neighbours of the 0-inward-degree nodes reduce their inward degree by 1.
   * When a node's inward degree becomes 0, it is added to the BFS queue.
   *
   * When the BFS queue becomes empty, all nodes in a cycle will have inward degree > 0.
   */
  public static class Solution1 implements Solution, BreadthFirstSearch {

    @Override
    public boolean canFinish(int numCourses, int[][] prerequisites) {
      if(numCourses <= 0) return false;

      int[] inwardDegrees = new int[numCourses];
      for(int[] prerequisite : prerequisites)
        inwardDegrees[prerequisite[1]]++;

      Queue<Integer> queue = new LinkedList<>();
      for(int i = 0; i < inwardDegrees.length; i++)
        if(inwardDegrees[i] == 0) queue.offer(i);

      while(!queue.isEmpty()) {
        int x = queue.poll();
        for(int[] prerequisite : prerequisites) {
          if(x == prerequisite[0]) {
            inwardDegrees[prerequisite[1]]--;
            if(inwardDegrees[prerequisite[1]] == 0) queue.offer(prerequisite[1]);
          }
        }
      }
      for(int inwardDegree : inwardDegrees)
        if(inwardDegree != 0) return false;

      return true;
    }

  }

  /**
   * Time: O(n*p), Space: O(n) where:
   *   n is the number of courses
   *   p is the number of prerequisites
   *
   * Same idea as to Solution1 except this solution starts from the "free" courses i.e. the ones have no prerequisite.
   * Once we have identified the "free" courses, the ones that require the "free" courses as their prerequisites become
   * "cheaper" (decreasing the outward degree of the neighbours of the "free" nodes by 1 in every iteration).
   *
   * When the BFS finishes, all the "non-free" courses are the ones that got stuck in a loop.
   */
  public static class Solution2 implements Solution, BreadthFirstSearch {

    @Override
    public boolean canFinish(int numCourses, int[][] prerequisites) {
      if(numCourses <= 0) return false;

      int[] outwardDegrees = new int[numCourses];
      for(int[] prerequisite : prerequisites)
        outwardDegrees[prerequisite[0]]++;

      Queue<Integer> queue = new LinkedList<>();
      for(int i = 0; i < outwardDegrees.length; i++)
        if(outwardDegrees[i] == 0) queue.offer(i);

      while(!queue.isEmpty()) {
        int x = queue.poll();
        for(int[] prerequisite : prerequisites) {
          if(x == prerequisite[1]) {
            outwardDegrees[prerequisite[0]]--;
            if(outwardDegrees[prerequisite[0]] == 0) queue.offer(prerequisite[0]);
          }
        }
      }
      for(int outwardDegree : outwardDegrees)
        if(outwardDegree != 0) return false;

      return true;
    }

  }

  /**
   * Time: O(n*p), Space: O(n+p) where:
   *   n is the number of courses
   *   p is the number of prerequisites
   *
   * A DFS solution that terminates when a loop is detected.
   * It uses 2 arrays of size n to mark the visited nodes and the one currently being visited.
   */
  public static class Solution3 implements Solution, DepthFirstSearch {

    @Override
    public boolean canFinish(int numCourses, int[][] prerequisites) {
      Map<Integer, Set<Integer>> prereqLookup = indexPrerequisites(numCourses, prerequisites);
      return canFinish(numCourses, prereqLookup);
    }

    // speed O(n+p)
    private static Map<Integer, Set<Integer>> indexPrerequisites(int numCourses, int[][] prerequisites) {
      Map<Integer, Set<Integer>> ret = new HashMap<>();

      for(int c = 0; c < numCourses; c++)
        ret.put(c, new HashSet<Integer>());

      for(int[] prereq : prerequisites) {
        int from = prereq[0];
        int to = prereq[1];
        ret.get(from).add(to);
      }
      return ret;
    }

    // O(n*p)
    private static boolean canFinish(int numCourses, Map<Integer, Set<Integer>> prereqLookup) {
      boolean[] visited = new boolean[numCourses];
      for(int c = 0; c < numCourses; c++) {
        if(visited[c]) continue;
        if(hasCycle(c, prereqLookup, visited)) return false;
      }
      return true;
    }

    private static boolean hasCycle(int from, Map<Integer, Set<Integer>> prereqLookup, boolean[] visited) {
      boolean[] visiting = new boolean[visited.length];
      return hasCycle(from, prereqLookup, visiting, visited);
    }

    private static boolean hasCycle(int from, Map<Integer, Set<Integer>> prereqLookup, boolean[] visiting, boolean[] visited) {
      visiting[from] = true;
      Set<Integer> prereqs = prereqLookup.get(from);
      for(int prereq : prereqs) {
        if(visited[prereq]) continue;
        if(visiting[prereq] || hasCycle(prereq, prereqLookup, visiting, visited))
          return true;
      }
      visiting[from] = false;
      visited[from] = true;
      return false;
    }

  }

  public static void main(String[] args) {
    int numCourses = 3;
    int[][] prerequisites = new int[][] {{0, 1}, {0, 2}, {1, 2}};
    System.out.println(new Solution3().canFinish(numCourses, prerequisites));
  }

}
