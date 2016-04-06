package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given a set of distinct integers, S, return all possible subsets.
 *
 * Note:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example,
 * If S = [1,2,3], a solution is:
 *
 *   [
 *     [3],
 *     [1],
 *     [2],
 *     [1,2,3],
 *     [1,3],
 *     [2,3],
 *     [1,2],
 *     []
 *   ]
 */
public class _078_Subsets implements Medium {

  public static class Solution1 {
    public List<List<Integer>> subsets(int[] S) {
      List<List<Integer>> results = new ArrayList<>();
      results.add(new ArrayList<Integer>());
      Arrays.sort(S);
      for(int curr : S) {
        List<List<Integer>> cache = new ArrayList<>();
        for(List<Integer> lst : results) {
          List<Integer> copy = new ArrayList<>(lst);
          copy.add(curr);
          cache.add(copy);
        }
        results.addAll(cache);
      }
      return results;
    }
  }

  /**
   * Number of subsets for {1 , 2 , 3 } = 2^3 .
   * why ?
   * case    possible outcomes for the set of subsets
   * 1   ->          Take or dont take = 2
   * 2   ->          Take or dont take = 2
   * 3   ->          Take or dont take = 2
   *
   * therefore , total = 2*2*2 = 2^3 = { { } , {1} , {2} , {3} , {1,2} , {1,3} , {2,3} , {1,2,3} }
   *
   * Lets assign bits to each outcome  -> First bit to 1 , Second bit to 2 and third bit to 3
   * Take = 1
   * Dont take = 0
   *
   * 0) 0 0 0  -> Dont take 3, Dont take 2, Dont take 1 = { }
   * 1) 0 0 1  -> Dont take 3, Dont take 2,      take 1 = { 1 }
   * 2) 0 1 0  -> Dont take 3,      take 2, Dont take 1 = { 2 }
   * 3) 0 1 1  -> Dont take 3,      take 2,      take 1 = { 1, 2 }
   * 4) 1 0 0  ->      take 3, Dont take 2, Dont take 1 = { 3 }
   * 5) 1 0 1  ->      take 3, Dont take 2,      take 1 = { 1, 3 }
   * 6) 1 1 0  ->      take 3,      take 2, Dont take 1 = { 2, 3 }
   * 7) 1 1 1  ->      take 3,      take 2,      take 1 = { 1, 2 , 3 }
   *
   * In the above logic ,Insert S[i] only if (j>>i)&1 ==true   { j E { 0,1,2,3,4,5,6,7 }   i = ith element in the input array }
   *
   * element 1 is inserted only into those places where 1st bit of j is 1
   * if( j >> 0 &1 )  ==> for above above eg. this is true for sl.no.( j )= 1 , 3 , 5 , 7
   *
   * element 2 is inserted only into those places where 2nd bit of j is 1
   * if( j >> 1 &1 )  == for above above eg. this is true for sl.no.( j ) = 2 , 3 , 6 , 7
   *
   * element 3 is inserted only into those places where 3rd bit of j is 1
   * if( j >> 2 & 1 )  == for above above eg. this is true for sl.no.( j ) = 4 , 5 , 6 , 7
   *
   * Time complexity : O(n*2^n) , for every input element loop traverses the whole solution set length i.e. 2^n
   */
  public static class Solution2 {
    public List<List<Integer>> subsets(int[] S) {
      Arrays.sort(S);
      int totalNumber = 1 << S.length;
      List<List<Integer>> collection = new ArrayList<>(totalNumber);
      for(int i = 0; i < totalNumber; i++) {
        List<Integer> set = new LinkedList<>();
        for(int j = 0; j < S.length; j++)
          if((i & (1 << j)) != 0)
            set.add(S[j]);
        collection.add(set);
      }
      return collection;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution1().subsets(new int[]{1, 2, 3}));
    System.out.println(new Solution2().subsets(new int[]{1, 2, 3}));
  }

}
