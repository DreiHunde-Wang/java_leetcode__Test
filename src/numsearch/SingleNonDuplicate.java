package src.numsearch;

/**
 * ����һ������������ɵ��������飬����ÿ��Ԫ�ض���������Σ�Ψ��һ����ֻ�����һ�Ρ�
 * �����ҳ�������ֻ����һ�ε��Ǹ�����
 * ����ƵĽ�������������� O(log n) ʱ�临�ӶȺ� O(1) �ռ临�Ӷȡ�
 * ���ӣ�https://leetcode-cn.com/problems/single-element-in-a-sorted-array
 * @author Dreihunde
 *
 */
public class SingleNonDuplicate {
	//method 1 ��� O(n) O(1)
    public int singleNonDuplicate1(int[] nums) {
        int ret = 0;
        for (int num : nums) {
            ret = ret ^ num;
        }
        return ret;
    }

    //method 2 ���ֲ��� O(logn) O(1)
    public int singleNonDuplicate2(int[] nums) {
        int ret = 0;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (left == right || mid == 0 || mid == nums.length - 1) {
                break;
            }
            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            }
            //���������ұߵ�����������е�����Ϊ����ʱ������߱Ƚϣ�Ϊż��ʱ�����ұ߱Ƚ�
            if ((nums[mid] == nums[mid - 1] && mid % 2 != 0) || (nums[mid] == nums[mid + 1] && mid % 2 == 0)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }
        
        return nums[left];
    }

    //method3 ȫ����Ķ��� O(logn) O(1) ��λ�������ʣ����Եõ�mid�����ڵ���֮���ϵ �� mid ��ż��ʱ��mid + 1 = mid ^ 1, ��midΪ����ʱ, mid - 1 = mid ^ 1;
    public int singleNonDuplicate3(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (nums[mid] == nums[mid ^ 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }

    //method 4 ż����Χ�Ķ��ֲ��� O(logn) O(1) ���ڵ��������һ��������ż���ԣ���˸�������ֵһ��Ϊż��
    public int singleNonDuplicate4(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            mid -= mid & 1;
            if (nums[mid] == nums[mid + 1]) {
                low = mid + 2;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
