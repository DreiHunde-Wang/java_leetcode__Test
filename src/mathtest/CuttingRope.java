package src.mathtest;

/**
 * ����һ������Ϊ n �����ӣ�������Ӽ����������ȵ� m �Σ�m��n����������n>1����m>1����
 * ÿ�����ӵĳ��ȼ�Ϊ k[0],k[1]...k[m-1] ������ k[0]*k[1]*...*k[m-1] 
 * ���ܵ����˻��Ƕ��٣����磬�����ӵĳ�����8ʱ�����ǰ������ɳ��ȷֱ�Ϊ2��3��3�����Σ���ʱ�õ������˻���18��
 * @author Dreihunde
 *
 */
public class CuttingRope {
	
	//method 1 math ��ѳ���Ϊn / m = e ԼΪ3
    public int cuttingRope(int n) {
        if (n <= 3)
            return n - 1;
        int m = n / 3;
        int res = n % 3;
        if (res == 0)
            return pow(3, m);
        if (res == 1)
            return pow(3, m - 1) * 4;
        return pow(3, m) * 2;
    }

    private int pow(int a, int n) {
        int ret = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                ret *= a;
            }
            n >>>= 1;
            a *= a;
        }
        return ret;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 10;
		CuttingRope cr = new CuttingRope();
		System.out.println(cr.cuttingRope(n));
	}

}
