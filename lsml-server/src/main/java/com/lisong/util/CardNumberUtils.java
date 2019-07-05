package com.lisong.util;

import com.lisong.common.DictDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class CardNumberUtils {

    /** 批量生成自增会员卡号，不可自校验. */
    public static Set<String> generateBatchIncrementNumber(
            int startNumber, int numberCount, DictDefinition.MakeCardType makeCardType) {
        Assert.isTrue( startNumber >= 0,"startNumber必须大于等于0");
        String prefix = makeCardType.getCode();
        Set<String> numberSet = new HashSet<>(numberCount);
        while (numberSet.size() < numberCount) {
            String number = StringUtils.leftPad(String.valueOf(startNumber++), 8, "0");
            String ret = prefix.concat(number);
            if (!numberSet.contains(ret)) {
                numberSet.add(ret);
            }
        }
        return numberSet;
    }

    private static String generateRandomNumber(String flag) {
        String prefix = StringUtils.leftPad(StringUtils.stripToEmpty(flag), 3, "0");
        Random random = new Random();
        long randData = (long) (999999999999L * random.nextDouble());
        String ret = prefix.concat(StringUtils.leftPad(String.valueOf(randData), 12, "0"));
        return ret + getBankCardCheckCode(ret);
    }

    public static Set<String> generateDistinctBatchRandomNumber(
            Set<String> historyNumberSet, int numberCount, String flag) {
        Set<String> numberSet = new HashSet<>(numberCount);
        if (historyNumberSet == null) {
            historyNumberSet = new HashSet<>(0);
        }
        while (numberSet.size() < numberCount) {
            String number = generateRandomNumber(flag);
            if (!historyNumberSet.contains(number)) {
                numberSet.add(number);
            }
        }
        return numberSet;
    }

    public static boolean verifyRandomNumber(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /** 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位 */
    private static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null
                || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            // 如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        Long start = System.currentTimeMillis();
        try {
            for (int i = 0; i < 1000000; i++) {
                String id = generateRandomNumber("616");
                set.add(id);
            }
            Long end = System.currentTimeMillis();
            System.out.println(set.size());
            System.out.println(TimeUnit.MILLISECONDS.toMillis(end - start) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //        System.out.println(checkBankCard("6166892647646329"));
    }
}
