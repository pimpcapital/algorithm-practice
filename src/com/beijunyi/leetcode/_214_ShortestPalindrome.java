package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.difficulty.ReallyHard;
import com.beijunyi.leetcode.category.solution.KMP;

public class _214_ShortestPalindrome implements Hard, ReallyHard {

  public interface Solution {
    String shortestPalindrome(String s);
  }

  /**
   * It's only O(n^2).
   *
   * Consider the input aabababababababababababababa. That leads to recursive calls with these strings:
   *
   * aabababababababababababababa
   * aababababababababababababa
   * aabababababababababababa
   * aababababababababababa
   * aabababababababababa
   * aababababababababa
   * aabababababababa
   * aababababababa
   * aabababababa
   * aababababa
   * aabababa
   * aababa
   * aaba
   * aa
   */
  public static class Solution1 implements Solution {
    @Override
    public String shortestPalindrome(String s) {
      int left = 0;
      for(int right = s.length() - 1; right >= 0; right--) {
        if(s.charAt(right) == s.charAt(left)) left++;
      }
      if(left == s.length()) return s;
      String suffix = s.substring(left);
      return new StringBuffer(suffix).reverse().toString() + shortestPalindrome(s.substring(0, left)) + suffix;
    }
  }

  public static class Solution2 implements Solution, KMP {

    @Override
    public String shortestPalindrome(String s) {
      String r = new StringBuilder(s).reverse().toString();
      int[] lps = getPartialMatchingTable(s + '|' + r);
      return r.substring(0, r.length() - lps[lps.length - 1]) + s;
    }

    // KMP get longest prefix and suffix count
    private static int[] getPartialMatchingTable(String s) {
      int[] lps = new int[s.length()];
      int i = 1;
      int len = 0;

      while(i < s.length()) {
        if(s.charAt(i) == s.charAt(len))
          lps[i++] = ++len;
        else if(len == 0)
          lps[i++] = 0;
        else if(s.charAt(i) != s.charAt(0))
          len = 0;
        else
          len = lps[len - 1]; // ?
      }

      return lps;
    }
  }

  public static class Solution3 implements Solution, KMP {
    @Override
    public String shortestPalindrome(String s) {
      if(s==null||s.length()==0){
        return s;
      }
      char[] arr = s.toCharArray();
      StringBuilder sb = new StringBuilder();
      int cent = getCentralPoint(arr);
      for(int i = arr.length-1;i>=cent;i--){
        sb.append(arr[i]);
      }
      sb.append(s);
      return sb.toString();
    }

    private int getCentralPoint(char[] arr){
      int mid = (arr.length-1)/2;
      int i = mid;
      int j = mid;
      while(i>=0){
        i = mid;
        j = mid;
        while(i>=0&&arr[i]==arr[mid]){
          i--;
        }
        while(j<arr.length&&arr[j]==arr[mid]){
          j++;
        }
        int cp = centralPoint(arr,i,j);
        if(cp!=-1){
          return cp;
        }
        mid = i;
      }
      return 0;
    }

    private int centralPoint(char[] arr,int i,int j){
      while(i>=0&&j<arr.length){
        if(arr[i]!=arr[j]){
          return -1;
        }
        i--;
        j++;
      }
      return i!=-1?-1:j;
    }
  }

  public static void main(String args[]) {
    String str;
    String result;

    for(Solution s : Arrays.asList(new Solution2())) {
      str = "aacecaaa";
      result = s.shortestPalindrome(str);
      System.out.println(result);

      str = "abcd";
      result = s.shortestPalindrome(str);
      System.out.println(result);

      str = "abbabaab";
      result = s.shortestPalindrome(str);
      System.out.println(result); // baababbabaab

      str = "ababbbabbaba";
      result = s.shortestPalindrome(str);
      System.out.println(result); // ababbabbbababbbabbaba
    }
  }

}
