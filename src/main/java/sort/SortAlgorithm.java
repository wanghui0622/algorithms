package sort;

import java.util.Arrays;

/**
 * 基本排序算法
 * 1、选择排序:核心就不停的选择无序区最小值跟无序区的第一个值交换位置（升序时）。
 * 首先，找到数组中最小的项，然后与第一项交换。然后，找到下一个最小的物品并将其与第二个条目交换。
 * 以这种方式继续，直到对整个数组进行排序。
 * <p>
 * 2、插入排序：左侧已排序元素相对有序
 * 人们通常用来对桥牌进行分类的算法是一次考虑一张牌，将每张牌插入到已经考虑过的牌中的适当位置（对它们进行分类）。
 * 在计算机实现中，我们需要在将当前项目插入到空出位置之前，通过将较大的项目向右移动一个位置来为当前项目腾出空间。
 * <p>
 * 3、希尔排序（Shell Sort）：
 * 希尔排序是插入排序的一种简单扩展，它通过允许交换相距较远的条目来提高速度，以生成可以有效排序的部分排序数组，最终可以通过插入排序进行排序。
 * 想法是重新排列数组，使其具有以下性质：每进行第h个条目（从任何地方开始）都会产生排序的序列，这种阵列是h-sorted的。
 * <p>
 * 4、归并排序：
 * 合并两个有序数组以组成一个更大的有序数组；并归算法体现了分治思想，是一个渐进最优的基于比较排序的算法。
 * 提供两种并归排序实现方案：自顶而下和自底向上
 * <p>
 * <p>
 * 5、快速排序：
 * 与并归排序相似，快速排序将数组按照切分点，分为两部分，左边元素都小于切分点，右边元素都大于切分点。当切分点两侧的元素有序时，数组有序。
 *
 * @author wanghui email:wanghuiaf@yonyou.com
 * @create 2020-04-13 上午11:03
 */
public class SortAlgorithm {

    private static Comparable[] aux;

    public static void main(String[] args) {
        Integer[] selectSortArray = {8, 7, 1, 3, 5, 2};
        selectSort(selectSortArray);
        System.out.println(Arrays.toString(selectSortArray));
        String[] a = {"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        System.out.println(Arrays.toString(a));
        quickSort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 选择排序, 比较次数~n*n/2,交换次数~n。
     * 时间复杂度：O(N)
     *
     * @param a 待排序数组
     */
    public static void selectSort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;    //最小值索引
            for (int j = i + 1; j < n; j++) {//获取[i+1,n-1]区间的最小值
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);//最小值与索引位置数据交换位置
        }
    }

    /**
     * 插入排序,对部分有序数组十分高效，同时很适合小估摸数组。
     * 比较和交换次数：
     * 平均情况下：~n*n/4, ~n*n/4
     * 最坏情况下：~n*n/2， ~n*n/2
     * 最好情况下：~n-1,0
     *
     * @param a 待排序数组
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
     * @param a 待排序数组
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
    /*    ***************************归并排序 start****************************************       */

    /**
     * 自顶向下归并排序
     *
     * @param a 待排序数组
     */
    public static void mergeSort1(Comparable[] a) {
        aux = new Comparable[a.length];
        mergeSort1(a, 0, a.length - 1);
    }

    /**
     * 自顶向下归并排序
     * 子数组的依赖树结构:
     *               a[0, length-1]
     *  a[0,(length-1)/2]   a[(length-1)/2 +1 , length-1]
     *   ....  ....   .... ....   ....
     *   a[0,1]  a[2,3]  ...   a[length-2, length-1]
     *
     * 时间：~NlgN
     * @param a     待排序数组
     * @param lo    下限索引
     * @param hi    上限索引
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
     * 自底向上的归并排序：比较适合用链表组织的数据
     * 数组a[lo,hi]
     * 首先，归并a[0,1],a[2,3], ...,a[hi-1,hi] ---两两归并
     * 第二，归并a[0,3],a[4,7], ...,a[hi-3,hi] ---四四归并
     * ...
     * 最后，归并a[lo,hi]
     * @param a 待排序数组
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
     * 原地归并：
     * mid将数组一分为二，每个部分都有序
     *
     * @param a   待排序数组
     * @param lo  数组下限索引
     * @param mid 拆分位置索引
     * @param hi  数组上限索引
     */
    private static void mergeArray(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        Comparable[] aux = new Comparable[a.length]; //辅助数组
        System.arraycopy(a, 0, aux, 0, a.length); //拷贝数组到辅助数组

        /*
         * 将两个有序数组归并为一个更大的有序数组
         * 1. 两个有序数组: [lo,mid],[mid+1,hi]
         * 2. lo <= i <=mid, mid+1 <= j <= hi
         * 遍历数组a，通过比较辅助数组aux中元素的大小，确定元素在数组a中的合适位置
         * 存在以下三种情况：
         * 1. i > mid 时，
         * aux[lo]~aux[mid]中的所有元素都已经在数组a中找到合适的位置；
         * 只需要在遍历过程中将aux[mid+1]~aux[hi]中剩余的元素有序的赋值给数组a即可。
         * 2. j > hi 时，
         * aux[mid+1]~aux[hi]中的所有元素都已经在数组a中找到合适的位置；
         * 只需要在遍历过程中将aux[lo]~aux[mid]中剩余的元素有序的赋值给数组a即可。
         * 3. lo <= i <=mid &&  mid+1 <= j <= hi  时，
         * 数组a当前位置上的元素，应该是aux[i],aux[j]中小的那个。
         */
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }
    /*    ***************************归并排序 end****************************************       */

    /*    ***************************快速排序 start****************************************     */

    /**
     * 快速排序入口函数
     *
     * @param a 待排序数组
     */
    public static void quickSort(Comparable[] a) {
        quickSort(a, 0, a.length - 1);
    }

    /**
     * 快速排序
     * @param a     待排序数组
     * @param lo    下限索引
     * @param hi    上限索引
     */
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
     * @param a     待排序数组
     * @param lo    下限索引
     * @param hi    上限索引
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
     * @param a
     */
    public static void quick3Sort(Comparable[] a) {
        quick3Sort(a, 0, a.length - 1);
    }


    /**
     * 三项切分的快速排序实现
     * 数组分为四段区域：
     * 第一段-->[lo-lt) 小于 a[lo]
     * 第二段-->[lt-i) 等于 a[lo]
     * 第三段-->[i-gt)  不确定区域
     * 第四段-->[gt-hi) 大于a[lo]
     *
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

    /*    ***************************快速排序 end ****************************************     */

    /*   *************************** 工具方法 start ****************************************   */
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
     * 大小比较
     *
     * @param v 元素1
     * @param w 元素2
     * @return 当v<w时返回true ， 反之返回false 。
     */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 数据位置交换
     *
     * @param a 数组
     * @param i 待交换位置索引1
     * @param j 待交换位置索引2
     */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /*   *************************** 工具方法 end ****************************************   */

}
