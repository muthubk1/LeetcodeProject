package com.java.lessons.strings;

import java.util.HashSet;
import java.util.Set;

public class UniqueEmailAddress {


    public static void main(String[] args) {

        String[] emails = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com",
                "testemail+david@lee.tcode.com"};
        String[] localNames;

        Set<String> uniqueEmails = new HashSet<>();
        for (String email: emails ) {

            String[] parts = email.split("@");
            String[] local = parts[0].split("\\+");

            uniqueEmails.add(local[0].replace(".","")+"@"+parts[1]);
        }
        System.out.println(uniqueEmails);

    }
}
