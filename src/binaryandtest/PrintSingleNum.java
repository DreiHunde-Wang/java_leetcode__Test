package src.binaryandtest;



/**
 * һ���������� nums �����������֮�⣬�������ֶ����������Ρ�
 * ��д�����ҳ�������ֻ����һ�ε����֡�Ҫ��ʱ�临�Ӷ���O(n)���ռ临�Ӷ���O(1)��
 * @author Dreihunde
 *
 */
public class PrintSingleNum {
	//method 1 ��λ��� ����������㽻���� a ^ b = b ^ a�� ��ͬλΪ0����ͬλΪ1 O(n) O(1)
    public int[] singleNumbers(int[] nums) {
        int ret = 0;
        for (int num : nums) {
            ret ^= num;
        }

        int div = 1;
        //0˵����λ���,�ҳ�����ȵ�һ��1
        while ((div & ret) == 0) {
            div <<= 1;
        }
        int a = 0, b = 0;
        for (int num : nums) {
        	//������ȵ�1����num�����԰�a, b�ֿ�
            if ((num & div) != 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {1,2,5,2};
		PrintSingleNum ps = new PrintSingleNum();
		int[] rev = ps.singleNumbers(nums);
		System.out.println(rev[0] + " " + rev[1]);

	}

}
