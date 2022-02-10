package classdesign;

public class LazyTree {
	

}

class LazyTreeNode {
	int val;
	int leftBound;
	int rightBound;
	
	public LazyTreeNode() {
		
	}
	
	public LazyTreeNode(int val, int leftBound, int rightBound) {
		this.val = val;
		this.leftBound = leftBound;
		this.rightBound = rightBound;
	}
}
