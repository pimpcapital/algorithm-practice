package com.beijunyi.leetcode;

import java.util.*;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * The algorithm should run in linear time and in O(1) space.
 */
public class _229_MajorityElementTwo {

  public interface Solution {
    List<Integer> majorityElement(int[] nums);
  }

  public static class Solution1 implements Solution {

    @Override
    public List<Integer> majorityElement(int[] nums) {
      Set<Integer> possibleMaj = findPossibleMajorities(nums, 3);
      Map<Integer, Integer> counts = countElements(possibleMaj, nums);
      int threshold = nums.length / 3 + 1;
      removeKeysWithValueLessThan(counts, threshold);
      return new ArrayList<>(counts.keySet());
    }

    private static Set<Integer> findPossibleMajorities(int[] nums, int k) {
      Map<Integer, Integer> counts = new HashMap<>(k);
      for(int n : nums) {
        if(counts.containsKey(n)) {
          counts.put(n, counts.get(n) + 1);
        } else if(counts.size() < k) {
          counts.put(n, 1);
        } else {
          Integer minKey = findKeyWithMinValue(counts);
          if(minKey != null) {
            int value = counts.get(minKey);
            if(value == 1) counts.remove(minKey);
            else counts.put(minKey, value - 1);
          }
          if(counts.size() < k) counts.put(n, 1);
        }
      }
      return counts.keySet();
    }

    private static Integer findKeyWithMinValue(Map<Integer, Integer> counts) {
      Integer minKey = null;
      Integer min = null;
      for(Map.Entry<Integer, Integer> entry : counts.entrySet()) {
        if(min == null || entry.getValue() < min) {
          minKey = entry.getKey();
          min = entry.getValue();
        }
      }
      return minKey;
    }

    private static Map<Integer, Integer> countElements(Set<Integer> keys, int[] nums) {
      Map<Integer, Integer> ret = new HashMap<>();
      for(Integer key : keys)
        ret.put(key, 0);
      for(int num : nums) {
        if(ret.containsKey(num))
          ret.put(num, ret.get(num) + 1);
      }
      return ret;
    }

    private static void removeKeysWithValueLessThan(Map<Integer, Integer> counts, int threshold) {
      Iterator<Map.Entry<Integer, Integer>> it = counts.entrySet().iterator();
      while(it.hasNext()) {
        Map.Entry<Integer, Integer> next = it.next();
        if(next.getValue() < threshold) it.remove();
      }
    }

  }

  public static class Solution2 implements Solution {

    @Override
    public List<Integer> majorityElement(int[] nums) {
      //there should be at most 2 different elements in nums more than n/3
      //so we can find at most 2 elements based on Boyer-Moore Majority Vote algo
      List<Integer> res = new ArrayList<Integer>();
      if(nums.length==0) return res;
      int count1=0,count2=0,m1=0,m2=0;
      for(int n : nums){
        if(m1==n) count1++;
        else if(m2==n) count2++;
        else if(count1==0) {
          m1=n;
          count1=1;
        }
        else if(count2==0) {
          m2=n;
          count2=1;
        }
        else{
          count1--;
          count2--;
        }
      }
      count1=0;
      count2=0;
      //count the number for the 2 elements
      for(int n:nums){
        if(n==m1) count1++;
        else if(n==m2) count2++;
      }
      //if those appear more than n/3
      if(count1>nums.length/3) res.add(m1);
      if(count2>nums.length/3) res.add(m2);
      return res;
    }

  }

  public static void main(String[] args) {
    Solution s1 = new Solution1();
    Solution s2 = new Solution2();

    int[] nums1 = new int[] {1,1,1,2,3,4,5,6};
    List<Integer> r11 = s1.majorityElement(nums1);
    System.out.println(r11);
    List<Integer> r12 = s2.majorityElement(nums1);
    System.out.println(r12);

    int[] nums2 = new int[] {2,2,9,3,9,3,9,3,9,3,9,3,9,3,9,3,9};
    List<Integer> r21 = s1.majorityElement(nums2);
    System.out.println(r21);
    List<Integer> r22 = s2.majorityElement(nums2);
    System.out.println(r22);
  }

}
