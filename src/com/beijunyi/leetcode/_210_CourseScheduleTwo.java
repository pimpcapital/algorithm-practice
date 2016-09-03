package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed
 * as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to
 * finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses,
 * return an empty array.
 *
 * For example:
 *   2, [[1,0]]
 *   There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course
 *   order is [0,1]
 *
 *   4, [[1,0],[2,0],[3,1],[3,2]]
 *   There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both
 *   courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another
 *   correct ordering is[0,2,1,3].
 *
 * Note:
 *   The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a
 *   graph is represented.
 */
public class _210_CourseScheduleTwo implements Medium {

  public interface Solution {
    int[] findOrder(int numCourses, int[][] prerequisites);
  }

  public static class Solution1 implements Solution {
    @Override
    public int[] findOrder(int numCourses, int[][] prerequisites) {
      boolean[][] edges = indexEdges(numCourses, prerequisites);
      int[] inbounds = countInbounds(edges);
      int[] order = new int[numCourses];
      int index = 0;
      Queue<Integer> pq = new LinkedList<>();
      addRoots(pq, inbounds);
      while(!pq.isEmpty()) {
        int root = pq.poll();
        order[index++] = root;
        boolean[] reachables = edges[root];
        for(int t = 0; t < numCourses; t++) {
          if(reachables[t]) {
            reachables[t] = false;
            if(--inbounds[t] == 0) {
              pq.offer(t);
            }
          }
        }
      }
      if(index < order.length) order = new int[0];
      return order;
    }

    private static boolean[][] indexEdges(int numCourses, int[][] prerequisites) {
      boolean[][] ret = new boolean[numCourses][numCourses];
      for(int[] pre : prerequisites) {
        ret[pre[1]][pre[0]] = true;
      }
      return ret;
    }

    private static int[] countInbounds(boolean[][] edges) {
      int[] ret = new int[edges.length];
      for(boolean[] targets : edges) {
        for(int t = 0; t < targets.length; t++) {
          if(targets[t]) ret[t]++;
        }
      }
      return ret;
    }

    private static void addRoots(Queue<Integer> pq, int[] inbounds) {
      for(int i = 0; i < inbounds.length; i++) {
        if(inbounds[i] == 0) pq.offer(i);
      }
    }
  }

  public static void main(String args[]) {
    int numCourses;
    int[][] prerequisites;
    int[] result;

    for(Solution s : Arrays.asList(new Solution1())) {
      numCourses = 2;
      prerequisites = new int[][] {{1, 0}};
      result = s.findOrder(numCourses, prerequisites);
      System.out.println(Arrays.toString(result));
    }

  }

}
