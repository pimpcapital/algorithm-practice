package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.DepthFirstSearch;
import com.beijunyi.leetcode.category.solution.Swapping;

/**
 * Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no
 * palindromic permutation could be form.
 *
 * For example:
 *   Given s = "aabb", return ["abba", "baab"].
 *   Given s = "abc", return [].
 *
 * Hint:
 *   1) If a palindromic permutation exists, we just need to generate the first half of the string.
 *   2) To generate all distinct permutations of a (half of) string, use a similar approach from: Permutations II or
 *      Next Permutation.
 */
public class _267_PalindromePermutationTwo implements Medium, PremiumQuestion {

  public interface Solution {
    List<String> generatePalindromes(String s);
  }

  public static class Solution1 implements Solution, DepthFirstSearch {

    private List<Character> candidates;
    private String center;

    @Override
    public List<String> generatePalindromes(String s) {
      List<String> ret = new ArrayList<>();
      if(s.length() == 0) return ret;
      if(s.length() == 1) {
        ret.add(s);
        return ret;
      }

      if(!prepareCandidates(s)) return ret;
      generatePalindromes(new boolean[candidates.size()], new char[candidates.size()], 0, ret);
      return ret;
    }

    private boolean prepareCandidates(String s) {
      int[] counts = new int[256];
      for(int i = 0; i < s.length(); i++) counts[(int) s.charAt(i)]++;
      candidates = new ArrayList<>();
      center = "";
      for(int i = 0; i < counts.length; i++) {
        int count = counts[i];
        while(count > 1) {
          candidates.add((char) i);
          count -= 2;
        }
        if(count == 1) {
          if(!center.isEmpty()) return false;
          else center += (char) i;
        }
      }

      return true;
    }

    private void generatePalindromes(boolean[] used, char[] buffer, int offset, List<String> result) {
      if(offset == buffer.length) {
        String pal = String.valueOf(buffer) + center + new StringBuilder().append(buffer).reverse();
        result.add(pal);
        return;
      }
      Character prev = null;
      for(int i = 0; i < candidates.size(); i++) {
        if(used[i]) continue;
        char c = candidates.get(i);
        if(prev != null && prev == c) continue;
        buffer[offset] = c;
        used[i] = true;
        generatePalindromes(used, buffer, offset + 1, result);
        used[i] = false;
        prev = c;
      }
    }
  }

  public static class Solution2 implements Solution, Swapping {
    private List<String> results;
    private char[] chars;
    private int lengthMinusOne, halfLength;

    @Override
    public List<String> generatePalindromes(String s) {
      results = new ArrayList<>();
      chars = s.toCharArray();
      int length = chars.length;
      if(length == 0) return results;

      lengthMinusOne = length - 1;
      halfLength = length / 2;

      if(!prepareFirstHalf()) return results;
      generate(0);
      return results;
    }

    // Prepare the first half of the string. It will contain
    // a single character from each pair. The middle character
    // is placed in the middle.
    // When done, the array will look like abdc***
    private boolean prepareFirstHalf() {
      Arrays.sort(chars); // The array now looks like aabbcdd
      boolean foundMiddle = false;
      for(int readCursor = 0, writeCursor = 0; readCursor < chars.length; readCursor++) {
        char c = chars[readCursor];
        if(readCursor < lengthMinusOne && c == chars[readCursor + 1]) {
          chars[writeCursor++] = c;
          readCursor++;
        } else {
          if(!foundMiddle && (chars.length & 1) == 1) {
            foundMiddle = true;
            chars[chars.length / 2] = c;
          } else {
            return false;
          }
        }
      }
      return true;
    }

    // Generates permutations by swapping characters.
    private void generate(int start) {
      if(start == halfLength) {
        results.add(new String(chars));
        return;
      }
      char prev = 0;
      for(int i = start; i < halfLength; i++) {
        if(prev == chars[i]) continue;
        prev = chars[i];
        swap(start, i);
        generate(start + 1);
        swap(start, i);
      }
    }

    // Swap maintains the palindrome by mirroring chars on the right side.
    void swap(int a, int b) {
      char temp = chars[a];
      chars[a] = chars[b];
      chars[lengthMinusOne - a] = chars[b];
      chars[b] = temp;
      chars[lengthMinusOne - b] = temp;
    }

  }

  public static void main(String args[]) {
    String str;
    List<String> result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      str = "aabb";
      result = s.generatePalindromes(str);
      System.out.println(result);

      str = "aaccccbbd";
      result = s.generatePalindromes(str);
      System.out.println(result);

      str = "aaaaaa";
      result = s.generatePalindromes(str);
      System.out.println(result);
    }
  }

}
