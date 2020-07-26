package datastructure.demo.stack;

import java.util.Stack;

/**
 * 使用一个栈和递归实现堆的转置
 * @auther 王辉
 * @create 2020-07-24 23:21
 * @Description
 * 举例：
 * 依次压入：1 2 3 4 5
 * 由顶到底：5 4 3 2 1
 * 转 置 后：1 2 3 4 5
 *
 * 思路：
 * 从栈中获取所有元素，然后反向压入，即获取后压入顺序变为 5 4 3 2 1
 *
 *
 *
 */
public class ReverseStackUsingRecursive {

    /**
     * 转置堆栈
     * @param stack
     */
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
    }

    /**
     * 获取栈底元素返回；并将其他元素重新压入栈
     * @param stack
     * @return
     */
    private static int getAndRemoveLastElement(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }

    }
}
