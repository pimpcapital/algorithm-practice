package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Easy;

/**
 * Compare two version numbers version1 and version1.
 * If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
 *
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.
 *
 * Here is an example of version numbers ordering:
 *
 * 0.1 < 1.1 < 1.2 < 13.37
 */
public class _165_CompareVersionNumbers implements Easy {

  public static class Solution {
    public int compareVersion(String version1, String version2) {
      String[] versionSplit1 = version1.split("\\.");
      String[] versionSplit2 = version2.split("\\.");
      int max = Math.max(versionSplit1.length, versionSplit2.length);
      for(int i = 0; i < max; i++) {
        int v1 = i < versionSplit1.length ? Integer.valueOf(versionSplit1[i]) : 0;
        int v2 = i < versionSplit2.length ? Integer.valueOf(versionSplit2[i]) : 0;
        int compare = Integer.compare(v1, v2);
        if(compare != 0)
          return compare;
      }
      return 0;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().compareVersion("1.0", "1"));
  }
}
