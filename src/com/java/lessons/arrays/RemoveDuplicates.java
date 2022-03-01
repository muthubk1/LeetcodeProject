package com.java.lessons.arrays;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;

//        Set<Integer> setNum = new HashSet<>();
//        for(int n : nums){
//            setNum.add(n);
//        }
//        System.out.println("set=>"+setNum);
//        if(setNum.size() == nums.length)
//            return setNum.size();
//        else
//            return setNum.size();
    }

    public static void main(String[] args) {

        int []nums = {1,1,2};
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        int result = removeDuplicates.removeDuplicates(nums);
        System.out.println("result=>"+result);
    }
}
