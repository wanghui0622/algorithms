package sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 描述:
 * 堆排序
 * 首先构建一个二叉堆。
 * 二叉堆是完全二元树（二叉树）或者是近似完全二元树（二叉树）。
 * <p>
 * 二叉树的一些性质：
 * 位置为K的节点的父节点为K/2,而它的子节点的位置为2k(左节点)和2k+1(右节点)
 * <p>
 * 二叉堆有两种：最大堆和最小堆。
 * 最大堆：父结点的键值总是大于或等于任何一个子节点的键值；----升序排序的时候用大顶堆
 * 最小堆：父结点的键值总是小于或等于任何一个子节点的键值。----降序排序的时候用小顶堆
 * <p>
 * 此处实现的是大顶堆
 *
 * @author wanghui email:wanghuiaf@yonyou.com
 * @create 2020-04-15 上午10:54
 */
public class MaxPQ<Key extends Comparable<Key>> {
    /**
     * 通过数组实现二叉树
     */
    private Key[] pq;
    /**
     * 存储在pq中的元素个数。0没有使用。
     */
    private int N = 0;

    public MaxPQ(int maxN) {
        this.pq = (Key[]) new Comparable[maxN + 1];
    }

    /**
     * 判断二叉树是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 获取二叉树大小
     *
     * @return
     */
    public int size() {
        return N;
    }

    /**
     * 插入二叉树,从尾部插入，需要根据插入元素跟父节点元素的大小，做上浮操作。
     * 当v大于父节点时，上浮
     * 插入元素比较次数不超过lgN+1
     *
     * @param v
     */
    public void insert(Key v) {
        pq[++N] = v;
        swim(N);

    }

    /**
     * 获取并移除最大元素
     * 移除最大元素后，将二叉树最后一个节点的元素设置为树的根节点；此时，树的根节点小于其子节点，所以要做下沉操作
     * 删除最大元素操作不超过2lgN次比较
     *
     * @return
     */
    public Key delMax() {
        if (N == 1) {
            Key max = pq[1];
            pq[N--] = null;
            return max;
        } else if (N > 1) {
            Key max = pq[1];
            exch(1, N--);
            pq[N + 1] = null;
            sink(1);
            return max;
        } else {
            return null;
        }

    }


    /**
     * 元素大小比较
     *
     * @param i
     * @param j
     * @return
     */
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * 元素位置交换
     *
     * @param i
     * @param j
     */
    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    /**
     * 上浮操作，自下而上的调整元素顺序，使其符合二叉堆的条件
     *
     * @param k
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k /= 2;
        }
    }

    /**
     * 下沉操作，自上而下的调整元素顺序，使其符合二叉堆的条件
     *
     * @param k
     */
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }


    /**
     * 堆排序实现1
     * 1、调用insert(),将数组变成二叉堆（堆有序操作）
     * 2、下沉排序
     *
     * @param a
     */
    public void stackSort1(Comparable[] a) {
        int temp = a.length;
        MaxPQ aa = new MaxPQ(temp);
        for (int i = 0; i < temp; i++) {
            aa.insert(a[i]);
        }
        System.out.println("堆有序：" + Arrays.toString(aa.pq));
        for (int j = temp - 1; j >= 0; j--) {
            a[j] = aa.delMax();
        }
        System.out.println("排序后：" + Arrays.toString(a));
    }

    /**
     * 堆排序实现2
     * 1、构建二叉堆，从右至左的通过下沉操作构建二叉堆
     * 2、下沉排序
     * @param a
     */
    public void stackSort2(Comparable[] a) {

        int n = a.length;
        //step1、子二叉堆下沉操作实现大顶堆，算法思路：子二叉堆下沉成为有序堆后，父二叉堆下沉操作也是有序堆。
        for (int k = n / 2; k >= 1; k--) {
            sink(a, k, n);
        }
        System.out.println("堆有序：" + Arrays.toString(a));
        //step2、堆排序
        while (n > 1) {
            exch(a, 1, n--);
            sink(a, 1, n);
        }
        System.out.println("排序后：" + Arrays.toString(a));
    }


    /**
     * 子二叉堆下沉操作,交换时，要跟子节点中小的一方交换
     * 选出的子二叉树在变换成子二叉堆的时候，
     * 子节点大的跟父节点比较，如果大于父节点，则替换
     * @param a 数组a内部实现下沉
     * @param i 下沉开始节点
     * @param j 下沉结束节点
     */
    private void sink(Comparable[] a, int i, int j) {

        int v = i - 1;
        Comparable rootNode = a[v];
        for (int k = 2 * v + 1; k < j; k = 2 * k + 1) {
            //右子节点大于左子节点
            if(k+1 < j && a[k].compareTo(a[k+1])<0) {
                k++;
            }
            //父节点小于子节点中最大的
            if(rootNode.compareTo(a[k]) < 0) {
                a[v] = a[k];
                //将索引指向下一个子二叉树的父节点
                v = k;
            }else {
                break;
            }
        }
        a[v] = rootNode;
    }

    /**
     * 元素交换
     *
     * @param a 数组a内部实现元素交换
     * @param i 需要交换元素1
     * @param j 需要交换元素1
     */
    private void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = temp;
    }


    public static void main(String[] args) {
        String[] a = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        System.out.println("排序前：" + Arrays.toString(a));
        int temp = a.length;
        new MaxPQ(temp).stackSort2(a);


    }
}
