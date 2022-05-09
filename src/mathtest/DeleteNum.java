package src.mathtest;

/**
 * �б� arr ���ڷ�Χ [1, n] �е�����������ɣ������ϸ������������� arr Ӧ�������㷨��
 * �����ң�ɾ����һ�����֣�Ȼ��ÿ��һ������ɾ��һ����ֱ�������б�ĩβ��
 * �ظ�����Ĳ��裬������Ǵ��ҵ���Ҳ���ǣ�ɾ�����Ҳ�����֣�Ȼ��ʣ�µ�����ÿ��һ��ɾ��һ����
 * �����ظ��������������Һʹ��ҵ�������У�ֱ��ֻʣ��һ�����֡�
 * �������� n ������ arr ���ʣ�µ����֡�
 * @author Dreihunde
 *
 */
public class DeleteNum {
	//method 1 Լɪ�� O(logn) O(1)
    //Լɪ��
    //f(i)������[1,i]�������Ҹ�������һ�κ������������֣�f'(i)�Ǵ��������������һ�κ������Ҳ�����֣�����f(i)+f'(i)=i+1
    //��������д�����������һ�Σ���ʣ�µ����г���Ϊ[i/2]��[]��������ȡ����
    //�ʼ��������1��2��...��i���������ʣ�µ�����Ϊ��2,4,...,x(xȡֵ��i�йأ������������Ϊi-1��ż����Ϊi��
    //Ȼ���Ƕ�ʣ�µ����н����������ţ�����1,2,3,...,[i/2]��Ȼ�������д��������������Ҳ��f'([i/2])
    //���õ���������ӳ���֮ǰ�����У�����f(i)=f'([i/2]) * 2
    //����֮ǰ�Ĺ�ʽ����f(i) = 2 * ([i/2] + 1 - f([i/2])
    //ע�⿼����������������f(1)=1
    public int lastRemaining1(int n) {
        return n == 1 ? 1 : 2 * (n / 2 + 1 - lastRemaining1(n / 2));
    }

    //method 2 �Ȳ����� O(logn) O(1)
    public int lastRemaining2(int n) {
        //a1 ʣ�����еĵ�һ������
        int a1 = 1;
        //k ������ cnt �ܳ��� step ���� 
        int k = 0, cnt = n, step = 1;
        while (cnt > 1) {
            if (k % 2 == 0) { // ����
                a1 = a1 + step;
            } else { // ����
                a1 = (cnt % 2 == 0) ? a1 : a1 + step;
            }
            k++;
            cnt = cnt >> 1;
            step = step << 1;
        }
        return a1;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 9;
		
		DeleteNum dn = new DeleteNum();
		
		System.out.println(dn.lastRemaining1(n));
		System.out.println(dn.lastRemaining2(n));

	}

}
