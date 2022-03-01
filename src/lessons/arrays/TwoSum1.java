package com.java.lessons.arrays;

import java.util.HashMap;
import java.util.Map;

public class TwoSum1 {

    private void getIndices(int []nums, int target){

        for(int i=0;i<nums.length;i++) {
            System.out.print("i="+i+", num=>"+nums[i]+", ");
            for(int j=i+1;j<nums.length;j++) {
                System.out.print("j="+j+", num=>"+nums[j]+", ");
            }
        }

    }

    private int[] twoSumHashMap(int nums[], int target){

        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int complement = target - nums[i];
            if(map.containsKey(complement)){
                int res[] = new int[]{i, map.get(complement)};
                System.out.println("complement1=>"+map.get(complement));
                System.out.println("complement2=>"+map.get(nums[i]));
                System.out.println("i=>"+i);
                return res;
            }
            System.out.println("complement3=>"+complement);
            System.out.println("complement4=>"+i);
            map.put(nums[i], i);
            System.out.println("complement3=>"+nums[i]);
        }
        System.out.println("map==>"+map);
        return null;
    }
    public static void main(String[] args) {

        int []nums = {11,7,15,2};
        int target = 9;
        TwoSum1 twoSum1 = new TwoSum1();
        int[] result = twoSum1.twoSumHashMap(nums, target);
        for(int i:result){
            System.out.print("result==>"+i);
        }

    }
}
