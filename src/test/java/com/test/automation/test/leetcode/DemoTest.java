package com.test.automation.test.leetcode;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoTest {

    @Test
    public void f() {
        for (String str : Arrays.asList("abcabcbb")) {
            System.out.println(lengthOfLongestSubstring(str));
        }
    }

    public int lengthOfLongestSubstring(String s) {
        char[] chArr = s.toCharArray();
        int len=0;
        for(int i=0; i < chArr.length - 1; i++) {
            System.out.println("i = " + i);
            int temp = 0;
            int j = i + 1;
            List<Character> cList = new ArrayList();
            cList.add(chArr[i]);
            while((j < chArr.length) && !cList.contains(chArr[j])) {
                System.out.print(chArr[i] + "!=" + chArr[j] + " ");
                System.out.println("j = " + j);
                cList.add(chArr[j]);
                temp = j - i + 1;
                j++;
            }

            if(cList.size() > len) {
                len = cList.size();
            }
            System.out.println();
            System.out.println("temp " + temp);
            System.out.println("len " + len);

        }
        return len;
    }
}
