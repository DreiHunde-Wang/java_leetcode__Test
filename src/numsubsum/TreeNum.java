package numsubsum;

/**
 * 树状数组
 * 时间复杂度：
 * 构建树状数组/线段树：O(n)；
 * 更新树状数组/线段树：O(logn)；
 * 区域和检索：O(logn)。
 * 空间复杂度：
 * 构建树状数组/线段树：O(n)；
 * 更新树状数组/线段树：O(1)；
 * 区域和检索：O(1)。
 * @author Dreihunde
 *
 */
public class TreeNum {
	int[] tree;
	int[] nums;
	int n;
	
	public TreeNum(int[] _nums) {
		this.nums = _nums;
		this.n = _nums.length;
		this.tree = new int[n + 1];
		for (int i = 0; i < n; i++) {
			add(i + 1, nums[i]);
		}
	}
	
	//二进制最右边的1
	public int lowBit(int x) {
		return x & -x;
	}
	
	//查询前缀和
	public int query(int x) {
		int ans = 0;
		for (int i = x; i > 0; i -= lowBit(i)) {
			ans += tree[i];
		}
		return ans;
	}
	
	//在树状数组x位置增加u
	public void add(int x, int u) {
		for (int i = x; i <= n; i += lowBit(i)) {
			tree[i] += u;
		}
	}
	
	//更改指定位置的值
	public void update(int i, int val) {
		// 原有的值是 nums[i]，要使得修改为 val，需要增加 val - nums[i]
		add(i + 1, val - nums[i]);
		nums[i] = val;
	}
	
	public int sumRange(int l, int r) {
		return query(r + 1) - query(l);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] _nums = new int[] {1, 3, 5};
		TreeNum tn = new TreeNum(_nums);
		System.out.println(tn.sumRange(0, 2));
		tn.update(1, 2);
		System.out.println(tn.sumRange(0, 2));
		

	}

}
