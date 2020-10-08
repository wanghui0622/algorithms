package datastructure.demo.stack;

import java.util.Stack;

/**
 * 使用一个栈和递归实现堆的转置
 * @author 王辉
 * @create 2020-07-24 23:21
 * @Description
 * 举例：
 * 依次压入：1 2 3 4 5
 * 由顶到底：5 4 3 2 1
 * 转 置 后：1 2 3 4 5
 *
 * 思路：
 * 从栈中获取所有元素，然后反向压入，即获取后压入顺序变为 5 4 3 2 1
 * 注意：有很多方法可以实现堆栈转置，使用迭代是其中一种方法，但是如果堆栈中元素数量较多时，可能会导致java.lang.StackOverflowError
 *
 *
 */
public class ReverseStackUsingRecursive {

    /**
     * 转置堆栈
     * @param stack 待转置堆栈
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
     * @param stack 栈
     * @return      栈底元素
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
        for(int i = 1; i <= 50000 ;i++) {
            test.add(i);
        }
        System.out.print("原栈：");
       test.forEach(item -> System.out.print(item + " "));
        System.out.println();
        reverse(test);
        System.out.print("转置：");
        test.forEach(item -> System.out.print(item + " "));

    }
}
