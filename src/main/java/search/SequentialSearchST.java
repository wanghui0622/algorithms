package search;

import java.util.HashSet;
import java.util.Set;

/**
 * 描述: 基于无需链表的顺序查找
 *
 * @author wanghui email:wanghuiaf@yonyou.com
 * @create 2020-04-17 下午1:48
 */
public class SequentialSearchST<Key, Value> {

    public static void main(String[] arg) {

        String[] a = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
        SequentialSearchST<String, Integer > aa = new SequentialSearchST<String, Integer >();
        for(int i = 0; i < a.length; i++){
            aa.put(a[i], i);
        }

        aa.delete("L");
        System.out.println("AAA");

    }
    private Node first;
    private int size = 0;

    /**
     * 获取
     * @param key
     * @return
     */
    public Value get(Key key) {
        for(Node x = first; x != null; x = x.next){
            if(key.equals(x.key)) {
                return x.value;
            }
        }
        return null;
    }

    /**
     * 插入
     * @param key
     * @param value
     */
    public void put(Key key , Value value) {
        for(Node x = first; x != null; x = x.next){
            if(key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        size++;
        first = new Node(key, value, first);
    }

    /**
     * 删除
     * @param key
     */
    public void delete(Key key) {
        //节点为空时
        if(first == null) {
            throw new RuntimeException("链表为空");
        }else {
            if(key.equals(first.key)) {
                first = first.next;
                size--;
                return;
            }
        }

        Node node = first;

        Node fatherNode = null;
        Node deleteNode = null;
        while(node.next != null) {
            if(key.equals(node.next.key)) {
                fatherNode = node;
                deleteNode = node.next;
                break;
            }
            node = node.next;
        }
        if(fatherNode != null) {
            fatherNode.next = deleteNode.next;
            size--;
        }else {
            throw new RuntimeException("没有找到该节点:" + key);
        }

    }

    /**
     * 获取键的集合
     * @return
     */
    public Set<Key> keys(){
        if(first == null) {
            return null;
        }
        Set<Key> keys = new HashSet<Key>();
        for(Node x = first; x != null; x = x.next){
            keys.add(x.key);
        }
        return keys;
    }


    /**
     * 链表大小
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * 定义链表内部类；通过链表实现符号表
     */
    private class Node{
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
