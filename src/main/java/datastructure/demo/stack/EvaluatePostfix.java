package datastructure.demo.stack;

import datastructure.Stack;

/**
 * 后缀表达式计算(算式必须是完全括号表达式)
 * @author 王辉
 * @create 2020-07-03 22:11
 * @Description  后缀表达式：不包含括号，运算符放在两个运算对象的后面，所有的计算按运算符出现的顺序，
 *               严格从左向右进行（不再考虑运算符的优先规则）

 * (2+((3+4)*(5*6)))  --->   2 3 4 + 5 6 * * +
 * (((5+(7*(1+1)))*3)+(2*(1+1))) ---->   5 7 1 1 + * + 3 * 2 1 1 + * +
 */
public class EvaluatePostfix  {
    public static void main(String[] args) {
        String ss = "(2+((3-4)*(6/5)))";
        System.out.println(evaluatePostfix(infixToPostfix(ss)));
    }

    /**
     * 后续表达式计算
     * @param ss
     * @return
     */
    public static Integer evaluatePostfix(String ss){

        Stack<Integer> stack = new Stack<Integer>();
        for (int i=0; i<ss.length(); i++) {
            String s = String.valueOf(ss.charAt(i));
            if      (s.equals("+")) stack.push(stack.pop() + stack.pop());
            else if (s.equals("-")) stack.push(-stack.pop() + stack.pop());
            else if (s.equals("*")) stack.push(stack.pop() * stack.pop());
            else if (s.equals("/")) {
                Integer divisor = stack.pop();
                Integer dividend = stack.pop();
                stack.push(dividend / divisor);
            } else stack.push(Integer.parseInt(s));
        }
        return stack.pop();
    }

    /**
     * 算术表达式从中缀转换为后缀
     * 规则：从左到右遍历中缀表达式的每个数字和符号，
     * 若是数字就输出，即成为后缀表达式的一部分；
     * 若是符号，则判断其与栈顶符号的优先级，
     *  是右括号或优先级低于栈顶符号（乘除优先加减）
     *  则栈顶元素依次出找并输出，并将当前符号进栈，一直到最终输出后缀表达式为止。
     *
     * @param ss
     * @return
     */
    public static String infixToPostfix(String ss) {

        StringBuilder postFix = new StringBuilder();

        Stack<String> stack = new Stack<>();
        for (int i=0; i<ss.length(); i++) {
            String s = String.valueOf(ss.charAt(i));
            if      (s.equals("+") || s.equals("-")) stack.push(s);
            else if (s.equals("*") || s.equals("/")) stack.push(s);
            else if (s.equals(")")) postFix.append(stack.pop());
            else if (s.equals("(")) ;
            else                   postFix.append(s);
        }
        return postFix.toString();
    }
}
