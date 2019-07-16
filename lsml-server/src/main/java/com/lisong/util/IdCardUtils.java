package com.lisong.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class IdCardUtils {

    public static boolean checkIdCard(String cardNo) {
        if (cardNo.length() != 18) {
            return false;
        }

        Pattern pattern =
                Pattern.compile(
                        "^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$");
        Matcher matcher = pattern.matcher(cardNo);

        if (!matcher.matches()) {
            return false;
        }

        String matchDigit = "";
        // 1.将身份证号码前面的17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
        int[] intArr = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        int sum = 0;
        for (int i = 0; i < intArr.length; i++) {
            // 2.将这17位数字和系数相乘的结果相加。
            sum += Character.digit(cardNo.charAt(i), 10) * intArr[i];
        }
        //        System.out.println("Totally sum：" + sum);
        // 3.用加出来和除以11，看余数是多少？
        int mod = sum % 11;
        //        System.out.println("Totally sum%11 = " + mod);
        // 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。
        int[] intArr2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] intArr3 = {1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2};

        for (int i = 0; i < intArr2.length; i++) {
            int j = intArr2[i];
            if (j == mod) {
                if (intArr3[i] > 57) {
                    matchDigit = String.valueOf((char) intArr3[i]);
                } else {
                    matchDigit = String.valueOf(intArr3[i]);
                }
            }
        }

        if (matchDigit.equals(cardNo.substring(cardNo.length() - 1))) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String cardNo = "36900119751204003X"; // 36900119751204003X // 360481197512040035
        System.out.println(checkIdCard(cardNo));
    }
}
