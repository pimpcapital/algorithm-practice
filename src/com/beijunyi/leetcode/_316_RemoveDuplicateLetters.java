package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.solution.GreedyAlgorithm;
import com.beijunyi.leetcode.category.solution.Iterative;
import com.beijunyi.leetcode.category.solution.Recursive;

/**
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and
 * only once. You must make sure your result is the smallest in lexicographical order among all possible results.
 *
 * Example:
 *  Given "bcabc"
 *  Return "abc"
 *
 *  Given "cbacdcbc"
 *  Return "acdb"
 */
public class _316_RemoveDuplicateLetters implements Hard {

  public interface Solution {
    String removeDuplicateLetters(String s);
  }

  /**
   * Time: O(n)
   * Space: O(n)
   */
  public static class Solution1 implements Solution, GreedyAlgorithm, Recursive {

    @Override
    public String removeDuplicateLetters(String s) {
      if(s.length() == 0) return s;

      int[] count = new int[26];

      for(int i = 0; i < s.length(); i++)
        count[s.charAt(i) - 'a']++;

      int index = 0; // the index of the smallest char
      for(int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if(c < s.charAt(index))
          index = i;
        if(--count[c - 'a'] == 0) // must stop here. this is the last instance of this character
          break;
      }

      char head = s.charAt(index);
      String tail = s.substring(index + 1);
      tail = tail.replace(Character.toString(head), "");

      return head + removeDuplicateLetters(tail);
    }

  }

  /**
   * Time: O(n)
   * Space: O(1)
   */
  public static class Solution2 implements Solution, GreedyAlgorithm, Iterative {

    @Override
    public String removeDuplicateLetters(String s) {
      int[] lastIdxs = findLastIndicesOfEveryChar(s);
      StringBuilder result = new StringBuilder();

      int offset = 0;
      int checkpoint;
      while((checkpoint = findNextCheckpoint(lastIdxs)) != -1) {
        int idx = findSmallestCharUpToCheckPoint(s, offset, checkpoint, lastIdxs);
        char c = s.charAt(idx);
        lastIdxs[c - 'a'] = -1; // mark it with -1, so we can skip it later
        offset = idx + 1;
        result.append(c);
      }


      return result.toString();
    }

    // find out when did every character last appear in the string
    private static int[] findLastIndicesOfEveryChar(String s) {
      int[] lastIdxs = new int[26];
      Arrays.fill(lastIdxs, -1);
      for(int i = 0; i < s.length(); i++) {
        int charVal = s.charAt(i) - 'a';
        lastIdxs[charVal] = i;
      }
      return lastIdxs;
    }

    // find a safe range within which we can pick whatever we want as the next character without losing the ability to
    // have all characters
    private static int findNextCheckpoint(int[] lastIdxs) {
      int ret = -1;
      for(int i : lastIdxs)
        if(i != -1 && (ret == -1 || i < ret)) ret = i;
      return ret;
    }

    // be greedy and find the smallest character before the next checkpoint
    private static int findSmallestCharUpToCheckPoint(String s, int offset, int checkpoint, int[] lastIdxs) {
      int ret = -1;
      for(int i = offset; i <= checkpoint; i++) {
        char c = s.charAt(i);
        if(lastIdxs[c - 'a'] == -1) continue; // if we already had this char in the result, skip it
        if(ret == -1 || c < s.charAt(ret)) ret = i;
      }
      return ret;
    }

  }


  public static void main(String args[]) {
    String str;
    String result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      str = "bcabc";
      result = s.removeDuplicateLetters(str);
      System.out.println(result);

      str = "cbacdcbc";
      result = s.removeDuplicateLetters(str);
      System.out.println(result);

      str = "baab";
      result = s.removeDuplicateLetters(str);
      System.out.println(result);
    }
  }

}
