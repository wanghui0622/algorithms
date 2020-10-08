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

    /**
     * 后缀表达式计算入口
     * @param ss    完全括号中缀表达式
     * @return  计算结果
     */
    public static double evaluate(String ss){
        return evaluatePostfix(infixToPostfix(ss));
    }

    /**
     * 后续表达式计算
     * @param ss    后缀表达式
     * @return      计算结果
     */
    private static double evaluatePostfix(String ss){

        Stack<Double> stack = new Stack<Double>(); //计算中间过程存栈
        /*
         * 计算逻辑
         * 遇到操作数直接入栈
         * 遇到操作符，从栈中弹出两个栈顶操作数，计算后再入栈
         * 遍历结束（计算结束）栈中只有一个元素（计算结果）
         */
        for (int i=0; i<ss.length(); i++) {
            String s = String.valueOf(ss.charAt(i));
            switch (s) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    stack.push(-stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    Double divisor = stack.pop();
                    Double dividend = stack.pop();
                    stack.push(dividend / divisor);
                    break;
                default:
                    stack.push(Double.parseDouble(s));
                    break;
            }
        }
        return stack.pop();
    }

    /**
     * 算术表达式从中缀转换为后缀规则：
     * 从左到右遍历中缀表达式的每个数字和符号
     * 若是数字就输出，即成为后缀表达式的一部分；
     * 若是左括号，直接忽略
     * 若是右括号，取出栈顶操作符拼接到后缀表达式字符串尾部
     * 若是操作符，直接入栈
     * (2+((3+4)*(5*6)))  --->   2 3 4 + 5 6 * * +
     * @param ss    完全括号中缀表达式
     * @return      后缀表达式
     */
    private static String infixToPostfix(String ss) {

        StringBuilder postFix = new StringBuilder(); // 后缀表达式
        Stack<String> stack = new Stack<>(); //转换后缀表达式的辅助堆栈
        for (int i=0; i<ss.length(); i++) {
            String s = String.valueOf(ss.charAt(i));
            switch (s) {
                case "+":
                case "-":
                case "*":
                case "/":
                    stack.push(s);
                    break;
                case ")":
                    postFix.append(stack.pop());
                    break;
                case "(":
                    ;
                    break;
                default:
                    postFix.append(s);
                    break;
            }
        }
        return postFix.toString();
    }

    public static void main(String[] args) {
        String ss = "(2+((3-4)*(6/5)))";
        System.out.println(evaluate(ss));
    }
}
