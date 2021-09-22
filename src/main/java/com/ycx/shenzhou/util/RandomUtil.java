package com.ycx.shenzhou.util;

import java.util.List;

public class RandomUtil {
    public static int[] getRandomNum(int n, int len) {   // n表示原数组的长度，len表示要取前几个元素
        int[] a = new int[n];
        int[] b = new int[len];
        for(int i = 0; i < n; i++) {
            a[i] = i;
        }
        for(int i = 1; i < n; i++) {
            int temp = (int)(Math.random() * n);
            a[i] ^= a[temp];
            a[temp] ^= a[i];
            a[i] ^= a[temp];
        }
        for(int i = 0; i < len; i++) {
            b[i] = a[i];
        }
        return b;
    }

    public static int randomInt(int l, int r) {
        return (int)(Math.random() * (r - l + 1) + l);
    }
}
