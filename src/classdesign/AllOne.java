package classdesign;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。
 * 实现 AllOne 类：
 * AllOne() 初始化数据结构的对象。
 * inc(String key) 字符串 key 的计数增加 1 。如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。
 * dec(String key) 字符串 key 的计数减少 1 。如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。测试用例保证：在减少计数前，key 存在于数据结构中。
 * getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。
 * getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。
 * 链接：https://leetcode-cn.com/problems/all-oone-data-structure
 * @author Dreihunde
 *
 */
//method1 双向链表+map映射，记录count值 O(n) O(1)
class AllOne {
    HashMap<String, Node> map;
    //递增双向链表
    Node head, tail;
    class Node {
        Node prev;
        Node next;
        int count;
        String val;

        public Node(String _val, int _count) {
            this.val = _val;
            this.count = _count;
        }
    }

    public AllOne() {
        map = new HashMap<>();
        head = new Node(null, 0);
        tail = new Node(null, Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
    }
    
    public void inc(String key) {
        Node t = map.get(key);
        if (t == null) {
            t = new Node(key, 1);
            insert(head, t);
            map.put(key, t);
        } else {
            t.count++;
            while (t.count > t.next.count) {
                swap(t, t.next);
            }
        }
    }
    
    public void dec(String key) {
        Node t = map.get(key);
        if (!map.containsKey(key)) {
            return;
        }
        if (t.count == 1) {
            map.remove(key);
            delete(t);
        } else {
            t.count--;
            while (t.count < t.prev.count) {
                swap(t.prev, t);
            }
        }
    }
    
    public String getMaxKey() {
        return tail.prev == head ? "" : tail.prev.val;
    }
    
    public String getMinKey() {
        return head.next == tail ? "" : head.next.val;
    }

    public void insert(Node prev, Node cur) {
        cur.next = prev.next;
        cur.next.prev = cur;
        prev.next = cur;
        cur.prev = prev;
    }

    public void swap(Node prev, Node cur) {
        Node pprev = prev.prev;
        Node nnext = cur.next;
        pprev.next = cur;
        cur.prev = pprev;
        cur.next = prev;
        prev.prev = cur;
        prev.next = nnext;
        nnext.prev = prev;
    }

    public void delete(Node cur) {
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
    }
    
    public static void main(String[] args) {
		AllOne ao = new AllOne();
		ao.inc("hello");
		ao.inc("hello");
		System.out.println(ao.getMaxKey());
		System.out.println(ao.getMinKey());
		ao.inc("leet");
		System.out.println(ao.getMaxKey());
		System.out.println(ao.getMinKey());
	}
}

//method1 双向链表+map映射，需要时扩展count O(n) O(1)
class AllOne2 {
    HashMap<String, Node> map;
    //递增双向链表
    Node head, tail;
    class Node {
        Node prev;
        Node next;
        int count;
        Set<String> set;

        public Node(String _val, int _count) {
            set = new HashSet<>();
            this.set.add(_val);
            this.count = _count;
        }

        public void addKey(String _key) {
            set.add(_key);
        }

        public void removeKey(String _key) {
            set.remove(_key);
            if (set.isEmpty()) {
                delete();
            }
        }

        public void insert(Node prev) {
            Node cur = this;
            cur.next = prev.next;
            cur.next.prev = cur;
            prev.next = cur;
            cur.prev = prev;
        }

        public void swap(Node prev) {
            Node cur = this;
            Node pprev = prev.prev;
            Node nnext = cur.next;
            pprev.next = cur;
            cur.prev = pprev;
            cur.next = prev;
            prev.prev = cur;
            prev.next = nnext;
            nnext.prev = prev;
        }

        public void delete() {
            Node cur = this;
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
        }
    }

    public AllOne2() {
        map = new HashMap<>();
        head = new Node("", 0);
        tail = new Node("", Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
    }
    
    public void inc(String key) {
        Node t = map.get(key);
        if (t == null) {
            if (head.next.count == 1) {
                head.next.addKey(key);
                map.put(key, head.next);
            } else {
                t = new Node(key, 1);
                t.insert(head);
                map.put(key, t);
            } 
        } else {
            if (t.next.count == t.count + 1) {
                t.removeKey(key);
                t.next.addKey(key);
                map.put(key, t.next);
            } else {
                Node next = new Node(key, t.count + 1);
                next.insert(t);
                t.removeKey(key);
                next.addKey(key);
                map.put(key, next);
            }
        }
    }
    
    public void dec(String key) {
        Node t = map.get(key);
        if (!map.containsKey(key)) {
            return;
        }
        if (t.count == 1) {
            map.remove(key);         
        } else {
            if (t.prev.count == t.count - 1) {
                t.prev.addKey(key);
                map.put(key, t.prev);
            } else {
                Node prev = new Node(key, t.count - 1);
                prev.insert(t.prev);
                map.put(key, prev);
            }
        }
        t.removeKey(key);
    }
    
    public String getMaxKey() {
        return tail.prev == head ? "" : tail.prev.set.iterator().next();
    }
    
    public String getMinKey() {
        return head.next == tail ? "" : head.next.set.iterator().next();
    }

    
}
