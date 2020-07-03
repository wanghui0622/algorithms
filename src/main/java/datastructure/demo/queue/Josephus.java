package datastructure.demo.queue;

import datastructure.Queue;

/**
 * 约瑟夫斯问题
 * @auther 王辉
 * @create 2020-07-03 23:35
 * @Description 在古代的约瑟夫斯问题中，N 人处于困境，并同意采取以下减少人口的策略。
 * 他们将自己排成一个圈（从0到N-1的位置）并绕圈前进，消灭了第M个人，直到只剩下一个人。
 */
public class Josephus {
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);

        // 初始化队列
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);

        while (!queue.isEmpty() && queue.size() != 1) {
            //不等于m的元素重新入队
            for (int i = 0; i < m-1; i++)
                queue.enqueue(queue.dequeue());
            System.out.print(queue.dequeue() + " ");
        }
        System.out.println("幸存者：" + queue.peek());
    }
}
