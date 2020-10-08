package datastructure.demo.stack;

import java.util.Stack;

/**
 * 栈排序
 *
 * @author 王辉
 * @create 2020-07-26 11:54
 * @Description 要求:
 * 只申请一个辅助栈和必要的变量实现栈从【栈顶】到【栈底】按照【由大到小】的顺序排序
 * 举例：堆栈排序前：3 1 6 2 5 4；排序后，1 2 3 4 5 6
 * <p>
 * 分析：
 * 首先,辅助栈从栈顶到栈底元素由小到大；
 * 然后,将辅助栈元素依次压入原栈，即可实现栈排序
 */
public class StackSortStack {

    public static void sortStackByStack(Stack<Integer> stack) {

        Stack<Integer> helpS = new Stack<Integer>(); //辅助栈
        //遍历stack所有元素
        while (!stack.isEmpty()) {
            int cur = stack.pop(); //获取stack栈顶元素
            //当cur大于辅助栈(helpS)中的元素时，将辅助栈中的元素压回stack
            while (!helpS.isEmpty() && helpS.peek() < cur) {
                stack.push(helpS.pop());
            }
            //只有辅助栈为空或者辅助栈(helpS)栈顶元素大于cur时，才将cur压入到辅助栈
            helpS.push(cur);
        }
        while (!helpS.isEmpty()) {
            stack.push(helpS.pop());
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
        System.out.print("排序前：");
        stack.forEach(item -> System.out.print(item + " "));
        System.out.println();
        sortStackByStack(stack);
        System.out.print("排序后：");
        stack.forEach(item -> System.out.print(item + " "));
    }
}
