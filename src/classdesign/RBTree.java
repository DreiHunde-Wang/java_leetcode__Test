package classdesign;

import java.util.Objects;

public class RBTree<K, V> {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class RBNode<K, V> {
	K key;
	V value;
	RBNode<K, V> parent;
	RBNode<K, V> left;
	RBNode<K, V> right;
	boolean color;
	private static final boolean BLACK = false;
	private static final boolean RED = true;
	
	public RBNode() {
		
	}
	
	public RBNode(K key, V value, boolean color) {
		this.key = key;
		this.value = value;
		this.color = color;
	}
	
	public boolean isRed(RBNode node) {
		if (node == null)	return false;
		return node.color == RED;
	}
	
	public K getKey() {
		return this.key;
	}
	
	public V getValue() {
		return this.value;
	}
	
	public int hashCode() {
		return Objects.hashCode(key) ^ Objects.hashCode(value);
	}
	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o instanceof RBNode) {
			RBNode node = (RBNode) o;
			return node.getKey() == this.getKey() && node.getValue() == this.getValue();
		}
		return false;
	}
	
	//左旋
	private RBNode rotateLeft(RBNode h) {
		assert this.isRed(h.right);
		RBNode x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}
	
	//右旋
	private RBNode rotateRight(RBNode h) {
		assert this.isRed(h.left);
		RBNode x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}
	
	//变色
	private void flipColor(RBNode h) {
		assert !this.isRed(h);
		assert this.isRed(h.left);
		assert this.isRed(h.right);
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}
	
	//对比
	private int compare(K orgin, Object key) {
		if ((int) orgin < (int) key) {
			return -1;
		} else if ((int) orgin > (int) key) {
			return 1;
		} else {
			return 0;
		}
		
	}
	
	//插入
	public RBNode put(RBNode h, K key, V value) {
		//assert at bottom and color it red
		if (h == null)	return new RBNode(key, value, RED);
		int cmp = compare(key, h.key);
		if (cmp < 0) {
			h.left = put(h.left, key, value);
		} else if (cmp > 0) {
			h.right = put(h.right, key, value);
		} else {
			h.value = value;
		}
		
		if (isRed(h.right) && !isRed(h.left)) {
			rotateLeft(h);
		} else if (!isRed(h.right) && isRed(h.left)) {
			rotateRight(h);
		} else if (isRed(h.right) && isRed(h.left)) {
			flipColor(h);
		}
		return h;
	}
	
}
