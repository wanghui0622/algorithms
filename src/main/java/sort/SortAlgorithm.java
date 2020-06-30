package sort;

import java.util.Arrays;

/**
 * 描述:
 * 排序算法
 *
 * @author wanghui email:wanghuiaf@yonyou.com
 * @create 2020-04-13 上午11:03
 */
public class SortAlgorithm {

    private static Comparable[] aux;

    public static void main(String[] args) {
        String[] a = {"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        System.out.println(Arrays.toString(a));
        quickSort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 选择排序, 比较次数~n*n/2,交换次数~n。
     *
     * @param a
     */
    public static void selectSort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
                exch(a, i, min);
            }
        }
    }

    /**
     * 插入排序,对部分有序数组十分高效，同时很适合小估摸数组。
     * 比较和交换次数：
     * 平均情况下：~n*n/4, ~n*n/4
     * 最坏情况下：~n*n/2， ~n*n/2
     * 最好情况下：~n-1,0
     *
     * @param a
     */
    public static void insertionSort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    /**
     * 希尔排序，通过插入排序实现；可用于大型数组。与选择和插入排序相比较，数组越大，优势越明显
     * 比较次数：最坏情况下~n^(3/2)
     *
     * @param a
     */
    public static void shellSort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        //h的最有选择较复杂，这里的实现使用了序列1/2(3^k-1),从n/3开始递减至1
        while (h < n / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = 0; i < n; i++) {
                for (int j = i; j > h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    /**
     * 归并排序
     *
     * @param a
     */
    public static void mergeSort1(Comparable[] a) {
        aux = new Comparable[a.length];
        mergeSort1(a, 0, a.length - 1);
    }

    /**
     * 自顶向下归并排序
     * 时间：~nlgn
     *
     * @param a
     * @param lo
     * @param hi
     */
    private static void mergeSort1(Comparable[] a, int lo, int hi) {

        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        mergeSort1(a, lo, mid);
        mergeSort1(a, mid + 1, hi);
        mergeArray(a, lo, mid, hi);
    }


    /**
     * 自底向上的归并排序
     *
     * @param a
     */
    private static void mergeSort2(Comparable[] a) {
        int n = a.length;
        aux = new Comparable[n];

        /*
        第一层循环：每次内循环归并的数组大小
        第二层循环：循环归并每一个分隔的子数组
         */
        for (int sz = 1; sz < n; sz = sz + sz) {
            for (int lo = 0; lo < n - sz; lo += sz + sz) {
                mergeArray(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
        }
    }

    /**
     * 原地归并
     *
     * @param a
     * @param lo
     * @param mid
     * @param hi
     */
    private static void mergeArray(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        Comparable[] aux = new Comparable[a.length];
        for (int k = 0; k < a.length; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }

        }

    }

    /**
     * 快速排序
     *
     * @param a
     */
    public static void quickSort(Comparable[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(Comparable[] a, int lo, int hi) {

        if (hi <= lo) {
            return;
        }
        int j = quickPartition(a, lo, hi);
        quickSort(a, lo, j - 1);
        quickSort(a, j + 1, hi);
    }

    /**
     * 切分操作
     * 快速排序的关键函数，切分后，切分点前后数组应该满足一下条件：
     * lo,j-1中所有元素均不大于索引位置为j的元素
     * j+1,hi中所有元素均不小于索引位置为j的元素
     *
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    private static int quickPartition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);

        return j;
    }

    /**
     * 三项切分的快速排序
     */
    public static void quick3Sort(Comparable[] a) {
        quick3Sort(a, 0, a.length-1);
    }


    /**
     * 三项切分的快速排序实现
     * 数组分为四段区域：
     * 第一段-->[lo-lt) 小于 a[lo]
     * 第二段-->[lt-i) 等于 a[lo]
     * 第三段-->[i-gt)  不确定区域
     * 第四段-->[gt-hi) 大于a[lo]
     * @param a
     * @param lo
     * @param hi
     */
    private static void quick3Sort(Comparable[] a, int lo, int hi) {
        if (hi < lo) {
            return;
        }
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                exch(a, lt++, i++);
            } else if (cmp > 0) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }
        quick3Sort(a, lo, lt - 1);
        quick3Sort(a, gt + 1, hi);
    }


    /**
     * 判断是否有序
     *
     * @param a
     * @return
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 数组输出
     *
     * @param a
     */
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /**
     * 降序比较大小写；当v<w时返回true，反之返回false。
     *
     * @param v
     * @param w
     * @return
     */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 数据位置交换。
     *
     * @param a
     * @param i
     * @param j
     */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;

    }

}
