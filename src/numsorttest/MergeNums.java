package numsorttest;

public class MergeNums {
	//method 1 正序遍历合并 O(m + n) O(m + n)
	public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int lt = 0;
        int rt = 0;
        int it = 0;
        int[] temp = new int[m + n];
        while (lt < m && rt < n) {
            if (nums1[lt] <= nums2[rt]) {
                temp[it++] = nums1[lt++];
            } else {
                temp[it++] = nums2[rt++];
            }
        }
        while (lt < m) {
            temp[it++] = nums1[lt++];
        }

        while (rt < n) {
            temp[it++] = nums2[rt++];
        }
        it = 0;
        while (it < m + n) {
            nums1[it] = temp[it];
            it++;
        }
    }
	
	//method 2 逆序遍历合并 O(m + n) O(1)
	public void merge2(int[] nums1, int m, int[] nums2, int n) {
		int lt = m - 1;
		int rt = n - 1;
		int it = m + n - 1;
		while (rt >= 0 && lt >= 0) {
			if (nums1[lt] > nums2[rt]) {
				nums1[it--] = nums1[lt--];
			} else {
	        		nums1[it--] = nums2[rt--];
			}
		}
		while (lt >= 0) {
			nums1[it--] = nums1[lt--];
		}
	        
		while (rt >= 0) {
			nums1[it--] = nums2[rt--];
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
