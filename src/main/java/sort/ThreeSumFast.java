package sort;

import java.util.Arrays;

/**
 * 快速3-sum
 * @auther 王辉
 * @create 2020-07-08 0:14
 * @Description 该类为计数提供了静态方法,然后输出一个由不同整数组成的数组中的三组的个数和为0(忽略整数溢出)。
 * 时间复杂度N^2logN;暴力匹配时间复杂度为N^3
 */
public class ThreeSumFast {
    // Do not instantiate.
    private ThreeSumFast() { }

    /**
     * 如果排序后的数组a[]包含任何重复的整数，则返回true
     * @param a
     * @return
     */
    private static boolean containsDuplicates(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] == a[i-1]) return true;
        return false;
    }

    /**
     *
     * 如果 {@code a[i] + a[j] + a[k] == 0}打印索引 (i, j, k) 其中： {@code i < j < k}
     *
     * @param a
     * @throws IllegalArgumentException
     */
    public static void printAll(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        if (containsDuplicates(a)) throw new IllegalArgumentException("数组包含重复整数");
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int k = Arrays.binarySearch(a, -(a[i] + a[j]));
                if (k > j) System.out.println(a[i] + " " + a[j] + " " + a[k]);
            }
        }
    }

    /**
     *
     * 返回使@code a[i] + a[j] + a[k] == 0的不同数对(i, j)的数目
     *
     * @param a
     * @return
     */
    public static int count(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        if (containsDuplicates(a)) throw new IllegalArgumentException("数组包含重复整数");
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int k = Arrays.binarySearch(a, -(a[i] + a[j]));
                if (k > j) count++;
            }
        }
        return count;
    }

    public static void main(String[] args)  {
        int[] a = new int[]{1,2,3,4,-1,-2,-3};
        printAll(a);
        int count = count(a);
        System.out.println(count);
    }
}
