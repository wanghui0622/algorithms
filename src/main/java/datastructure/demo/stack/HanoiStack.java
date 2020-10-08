package datastructure.demo.stack;

import java.util.Stack;

/**
 * 汉诺塔
 *
 * @author 王辉
 * @create 2020-07-26 12:04
 * @Description
 * 每次只能移动一个塔柱，即不能直接从右侧到左侧，也不能从左侧到右侧，必须借助中间塔柱。目标：塔从最左侧塔柱移动到最右侧塔柱。
 * 递归实现
 * 非递归实现两种方案
 */
public class HanoiStack {

    /**
     * 递归实现汉诺塔
     *
     * @param num   汉诺塔的层高
     * @param left  左侧塔柱名称标识
     * @param mid   中间塔柱名称标识
     * @param right 右侧塔柱名称标识
     * @return 返回完成任务所需的步骤次数
     */
    public static int hanoiProblemByRecursion(int num, String left, String mid, String right) {
        if (num < 1) {
            return 0;
        }
        //从最左侧移动到最右侧
        return process(num, left, mid, right, left, right);
    }

    /**
     * 递归实现汉诺塔具体逻辑
     *
     * @param num   汉诺塔的层高
     * @param left  左侧塔柱名称标识
     * @param mid   中间塔柱名称标识
     * @param right 右侧塔柱名称标识
     * @param from  移动起始位置的塔柱标识----塔元素开始所在的位置
     * @param to    移动结束位置的塔柱标识----塔元素结束所在的位置
     * @return  返回完成任务所需的步骤次数，并打印输出步骤
     */
    public static int process(int num, String left, String mid, String right, String from, String to) {

        boolean bOneStep = from.equals(mid) || to.equals(mid);// 从from到to位置只需要一步
        //移动的塔层数为1时
        if (num == 1) {
            if (bOneStep) {
                System.out.println("Move 1 from " + from + " to " + to);
                return 1;
            } else {
                System.out.println("Move 1 from " + from + " to " + mid);
                System.out.println("Move 1 from " + mid + " to " + to);
                return 2;
            }
        }

        if (bOneStep) {
            /*
             * 将num层塔从from移动至to，其中有一个是中间塔柱：
             * Step1 [1,num-1]层塔从from移动到another
             * Step2 num层塔从from移动到to
             * Step3 [1,num-1]层塔从another移动到to
             */
            String another = (from.equals(left) || to.equals(left)) ? right : left;//确定from、to之外的塔柱
            int part1 = process(num - 1, left, mid, right, from, another);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + to);
            int part3 = process(num - 1, left, mid, right, another, to);
            //返回num层塔从from移动到to需要的步数
            return part1 + part2 + part3;
        } else {
            /*
             * 将num层塔从from移动至to：
             * Step1、[1,num-1]层塔从from移动到to
             * Step2、num层塔从from移动到mid
             * Step3、[1,num-1]层塔从to移动到from
             * Step4、num层塔从mid移动到to
             * Step5、[1,num-1]层塔从from移动到to
             *
             */
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

    /**
     * 非递归方法实现,通过堆栈实现
     * @param num   汉诺塔的层高
     * @param left  左侧塔柱名称标识
     * @param mid   中间塔柱名称标识
     * @param right 右侧塔柱名称标识
     * @return 返回完成任务所需的步骤次数
     */
    public static int hanoiProblemByStack(int num, String left, String mid, String right) {
        Stack<Integer> lS = new Stack<Integer>(); //左塔堆
        Stack<Integer> mS = new Stack<Integer>(); //中塔堆
        Stack<Integer> rS = new Stack<Integer>(); //右塔堆
        lS.push(Integer.MAX_VALUE);
        mS.push(Integer.MAX_VALUE);
        rS.push(Integer.MAX_VALUE);
        //初始化塔均在左侧塔柱
        for (int i = num; i > 0; i--) {
            lS.push(i);
        }
        Action[] record = {Action.No};  //记录上一次移动方向
        int step = 0;   //步数统计
        while (rS.size() != num + 1) { //rS.size() == num + 1 时，塔都已移动到右侧塔柱
            /*
             * 塔移动的四种情况
             * LToM:    从左塔到中塔
             * MToL:    从中塔到左塔
             * MToR:    从中塔到右塔
             * RToM:    从右塔到中塔
             */
            step += fStackTotStack(record, Action.MToL, Action.LToM, lS, mS, left, mid);
            step += fStackTotStack(record, Action.LToM, Action.MToL, mS, lS, mid, left);
            step += fStackTotStack(record, Action.RToM, Action.MToR, mS, rS, mid, right);
            step += fStackTotStack(record, Action.MToR, Action.RToM, rS, mS, right, mid);
        }
        return step;
    }

    /**
     * 移动操作
     * 可以移动的条件：小压大并且不是上一步的逆操作
     * @param record        移动方向记录集合
     * @param reverseAct    本次移动方向逆方向
     * @param nowAct        本次移动方向
     * @param fStack        移出堆栈
     * @param tStack        移入堆栈
     * @param from          来源塔柱名称
     * @param to            目标塔柱名称
     * @return  移动步数
     */
    private static int fStackTotStack(Action[] record, Action reverseAct,
                                      Action nowAct, Stack<Integer> fStack, Stack<Integer> tStack,
                                      String from, String to) {
        /*
         * 满足以下两个条件才能移动：
         * 这次移动方向，不能是上次移动的逆方向
         * 目标塔柱栈顶元素必须大于来源塔柱栈顶元素
         */
        if (record[0] != reverseAct && fStack.peek() < tStack.peek()) {
            tStack.push(fStack.pop());
            System.out.println("Move " + tStack.peek() + " from " + from + " to " + to);
            record[0] = nowAct;
            return 1;
        }
        return 0;
    }

    /**
     * 移动方向枚举
     */
    private static enum Action {
        No, LToM, MToL, MToR, RToM
    }

     public static void main(String[] args) {
        int num = 2;

        // solution 1
        int steps1 = hanoiProblemByRecursion(num, "left", "mid", "right");
        System.out.println("It will move " + steps1 + " steps.");
        System.out.println("===================================");
        // solution 2
        int steps2 = hanoiProblemByStack(num, "left", "mid", "right");
        System.out.println("It will move " + steps2 + " steps.");
        System.out.println("===================================");

    }
}
