package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.beijunyi.leetcode.category.difficulty.Hard;

/**
 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.
 *
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 *
 * For example,
 * words: ["This", "is", "an", "example", "of", "text", "justification."]
 * L: 16.
 *
 * Return the formatted lines as:
 * [
 *   "This    is    an",
 *   "example  of text",
 *   "justification.  "
 * ]
 * Note: Each word is guaranteed not to exceed L in length.X
 *
 * Corner Cases:
 * A line other than the last line might contain only one word. What should you do in this case?
 * In this case, that line should be left-justified.
 */
public class _068_TextJustification implements Hard {

  public static class Solution2 {
    public List<String> fullJustify(String[] words, int L) {
      List<String> result = new ArrayList<>();
      int lineLetters = 0;
      int lineSpaces = 0;
      List<String> currentLine = new ArrayList<>();
      for(String word : words) {
        int predictedLength = lineLetters + lineSpaces + word.length();
        if(lineLetters != 0)
          predictedLength++;
        if(predictedLength > L) {
          result.add(generateLine(lineLetters, currentLine, L, false));
          currentLine.clear();
          lineLetters = 0;
          lineSpaces = 0;
        }
        currentLine.add(word);
        boolean firstWord = lineLetters == 0;
        lineLetters += word.length();
        if(!firstWord)
          lineSpaces++;
      }
      result.add(generateLine(lineLetters, currentLine, L, true));
      return result;
    }

    private String generateLine(int lineLetters, List<String> line, int max, boolean lastLine) {
      int gaps = line.size() - 1;
      int spaces = max - lineLetters;
      StringBuilder sb = new StringBuilder();
      if(gaps == 0 || lastLine) {
        for(String w : line) {
          if(sb.length() != 0)
            sb.append(" ");
          sb.append(w);
        }
        for(int rs = 0; rs < spaces - line.size() + 1; rs++)
          sb.append(" ");
      } else {
        int gapIndex = 0;
        int fatGap = spaces % gaps;
        int gapSpaces = spaces / gaps;
        for(String w : line) {
          sb.append(w);
          if(gapIndex < gaps) {
            for(int gs = 0; gs < gapSpaces; gs++)
              sb.append(" ");
            if(gapIndex < fatGap)
              sb.append(" ");
            gapIndex++;
          }
        }
      }
      return sb.toString();
    }
  }

  /**
   * For each line, I first figure out which words can fit in.
   * According to the code, these words are words[i] through words[i+k-1].
   * Then spaces are added between the words.
   * The trick here is to use mod operation to manage the spaces that can't be evenly distrubuted: the first (L-l) % (k-1) gaps acquire an additional space.
   */
  public static class Solution1{
    public List<String> fullJustify(String[] words, int L) {
      List<String> list = new LinkedList<>();

      for(int i = 0, w; i < words.length; i = w) {
        int len = -1;
        for(w = i; w < words.length && len + words[w].length() + 1 <= L; w++) {
          len += words[w].length() + 1;
        }

        StringBuilder strBuilder = new StringBuilder(words[i]);
        int space = 1, extra = 0;
        if(w != i + 1 && w != words.length) { // not 1 char, not last line
          space = (L - len) / (w - i - 1) + 1;
          extra = (L - len) % (w - i - 1);
        }
        for(int j = i + 1; j < w; j++) {
          for(int s = space; s > 0; s--) strBuilder.append(' ');
          if(extra-- > 0) strBuilder.append(' ');
          strBuilder.append(words[j]);
        }
        int strLen = L - strBuilder.length();
        while(strLen-- > 0) strBuilder.append(' ');
        list.add(strBuilder.toString());
      }

      return list;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution1().fullJustify(new String[]{"What","must","be","shall","be."}, 12));
    System.out.println(new Solution2().fullJustify(new String[]{"What","must","be","shall","be."}, 12));
  }

}
