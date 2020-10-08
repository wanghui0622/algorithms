package datastructure.demo.stack;

import datastructure.Stack;

/**
 * @author 王辉
 * @create 2020-07-01 23:02
 * @Description 堆栈实现算数计算器
 * 1、简单整数计算：表达式运算符只有：加、减、乘、除
 * 2、使用Dijkstra的两栈算法计算完全括号算术表达式
 *    * 使用完全括号算术表达式通过括号判断优先级
 *    * 左括号忽略，遇到数字，入数字栈；遇到符号，入符号栈
 *    * 遇到右括号：数字栈中取出两个数字，从符号栈取出一个符号，计算后，将结果入栈
 */
public class Evaluate {

    /**
     * 简单整数计算
     * 表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分
     * 思路：
     * 1、加减乘除的优先级是：* / 大于 + -
     * 2、算数表达式的首尾都是数字
     * 3、定义一个存放数字的堆栈，将数字放进堆栈或者取出计算完成后放入
     * 计算后放入的规则：
     * 遇到乘法、除法符号，取出堆栈中的数字和当前数字进行乘法或除法运算，将结果放入堆栈
     * 遇到减法时，将当前数值取相反数，然后放入堆栈中
     * 4、遍历完以后，将堆栈中的数字求和即为结果。
     *
     * @param s 算数计算表达式
     * @return  计算结果
     */
    public static double calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        s = s.replace(" ", ""); //防止表达式中有空格影响计算
        Stack<Integer> num = new Stack<>();     //存储中间结果，最后求和使用
        int n = 0;  //操作数变量
        char op = '+'; //中间计算过程操作符
        for (int i = 0; i < s.length(); i++) {
            //数字0-9在ASCII中的十进制表示：48-57
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                n = n * 10 + (c - '0');
            }
            //遇到操作符或者最后一个操作数时，开始计算
            if (!Character.isDigit(c) || i == s.length() - 1) {
                int pre;
                switch (op) {
                    case '+':   //遇到加号直接入栈
                        num.push(n);
                        break;
                    case '-':  //遇到减号取相反数入栈
                        num.push(-n);
                        break;
                    case '*': //遇到乘法取出栈顶元素，乘积入栈
                        pre = num.pop();
                        num.push(pre * n);
                        break;
                    case '/': //遇到除法取出栈顶元素，乘积入栈
                        pre = num.pop();
                        num.push(pre / n);
                        break;
                }
                op = c;
                n = 0;
            }
        }
        double res = 0;
        while (!num.isEmpty()) {
            res += num.pop();
        }
        return res;
    }

    /**
     * 使用Dijkstra的两栈算法计算完全括号算术表达式
     * 该方法是完全括号表达式函数
     * 如：( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )
     * 需要使用 ((3*4)+2) 代替 3*4+2
     * 计算规则：
     * 遇到左括号(:直接跳过
     * 遇到符号+ - * / sqrt：入堆ops
     * 遇到数字：  入堆vals
     * 遇到右括号):
     * 从vals中取出堆顶：a, 再从ops中取出操作符，然后再从vals中取出堆顶 ，与 a 进行算数运算
     * 将计算结果存入vals中
     * 以 ((3*4)+2) ：说明：
     * loop1 : ( -----> 跳过
     * loop2 : ( -----> 跳过
     * loop3 : 3 -----> vals.push(3)     vars 3
     * loop4 : * -----> ops.push(*)      vars 3     ops *
     * loop5 : 4 -----> vals.push(4)     cars 4 3   ops *
     * loop6 : ) -----> a = vals.pop() = 4  cars 3   ops *
     * op = ops.pop() = *
     * vals.push(vals.pop() * 4) = vals.push(12)   vals 12 ops
     * loop7 : + -----> ops.push(+)      vars 12     ops +
     * loop8 : 2 -----> vals.push(2)     vars 2  12  ops +
     * loop9 : ) ----->a = vals.pop() = 2  cars 12   ops +
     * op = ops.pop() = +  cars 12   ops
     * vals.push(vals.pop() + 2) = vals.push(14)    vals 14 ops
     *
     * @param ss    算数表达式
     * @return      计算结果
     */
    public static double calculateByDijkstra(String ss) {

        if (ss == null || ss.length() == 0) return 0;
        ss = ss.replace(" ", "");

        //算数运算符
        Stack<String> ops = new Stack<String>();
        //操作数
        Stack<Double> vals = new Stack<Double>();

        for (int i = 0; i < ss.length(); i++) {

            String s = String.valueOf(ss.charAt(i));
            switch (s) {
                case "(":
                    ;
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "sqrt":
                    ops.push(s);
                    break;
                case ")":
                    String op = ops.pop();
                    double v = vals.pop();
                    switch (op) {
                        case "+":
                            v = vals.pop() + v;
                            break;
                        case "-":
                            v = vals.pop() - v;
                            break;
                        case "*":
                            v = vals.pop() * v;
                            break;
                        case "/":
                            v = vals.pop() / v;
                            break;
                        case "sqrt":
                            v = Math.sqrt(v);
                            break;
                    }
                    vals.push(v);
                    break;
                default:
                    vals.push(Double.parseDouble(s));
                    break;
            }
        }

        return vals.pop();
    }

    public static void main(String[] args) {
        System.out.println(Evaluate.calculate("3*4+2-1"));
        System.out.println(Evaluate.calculateByDijkstra("(((3*4)+2)-1)"));
    }
}
