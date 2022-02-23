package stringnumtest;

/**
 * 给你一个字符串 s ，根据下述规则反转字符串：
 * 所有非英文字母保留在原有位置。
 * 所有英文字母（小写或大写）位置反转。
 * 返回反转后的 s 。
 * https://leetcode-cn.com/problems/reverse-only-letters/
 * @author Dreihunde
 *
 */
public class ReverseOnlyLetters {
	//method 1 双指针 O(n) O(n)
    public String reverseOnlyLetters1(String s) {
        int n = s.length();
        if (n <= 1) {
            return s;
        }
        char[] arr = s.toCharArray();
        int left = 0;
        int right = n - 1;
        //交换左右字母
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

    //method 2 双指针 + 填充 O(n) O(n)
    public String reverseOnlyLetters(String s) {
        int n = s.length();
        if (n <= 1) {
            return s;
        }
        StringBuffer sb = new StringBuffer();
        int left = 0;
        int right = n - 1;
        //从左往右填充符号，从右往左填充字母
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
