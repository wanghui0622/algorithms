package datastructure.demo.queue;

import java.util.LinkedList;

/**
 * 生成窗口最大值
 * @auther 王辉
 * @create 2020-07-27 22:08
 * @Description 时间复杂度O(N)
 * 举例：数组[4, 3, 5, 4, 3, 3, 6, 7]
 * 窗口大小为3时，生产成的数组为：[5,5,5,4,6,7]
 */
public class SlidingWindowMaxArray {
    //使用双端队列，确保最大值在顶端，当窗口出现后，不断peekFirst，在这期间要保证peekFirst的下标在窗口内
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        //双向队列
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        //结果集
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        //遍历数组
        for (int i = 0; i < arr.length; i++) {
            //当数组当前值不小于队尾元素时，弹出队尾元素，这样保证了队顶元素是窗口内最大元素
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast();
            }
            //将当前元素添加到队尾
            qmax.addLast(i);
            //检查队顶元素是否失效，如果失效则移除
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }
            //当窗口出现后，取队顶元素添加到结果集
            if (i >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 4, 3, 5, 4, 3, 3, 6, 7 };
        int w = 3;
        printArray(getMaxWindow(arr, w));

    }
}
