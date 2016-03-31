package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Medium;

/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 *
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 *
 * Note:
 * The solution is guaranteed to be unique.
 */
public class _134_GasStation implements Medium {

  /**
   * The idea is if the sum of the gas is greater than the sum of cost, there must be a solution.
   *
   * Next, accumulate the "surplus" or "deficit" along the circle, at one point, you will have the biggest deficit.
   * Starting from the next station, you will never run into deficit so you can travel around the circle.
   */
  public static class Solution1 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
      int totalgas = 0;
      int totalcost = 0;
      int maxdiff = 0;
      int station = 0;
      for (int i = 0; i < gas.length; i++) {
        totalgas += gas[i];
        totalcost += cost[i];
        int diff = totalgas - totalcost;
        if (diff < maxdiff) {
          maxdiff = diff;
          station = i;
        }
      }
      if (totalcost > totalgas)
        return -1;
      station += 1;
      if (station == gas.length)
        station = 0;
      return station;
    }
  }

  /**
   * 1, if sum of gas is more than sum of cost, then there must be a solution. And the question guaranteed that the solution is unique(The first one I found is the right one).
   * 2, The tank should never be negative, so restart whenever there is a negative number.
   */
  public static class Solution2 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
      int sumGas = 0;
      int sumCost = 0;
      int start = 0;
      int tank = 0;
      for (int i = 0; i < gas.length; i++) {
        sumGas += gas[i];
        sumCost += cost[i];
        tank += gas[i] - cost[i];
        if (tank < 0) {
          start = i + 1;
          tank = 0;
        }
      }
      if (sumGas < sumCost)
        return -1;

      return start;
    }
  }

  public static class Solution3 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
      int start = gas.length - 1;
      int end = 0;
      int sum = gas[start] - cost[start];
      while (start > end) {
        if (sum >= 0) {
          sum += gas[end] - cost[end];
          ++end;
        }
        else {
          --start;
          sum += gas[start] - cost[start];
        }
      }
      return sum >= 0 ? start : -1;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution1().canCompleteCircuit(new int[]{6,1,4,3,5}, new int[]{3,8,2,4,2}));
    System.out.println(new Solution2().canCompleteCircuit(new int[]{6,1,4,3,5}, new int[]{3,8,2,4,2}));
    System.out.println(new Solution3().canCompleteCircuit(new int[]{6,1,4,3,5}, new int[]{3,8,2,4,2}));
  }



}
