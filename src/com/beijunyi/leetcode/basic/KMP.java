package com.beijunyi.leetcode.basic;

/**
 * https://tekmarathon.com/2013/05/14/algorithm-to-find-substring-in-a-string-kmp-algorithm/
 */
public class KMP {

  /**
   * Pre processes the pattern array based on proper prefixes and proper
   * suffixes at every position of the array
   *
   * @param str
   *            word that is to be searched in the search string
   * @return partial match table which indicates
   */
  public int[] preProcessPattern(char[] str) {
    int i = 0, j = -1;
    int[] b = new int[str.length + 1];

    b[i] = j;
    while (i < str.length) {
      while (j >= 0 && str[i] != str[j]) {
        // if there is mismatch consider the next widest border
        // The borders to be examined are obtained in decreasing order from
        //  the values b[i], b[b[i]] etc.
        j = b[j];
      }
      i++;
      j++;
      b[i] = j;
    }
    return b;
  }

}
