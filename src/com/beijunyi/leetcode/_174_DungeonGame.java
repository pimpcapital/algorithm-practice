package com.beijunyi.leetcode;

import com.beijunyi.leetcode.category.difficulty.Hard;

/**
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid.
 * Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
 *
 * The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
 *
 * Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms;
 * other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 *
 * In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 *
 * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 *
 * For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
 *
 *  -2 (K) 	-3      3
 *  -5     -10      1
 *  10      30     -5 (P)
 *
 * Notes:
 *
 * The knight's health has no upper bound.
 * Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 * Credits:
 */
public class _174_DungeonGame implements Hard {

  public static class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
      int r = dungeon.length;
      int c = r == 0 ? 0 : dungeon[0].length;
      if(r == 0 || c == 0)
        return 0;
      for(int i = r - 1; i >= 0; i--) {
        for(int j = c - 1; j >= 0; j--) {
          if(i < r - 1 && j < c - 1)
            dungeon[i][j] = Math.max(0, Math.min(dungeon[i][j + 1], dungeon[i + 1][j]) - dungeon[i][j]);
          else if(j < c - 1)
            dungeon[i][j] = Math.max(0, dungeon[i][j + 1] - dungeon[i][j]);
          else if(i < r - 1)
            dungeon[i][j] = Math.max(0, dungeon[i + 1][j] - dungeon[i][j]);
          else
            dungeon[i][j] = Math.max(0, -dungeon[i][j]);
        }
      }
      return dungeon[0][0] + 1;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().calculateMinimumHP(new int[][]{
                                                                      {-2, -3, 3},
                                                                      {-5, -10, 1},
                                                                      {10, 30, -5}
    }));
  }

}
