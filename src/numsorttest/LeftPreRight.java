package numsorttest;

/**
 * ����һ���������飬ʵ��һ�����������������������ֵ�˳��ʹ�����������������ǰ�벿�֣�����ż��������ĺ�벿�֡�
 * @author Dreihunde
 *
 */
public class LeftPreRight {
	
	//method 1 ˫ָ�뽻�� O(n) O(1)
    public int[] exchange(int[] nums) {
        if (nums.length == 0)
            return new int[0];
        int left = 0;
        int right = nums.length - 1;
        
        //����ָ��û�����ͼ����ж�
        while (left < right) {
            //��ָ����������
            while (left < right && nums[left] % 2 == 1) {
                left++;
            }
            //��ָ������ż��
            while (left < right && nums[right] % 2 == 0) {
                right--;
            }
            //������ָ��(��ǰָ��ż��)����ָ��(��ǰָ������)
            swap(nums, left, right);
            //�Ѿ���������λ��ֱ������
            left++;
            right--;
        }

        return nums;
    }
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
