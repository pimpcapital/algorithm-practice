package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.difficulty.Medium;

public class Anagrams implements Medium {

  public static class Solution {
    public List<String> anagrams(String[] strs) {
      Map<String, List<String>> groups = new HashMap<>();

      for(String str : strs) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        String sig = new String(chars);

        List<String> group = groups.get(sig);
        if(group == null) {
          group = new ArrayList<>();
          groups.put(sig, group);
        }
        group.add(str);
      }

      List<String> ret = new ArrayList<>();
      for(Map.Entry<String, List<String>> entry : groups.entrySet()) {
        if(entry.getValue().size() == 1)
          continue;
        ret.addAll(entry.getValue());
      }

      return ret;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().anagrams(new String[] {"and", "dan"}));
  }
}
