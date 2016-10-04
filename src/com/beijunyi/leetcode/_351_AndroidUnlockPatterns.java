package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock
 * patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.
 *
 * Rules for a valid pattern:
 *   1) Each pattern must connect at least m keys and at most n keys.
 *   2) All the keys must be distinct.
 *   3) If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must
 *      have previously selected in the pattern. No jumps through non selected key is allowed.
 *   4) The order of keys used matters.
 *
 * Explanation:
 *   | 1 | 2 | 3 |
 *   | 4 | 5 | 6 |
 *   | 7 | 8 | 9 |
 *   Invalid move: 4 - 1 - 3 - 6
 *   Line 1 - 3 passes through key 2 which had not been selected in the pattern.
 *
 *   Invalid move: 4 - 1 - 9 - 2
 *   Line 1 - 9 passes through key 5 which had not been selected in the pattern.
 *
 *   Valid move: 2 - 4 - 1 - 3 - 6
 *   Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern
 *
 *   Valid move: 6 - 5 - 4 - 1 - 9 - 2
 *   Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.
 *
 *   Example:
 *   Given m = 1, n = 1, return 9.
 */
public class _351_AndroidUnlockPatterns implements Medium, PremiumQuestion {

  public interface Solution {
    int numberOfPatterns(int m, int n);
  }

  public static class Solution1 implements Solution {

    @Override
    public int numberOfPatterns(int m, int n) {
      if(m < 1 || n > 9) return 0;
      return numberOfPatterns(m, n, 1, new LinkedHashSet<>()) * 4
               + numberOfPatterns(m, n, 2, new LinkedHashSet<>()) * 4
               + numberOfPatterns(m, n, 5, new LinkedHashSet<>());
    }

    private static int numberOfPatterns(int m, int n, int current, Set<Integer> used) {
      if(n == 0) return 0;
      used.add(current);
      if(m > 0) m--;
      n--;
      int count = 0;
      if(m == 0) count++;
      for(int move : possibleMoves(current, used)) {
        count += numberOfPatterns(m, n, move, used);
      }
      used.remove(current);
      return count;
    }

    private static Collection<Integer> possibleMoves(int from, Set<Integer> used) {
      Collection<Integer> ret = new ArrayList<>();
      for(int to = 1; to <= 9; to++) {
        if(isPossibleMove(from, to, used)) {
          ret.add(to);
        }
      }
      return ret;
    }

    private static boolean isPossibleMove(int from, int to, Set<Integer> used) {
      if(used.contains(to)) return false;
      if(from == to) return false;
      int rowDis = Math.abs((from - 1) / 3 - (to - 1) / 3);
      int colDis = Math.abs((from - 1) % 3 - (to - 1) % 3);
      return rowDis == 1 || colDis == 1 || used.contains((from + to) / 2);
    }
  }

  public static class Solution2 implements Solution {

    private static final int[][] SKIP_MAP = makeSkipMap();

    @Override
    public int numberOfPatterns(int m, int n) {
      int ret = 0;
      // dfs search each length from m to n
      for(int i = m; i <= n; ++i) {
        ret += dfs(new boolean[10], 1, i - 1) * 4;    // 1, 3, 7, 9 are symmetric
        ret += dfs(new boolean[10], 2, i - 1) * 4;    // 2, 4, 6, 8 are symmetric
        ret += dfs(new boolean[10], 5, i - 1);        // 5
      }
      return ret;
    }

    // Skip array represents number to skip between two pairs
    private static int[][] makeSkipMap() {
      int ret[][] = new int[10][10];
      ret[1][3] = ret[3][1] = 2;
      ret[1][7] = ret[7][1] = 4;
      ret[3][9] = ret[9][3] = 6;
      ret[7][9] = ret[9][7] = 8;
      ret[1][9] = ret[9][1] = ret[2][8] = ret[8][2] = ret[3][7] = ret[7][3] = ret[4][6] = ret[6][4] = 5;
      return ret;
    }

    private static int dfs(boolean visited[], int from, int remain) {
      if(remain < 0) return 0;
      if(remain == 0) return 1;
      visited[from] = true;
      int ret = 0;
      for(int i = 1; i <= 9; ++i) {
        // If visited[i] is not visited and (two numbers are adjacent or skip number is already visited)
        if(!visited[i] && (SKIP_MAP[from][i] == 0 || (visited[SKIP_MAP[from][i]]))) {
          ret += dfs(visited, i, remain - 1);
        }
      }
      visited[from] = false;
      return ret;
    }
  }

  public static void main(String args[]) {
    int m;
    int n;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      m = 1;
      n = 1;
      result = s.numberOfPatterns(m, n);
      System.out.println(result);

      m = 1;
      n = 2;
      result = s.numberOfPatterns(m, n);
      System.out.println(result);

      m = 3;
      n = 3;
      result = s.numberOfPatterns(m, n);
      System.out.println(result);

      m = 1;
      n = 3;
      result = s.numberOfPatterns(m, n);
      System.out.println(result);

      System.out.println();
    }
  }

}
