package src.numsearch;

/**
 * 给定两个大小分别�? m �? n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位�? �?
 * 算法的时间复杂度应该�? O(log (m+n)) �?
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * @author Dreihunde
 *
 */
public class FindMedianSortedArrays {
	//method 1 二分拆除 O(logn) O(1)
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int totalLen = m + n;
        if (totalLen % 2 == 1) {
            int mIndex = totalLen / 2;
            double median = getMedian(nums1, nums2, mIndex + 1);
            return median;
        } else {
            int mIndex = totalLen / 2;
            double median1 = getMedian(nums1, nums2, mIndex);
            double median2 = getMedian(nums1, nums2, mIndex + 1);
            return (median1 + median2) / 2;
        }
    }

    private int getMedian(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int i1 = 0;
        int i2 = 0;
        /* 主要思路：要找到�? k (k>1) 小的元素，那么就�? pivot1 = nums1[k/2-1] �? pivot2 = nums2[k/2-1] 进行比较
         * 这里�? "/" 表示整除
         * nums1 中小于等�? pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 �?
         * nums2 中小于等�? pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 �?
         * �? pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超�? (k/2-1) + (k/2-1) <= k-2 �?
         * 这样 pivot 本身�?大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那�? nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那�? nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比�? k 小的元素要小），因此�?要修�? k 的�?�，减去删除的数的个�?
         */
        while (true) {
            //边界情况
            if (i1 == m) {
                return nums2[i2 + k - 1];
            }
            if (i2 == n) {
                return nums1[i1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[i1], nums2[i2]);
            }

            //正常情况
            int half = k / 2;
            int newI1 = Math.min(m, i1 + half) - 1;
            int newI2 = Math.min(n, i2 + half) - 1;
            int pivot1 = nums1[newI1];
            int pivot2 = nums2[newI2];
            if (pivot1 <= pivot2) {
                k -= (newI1 - i1 + 1);
                i1 = newI1 + 1;
            } else {
                k -= (newI2 - i2 + 1);
                i2 = newI2 + 1;   
            }
        }
    }

    //method 2 math，划分数�? O(log(min(m, n))) O(1)
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays2(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;
        // median1：前�?部分的最大�??
        // median2：后�?部分的最小�??
        int median1 = 0, median2 = 0;

        while (left <= right) {
            // 前一部分包含 nums1[0 .. i-1] �? nums2[0 .. j-1]
            // 后一部分包含 nums1[i .. m-1] �? nums2[j .. n-1]
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;

            // nums_im1, nums_i, nums_jm1, nums_j 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]
            int nums_im1 = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
            int nums_i = (i == m ? Integer.MAX_VALUE : nums1[i]);
            int nums_jm1 = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
            int nums_j = (j == n ? Integer.MAX_VALUE : nums2[j]);

            if (nums_im1 <= nums_j) {
                median1 = Math.max(nums_im1, nums_jm1);
                median2 = Math.min(nums_i, nums_j);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }

        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }

    //method 3 搜索索引 O(m + n) O(1)
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
                right = A[aStart++];
            } else {
                right = B[bStart++];
            }
        }
        if ((len & 1) == 0)
            return (left + right) / 2.0;
        else
            return right;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
