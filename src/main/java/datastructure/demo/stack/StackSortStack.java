package datastructure.demo.stack;

import java.util.Stack;

/**
 * 栈排序
 * @author 王辉
 * @create 2020-07-26 11:54
 * @Description
 * 只申请一个辅助栈和必要的变量实现栈从栈顶到底按照由大到小的顺序排序
 *
 * 首先实现：辅助栈从栈顶到栈底元素由小到大；
 * 然后，将辅助栈元素依次压入原栈，即可实现栈排序
 *
 */
public class StackSortStack {

    public static void sortStackByStack(Stack<Integer> stack) {

        Stack<Integer> help = new Stack<Integer>();
        //遍历stack所有元素
        while (!stack.isEmpty()) {
            //获取stack栈顶元素
            int cur = stack.pop();
            //当cur大于辅助栈中的元素时，将辅助栈中的元素压回stack
            while (!help.isEmpty() && help.peek() < cur) {
                stack.push(help.pop());
            }
            //只有辅助栈为空或者辅助栈栈顶元素大于cur时，才将cur压入到辅助栈
            //只有这样才能保证从顶到底由小到大
            help.push(cur);
        }
        while (!help.isEmpty()) {
            stack.push(help.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(3);
        stack.push(1);
        stack.push(6);
        stack.push(2);
        stack.push(5);
        stack.push(4);
        sortStackByStack(stack);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }
}
