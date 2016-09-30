package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.DepthFirstSearch;
import com.beijunyi.leetcode.category.solution.Memoization;

public class _294_FlipGameTwo implements Medium, PremiumQuestion {

  public interface Solution {
    boolean canWin(String s);
  }

  public static class Solution1 implements Solution, Memoization {
    @Override
    public boolean canWin(String s) {
      Map<BitSet, Boolean> cache = new HashMap<>();
      return canWin(toBits(s), cache);
    }

    private static boolean canWin(BitSet n, Map<BitSet, Boolean> cache) {
      if(cache.containsKey(n)) return cache.get(n);
      boolean result = false;
      for(BitSet m : possibleMoves(n)) {
        boolean opponentCanWin = canWin(m, cache);
        if(!opponentCanWin) {
          result = true;
          break;
        }
      }
      cache.put(n, result);
      return result;
    }

    private static List<BitSet> possibleMoves(BitSet n) {
      List<BitSet> ret = new ArrayList<>();
      for(int i = 1; i < n.length(); i++) {
        if(n.get(i) && n.get(i - 1)) {
          BitSet move = n.get(0, n.length());
          move.set(i, false);
          move.set(i - 1, false);
          ret.add(move);
        }
      }
      return ret;
    }

    private static BitSet toBits(String s) {
      BitSet ret = new BitSet(s.length());
      for(int i = 0; i < s.length(); i++) {
        if(s.charAt(i) == '+') ret.set(i);
      }
      return ret;
    }
  }

  public static class Solution2 implements Solution, DepthFirstSearch {
    @Override
    public boolean canWin(String s) {
      for(int i = 0; i < s.length() - 1; ++i)
        if(s.charAt(i) == '+' && s.charAt(i + 1) == '+' && !canWin(s.substring(0, i) + "--" + s.substring(i + 2)))
          return true;
      return false;
    }
  }

  public static void main(String args[]) {
    String str;
    boolean result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      str = "";
      result = s.canWin(str);
      System.out.println(result);

      str = "+";
      result = s.canWin(str);
      System.out.println(result);

      str = "++";
      result = s.canWin(str);
      System.out.println(result);

      str = "++++";
      result = s.canWin(str);
      System.out.println(result);

      str = "+++++";
      result = s.canWin(str);
      System.out.println(result);

      System.out.println();
    }
  }

}
