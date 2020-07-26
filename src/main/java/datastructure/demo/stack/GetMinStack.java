package datastructure.demo.stack;

import java.util.Stack;

/**
 * 实现栈的基本功能，并且可以返回栈中最小元素时间复杂度O(1)
 * @auther 王辉
 * @create 2020-07-24 22:52
 * @Description 通过两个栈来实现该功能，第一个栈用于存放数据，第二个存放数据栈中最小的数的集合。
 * 举例 ： 依次入栈 1 3 2 4 1
 * 则：数据栈从栈顶到栈底 1 4 2 3 1
 *    存放最小数的栈：1 1或1 1 1 1 1
 *
 * 以下两种实现方式：
 * 方式1、入栈节省空间，但是弹出时需要比较耗时（时间置换空间）
 * 方式2、入栈耗费空间，但是弹出时不需要比较（空间置换时间）
 */
public class GetMinStack {
    /**
     * 实现方式1
     * 入栈操作时，
     * 如果stackData 和 stackMin均为空，则均做入栈操作；
     * 如果如果stackMin不为空，
     * 则获取stackMin中最小数（即栈顶元素）比较，如果小于栈顶元素，stackMin同样入栈操作；否则，不操作。
     *
     * 出栈时，
     * 如果stackData栈顶元素与stackMin元素相同，则均做出栈操作，否则，只对stackData做出栈操作。
     */
    public static class MyStack1 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack1() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }
        public void push(int newNum) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            } else if (newNum <= this.getmin()) {
                this.stackMin.push(newNum);
            }
            this.stackData.push(newNum);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            int value = this.stackData.pop();
            if (value == this.getmin()) {
                this.stackMin.pop();
            }
            return value;
        }

        /**
         * 获取最小值，不能做出栈操作
         * @return
         */
        public int getmin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }

    /**
     * 实现方式2
     * 入栈操作时，
     * 如果stackData 和 stackMin均为空，则均做入栈操作；
     * 如果如果stackMin不为空，
     * 则获取stackMin中最小数（即栈顶元素）比较，如果小于栈顶元素，stackMin同样入栈操作；
     * 否则，取最小值再次入栈
     *
     * 出栈时，
     * 无论stackData栈顶元素与stackMin元素是否相同，均做出栈操作。
     */
    public static class MyStack2 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack2() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int newNum) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            } else if (newNum < this.getmin()) {
                this.stackMin.push(newNum);
            } else {
                int newMin = this.stackMin.peek();
                this.stackMin.push(newMin);
            }
            this.stackData.push(newNum);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            this.stackMin.pop();
            return this.stackData.pop();
        }

        public int getmin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }

    public static void main(String[] args) {
        MyStack1 stack1 = new MyStack1();
        stack1.push(3);
        System.out.println(stack1.getmin());
        stack1.push(4);
        System.out.println(stack1.getmin());
        stack1.push(1);
        System.out.println(stack1.getmin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getmin());

        System.out.println("=============");

        MyStack1 stack2 = new MyStack1();
        stack2.push(3);
        System.out.println(stack2.getmin());
        stack2.push(4);
        System.out.println(stack2.getmin());
        stack2.push(1);
        System.out.println(stack2.getmin());
        System.out.println(stack2.pop());
        System.out.println(stack2.getmin());
    }
}
