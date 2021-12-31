package mathtest;

/**
 * 对于一个 正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」。
 * 给定一个 整数 n， 如果是完美数，返回 true，否则返回 false
 * 1 <= num <= 10^8
 * @author Dreihunde
 *
 */
public class IsPerfectNum {
	//method 1 枚举 O(m^(1/2)) O(1)
    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int sum = 1;
        // for (int i = 2; i <= Math.sqrt(num); i++) {
        for (int i = 2; i <= num / i; i++) {
            if (num % i == 0)
                sum += i + num / i;
        }
        return sum == num;
    }

    //method 2 数学 O(1) O(1) 完美数满足2^(p - 1) * (2^p - 1) p为质数且 2^p-1也为素数
    public boolean checkPerfectNumber2(int num) {
        return num == 6 || num == 28 || num == 496 || num == 8128 || num == 33550336;
    }


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
