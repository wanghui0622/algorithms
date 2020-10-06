package datastructure;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * 双向链表
 *
 * @author 王辉
 * @create 2020-07-04 13:00
 * @Description
 */
public class DoublyLinkedList<Item> implements Iterable<Item> {
    private int n;        // 元素数
    private Node pre;     // 头结点：链表的root（根）位置，第一个元素之前，pre.item=null，pre.prev = null
    private Node post;    // 尾结点：链表的尾位置，最后一个元素的之后, pre.item=null,item.next = null

    public DoublyLinkedList() {
        pre = new Node();
        post = new Node();
        pre.next = post;
        post.prev = pre;
    }

    // 双向链表辅助类：相比于单向俩边，每个node都有要给前驱节点和后驱节点
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    // 向链表中插入元素
    public void add(Item item) {
        Node last = post.prev;
        Node x = new Node();
        x.item = item;
        x.next = post;
        x.prev = last;
        post.prev = x;
        last.next = x;
        n++;
    }

    public ListIterator<Item> iterator() {
        return new DoublyLinkedListIterator();
    }

    /**
     * 假设在迭代期间没有调用DoublyLinkedList.add()
     * 实现一个双向链表迭代器
     */
    private class DoublyLinkedListIterator implements ListIterator<Item> {
        private Node current = pre.next;  // next()返回的节点
        private Node lastAccessed = null;      // 由prev()或next()返回的最后一个节点
        // 在插入remove()或add()时重置为null
        private int index = 0;

        /**
         * 下一个元素是否存在
         * @return
         */
        public boolean hasNext() {
            return index < n;
        }

        /**
         * 是否存在前一个元素
         * @return
         */
        public boolean hasPrevious() {
            return index > 0;
        }

        /**
         * 下一个元素索引
         * @return
         */
        public int nextIndex() {
            return index;
        }

        /**
         * 上一个元素索引
         * @return
         */
        public int previousIndex() {
            return index - 1;
        }

        /**
         * 获取下一个元素
         *
         * @return   最后访问元素的下一个元素
         */
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            lastAccessed = current;
            Item item = current.item;
            current = current.next;
            index++;
            return item;
        }

        /**
         * 获取上一个元素
         *
         * @return   最后访问元素的上一个元素
         */
        public Item previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            current = current.prev;
            index--;
            lastAccessed = current;
            return current.item;
        }

        /**
         * 没有调用remove() or add()的前提下，设置被next() or previous()最后访问的元素
         * 前提：在最后一次调用next()或previous()之后没有调用remove()或add()
         */
        public void set(Item item) {
            if (lastAccessed == null) throw new IllegalStateException();
            lastAccessed.item = item;
        }

        /**
         * 删除最后被next()或previous()访问的元素
         * 前提：在最后一次调用next()或previous()之后没有调用remove()或add()
         */
        public void remove() {
            if (lastAccessed == null) throw new IllegalStateException();
            Node x = lastAccessed.prev;
            Node y = lastAccessed.next;
            x.next = y;
            y.prev = x;
            n--;
            if (current == lastAccessed)
                current = y;
            else
                index--;
            lastAccessed = null;
        }

        /**
         * 添加一个元素到链表
         *
         * @param item
         */
        public void add(Item item) {
            Node x = current.prev;
            Node y = new Node();
            Node z = current;
            y.item = item;
            x.next = y;
            y.next = z;
            z.prev = y;
            y.prev = x;
            n++;
            index++;
            lastAccessed = null;
        }

    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }

    public static void main(String[] args) {
        int n = 5;

        //初始化双向链表
        System.out.println(n + " 0-99间的随机数");
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        for (int i = 0; i < n; i++)
            list.add((int) (Math.random() * 100));
        System.out.println(list);
        System.out.println();

        //双向链表迭代器
        ListIterator<Integer> iterator = list.iterator();

        // 使用next()和set()链表方向遍历
        System.out.println("通过next()和set()向每个元素添加1");
        while (iterator.hasNext()) {
            int x = iterator.next();
            iterator.set(x + 1);
        }
        System.out.println(list);
        System.out.println();

        // 使用next()和set()
        System.out.println("通过previous()和set()将每个元素乘以3");
        while (iterator.hasPrevious()) {
            int x = iterator.previous();
            iterator.set(x + x + x);
        }
        System.out.println(list);
        System.out.println();


        // 使用 next() and remove()
        System.out.println("通过next()和remove()删除所有4的倍数元素");
        while (iterator.hasNext()) {
            int x = iterator.next();
            if (x % 4 == 0) iterator.remove();
        }
        System.out.println(list);
        System.out.println();


        //  previous() and remove()
        System.out.println("通过previous()和remove()删除元素");
        while (iterator.hasPrevious()) {
            int x = iterator.previous();
            if (x % 2 == 0) iterator.remove();
        }
        System.out.println(list);
        System.out.println();


        // next() and add()
        System.out.println("通过next() and add()添加元素");
        while (iterator.hasNext()) {
            int x = iterator.next();
            iterator.add(x + 1);
        }
        System.out.println(list);
        System.out.println();

        // previous() and add()
        System.out.println("previous() and add()添加元素");
        while (iterator.hasPrevious()) {
            int x = iterator.previous();
            iterator.add(x * 10);
            iterator.previous();
        }
        System.out.println(list);
        System.out.println();
    }
}

