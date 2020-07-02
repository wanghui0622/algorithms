package datastructure.demo.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 固定容量的字符串堆栈
 * @auther 王辉
 * @create 2020-07-02 22:46
 * @Description 使用数组实现固定容量的字符串堆栈
 */
public class FixedCapacityStackOfStrings implements Iterable<String> {
    private String[] a;  // holds the items
    private int N;       // number of items in stack

    // create an empty stack with given capacity
    public FixedCapacityStackOfStrings(int capacity) {
        a = new String[capacity];
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean isFull() {
        return N == a.length;
    }

    public void push(String item) {
        a[N++] = item;
    }

    public String pop() {
        return a[--N];
    }

    public String peek() {
        return a[N - 1];
    }

    public Iterator<String> iterator() {
        return new ReverseArrayIterator();
    }


    public class ReverseArrayIterator implements Iterator<String> {
        private int i = N - 1;

        public boolean hasNext() {
            return i >= 0;
        }

        public String next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[i--];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
