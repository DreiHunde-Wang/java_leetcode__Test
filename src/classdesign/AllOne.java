package classdesign;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * �������һ�����ڴ洢�ַ������������ݽṹ�����ܹ����ؼ�����С�������ַ�����
 * ʵ�� AllOne �ࣺ
 * AllOne() ��ʼ�����ݽṹ�Ķ���
 * inc(String key) �ַ��� key �ļ������� 1 ��������ݽṹ���в����� key ����ô�������Ϊ 1 �� key ��
 * dec(String key) �ַ��� key �ļ������� 1 ����� key �ļ����ڼ��ٺ�Ϊ 0 ����ô��Ҫ����� key �����ݽṹ��ɾ��������������֤���ڼ��ټ���ǰ��key ���������ݽṹ�С�
 * getMaxKey() ��������һ�����������ַ��������û��Ԫ�ش��ڣ�����һ�����ַ��� "" ��
 * getMinKey() ��������һ��������С���ַ��������û��Ԫ�ش��ڣ�����һ�����ַ��� "" ��
 * ���ӣ�https://leetcode-cn.com/problems/all-oone-data-structure
 * @author Dreihunde
 *
 */
//method1 ˫������+mapӳ�䣬��¼countֵ O(n) O(1)
class AllOne {
    HashMap<String, Node> map;
    //����˫������
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

//method1 ˫������+mapӳ�䣬��Ҫʱ��չcount O(n) O(1)
class AllOne2 {
    HashMap<String, Node> map;
    //����˫������
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
