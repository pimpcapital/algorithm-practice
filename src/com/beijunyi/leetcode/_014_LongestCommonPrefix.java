package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.solution.BinarySearch;
import com.beijunyi.leetcode.category.solution.DivideAndConquer;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 */
public class _014_LongestCommonPrefix implements Easy {

  public interface Solution {
    String longestCommonPrefix(String[] strs);
  }

  public static class Solution1 implements Solution {

    @Override
    public String longestCommonPrefix(String[] strs) {
      String candidate = findShortestString(strs);
      if(candidate == null)
        return "";
      int max = candidate.length();
      for(String str : strs) {
        int count = countMatchedStart(str, candidate, max);
        max = Math.min(count, max);
      }
      return candidate.substring(0, max);
    }

    private static String findShortestString(String[] strs) {
      String ret = null;
      for(String str : strs) {
        if(ret == null || ret.length() > str.length())
          ret = str;
      }
      return ret;
    }

    private static int countMatchedStart(String str, String candidate, int max) {
      max = Math.min(max, str.length());
      max = Math.min(max, candidate.length());
      for(int i = 0; i < max; i++) {
        if(str.charAt(i) != candidate.charAt(i))
          return i;
      }
      return max;
    }
  }

  /**
   * In the worst case we have nn equal strings with length mm
   *
   * Time complexity:
   *  O(S), where S is the number of all characters in the array, S=m*n Time complexity is T(n)=2T(n/2)+O(m). Therefore
   *  time complexity is O(S). In the best case this algorithm performs O(minLen*n) comparisons, where minLen is the
   *  shortest string of the array
   *
   * Space complexity : O(m*log(n))
   * There is a memory overhead since we store recursive calls in the execution stack. There are log(n) recursive calls,
   * each store need mm space to store the result, so space complexity is O(m*log(n))
   */
  public static class Solution2 implements Solution, DivideAndConquer {
    @Override
    public String longestCommonPrefix(String[] strs) {
      if (strs == null || strs.length == 0) return "";
      return longestCommonPrefix(strs, 0 , strs.length - 1);
    }

    private String longestCommonPrefix(String[] strs, int l, int r) {
      if (l == r) {
        return strs[l];
      }
      else {
        int mid = (l + r)/2;
        String lcpLeft =   longestCommonPrefix(strs, l , mid);
        String lcpRight =  longestCommonPrefix(strs, mid + 1,r);
        return commonPrefix(lcpLeft, lcpRight);
      }
    }

    String commonPrefix(String left,String right) {
      int min = Math.min(left.length(), right.length());
      for (int i = 0; i < min; i++) {
        if ( left.charAt(i) != right.charAt(i) )
          return left.substring(0, i);
      }
      return left.substring(0, min);
    }
  }

  /**
   * In the worst case we have nn equal strings with length mm
   *
   * Time complexity : O(S*log(n)), where SS is the sum of all characters in all strings.
   *  The algorithm makes log(n) iterations, for each of them there are S=m*n comparisons, which gives in total
   *  O(S*log(n)) time complexity.
   *
   * Space complexity : O(1).
   */
  public static class Solution3 implements Solution, BinarySearch {

    @Override
    public String longestCommonPrefix(String[] strs) {
      if (strs == null || strs.length == 0)
        return "";
      int minLen = Integer.MAX_VALUE;
      for (String str : strs)
        minLen = Math.min(minLen, str.length());
      int low = 1;
      int high = minLen;
      while (low <= high) {
        int middle = (low + high) / 2;
        if (isCommonPrefix(strs, middle))
          low = middle + 1;
        else
          high = middle - 1;
      }
      return strs[0].substring(0, (low + high) / 2);
    }

    private boolean isCommonPrefix(String[] strs, int len){
      String str1 = strs[0].substring(0,len);
      for (int i = 1; i < strs.length; i++)
        if (!strs[i].startsWith(str1))
          return false;
      return true;
    }

  }

  public static void main(String args[]) {
    String strs[];
    String result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      strs = new String[] {"leetcode", "leetc", "leeds"};
      result = s.longestCommonPrefix(strs);
      System.out.println(result);
    }
  }

}
