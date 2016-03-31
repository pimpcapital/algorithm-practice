package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Easy;

/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 *
 * Some hints:
 *
 * Could negative integers be palindromes? (ie, -1)
 * If you are thinking of converting the integer to string, note the restriction of using extra space.
 * You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?
 * There is a more generic way of solving this problem.
 */
public class _009_PalindromeNumber implements Easy {

  public static class Solution1 {
    public boolean isPalindrome(int x) {
      if(x < 0)
        return false;
      int left = 1;
      int temp = x;
      while((temp /= 10) != 0)
        left *= 10;
      int right = 1;
      while(right < left) {
        if(x / right % 10 != x / left % 10)
          return false;
        left /= 10;
        right *= 10;
      }
      return true;
    }
  }

  public static class Solution2 {
    public boolean isPalindrome(int x) {
      int palindromeX = 0;
      int inputX = x;
      while(x>0){
        palindromeX = palindromeX*10 + (x % 10);
        x = x/10;
      }
      return palindromeX==inputX;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution1().isPalindrome(12321));
    System.out.println(new Solution2().isPalindrome(12321));
  }
}
