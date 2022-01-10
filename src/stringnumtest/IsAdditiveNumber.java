package stringnumtest;

/**
 * 累加数 是一个字符串，组成它的数字可以形成累加序列。
 * 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 * 给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
 * 说明：累加序列里的数 不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 * @author Dreihunde
 *
 */
public class IsAdditiveNumber {
	//method 1 遍历 O(n^3) O(n)
    public boolean isAdditiveNumber1(String num) {
        int n = num.length();
        for (int secondStart = 1; secondStart < n - 1; ++secondStart) {
            if (num.charAt(0) == '0' && secondStart != 1) {
                break;
            }
            for (int secondEnd = secondStart; secondEnd < n - 1; ++secondEnd) {
                if (num.charAt(secondStart) == '0' && secondStart != secondEnd) {
                    break;
                }
                if (valid(secondStart, secondEnd, num)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean valid(int secondStart, int secondEnd, String num) {
        int n = num.length();
        int firstStart = 0, firstEnd = secondStart - 1;
        while (secondEnd <= n - 1) {
            String third = stringAdd(num, firstStart, firstEnd, secondStart, secondEnd);
            int thirdStart = secondEnd + 1;
            int thirdEnd = secondEnd + third.length();
            if (thirdEnd >= n || !num.substring(thirdStart, thirdEnd + 1).equals(third)) {
                break;
            }
            if (thirdEnd == n - 1) {
                return true;
            }
            firstStart = secondStart;
            firstEnd = secondEnd;
            secondStart = thirdStart;
            secondEnd = thirdEnd;
        }
        return false;
    }

    public String stringAdd(String s, int firstStart, int firstEnd, int secondStart, int secondEnd) {
        StringBuffer third = new StringBuffer();
        int carry = 0, cur = 0;
        while (firstEnd >= firstStart || secondEnd >= secondStart || carry != 0) {
            cur = carry;
            if (firstEnd >= firstStart) {
                cur += s.charAt(firstEnd) - '0';
                --firstEnd;
            }
            if (secondEnd >= secondStart) {
                cur += s.charAt(secondEnd) - '0';
                --secondEnd;
            }
            carry = cur / 10;
            cur %= 10;
            third.append((char) (cur + '0'));
        }
        third.reverse();
        return third.toString();
    }

    //method 2 dfs + 剪枝 O(n^2) O(n)
    public boolean isAdditiveNumber2(String num) {
        return dfs(num, 0, 0, 0, 0);
    }

    private boolean dfs(String num, int index, int count, long prepre, long pre) {
        if (index >= num.length()) {
            return count > 2;
        }

        long current = 0;

        for (int i = index; i < num.length(); i++) {
            char c = num.charAt(i);
            //0不能当数字首位使用，但可以使用自身
            if (num.charAt(index) == '0' && i > index) {
                return false;
            }
            current = current * 10 + c - '0';
            if (count > 1) {
                long sum = prepre + pre;
                //剪枝，current大于sum就错误，小于就继续增加索引
                if (sum < current) {
                    return false;
                }
                if (sum > current) {
                    continue;
                }
            }

             // 当前满足条件了，或者还不到两个数，向下一层探索
            if (dfs(num, i + 1, count + 1, pre, current)) {
                return true;
            }
        }

        return false;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String num = "199100199";
		
		IsAdditiveNumber ia = new IsAdditiveNumber();
		System.out.println(ia.isAdditiveNumber1(num));
		System.out.println(ia.isAdditiveNumber2(num));

	}

}
