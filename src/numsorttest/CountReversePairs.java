package src.numsorttest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * @author Dreihunde
 *
 */
public class CountReversePairs {
	//method 1 暴力(超时) O(n^2) O(1)
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

    //method 2 归并排序 O(nlogn) O(n)
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
    
    //method 3 离散树 O(nlogn) O(n)
    public int reversePairs3(int[] nums) {
        int len = nums.length;

        if (len < 2) {
            return 0;
        }

        // 离散化：使得数字更紧凑，节约树状数组的空间
        // 1、使用二分搜索树是为了去掉重复元素
        Set<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < len; i++) {
            treeSet.add(nums[i]);
        }

        // 2、把排名存在哈希表里方便查询
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rankIndex = 1;
        for (Integer num : treeSet) {
            rankMap.put(num, rankIndex);
            rankIndex++;
        }

        int count = 0;
        // 在树状数组内部完成前缀和的计算
        // 规则是：从后向前，先给对应的排名 + 1，再查询前缀和
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
         * 单点更新：将 index 这个位置 + delta
         *
         * @param i
         * @param delta
         */
        public void update(int i, int delta) {
            // 从下到上，最多到 size，可以等于 size
            while (i <= this.len) {
                tree[i] += delta;
                i += lowbit(i);
            }
        }


        // 区间查询：查询小于等于 tree[index] 的元素个数
        // 查询的语义是「前缀和」
        public int query(int i) {
            // 从右到左查询
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= lowbit(i);
            }
            return sum;
        }
        
        //数字x的最后一位2进制1对应的位置
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
