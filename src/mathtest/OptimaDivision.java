package src.mathtest;

/**
 * 给定�?组正整数，相邻的整数之间将会进行浮点除法操作。例如， [2,3,4] -> 2 / 3 / 4 �?
 * 但是，你可以在任意位置添加任意数目的括号，来改变算数的优先级�?
 * 你需要找出�?�么添加括号，才能得到最大的结果，并且返回相应的字符串格式的表达式�??
 * 你的表达式不应该含有冗余的括号�??
链接：https://leetcode-cn.com/problems/optimal-division
 * @author Dreihunde
 *
 */
public class OptimaDivision {
	//method 1 math O(n) O(n)
    public String optimalDivision1(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return Integer.toString(nums[0]);
        }
        if (n == 2) {
            return Integer.toString(nums[0]) + "/" + Integer.toString(nums[1]);
        }
        StringBuffer sb = new StringBuffer();
        sb.append(Integer.toString(nums[0]));
        sb.append("/(");
        sb.append(Integer.toString(nums[1]));
        for (int i = 2; i < n; i++) {
            sb.append("/");
            sb.append(Integer.toString(nums[i]));
        }
        sb.append(")");
        return sb.toString();

    }

    //method 2 dp O(n^3) O(n^3)
    public String optimalDivision(int[] nums) {
        int n = nums.length;
        NodeT[][] dp = new NodeT[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[i][j] = new NodeT();
            }
        }

        for (int i = 0; i < n; i++) {
            dp[i][i].minVal = nums[i];
            dp[i][i].maxVal = nums[i];
            dp[i][i].minStr = String.valueOf(nums[i]);
            dp[i][i].maxStr = String.valueOf(nums[i]);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j + i < n; j++) {
                for (int k = j; k < j + i; k++) {
                    if (dp[j][j + i].maxVal < dp[j][k].maxVal / dp[k + 1][j + i].minVal) {
                        dp[j][j + i].maxVal = dp[j][k].maxVal / dp[k + 1][j + i].minVal;
                        if (k + 1 == j + i) {
                            dp[j][j + i].maxStr = dp[j][k].maxStr + "/" + dp[k + 1][j + i].minStr;
                        } else {
                            dp[j][j + i].maxStr = dp[j][k].maxStr + "/(" + dp[k + 1][j + i].minStr + ")";
                        }
                    }
                    if (dp[j][j + i].minVal > dp[j][k].minVal / dp[k + 1][j + i].maxVal) {
                        dp[j][j + i].minVal = dp[j][k].minVal / dp[k + 1][j + i].maxVal;
                        if (k + 1 == j + i) {
                            dp[j][j + i].minStr = dp[j][k].minStr + "/" + dp[k + 1][j + i].maxStr; 
                        } else {
                            dp[j][j + i].minStr = dp[j][k].minStr + "/(" + dp[k + 1][j + i].maxStr + ")"; 
                        }
                    }
                }
            }
        }
        return dp[0][n - 1].maxStr;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class NodeT {
    double maxVal, minVal;
    String minStr, maxStr;

    public NodeT() {
        this.minVal = 10000.0;
        this.maxVal = 0.0;
    }
}
