package com.lisong.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class ActivationCodeUtils {

    private static final String Base32Alphabet = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";

    private static final long password = 641179732505L;

    private static final int CHECK_BIT_LEN = 8;

    /**
     * 生成新的序列号.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190605 17:20</font><br>
     * 标志位 + 数据位 + 校验码<br>
     * 将 codeLen * 5 个二进制位映射到 ABCDEFGHJKLMNPQRSTUVWXYZ23456789 表示的序列号，根据2^5=32，每5个二进制位代表一个字符。
     *
     * @param codeLen - 激活码长度
     * @param flag - 激活码标志位值
     * @param flagBitLen - 激活码标志位长度
     * @param checkBitLen - 激活码校验位长度
     * @return java.lang.String
     * @author Rushing0711
     * @since 1.0.0
     */
    private static String generateCode(int codeLen, int flag, int flagBitLen, int checkBitLen) {
        Long ret = 0L; // 长整形ID
        Random random = new Random();
        int checkModData = 1 << checkBitLen;
        int totalBitLen = codeLen * 5;
        int dataBitLen = totalBitLen - checkBitLen - flagBitLen;
        long randData = (long) (1 + (1L << dataBitLen - 1) * random.nextDouble());
        if (flagBitLen > 0) {
            flag = flag & ((1 << flagBitLen) - 1); // 防止越位，若16位标识则是 0xffff
            ret += (long) flag << (totalBitLen - flagBitLen); // 高位标志位
        }

        ret += randData << checkBitLen; // 中位数据位
        long checkNum = (ret >> checkBitLen) % checkModData; // 低位校验位
        ret += checkNum; // 1 - 7位 校验位
        ret = ret ^ password;
        return encode(ret, codeLen);
    }

    private static String generateCode(int codeLen) {
        int flag = 1;
        int flagBitLen = Integer.toBinaryString(flag).length();
        return generateCode(codeLen, flag, flagBitLen, CHECK_BIT_LEN);
    }

    /** 获取一批不重复的兑换码. */
    public static Set<String> generateDistinctBatchCode(
            Set<String> historyCodeSet, int codeCount, int codeLen) {
        Set<String> codeSet = new HashSet<>(codeCount);
        if (historyCodeSet == null) {
            historyCodeSet = new HashSet<>(0);
        }
        while (codeSet.size() < codeCount) {
            String code = generateCode(codeLen);
            if (!historyCodeSet.contains(code)) {
                codeSet.add(code);
            }
        }
        return codeSet;
    }

    /** 将兑换码编码为字符串. */
    private static String encode(long rawCode, int codeLen) {
        StringBuilder encryptedCode = new StringBuilder(16);
        long tmpRandValue = rawCode;
        for (int i = 0; i < codeLen; i++) {
            int code = (int) (tmpRandValue & 0x1F);
            char convertCode = Base32Alphabet.charAt(code);
            encryptedCode.append(convertCode);
            tmpRandValue = tmpRandValue >> 5;
        }
        return encryptedCode.reverse().toString();
    }

    /** 将兑换码解码为数字. */
    private static long decode(String encryptedCode) {
        long id = 0;
        for (int i = 0; i < encryptedCode.length(); i++) {
            int originNum = Base32Alphabet.indexOf(encryptedCode.charAt(i));
            if (originNum == -1) {
                return 0;
            }
            id = id << 5;
            id += originNum;
        }
        return id;
    }

    /** 校验序列号是否合法 */
    private static boolean verifyCode(String encryptedCode, int checkBitLen) {
        long id = 0;
        int checkModData = 1 << checkBitLen;
        for (int i = 0; i < encryptedCode.length(); ++i) {
            long originNum = Base32Alphabet.indexOf(encryptedCode.charAt(i));
            if (originNum == -1) {
                return false; // 字符非法
            }
            id = id << 5;
            id += originNum;
        }

        id = id ^ password;

        long data = id >> checkBitLen;
        long checkNum = id & (checkModData - 1); // 最后7位是校验码

        if (data % checkModData == checkNum) {
            return true;
        }
        return false;
    }

    public static boolean verifyCode(String code) {
        return verifyCode(code, CHECK_BIT_LEN);
    }

    /** 从序列号提取标识. */
    public static Long getFlagFromCode(String encryptedCode, int flagBitLen) {
        long id = decode(encryptedCode);
        return id >> (encryptedCode.length() * 5 - flagBitLen);
    }

    public static void main(String[] args) {
        Set<String> set1 = new HashSet<>();
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String s = generateCode(8);
            set1.add(s);
        }
        Long end = System.currentTimeMillis();
        System.out.println(set1.size());
        System.out.println(TimeUnit.MILLISECONDS.toMillis(end - start) + "ms");

        /*Long start2 = System.currentTimeMillis();
        Set<String> set2 = generateDistinctBatchCode(set1, 10000, 8);
        Long end2 = System.currentTimeMillis();
        System.out.println(set2.size());
        System.out.println(TimeUnit.MILLISECONDS.toMillis(end2 - start2) + "ms");*/

        /*String encryptedCode = generateCode(8);
        System.out.println(encryptedCode);
        System.out.println(verify(encryptedCode));*/
    }
}
