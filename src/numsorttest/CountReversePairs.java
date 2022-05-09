package src.numsorttest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * �������е��������֣����ǰ��һ�����ִ��ں�������֣����������������һ������ԡ�����һ�����飬�����������е�����Ե�������
 * @author Dreihunde
 *
 */
public class CountReversePairs {
	//method 1 ����(��ʱ) O(n^2) O(1)
    public int reversePairs1(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int count = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] > nums[j]) {
                    count++;
                }
            }
        }

        return count;
    }

    //method 2 �鲢���� O(nlogn) O(n)
    int count;
    public int reversePairs2(int[] nums) {
        count = 0;
        mergeSort(nums, 0, nums.length - 1, new int[nums.length]);
        return count;
    }

    private void mergeSort(int[] nums, int l, int r, int[] temp) {
        if (l >= r) {
            return;
        }

        int mid = (l + r) / 2;
        mergeSort(nums, l, mid, temp);
        mergeSort(nums, mid + 1, r, temp);
        if (nums[mid] <= nums[mid + 1])
            return;
        mergeOfTwoNum(nums, l, mid, r, temp);
    }

    private void mergeOfTwoNum(int[] nums, int l, int mid, int r, int[] temp){
        int i = l; 
        int j = mid + 1;
        int t = 0;

        while (i <= mid && j <= r) {
            if (nums[i] <= nums[j]) {
                temp[t] = nums[i];
                i++;
            } else {
                temp[t] = nums[j];
                count += (mid - i + 1);
                j++;
            }
            t++;
        }
        while (i <= mid) {
            temp[t] = nums[i];
            i++;
            t++;
        }

        while(j <= r) {
            temp[t] = nums[j];
            j++;
            t++;
        }
        t = 0;
        while (l <= r) {
            nums[l] = temp[t];
            t++;
            l++;
        }
    }
    
    //method 3 ��ɢ�� O(nlogn) O(n)
    public int reversePairs3(int[] nums) {
        int len = nums.length;

        if (len < 2) {
            return 0;
        }

        // ��ɢ����ʹ�����ָ����գ���Լ��״����Ŀռ�
        // 1��ʹ�ö�����������Ϊ��ȥ���ظ�Ԫ��
        Set<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < len; i++) {
            treeSet.add(nums[i]);
        }

        // 2�����������ڹ�ϣ���﷽���ѯ
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rankIndex = 1;
        for (Integer num : treeSet) {
            rankMap.put(num, rankIndex);
            rankIndex++;
        }

        int count = 0;
        // ����״�����ڲ����ǰ׺�͵ļ���
        // �����ǣ��Ӻ���ǰ���ȸ���Ӧ������ + 1���ٲ�ѯǰ׺��
        FenwickTree fenwickTree = new FenwickTree(rankMap.size());

        for (int i = len - 1; i >= 0; i--) {
            int rank = rankMap.get(nums[i]);
            fenwickTree.update(rank, 1);
            count += fenwickTree.query(rank - 1);
        }
        return count;
    }

    private class FenwickTree {
        private int[] tree;
        private int len;

        public FenwickTree(int n) {
            this.len = n;
            tree = new int[n + 1];
        }

        /**
         * ������£��� index ���λ�� + delta
         *
         * @param i
         * @param delta
         */
        public void update(int i, int delta) {
            // ���µ��ϣ���ൽ size�����Ե��� size
            while (i <= this.len) {
                tree[i] += delta;
                i += lowbit(i);
            }
        }


        // �����ѯ����ѯС�ڵ��� tree[index] ��Ԫ�ظ���
        // ��ѯ�������ǡ�ǰ׺�͡�
        public int query(int i) {
            // ���ҵ����ѯ
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= lowbit(i);
            }
            return sum;
        }
        
        //����x�����һλ2����1��Ӧ��λ��
        public int lowbit(int x) {
            return x & (-x);
        }
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {7,5,6,4};
		CountReversePairs cr = new CountReversePairs();
		long startTime=System.nanoTime(); 
		System.out.println(cr.reversePairs1(nums));
		long endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime=System.nanoTime(); 
		System.out.println(cr.reversePairs2(nums.clone()));
		endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime=System.nanoTime(); 
		System.out.println(cr.reversePairs3(nums));
		endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}
