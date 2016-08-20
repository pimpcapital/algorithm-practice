package com.beijunyi.leetcode;

import java.util.Arrays;

/**
 * Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The
 * definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.
 *
 * Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do
 * is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the
 * celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
 *
 * You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int
 * findCelebrity(n), your function should minimize the number of calls to knows.
 *
 * Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a
 * celebrity in the party. If there is no celebrity, return -1.
 */
public class _277_FindTheCelebrity {

  public interface Solution {

    void init(int n, int[][] relations);

    int findCelebrity(int n);
  }

  public interface RelationSolution extends Solution {

    boolean knows(int a, int b);

  }

  public static abstract class AbstractRelationSolution implements RelationSolution {

    private boolean[][] network;

    public void init(int n, int[][] relations) {
      network = new boolean[n][n];
      for(int[] relation : relations) {
        int a = relation[0];
        int b = relation[1];
        network[a][b] = true;
      }
    }

    public boolean knows(int a, int b) {
      return network[a][b];
    }

  }

  public static class Solution1 extends AbstractRelationSolution implements Solution {

    @Override
    public int findCelebrity(int n) {
      if(n <= 1) return n - 1;
      int cele = 0;
      for(int b = 1; b < n; b++) {   // if b is not known by the current candidate, b is definitely not celebrity
        if(knows(cele, b)) cele = b; // if the current candidate knows b, choose b to be the new candidate
      }

      for(int i = 0; i < n; i++) {
        if(i == cele) continue;
        if(knows(cele, i)) return -1; // if this candidate knows anyone, invalid
        if(!knows(i, cele)) return -1; // if anyone does not know the candidate, invalid
      }

      return cele;
    }




  }

  public static void main(String args[]) {
    int n;
    int[][] relations;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      n = 2;
      relations = new int[][] {
        {0, 1}
      };
      s.init(n, relations);
      result = s.findCelebrity(n);
      System.out.println(result);

      n = 2;
      relations = new int[][] {
        {0, 1}, {1, 0}
      };
      s.init(n, relations);
      result = s.findCelebrity(n);
      System.out.println(result);

      n = 2;
      relations = new int[][] {};
      s.init(n, relations);
      result = s.findCelebrity(n);
      System.out.println(result);
    }
  }



}
