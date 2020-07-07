package datastructure.demo.queue;

import datastructure.Stack;

import java.util.NoSuchElementException;

/**
 * 两个堆栈实现一个队列
 * @auther 王辉
 * @create 2020-07-04 22:50
 * @Description
 * s1:入队时，元素永远只存在s1中；出队时，元素永远从s2中出队。
 * 出队时，如遇到s2为空，一次性将s1压如到s2，然后再出队
 */
public class QueueWithTwoStacks <Item> {
    private Stack<Item> stack1;    // back of queue
    private Stack<Item> stack2;    // front of queue

    // 空队列
    public QueueWithTwoStacks() {
        stack1 = new Stack<Item>();
        stack2 = new Stack<Item>();
    }

    // 从stack1 移动所有元素到 stack2
    private void moveStack1ToStack2() {
        while (!stack1.isEmpty())
            stack2.push(stack1.pop());
    }

    // 是否为空队列
    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }


    // 返回队列大小
    public int size() {
        return stack1.size() + stack2.size();
    }

    // 队顶元素
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        if (stack2.isEmpty()) moveStack1ToStack2();
        return stack2.peek();
    }

    // 添加元素
    public void enqueue(Item item) {
        stack1.push(item);
    }

    // 移除队顶元素
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        if (stack2.isEmpty()) moveStack1ToStack2();
        return stack2.pop();
    }
}