package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given a digit string, return all possible letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 *
 *  [1 o_o ] [2 abc ] [3 def ]
 *  [4 ghi ] [5 jkl ] [6 mno ]
 *  [7 pqrs] [8 tuv ] [6 wxyz]
 *  [* +   ] [ 0 _  ] [# ^   ]
 *
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 */
public class _017_LetterCombinationsOfAPhoneNumber implements Medium {

  public interface Solution {
    List<String> letterCombinations(String digits);
  }

  public static class Solution1 implements Solution {

    @Override
    public List<String> letterCombinations(String digits) {
      String[] matrix = makeMatrix();
      List<String> ret = new ArrayList<>();
      if(digits.isEmpty()) return ret;
      ret.add("");
      for(int i = 0; i < digits.length(); i++) {
        int digit = digits.charAt(i) - '0';
        String candidates = matrix[digit];
        List<String> newRet = new ArrayList<>();
        for(String str : ret) {
          for(int j = 0; j < candidates.length(); j++) {
            char c = candidates.charAt(j);
            newRet.add(str + c);
          }
        }
        ret = newRet;
      }
      return ret;
    }

    private static String[] makeMatrix() {
      return new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    }

  }

  public static class Solution2 implements Solution {

    private static final String[] KEY_PAD
      = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    @Override
    public List<String> letterCombinations(String digits) {
      List<String> result = new ArrayList<>();
      if(digits.length() > 0) {
        char[] current = new char[digits.length()];
        buildCombinations(digits.toCharArray(), 0, current, result);
      }
      return result;
    }

    private void buildCombinations(char[] digits, int offset, char[] current, List<String> result) {
      if(offset == digits.length) {
        result.add(new String(current));
        return;
      }
      int currentDigit = digits[offset] - '0';
      for(char candidate : KEY_PAD[currentDigit].toCharArray()) {
        current[offset] = candidate;
        buildCombinations(digits, offset + 1, current, result);
      }
    }
  }

  public static void main(String args[]) {
    String digits;
    List<String> result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      digits = "";
      result = s.letterCombinations(digits);
      System.out.println(result);

      digits = "23";
      result = s.letterCombinations(digits);
      System.out.println(result);
    }

  }

}
