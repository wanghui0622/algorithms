package datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 描述:
 * 先进先出(FIFO)队列，在队尾插入元素，队顶获取元素
 * 该队列采用基于链表实现，
 * 在队列中定义三个属性：
 * 其中：
 * n:记录队列中元素数量，初始化为0，调用enqueue增1，调用dequeue减一；
 * first：指向最早添加的元素，即队顶元素
 * last : 指向最后添加的元素，即队尾元素
 * 1)当且仅当n=1时，first == last,此时指向同一个引用；
 * 2)first链表的最后一个元素 == last；
 * 使用first和last可以实现快速入队，如果只用first的话，每次入队都需要遍历找到队尾才能插入
 *
 * @author wanghui email:wanghuiaf@yonyou.com
 * @create 2020-06-29 下午2:57
 */
public class  Queue<Item> implements Iterable<Item>  {
    private Node<Item> first;    // 指向最早添加的节点
    private Node<Item> last;     // 指向最近添加的节点
    private int n;               // 队列中元素数



    /**
     * 初始化一个空队列
     */
    public Queue() {
        first = null;
        last  = null;
        n = 0;
    }

    /**
     * 队列判空
     *
     * @return 如果队列为空，返回{@code true};否则，返回 {@code false}
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * 队列元素数量
     *
     * @return n
     */
    public int size() {
        return n;
    }

    /**
     * 返回队顶元素，不移除
     *
     * @return  队顶元素
     * @throws  NoSuchElementException 如果队列为空
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    /**
     * 向队列（队尾）中添加元素
     *
     * @param  item
     */
    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        n++;
    }

    /**
     * 移除并返回对顶元素
     *
     * @return 对顶元素
     * @throws NoSuchElementException 如果队列为空
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    /**
     * 队列的字符串形式
     *
     * @return 对头的队尾元素的字符串，用空格间隔
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    // 内部类，链存储数据
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * 迭代
     *
     * @return
     */
    public Iterator<Item> iterator()  {
        return new LinkedIterator(first);
    }

    // 迭代器内部类
    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
