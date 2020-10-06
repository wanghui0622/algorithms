package datastructure.demo.queue;

import datastructure.Queue;

/**
 * 约瑟夫斯问题
 * @author 王辉
 * @create 2020-07-03 23:35
 * @Description 在古代的约瑟夫斯问题中，N 人处于困境，并同意采取以下减少人口的策略：
 * 他们将自己排成一个圈（从0到N-1的位置）并绕圈前进，消灭了第M个人，直到只剩下一个人。
 *  * 举例：七个人 A B C D E F G，构成一个圈，序号为2的被淘汰
 *  * 第一轮：B被淘汰，此时顺序变为：C D E F G A
 *  * 第二轮：D被淘汰，此时顺序变为：E F G A C
 *  * 第三轮：F被淘汰，此时顺序变为：G A C E
 *  * 第四轮：A被淘汰，此时顺序变为：C E G
 *  * 第五轮：E被淘汰，此时顺序变为：G C
 *  * 第六轮：C被淘汰，此时仅剩1名幸存者G.
 *
 * 思路：
 * 计数开始，没有自杀的人，位置移动到队尾，直到遇到需要自杀位置的人，然后从该位置后一个位置的人，开始重新计数。
 * 重复以上操作，直到剩下一个人为止。
 * 该情景正好符合队列先进先出的原则，符合条件的人直接出队，不符合条件的人出队后再入队。
 */
public class Josephus {
    public static void main(String[] args) {
        /*int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);*/
        int m = 2;
        int n = 7;
        System.out.println("游戏中共有:[" + n + "人，自杀位置是:[" + m + "]");
        System.out.print("依次自杀的人是: ");

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
        System.out.println();
        System.out.println("最后的幸存者是:" + queue.peek());
    }
}
