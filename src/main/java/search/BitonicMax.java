package search;

import utils.StdRandom;

/**
 * 双向搜索
 * @author 王辉
 * @create 2020-07-08 22:19
 * @Description
 * 如果数组由递增的整数序列，紧随其后的递减的整数序列组成，则该数组是双音调的。
 * 编写一个程序，给定一个n个 不同的int值的双音数组，确定该数组中是否有给定的整数。
 */
public class BitonicMax {
    // create a bitonic array of size N
    public static int[] bitonic(int N) {
        int mid = StdRandom.uniform(N);
        int[] a = new int[N];
        for (int i = 1; i < mid; i++) {
            a[i] = a[i-1] + 1 + StdRandom.uniform(9);
        }

        if (mid > 0) a[mid] = a[mid-1] + StdRandom.uniform(10) - 5;

        for (int i = mid + 1; i < N; i++) {
            a[i] = a[i-1] - 1 - StdRandom.uniform(9);
        }

        for (int i = 0; i < N; i++) {
            System.out.println(a[i]);
        }
        return a;
    }

    // find the index of the maximum in a bitonic subarray a[lo..hi]
    public static int max(int[] a, int lo, int hi) {
        if (hi == lo) return hi;
        int mid = lo + (hi - lo) / 2;
        if (a[mid] < a[mid + 1]) return max(a, mid+1, hi);
        if (a[mid] > a[mid + 1]) return max(a, lo, mid);
        else return mid;
    }



    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);
        int[] a = bitonic(N);
        System.out.println("max = " + a[max(a, 0, N-1)]);
    }
}
