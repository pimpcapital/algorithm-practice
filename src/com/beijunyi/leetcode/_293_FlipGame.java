package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters:
 * + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no
 * longer make a move and therefore the other person will be the winner.
 *
 * Write a function to compute all possible states of the string after one valid move.
 *
 * For example, given s = "++++", after one move, it may become one of the following states:
 *   [
 *     "--++",
 *     "+--+",
 *     "++--"
 *   ]
 * If there is no valid move, return an empty list [].
 */
public class _293_FlipGame implements Easy, PremiumQuestion{

  public interface Solution {
    List<String> generatePossibleNextMoves(String s);
  }

  public static class Solution1 implements Solution {

    public List<String> generatePossibleNextMoves(String s) {
      List<String> ret = new ArrayList<>();
      char[] chars = s.toCharArray();
      for(int i = 1; i < chars.length; i++) {
        if(chars[i] == chars[i - 1] && chars[i] == '+') {
          chars[i] =  '-';
          chars[i - 1] =  '-';
          ret.add(new String(chars));
          chars[i] =  '+';
          chars[i - 1] =  '+';
        }
      }
      return ret;
    }

  }

  public static void main(String args[]) {
    String str;
    List<String> result;

    for(Solution s : Arrays.asList(new Solution1())) {
      str = "++++";
      result = s.generatePossibleNextMoves(str);
      System.out.println(result);
    }

  }

}
