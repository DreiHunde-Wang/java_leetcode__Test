package numsubsum;

/**
 * ��״����
 * ʱ�临�Ӷȣ�
 * ������״����/�߶�����O(n)��
 * ������״����/�߶�����O(logn)��
 * ����ͼ�����O(logn)��
 * �ռ临�Ӷȣ�
 * ������״����/�߶�����O(n)��
 * ������״����/�߶�����O(1)��
 * ����ͼ�����O(1)��
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
	
	//���������ұߵ�1
	public int lowBit(int x) {
		return x & -x;
	}
	
	//��ѯǰ׺��
	public int query(int x) {
		int ans = 0;
		for (int i = x; i > 0; i -= lowBit(i)) {
			ans += tree[i];
		}
		return ans;
	}
	
	//����״����xλ������u
	public void add(int x, int u) {
		for (int i = x; i <= n; i += lowBit(i)) {
			tree[i] += u;
		}
	}
	
	//����ָ��λ�õ�ֵ
	public void update(int i, int val) {
		// ԭ�е�ֵ�� nums[i]��Ҫʹ���޸�Ϊ val����Ҫ���� val - nums[i]
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
