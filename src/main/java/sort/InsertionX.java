package sort;

/**
 * 带标记的插入排序
 * @auther 王辉
 * @create 2020-07-09 23:43
 * @Description
 * 该实现消除了内部循环中的j > 0测试，方法是首先将最小的项放入位置。
 * 使用SortCompare.java来评估这样做的有效性。
 * 注意:通常可以用这种方法避免索引越界测试—使测试被消除的项称为标记。
 */
public class InsertionX {
    // This class should not be instantiated.
    private InsertionX() { }

    /**
     * 使用自然顺序按升序重新排列数组
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        int n = a.length;

        // 把最小元素放在下表为0的位置，并记录需要交换的元素数
        int exchanges = 0;
        for (int i = n-1; i > 0; i--) {
            if (less(a[i], a[i-1])) {
                exch(a, i, i-1);
                exchanges++;
            }
        }
        show(a);
        if (exchanges == 0) return;


        // insertion sort with half-exchanges
        for (int i = 2; i < n; i++) {
            Comparable v = a[i];
            int j = i;
            while (less(v, a[j-1])) {
                a[j] = a[j-1];
                j--;
            }
            a[j] = v;
            show(a);
        }

        assert isSorted(a);
    }

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // a is sorted?
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String[] a = {"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        show(a);
        InsertionX.sort(a);
        show(a);
    }
}
