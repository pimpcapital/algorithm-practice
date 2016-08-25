package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given a string, determine if a permutation of the string could form a palindrome.
 *
 * For example,
 * "code" -> False, "aab" -> True, "carerac" -> True.
 *
 * Hint:
 *   1) Consider the palindromes of odd vs even length. What difference do you notice?
 *   2) Count the frequency of each character.
 *   3) If each character occurs even number of times, then it must be a palindrome. How about character which occurs
 *      odd number of times?
 */
public class _266_PalindromePermutation implements Easy, PremiumQuestion {

  public interface Solution {
    boolean canPermutePalindrome(String s);
  }

  public static class Solution1 implements Solution {
    @Override
    public boolean canPermutePalindrome(String s) {
      if(s.length() < 2) return true;
      int[] counts = new int[256];
      for(int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        counts[(int)c]++;
      }
      boolean oddSeen = false;
      for(int i = 0; i < 256; i++) {
        if(counts[i] % 2 == 1) {
          if(oddSeen) return false;
          oddSeen = true;
        }
      }
      return true;
    }
  }

  public static class Solution2 implements Solution {
    @Override
    public boolean canPermutePalindrome(String s) {
      Set<Character> set = new HashSet<>();
      for(int i = 0; i < s.length(); ++i)
        if(!set.add(s.charAt(i))) set.remove(s.charAt(i));
      return set.size() <= 1;
    }
  }

  public static void main(String args[]) {
    String str;
    boolean result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())){
      str = "aab";
      result = s.canPermutePalindrome(str);
      System.out.println(result);

      str = "carerac";
      result = s.canPermutePalindrome(str);
      System.out.println(result);

      System.out.println();
    }
  }

}
