package datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 描述: 包,只进不出的数据结构
 *
 * @author wanghui email:wanghuiaf@yonyou.com
 * @create 2020-04-29 上午11:35
 */
public class Bag<Item> implements Iterable<Item> {
    private Node<Item> first;    // 包开始节点
    private int n;               //包中元素数

    /**
     * 初始化空包
     */
    public Bag() {
        first = null;
        n = 0;
    }

    /**
     * 是否为空包
     *
     * @return 如果是空包返回：{@code true};否则返回：{@code false}
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * 返回包中元素数量
     *
     * @return n
     */
    public int size() {
        return n;
    }

    /**
     * 向包中添加元素
     *
     * @param  item 待添加元素
     */
    public void add(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }


    /**
     * 返回一个迭代器，该迭代器以任意顺序遍历此包中的项。
     *
     * @return 返回迭代器中的一个任意项
     */
    public Iterator<Item> iterator()  {
        return new LinkedIterator(first);
    }


    // 内部类，链存储数据
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    // 一个简单的链表迭代器内部类
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