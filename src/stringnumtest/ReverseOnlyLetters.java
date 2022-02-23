package stringnumtest;

/**
 * ����һ���ַ��� s ��������������ת�ַ�����
 * ���з�Ӣ����ĸ������ԭ��λ�á�
 * ����Ӣ����ĸ��Сд���д��λ�÷�ת��
 * ���ط�ת��� s ��
 * https://leetcode-cn.com/problems/reverse-only-letters/
 * @author Dreihunde
 *
 */
public class ReverseOnlyLetters {
	//method 1 ˫ָ�� O(n) O(n)
    public String reverseOnlyLetters1(String s) {
        int n = s.length();
        if (n <= 1) {
            return s;
        }
        char[] arr = s.toCharArray();
        int left = 0;
        int right = n - 1;
        //����������ĸ
        while (true) {
            while (left < n && !Character.isLetter(arr[left])) {
                left++;
            }
            while (right >= 0 && !Character.isLetter(arr[right])) {
                right--;
            }
            if (left >= right) {
                break;
            }
            swap(arr, left, right);
            left++;
            right--;
        }
        return new String(arr);
        
    }

    private void swap(char[] arr, int i, int j) {
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    //method 2 ˫ָ�� + ��� O(n) O(n)
    public String reverseOnlyLetters(String s) {
        int n = s.length();
        if (n <= 1) {
            return s;
        }
        StringBuffer sb = new StringBuffer();
        int left = 0;
        int right = n - 1;
        //�������������ţ��������������ĸ
        while (left < n) {
            char c = s.charAt(left);
            if (!Character.isLetter(c)) {
                sb.append(c);
            } else {
                while (right >= 0 && !Character.isLetter(s.charAt(right))) {
                    right--;
                } 
                if (right >= 0) {
                    sb.append(s.charAt(right));
                    right--;
                }              
            }
            left++;
        }
        return sb.toString(); 
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
