package datastructure.demo.queue;

import java.util.Stack;

/**
 * 两个堆栈实现一个队列
 * @author 王辉
 * @create 2020-07-24 23:13
 * @Description
 * 思路：一个堆栈用于入队，一个堆栈用于出队
 * 当入队时，直接将数据压入入队堆栈
 * 当出队或获取队顶元素时，判断出队堆栈是否为空，如果为空则现将入队堆栈中的数据全部压入出队堆栈中，再出队。否则直接出队。
 *
 * 堆栈：先进后出
 * 队列：先进先出
 */
public class TwoStacksImplementQueue {

    public static class TwoStacksQueue {
        public Stack<Integer> stackPush;
        public Stack<Integer> stackPop;

        public TwoStacksQueue() {
            stackPush = new Stack<Integer>();
            stackPop = new Stack<Integer>();
        }

        //入队
        public void add(int pushInt) {
            stackPush.push(pushInt);
        }

        //出队
        public int poll() {
            addStackPop();
            return stackPop.pop();
        }

        //获取队顶元素
        public int peek() {
            addStackPop();
            return stackPop.peek();
        }

        //出队或获取队顶元素前操作
        private void addStackPop() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            } else if (stackPop.empty()) {
                while (!stackPush.empty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }
    }

    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }
}
