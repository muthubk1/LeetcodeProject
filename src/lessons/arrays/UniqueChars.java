package com.java.lessons.arrays;

public class UniqueChars {

    private boolean isUniqueChars(String str) {

        if(str.length() > 128) return false;

        boolean []charSet =new boolean[128];
        for (int i=0;i<str.length();i++){

            int val = str.charAt(i);
            System.out.print("i {}"+ i + "str.charAt(i)="+str.charAt(i));
            System.out.println(", val {}"+ val);
            if(charSet[val]) {
                return false;
            }
            charSet[val] = true;
        }

        return true;
    }

    public static void main(String[] args) {

        String str = "ab"; // leetcode , aabb, loveleetcode
        UniqueChars uniqueChars = new UniqueChars();
        boolean result = uniqueChars.isUniqueChars(str);
        System.out.println("isUniqueChars=>"+result);
    }
}
