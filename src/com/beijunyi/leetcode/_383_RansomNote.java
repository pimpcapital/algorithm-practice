package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.difficulty.ReallyEasy;

/**
 * Given  an  arbitrary  ransom  note  string  and  another  string  containing  letters from  all  the  magazines,  write  a  function
 * that  will  return  true  if  the  ransom   note  can  be  constructed  from  the  magazines ;  otherwise,  it  will  return  false.   
 *
 * Each  letter  in  the  magazine  string  can  only  be  used  once  in  your  ransom  note.
 *
 * Note:
 *   You may assume that both strings contain only lowercase letters.
 *
 *   canConstruct("a", "b") -> false
 *   canConstruct("aa", "ab") -> false
 *   canConstruct("aa", "aab") -> true
 */
public class _383_RansomNote implements Easy, ReallyEasy {

  public interface Solution {
    boolean canConstruct(String ransomNote, String magazine);
  }

  public static class Solution1 implements Solution {
    @Override
    public boolean canConstruct(String ransomNote, String magazine) {
      int[] count = new int[26];
      for(char c : magazine.toCharArray()) count[c - 'a']++;
      for(char c : ransomNote.toCharArray()) if(--count[c - 'a'] < 0) return false;
      return true;
    }
  }

  public static void main(String args[]) {
    String ransomNote;
    String magazine;
    boolean result;

    for(Solution s : Arrays.asList(new Solution1())) {
      ransomNote = "aa";
      magazine = "ab";
      result = s.canConstruct(ransomNote, magazine);
      System.out.println(result);

      ransomNote = "aa";
      magazine = "aab";
      result = s.canConstruct(ransomNote, magazine);
      System.out.println(result);
    }
  }

}
