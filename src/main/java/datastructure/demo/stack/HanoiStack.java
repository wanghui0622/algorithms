package datastructure.demo.stack;

import java.util.Stack;

/**
 * 汉诺塔
 * @auther 王辉
 * @create 2020-07-26 12:04
 * @Description
 * 每次只能移动一个塔柱，即不能直接从右侧到左侧，也不能从左侧到右侧，必须借助中间塔柱。
 * 递归实现和非递归实现两种方案
 *
 */
public class HanoiStack {

    //递归实现汉诺塔
    public static int hanoiProblem1(int num, String left, String mid,
                                    String right) {
        if (num < 1) {
            return 0;
        }
        //从最左侧移动到最右侧
        return process(num, left, mid, right, left, right);
    }

    /**
     * 递归实现汉诺塔具体逻辑，将塔从from移动到to
     *
     * @param num       汉诺塔的层高
     * @param left      左侧塔柱名称标识
     * @param mid       中间塔柱名称标识
     * @param right     右侧塔柱名称标识
     * @param from      移动起始位置的塔柱标识---塔元素开始所在的位置
     * @param to        移动结束位置的塔柱标识---塔元素结束所在的位置
     * @return  返回完成任务所需的步骤次数，并打印输出步骤
     */
    public static int process(int num, String left, String mid, String right,
                              String from, String to) {
        //当左塔只有1个元素时，根据from和to，最多移动两步
        if (num == 1) {
            if (from.equals(mid) || to.equals(mid)) {
                System.out.println("Move 1 from " + from + " to " + to);
                return 1;
            } else {
                System.out.println("Move 1 from " + from + " to " + mid);
                System.out.println("Move 1 from " + mid + " to " + to);
                return 2;
            }
        }
        //如果from或者to其中一个是mid，那么必然另外一个是left或者right
        if (from.equals(mid) || to.equals(mid)) {
            //from == mid，to == left  ：移动到左塔
            //from == mid, to == right: 移动到右塔
            //from == left, to == mid : 移动到中塔
            //from == right, to = mid : 移动到中塔

            String another = (from.equals(left) || to.equals(left)) ? right : left;
            int part1 = process(num - 1, left, mid, right, from, another);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + to);
            int part3 = process(num - 1, left, mid, right, another, to);
            return part1 + part2 + part3;
        } else {
            int part1 = process(num - 1, left, mid, right, from, to);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + mid);
            int part3 = process(num - 1, left, mid, right, to, from);
            int part4 = 1;
            System.out.println("Move " + num + " from " + mid + " to " + to);
            int part5 = process(num - 1, left, mid, right, from, to);
            return part1 + part2 + part3 + part4 + part5;
        }
    }



    //移动方向枚举
    private static enum Action {
        No, LToM, MToL, MToR, RToM
    }

    //非递归方法实现,通过堆栈实现
    public static int hanoiProblem2(int num, String left, String mid, String right) {
        Stack<Integer> lS = new Stack<Integer>(); //左塔堆
        Stack<Integer> mS = new Stack<Integer>(); //中塔堆
        Stack<Integer> rS = new Stack<Integer>(); //右塔堆
        lS.push(Integer.MAX_VALUE);
        mS.push(Integer.MAX_VALUE);
        rS.push(Integer.MAX_VALUE);
        for (int i = num; i > 0; i--) {
            lS.push(i);
        }
        Action[] record = { Action.No };
        int step = 0;
        while (rS.size() != num + 1) {
            step += fStackTotStack(record, Action.MToL, Action.LToM, lS, mS, left, mid);
            step += fStackTotStack(record, Action.LToM, Action.MToL, mS, lS, mid, left);
            step += fStackTotStack(record, Action.RToM, Action.MToR, mS, rS, mid, right);
            step += fStackTotStack(record, Action.MToR, Action.RToM, rS, mS, right, mid);
        }
        return step;
    }

    //打印移动步骤
    private static int fStackTotStack(Action[] record, Action preNoAct,
                                     Action nowAct, Stack<Integer> fStack, Stack<Integer> tStack,
                                     String from, String to) {
        if (record[0] != preNoAct && fStack.peek() < tStack.peek()) {
            tStack.push(fStack.pop());
            System.out.println("Move " + tStack.peek() + " from " + from + " to " + to);
            record[0] = nowAct;
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        int num = 2;

        // solution 1
        int steps1 = hanoiProblem1(num, "left", "mid", "right");
        System.out.println("It will move " + steps1 + " steps.");
        System.out.println("===================================");

        // solution 2
        int steps2 = hanoiProblem2(num, "left", "mid", "right");
        System.out.println("It will move " + steps2 + " steps.");
        System.out.println("===================================");

    }
}
