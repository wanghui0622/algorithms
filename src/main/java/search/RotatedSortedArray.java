package search;

import java.util.Arrays;

/**
 *
 * 在已排序的旋转数组中搜索
 * @author 王辉
 * @create 2020-07-08 22:47
 * @Description
 */
public class RotatedSortedArray {

    /**
     * 将数组k向右旋转
     * @param a
     * @param k
     * @return
     */
    public static int[] rotate(int[] a, int k) {
        int N = a.length;
        if (k < 0 || k >= N) throw new RuntimeException("illegal value of k");
        int[] b = new int[N];
        for (int i = 0; i < N; i++) {
            b[i] = a[(i - k + N) % N];
        }
        return b;
    }

    /**
     * 长度为N的排序数组，包含1,3,5，…2 n - 1
     * @param N
     * @return
     */
    public static int[] sortedArray(int N) {
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = 2*i + 1;
        }
        return a;
    }

    /**
     * x是否在排序后的数组a[]
     * @param a
     * @param x
     * @return
     */
    public static boolean searchInSortedArray(int[] a, int x) {
        return Arrays.binarySearch(a, x) >= 0;
    }

    /**
     * 返回最小键的索引k (b[k] < b[k-1]的唯一索引)
     * @param b
     * @return
     */
    private static int findMinimumIndex(int[] b) {
        int N = b.length;
        if (N <= 1) return 0;          // array of length 0 or 1
        if (b[0] < b[N-1]) return 0;   // already sorted

        // invariant b[lo] > b[hi]
        int lo = 0, hi = N-1;
        while (true) {
            if (hi == lo+1) return hi;
            int mid = lo + (hi - lo) / 2;
            if      (b[mid] < b[hi]) hi = mid;
            else if (b[mid] > b[hi]) lo = mid;
        }
    }

    /**
     * x是否在旋转后的排序数组b中[]
     * @param b
     * @param x
     * @return
     */
    public static boolean searchInRotatedSortedArray1(int[] b, int x) {
        int N = b.length;
        int k = findMinimumIndex(b);
        if (k == 0)         return Arrays.binarySearch(b, x) >= 0;
        else if (x >= b[0]) return Arrays.binarySearch(b, 0, k, x) >= 0;
        else                return Arrays.binarySearch(b, k, N, x) >= 0;
    }

    /**
     * x是否在旋转后的排序数组b中[]
     * @param b
     * @param x
     * @return
     */
    public static boolean searchInRotatedSortedArray2(int[] b, int x) {
        int N = b.length;
        int lo = 0, hi = N-1;
        while (true) {
            if (hi < lo) return false;
            int mid = lo + (hi - lo) / 2;
            if (b[mid] == x) return true;
            if      (b[lo] <= x && x < b[mid]) return Arrays.binarySearch(b, lo, mid, x) >= 0;
            else if (b[mid] < x && x <= b[hi]) return Arrays.binarySearch(b, mid+1, hi+1, x) >= 0;
            else if (b[mid] < b[hi]) hi = mid - 1;
            else                     lo = mid + 1;
        }
    }



    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int[] a = sortedArray(N);

        // test all rotations
        for (int k = 0; k < N; k++) {
            int[] b = rotate(a, k);

            // test all search keys
            for (int x = 0; x <= 2*N; x++) {

                boolean result  = searchInSortedArray(a, x);
                boolean result1 = searchInRotatedSortedArray1(b, x);
                boolean result2 = searchInRotatedSortedArray2(b, x);
                if ((result != result1) || (result != result2)) {
                    System.out.println("   - a[] = ");
                    System.out.print("     ");
                    for (int i = 0; i < N; i++)
                        System.out.printf("%2d ", a[i]);
                    System.out.println();
                    System.out.println("   - k   = " + k);
                    System.out.println("   - b[] = ");
                    System.out.print("     ");
                    for (int i = 0; i < N; i++)
                        System.out.printf("%2d ", b[i]);
                    System.out.println();
                    System.out.println("   - x         = " + x);
                    System.out.println("   - searchInSortedArray(a, x)          = " + result);
                    System.out.println("   - searchInRotatedSortedArray1(b, x)  = " + result1);
                    System.out.println("   - searchInRotatedSortedArray2(b, x)  = " + result2);
                    System.out.println();
                }
            }
        }
    }
}
