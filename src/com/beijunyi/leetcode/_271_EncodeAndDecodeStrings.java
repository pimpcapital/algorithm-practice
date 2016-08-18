package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is
 * decoded back to the original list of strings.
 *
 * Machine 1 (sender) has the function:
 *   String encode(List<String> strs) {
 *     // ... your code
 *     return encodedString;
 *   }
 *
 * Machine 2 (receiver) has the function:
 *   List<String> decode(String s) {
 *     //... your code
 *     return strs;
 *   }
 *
 * So Machine 1 does:
 *   String encodedString = encode(strs);
 * and Machine 2 does:
 *   List<String> strs2 = decode(encodedString);
 * strs2 in Machine 2 should be the same as strs in Machine 1.
 *
 * Implement the encode and decode methods.
 *
 * Note:
 *   1) The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be
 *      generalized enough to work on any possible characters.
 *   2) Do not use class member/global/static variables to store states. Your encode and decode algorithms should be
 *      stateless.
 *   3) Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode
 *      algorithm.
 */
public class _271_EncodeAndDecodeStrings implements Medium, PremiumQuestion {

  public interface Solution {
    // Encodes a list of strings to a single string.
    String encode(List<String> strs);

    // Decodes a single string to a list of strings.
    List<String> decode(String s);
  }

  public static class Solution1 implements Solution {

    @Override
    public String encode(List<String> strs) {
      StringBuilder sb = new StringBuilder();
      for(String str : strs) {
        for(int i = 0; i < str.length(); i++) {
          char c = str.charAt(i);
          switch(c) {
            case '\n':
            case '\\':
              sb.append('\\');
            default:
              sb.append(c);
          }
        }
        sb.append("\n");
      }
      return sb.toString();
    }

    @Override
    public List<String> decode(String s) {
      List<String> ret = new ArrayList<>();
      boolean escaping = false;
      StringBuilder sb = new StringBuilder();
      for(int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if(escaping) {
          sb.append(c);
          escaping = false;
        } else {
          switch(c) {
            case '\\':
              escaping = true;
              break;
            case '\n':
              ret.add(sb.toString());
              sb = new StringBuilder();
              break;
            default:
              sb.append(c);
          }
        }
      }
      return ret;
    }
  }

  public static class Solution2 implements Solution {
    @Override
    public String encode(List<String> strs) {
      StringBuilder sb = new StringBuilder();
      for(String s : strs) {
        sb.append(s.length()).append('|').append(s);
      }
      return sb.toString();
    }

    @Override
    public List<String> decode(String s) {
      List<String> ret = new ArrayList<>();
      int i = 0;
      while(i < s.length()) {
        int headerEnds = s.indexOf('|', i);
        int size = Integer.valueOf(s.substring(i, headerEnds));
        ret.add(s.substring(headerEnds + 1, headerEnds + size + 1));
        i = headerEnds + size + 1;
      }
      return ret;
    }
  }

  public static void main(String args[]) {
    List<String> strs;
    String encoded;
    List<String> decoded;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      strs = Arrays.asList("this", "is", "some", "test\nstrings");
      encoded = s.encode(strs);
      System.out.println(encoded);
      decoded = s.decode(encoded);
      System.out.println(decoded);
    }
  }

}
