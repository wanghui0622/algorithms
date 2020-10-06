package datastructure.demo.queue;

import java.util.LinkedList;

/**
 * 生成窗口最大值，时间复杂度要求：O(N)
 * @author 王辉
 * @create 2020-07-27 22:08
 * @Description
 * 举例：数组[4, 3, 5, 4, 3, 3, 6, 7]，窗口大小为3时，生产成的数组为：[5,5,5,4,6,7]
 * 第一次（初始化）窗口：[4,3,5] --> 5
 * 第二次移动窗口：[3,5,4]-->5
 * 第三次移动窗口：[5,4,3]-->5
 * 第四次移动窗口：[4,3,3]-->4
 * 第五次移动窗口：[3,3,6]-->6
 * 第六次移动窗口：[3,6,7]-->7
 * 输出:5 5 5 4 6 7
 *
 * 分析：
 * 1、要求时间复杂度O(N),则数组arr只能遍历一次
 * 2、窗口每移动一次，要确定出当前窗口内的最大值
 * 3、窗口边界问题确定：[i- w + 1, i]
 * 使用一个双端队列，当前窗口内的元素，队头为最大值，队尾为最小值（双端队列由队头到队尾有序依次减小）。
 * 每一移入窗口的元素，都要根据队尾元素比较，如果小于队尾元素，则直接入队；如果大于等于队尾元素，先弹出队尾元素，再入队。
 * 依举例数组为例：
 * 初始化：dQueue={},队列存储数组元素下标
 * 遍历数组：
 *
 * 0:dQueue={0}      此时队列为空，0入队
 * 1:dQueue={0,1}   此时(arr[0]=4)>(arr[1]=3),1直接入队
 * 2:dQueue={2}     此时(arr[1]=3)<(arr[2]=5),(arr[1]=3)出队;(arr[0]=4)<(arr[2]=5),(arr[0]=4)出队；
 *                  队列为空，2入队。此时窗口内有三个元素，需要在队列中获取最大元素（队头）---5
 * 3:dQueue={2,3}   此时(arr[2]=5)>(arr[3]=4),3直接入队。
 *                  此时窗口内有三个元素，需要在队列中获取最大元素（队头）---5
 * 4:dQueue={2,3,4} 此时(arr[3]=4)>(arr[4]=3),4直接入队。
 *                  此时窗口内有三个元素，需要在队列中获取最大元素（队头）---5
 * 5:dQueue={3,5}   此时2过期，需要移除，队头变为3;
 *                  队尾(arr[4]=3)<=(arr[5]=3),4出队，5入队。
 *                  此时窗口内有三个元素，需要在队列中获取最大元素（队头）---4
 * 6:dQueue={6}     此时3过期，需要移除，队头变为5；
 *                  队尾(arr[5]=3) < (arr[6]=6), (arr[5]=3) 出队，(arr[6]=6)入队；
 *                  此时窗口内有三个元素，需要在队列中获取最大元素（队头）---6
 * 7:dQueue={7}     队尾(arr[6]=6) < (arr[7]=7), (arr[6]=6) 出队，(arr[7]=7)入队；
 *                  此时窗口内有三个元素，需要在队列中获取最大元素（队头）---7
 * 数组遍历完成输出结果
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
