package src.classdesign;

import java.util.HashMap;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？

 * @author Dreihunde
 *
 */
public class LRUCache {
	//记录key List映射
	HashMap<Integer, DLinkList> map = new HashMap<>();
	//当前内存使用情况
    int size;
    //总容量大小
    int capacity;
    //双向链表伪起始和终止，起始表示最近访问
    DLinkList head, tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = new DLinkList();
        this.tail = new DLinkList();
        head.next = tail;
        tail.pre = head;
    }
    /**
     * 输入key，返回对应的val
     * @param key input key
     * @return node.val output val 
     */
    public int get(int key) {
        DLinkList node = map.get(key);
        if (node == null) {
            return -1;
        }
        //最近访问
        MoveToHead(node);
        return node.val;
    }
    
    /**
     * 添加key-value映射，若存在则更新value
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        DLinkList node = map.get(key);
        if (node == null) {
            node = new DLinkList(key, value);
            addToHead(node);
            map.put(key, node);
            size++;
            if (size > capacity) {
                DLinkList res = deleteTail();
                map.remove(res.key);
                size--;
            }
        } else {
            MoveToHead(node);
            node.val = value;
        }
    }
    
    /**
     * 在头部添加节点node
     * @param node
     */
    private void addToHead(DLinkList node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }
    /**
     * 删除节点node
     * @param node
     */
    private void removeNode(DLinkList node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    
    /**
     * 将节点移至头部
     * @param node
     */
    private void MoveToHead(DLinkList node) {
        removeNode(node);
        addToHead(node);
    }
    /**
     * 删除尾部节点，并返回删除的节点
     * @return res tail node
     */
    private DLinkList deleteTail() {
        DLinkList res = tail.pre;
        removeNode(res);
        return res;
    }

    
}

class DLinkList {
    int key;
    int val;
    DLinkList pre;
    DLinkList next;

    public DLinkList() {

    }

    public DLinkList(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
