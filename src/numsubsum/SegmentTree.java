package numsubsum;

import java.util.concurrent.Semaphore;

/**
 * 线段树
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
public class SegmentTree {
	class STNode {
		//左边界
		int l;
		//右边界
		int r;
		//值
		int v;
		//懒标记
		int add;
		
		STNode(int _l, int _r) {
			this.l = _l;
			this.r = _r;
		}
	}
	
	int N = 20009;//大于要求的数字范围，比如2*10^4
	STNode[] tr = new STNode[N * 4];
	public SegmentTree() {
	}
	
	public SegmentTree(int[] nums) {
		int n = nums.length;
		build(1, 1, n);
		for (int i = 0; i < n; i++) {
			update(1, i + 1, i + 1, nums[i]);
		}
	}
	//聚合值
	void pushup(int u) {
		tr[u].v = tr[u << 1].v + tr[u << 1 | 1].v;
	}
	//下放值
	void pushdown(int u) {
		int add = tr[u].add;
		tr[u << 1].add += add;
		tr[u << 1].v += add;
		tr[u << 1 | 1].add += add;
		tr[u << 1 | 1].v += add;
		tr[u].add = 0;
	}
	
	void build(int u, int l, int r) {
		tr[u] = new STNode(l, r);
		if (l != r) {
			int mid = l + r >> 1;
			build(u << 1, l, mid);
			build(u << 1 | 1, mid + 1, r);
		}
	}
	
	void update(int u, int l, int r, int v) {
		if (l <= tr[u].l && r >= tr[u].r) {
			tr[u].v += v;
			tr[u].add += v;
		} else {
			pushdown(u);
			int mid = tr[u].l + tr[u].r >> 1;
			if (l <= mid) {
				update(u << 1, l, r, v);
			}
			if (r > mid) {
				update(u << 1 | 1, l, r, v);
			}
			pushup(u);
		}
	}
	
	/**
	 * 查询范围内的和
	 * @param u
	 * @param l
	 * @param r
	 * @return
	 */
	int query(int u, int l, int r) {
		if (l <= tr[u].l && r >= tr[u].r) {
			return tr[u].v;
		} else {
			pushdown(u);
			int mid = tr[u].l + tr[u].r >> 1;
			int ans = 0;
			if (l <= mid) {
				ans += query(u << 1, l, r);
			}
			if (r > mid) {
				ans += query(u << 1 | 1, l, r);
			}
			return ans;
		}
		
	}
	
	
	public static void main(String[] args) {
		int[] nums = new int[] {1, 3, 5};
		SegmentTree st = new SegmentTree(nums);

		System.out.println(st.query(1, 1, 3));
//		SegmentTree st = new SegmentTree();
//		int n = 2;
//		st.build(1, 1, n);
//		int[][] bs = new int[][] {{1,2,10},{2,2,15}};
//		for (int[] bo : bs) {
//			st.update(1, bo[0], bo[1], bo[2]);
//        }
//        int[] ans = new int[bs.length];
//        for (int i = 0; i < bs.length; i++) {
//            ans[i] = st.query(1, i + 1, i + 1);
//            System.out.print(ans[i] + " ");
//        }
        
        
	}

}
