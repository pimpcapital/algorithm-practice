package com.beijunyi.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Follow up for "Unique Paths":
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 *
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 *
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * The total number of unique paths is 2.
 *
 * Note: m and n will be at most 100.
 */
public class _063_UniquePathsTwo {

  public static class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
      return uniquePathsWithObstacles(obstacleGrid, 0, 0, new HashMap<Integer, Integer>());
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid, int x, int y, Map<Integer, Integer> cache) {
      int sig = x * (obstacleGrid.length + 1) + y;
      if(cache.containsKey(sig))
        return cache.get(sig);

      int result;
      if(y == obstacleGrid.length || x == obstacleGrid[0].length || obstacleGrid[y][x] == 1)
        result = 0;
      else if(y == obstacleGrid.length - 1 && x == obstacleGrid[0].length - 1)
        result = 1;
      else
        result = uniquePathsWithObstacles(obstacleGrid, x + 1, y, cache) + uniquePathsWithObstacles(obstacleGrid, x, y + 1, cache);

      cache.put(sig, result);
      return result;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().uniquePathsWithObstacles(new int[][] {
                                                                             {0, 0, 0, 0, 0},
                                                                             {0, 0, 0, 0, 1},
                                                                             {0, 0, 0, 1, 0},
                                                                             {0, 0, 0, 0, 0},
    }));
  }

}
