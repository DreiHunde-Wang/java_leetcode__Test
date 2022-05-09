package src.classdesign;


//路径压缩树状UnionFind union O(1) find O(1) O(n)
public class UnionFind {
	private int[] id;
	private int count;
	//统计每个点的子节点数目
	private int[] childrens;
		
	public UnionFind(int N) {
		this.count = N;
		this.id = new int[N];
		this.childrens = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			childrens[i] = 1;
		}
	}
		
	public int getCount() {
		return this.count;
	}
		
	public int find(int i) {
		if (i == id[i]) {
			return i;
		}
		return id[i] = find(id[i]);
	}
		
	public void union(int p, int q) {
		int proot = find(p);
		int qroot = find(q);
			
		if (proot == qroot) {
			return;
		}
			
		if (childrens[proot] > childrens[qroot]) {
			childrens[proot] += childrens[qroot];
			id[qroot] = proot;
		} else {
			childrens[qroot] += childrens[proot];
			id[proot] = qroot;
		}
		count--;
	}
		
	public boolean connect(int p, int q) {
		return find(p) == find(q);
	}

}

//树状并查集 union O(Height) find O(Height) O(n)
class QuickUnionFind {
	private int[] id;
	private int count;
	
	public QuickUnionFind(int N) {
		this.count = N;
		this.id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}
	
	public int getCount() {
		return this.count;
	}
	
	/**
	 * 
	 * @param p
	 * @param q
	 */
	public void union(int p, int q) {
		int proot = find(p);
		int qroot = find(q);
		if (proot == qroot) {
			return;
		}
		id[proot] = qroot;
		count--;
	}
	
	/**
	 * p and q has a common root
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean connect(int p, int q) {
		return find(p) == find(q);
	}
	
	/**
	 * find p root
	 * @param p
	 * @return root
	 */
	public int find(int p) {
		while (p != id[p]) {
			p = id[p];
		}
		return p;
	}	
}

//加权树状UnionFind union O(logn) find O(logn)
class WeightQuickUnion {
	//在QuickUnion的基础上增加了子节点数目，将简单数拼接到复杂树上
	private int[] id;
	private int count;
	//统计每个点的子节点数目
	private int[] childrens;
	
	public WeightQuickUnion(int N) {
		this.count = N;
		this.id = new int[N];
		this.childrens = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			childrens[i] = 1;
		}
	}
	
	public int getCount() {
		return this.count;
	}
	
	public int find(int p) {
		while (p != id[p]) {
			p = id[p];
		}
		return p;
	}
	
	public void union(int p, int q) {
		int proot = find(p);
		int qroot = find(q);
		
		if (proot == qroot) {
			return;
		}
		
		if (childrens[proot] > childrens[qroot]) {
			childrens[proot] += childrens[qroot];
			id[qroot] = proot;
		} else {
			childrens[qroot] += childrens[proot];
			id[proot] = qroot;
		}
		count--;
	}
	
	public boolean connect(int p, int q) {
		return find(p) == find(q);
	}
}

