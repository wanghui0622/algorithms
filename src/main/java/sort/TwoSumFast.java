package sort;

import java.util.Arrays;

/**
 * 快速计算2-sum算法
 *
 * @auther 王辉
 * @create 2020-07-07 23:56
 * @Description 读入n个整数，统计两个数之和为0的数对个数。时间复杂度是NLogN;暴力匹配的话，时间复杂度为N^2
 * 假设：没有重复数
 */
public class TwoSumFast {

    /**
     * 打印数对之和为0的数对索引
     * a[i] + a[j] = 0 ===> (i, j)
     * @param a
     */
    public static void printAll(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        if (containsDuplicates(a)) throw new IllegalArgumentException("数组包含重复整数");
        for (int i = 0; i < n; i++) {
            int j = Arrays.binarySearch(a, -a[i]);
            if (j > i) System.out.println(a[i] + " " + a[j]);
        }
    }

    /**
     * 返回使a[i] + a[j] = 0的不同数对(i, j)的数目
     * @param a
     * @return
     */
    public static int count(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        if (containsDuplicates(a)) throw new IllegalArgumentException("数组包含重复整数");
        int count = 0;
        for (int i = 0; i < n; i++) {
            int j = Arrays.binarySearch(a, -a[i]);
            if (j > i) count++;
        }
        return count;
    }

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

    public static void main(String[] args)  {
        int[] a = new int[]{1,2,3,4,-1,-2,-3};
        int count = count(a);
        System.out.println(count);
    }
}
