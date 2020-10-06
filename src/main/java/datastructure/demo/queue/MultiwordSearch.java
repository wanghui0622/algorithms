package datastructure.demo.queue;

import datastructure.Queue;

/**
 * 多字搜索
 * @author 王辉
 * @create 2020-07-04 23:03
 * @Description 在包含查询词的输入文件中查找在命令行上指定的顺序的最短间隔(单词数)。
 * 读取查询词q[1]，...，q[k]的序列，并从标准输入和输入中读取文档词d[1]，...，d[N]的序列。
 * 查找k个单词以相同顺序出现的最短间隔。（这里最短表示间隔中的单词数。）
 * 即找到索引i和j，使得d [i1] = q [1]，d [i2] = q [2]，...，d [ik] = q [k]且i1 <i2 <... <ik。
 *
 */
public class MultiwordSearch {
    public static void main(String[] args) {
        String[] words = null;

        Queue<Integer>[] queues = (Queue<Integer>[]) new Queue[args.length];
        for (int j = 0; j < args.length; j++) {
            queues[j] = new Queue<Integer>();
        }
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < args.length; j++) {
                if (words[i].equals(args[j])) queues[j].enqueue(i);
            }
        }

        // repeatedly find smallest interval starting at position of queues[0]
        boolean done = false;
        int bestlo = -1, besthi = words.length;
        while (!queues[0].isEmpty()) {
            int lo = queues[0].dequeue();
            int hi = lo;
            for (int j = 1; j < args.length; j++) {
                while (!queues[j].isEmpty() && queues[j].peek() <= hi) {
                    queues[j].dequeue();
                }
                if (queues[j].isEmpty())  {
                    done = true;
                    break;
                }
                else hi = queues[j].peek();
            }
            if (!done && hi - lo < besthi - bestlo) {
                besthi = hi;
                bestlo = lo;
            }

        }

        if (bestlo >= 0) {
            for (int i = bestlo; i <= besthi; i++)
                System.out.print(words[i] + " ");
            System.out.println();
        }
        else
            System.out.println("NOT FOUND");
    }
}
