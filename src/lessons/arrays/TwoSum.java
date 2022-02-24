package com.java.lessons.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
    Given an array of integers nums and an integer target,
    return indices of the two numbers such that they add up to target.
    You may assume that each input would have exactly one solution,
    and you may not use the same element twice.
    You can return the answer in any order.

   Example 1:
    Input: nums = [2,7,11,15], target = 9
    Output: [0,1]
    Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
   Example 2:
    Input: nums = [3,2,4], target = 6
    Output: [1,2]
   Example 3:
    Input: nums = [3,3], target = 6
    Output: [0,1]
*/
public class TwoSum {

    public int[] twoSumMap(int[] nums, int target) {

        Map<Integer,Integer> map = new HashMap<>();
        for(int i =0;i< nums.length;i++){

            int complement = target - nums[i];
            if(map.containsKey(complement)) {
                int [] res =  new int[] { map.get(complement), i};
                printArr(res);
                return  res;
            }
            map.put(nums[i], i);
        }
        System.out.println("map=>"+map);
        return new int[2];
    }

    public int[] twoSum(int[] nums, int target) {

        int[] result = new int[nums.length];
        for (int i=0; i < nums.length;i++) {
            for(int j=i+1 ;j<nums.length;j++){
                if(nums[j] == target - nums[i])
                    return new int[] {i,j};
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int nums[] = {2,7,11,15};
        int target = 9;
        TwoSum twoSum = new TwoSum();
//        int[] result = twoSum.twoSum(nums, target);
//        twoSum.printArr(result);
        int[] result1 = twoSum.twoSumMap(nums, target);
//        twoSum.printArr(result1);
    }

    private void printArr(int[] result){

        try {
            System.out.print("[");
            for(int k : result){

                System.out.print(k +", ");
            }
            System.out.print("]");
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
