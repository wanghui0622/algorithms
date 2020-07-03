package datastructure.demo.stack;

import datastructure.Stack;

import java.util.TreeMap;

/**
 * 中缀表达式计算
 * @auther 王辉
 * @create 2020-07-03 23:56
 * @Description
 * 计算思路：
 * 遇到数字：放入数字堆
 * 遇到符号：+ - * / 左括号， 根据规则放入或取出已有符号
 *  规则：
 *      如果符号栈为空、左括号、当前符号优先级高于堆顶符号，则直接放入
 *      如果符号为右括号，则此时符号栈的栈顶一定是左括号，取出
 *      如果符号优先级低于或等于栈顶符号，则需要从操作数栈取出两个操作数，并取出栈顶符号，将计算结果放入操作数栈；
 *      重复上一步，直到符号优先级高于栈顶符号，将符号入栈
 * 中缀表达式遍历完成后，如果符号栈不为空，则需要继续计算
 * 规则：
 *      依次从操作数栈取两个操作数，并取栈顶符号，将计算结果存放到操作数栈
 *      重复上一步直到符号栈为空，此时操作数栈只有一个元素，即计算结果
 * 特别注意：
 * 在计算时，从操作数栈取的第一个数在操作符的右边，第二数在操作符的左边。
 *
 * 3 + 5 * 6 - 7 * ( 8 + 5 ) ---->  -58.0
 */
public class EvaluateDeluxe {

    // 运算符的优先顺序
    private static TreeMap<String, Integer> precedence = new TreeMap<>();
    static {
        precedence.put("(", 0);
        precedence.put(")", 0);
        precedence.put("+", 1);
        precedence.put("-", 1);
        precedence.put("*", 2);
        precedence.put("/", 2);
    }

    /**
     *
     * @param ss
     * @return
     */
    public static double evaluateDeluxe(String ss){

        if(ss==null||ss.length()==0)return 0;
        ss=ss.replace(" ","");

        Stack<String> ops  = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();

        for(int i=0;i<ss.length();i++) {
            //s可能是操作数、也有可能是运算符
            String s =  String.valueOf(ss.charAt(i));
            // 如果是操作数，放入vals
            if (!precedence.containsKey(s)) {
                vals.push(Double.parseDouble(s));
                continue;
            }
            // 如果是操作符
            while (true) {
                // 最后一个条件确保优先级较高的运算符被优先计算
                if (ops.isEmpty() || s.equals("(") || (precedence.get(s) > precedence.get(ops.peek()))) {
                    ops.push(s);
                    break;
                }
                //计算表达式
                String op = ops.pop();
                // 忽略左括号
                if (op.equals("(")) {
                    assert s.equals(")");
                    break;
                }
                // 计算运算符和两个操作数，并将结果压入值栈
                else {
                    double val2 = vals.pop();
                    double val1 = vals.pop();
                    vals.push(eval(op, val1, val2));
                }
            }
        }

        // 完成解析字符串——仍有运算符和操作数保留在两个堆栈上
        while (!ops.isEmpty()) {
            String op = ops.pop();
            double val2 = vals.pop();
            double val1 = vals.pop();
            vals.push(eval(op, val1, val2));
        }

        // 堆栈(vals)上的最后一个值是表达式的值
        double result = vals.pop();
        assert vals.isEmpty();
        assert ops.isEmpty();
        return result;
    }


    /**
     * 两个操作数的算数运算
     * @param op        运算符
     * @param val1      操作数1
     * @param val2      操作数2
     * @return
     */
    private static double eval(String op, double val1, double val2) {
        if (op.equals("+")) return val1 + val2;
        if (op.equals("-")) return val1 - val2;
        if (op.equals("/")) return val1 / val2;
        if (op.equals("*")) return val1 * val2;
        throw new RuntimeException("不支持的算数运算符");
    }

    public static void main(String[] args) {
    }
}
