package src.numsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import src.printtreenode.ComnTest;

public class FindKSmallPairs {
	//method 1 优先队列 O(klogk) O(k)
    public List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2)->{
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        });
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[]{i,0});
        }
        while (k-- > 0 && !pq.isEmpty()) {
            int[] idxPair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);
            if (idxPair[1] + 1 < n) {
                pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }
        
        return ans;
    }

    //method 2 二分查找 O(k + (m + n)(log(diff(m) + diff(n)))), diff(arr) 表示数组 arr 中最大元素与最小元素之差, O(1)
    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;

        int left = nums1[0] + nums2[0];
        int right = nums1[n - 1] + nums2[m - 1];
        int pairSum = right;

        //找到k个及以上的最小对的上线值pairSum
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long cnt = 0;
            int start = 0;
            int end = m - 1;
            while (start < n && end >= 0) {
                if (nums1[start] + nums2[end] <= mid) {
                    start++;
                    cnt += end + 1;
                } else {
                    end--;
                }
            }
            if (cnt < k) {
                left = mid + 1;
            } else {
                pairSum = mid;
                right = mid - 1;
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        int pos = m - 1;
        //找到小于pairSum的数对
        for (int i = 0; i < n; i++) {
            while(pos >= 0 && nums1[i] + nums2[pos] >= pairSum) {
                pos--;
            }
            for (int j = 0; j <= pos && k > 0; j++, k--) {
                List<Integer> list = new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[j]);
                ans.add(list);
            }
        }

        pos = m - 1;
        //找到等于pairSum的数对
        for (int i = 0; i < n && k > 0; i++) {
            while(pos >= 0 && nums1[i] + nums2[pos] > pairSum) {
                pos--;
            }
            for (int j = i; j >= 0 && k > 0 && nums1[j] + nums2[pos] == pairSum; j--, k--) {
                List<Integer> list = new ArrayList<>();
                list.add(nums1[j]);
                list.add(nums2[pos]);
                ans.add(list);
            }
        }

        return ans;

    }

    //method 3 从两个数组各取前k个数，然后排序 O(k^2) O(k^2)
    public List<List<Integer>> kSmallestPairs3(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < Math.min(n, k); i++) {
            for (int j = 0; j < Math.min(m, k); j++) {
                ans.add(Arrays.asList(new Integer[]{nums1[i], nums2[j]}));
            }
        }
        ans.sort((a, b) -> (a.get(0) + a.get(1) - b.get(0) - b.get(1)));
        int len = Math.min(ans.size(), k);
        return ans.subList(0, len);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums1 = new int[] {1, 7, 11};
		int[] nums2 = new int[] {2, 4, 6};
		int k = 3;
		FindKSmallPairs fk = new FindKSmallPairs();
		
		ComnTest.printListList(fk.kSmallestPairs1(nums1, nums2, k));
		ComnTest.printListList(fk.kSmallestPairs2(nums1, nums2, k));
		ComnTest.printListList(fk.kSmallestPairs3(nums1, nums2, k));

	}

}
