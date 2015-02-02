package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.difficulty.Medium;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.
 * For "bbbbb" the longest substring is "b", with the length of 1.
 */
public class LongestSubstringWithoutRepeatingCharacters implements Medium {

  /**
   * Solution (DP, O(n)):
   *
   * Assume L[i] = s[m...i], denotes the longest substring without repeating
   * characters that ends up at s[i], and we keep a hashmap for every
   * characters between m ... i, while storing <character, index> in the
   * hashmap.
   *
   * We know that each character will appear only once.
   * Then to find s[i+1]:
   *
   * 1) if s[i+1] does not appear in hashmap
   *    we can just add s[i+1] to hash map. and L[i+1] = s[m...i+1]
   * 2) if s[i+1] exists in hashmap, and the hashmap value (the index) is k
   *    let m = max(m, k), then L[i+1] = s[m...i+1], we also need to update
   *    entry in hashmap to mark the latest occurency of s[i+1].
   *
   * Since we scan the string for only once, and the 'm' will also move from
   * beginning to end for at most once. Overall complexity is O(n).
   *
   * If characters are all in ASCII, we could use array to mimic hashmap.
   */
  public static class Solution {
    public int lengthOfLongestSubstring(String s) {
      // for ASCII char sequence, use this as a hashmap
      int[] charIndex = new int[256];
      Arrays.fill(charIndex, -1);
      int longest = 0, m = 0;
      for (int i = 0; i < s.length(); i++) {
        m = Math.max(charIndex[s.charAt(i)] + 1, m);    // automatically takes care of -1 case
        charIndex[s.charAt(i)] = i;
        longest = Math.max(longest, i - m + 1);
      }
      return longest;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().lengthOfLongestSubstring("abcabcbb"));
  }
}
