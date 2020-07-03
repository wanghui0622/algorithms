package datastructure.demo.stack;

import datastructure.Stack;

import static sun.java2d.cmm.ColorTransform.In;

/**
 * 平衡字符串
 * @auther 王辉
 * @create 2020-07-02 23:53
 * @Description 它从标准输入中按顺序读取左右括号，大括号和方括号的顺序，并使用堆栈来确定顺序是否正确平衡。
 * 例如，输入：[()]{}{[()()]()}打印true， [(])打印false
 * 思路：
 * 如果是左括号（大括号、方括号），则入栈操作，如果遇到右括号（大括号、方括号），则判断堆栈中是否有符号，如果没有，则肯定不对称，
 * 如果有，则堆顶不是对应的左括号，则不对称；
 * 如果有，且堆顶是对应的左括号，推出；继续
 */
public class Parentheses {
    private static final char LEFT_PAREN     = '(';
    private static final char RIGHT_PAREN    = ')';
    private static final char LEFT_BRACE     = '{';
    private static final char RIGHT_BRACE    = '}';
    private static final char LEFT_BRACKET   = '[';
    private static final char RIGHT_BRACKET  = ']';

    public static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == LEFT_PAREN)   stack.push(LEFT_PAREN);
            if (s.charAt(i) == LEFT_BRACE)   stack.push(LEFT_BRACE);
            if (s.charAt(i) == LEFT_BRACKET) stack.push(LEFT_BRACKET);

            if (s.charAt(i) == RIGHT_PAREN) {
                if (stack.isEmpty())           return false;
                if (stack.pop() != LEFT_PAREN) return false;
            }

            else if (s.charAt(i) == RIGHT_BRACE) {
                if (stack.isEmpty())           return false;
                if (stack.pop() != LEFT_BRACE) return false;
            }

            else if (s.charAt(i) == RIGHT_BRACKET) {
                if (stack.isEmpty())             return false;
                if (stack.pop() != LEFT_BRACKET) return false;
            }
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        System.out.println("[[]] : " + isBalanced("[[]]"));
        System.out.println("[()]{}{[()()]()}  : " + isBalanced("[()]{}{[()()]()}"));
        System.out.println("[{]}  : " + isBalanced("[{]}"));
    }
}
