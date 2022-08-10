package src.classdesign;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
 * https://leetcode.cn/problems/lfu-cache/
 */
public class LFU {
    public static void main(String[] args) {
        LFUCache cache = new LFUCache(1);
        cache.put(2, 1);
        System.out.println(cache.get(2));
        cache.put(3, 2);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
    }
}
/**
 * method 1 HashMap映射key->node + TreeSet排序
 * 插入，删除 O(logn)
 */
class LFUTreeSet {
    //容量
    private int cap;
    //时间戳
    private int time;
    //key-value映射表
    private HashMap<Integer, TreeSetNode> keyTable;
    //node排序集
    private TreeSet<TreeSetNode> nodes;

    public LFUTreeSet(int capacity) {
        this.cap = capacity;
        this.time = 0;
        this.keyTable = new HashMap<>();
        this.nodes = new TreeSet<>();
    }

    public int get(int key) {
        if (cap == 0 || !keyTable.containsKey(key)) {
            return -1;
        }
        //获取旧的node并移除
        TreeSetNode cache = keyTable.get(key);
        nodes.remove(cache);
        //更新node值
        cache.cnt += 1;
        cache.time = time++;
        keyTable.put(key, cache);
        nodes.add(cache);
        return cache.value;
    }

    public void put(int key, int value) {
        if (cap == 0) {
            return;
        }
        //如果key不存在
        if (!keyTable.containsKey(key)) {
            //如果容量已满,就先移除旧的
            if (keyTable.size() == cap) {
                keyTable.remove(nodes.first().key);
                nodes.remove(nodes.first());
            }
            TreeSetNode t = new TreeSetNode(1, time++, key, value);
            keyTable.put(key, t);
            nodes.add(t);
        } else {
            TreeSetNode cache = keyTable.get(key);
            nodes.remove(cache);
            cache.cnt += 1;
            cache.time = time++;
            cache.value = value;
            keyTable.put(key, cache);
            nodes.add(cache);
        }

    }
}

class TreeSetNode implements Comparable<TreeSetNode> {
    //使用频次
    public int cnt;
    //最后使用的时间戳
    public int time;
    public int key, value;

    public TreeSetNode(int cnt, int time, int key, int value) {
        this.cnt = cnt;
        this.time = time;
        this.key = key;
        this.value = value;
    }

    public int compareTo(TreeSetNode node2) {
        return this.cnt == node2.cnt ? this.time - node2.time : this.cnt - node2.cnt;
    }

    public int hashCode() {
        return cnt * 1000000007 + time;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TreeSetNode) {
            TreeSetNode t = (TreeSetNode) obj;
            return this.cnt == t.cnt && this.time == t.time && this.key == t.key && this.value == t.value;
        }
        return false;
    }
}

/**
 * HashMap+DlinkList
 * 插入，删除 O(1)
 */
class LFUCache {
    int minFreq, cap;
    HashMap<Integer, Node> keyTable;
    HashMap<Integer, DLinkedList> freqTable;

    public LFUCache(int capacity) {
        this.cap = capacity;
        this.minFreq = 0;
        this.keyTable = new HashMap<>();
        this.freqTable = new HashMap<>();
    }

    public int get(int key) {
        if (cap == 0 || !keyTable.containsKey(key)) {
            return -1;
        }
        Node node = keyTable.get(key);
        int freq = node.freq;
        freqTable.get(freq).remove(node);
        //如果删除旧节点后链表为空，则删除链表映射
        if (freqTable.get(freq).size == 0) {
            freqTable.remove(freq);
            if (minFreq == freq) {
                minFreq += 1;
            }
        }
        //插入到freq + 1中
        DLinkedList dlist = freqTable.getOrDefault(freq + 1, new DLinkedList());
        node.freq += 1;
        dlist.addFirst(node);
        freqTable.put(freq + 1, dlist);
        keyTable.put(key, node);
        return node.value;
    }


    public void put(int key, int value) {
        if (cap == 0) {
            return;
        }
        if (!keyTable.containsKey(key)) {
            //缓存满了则要做删除操作
            if (keyTable.size() == cap) {
                Node oldNode = freqTable.get(minFreq).getTail();
                keyTable.remove(oldNode.key);
                freqTable.get(minFreq).remove(oldNode);
                if (freqTable.get(minFreq).size == 0) {
                    freqTable.remove(minFreq);
                }
            }
            DLinkedList dlist = freqTable.getOrDefault(1, new DLinkedList());
            Node nNode = new Node(key, value, 1);
            dlist.addFirst(nNode);
            freqTable.put(1, dlist);
            keyTable.put(key, nNode);
            minFreq = 1;
        } else {
            Node node = keyTable.get(key);
            //删除旧的节点
            int freq = node.freq;
            freqTable.get(freq).remove(node);
            if (freqTable.get(freq).size == 0) {
                freqTable.remove(freq);
                if (minFreq == freq) {
                    minFreq += 1;
                }
            }
            //更新节点后重新添加
            DLinkedList dlist = freqTable.getOrDefault(freq + 1, new DLinkedList());
            node.freq += 1;
            node.value = value;
            dlist.addFirst(node);
            freqTable.put(freq + 1, dlist);
            keyTable.put(key, node);
        }
    }

}

class Node {
    int key, value, freq;
    Node prev, next;

    public Node() {
        this(-1, -1, 0);
    }

    public Node(int key, int value, int freq) {
        this.key = key;
        this.value = value;
        this.freq = freq;
    }

}

class DLinkedList {
    Node head, tail;
    int size;

    public DLinkedList() {
        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }

    public void addFirst(Node node) {
        Node next = head.next;
        head.next = node;
        node.prev = head;
        node.next = next;
        next.prev = node;
        size++;
    }

    public void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
        size--;
    }

    public Node getHead() {
        return head.next;
    }

    public Node getTail() {
        return tail.prev;
    }
}
