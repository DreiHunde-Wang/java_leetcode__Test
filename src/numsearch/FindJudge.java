package numsearch;

/**
 * 
 * @author Dreihunde
 *
 */
public class FindJudge {
	//method 1 遍历 O(n) O(n)
    public int findJudge1(int n, int[][] trust) {
        if (trust.length == 0 && n > 1)
            return -1;
        int[] judge = new int[n];
        for (int i = 0; i < trust.length; i++) {
            judge[trust[i][0] - 1] = -1;
            if (judge[trust[i][1] - 1] != -1) {
                judge[trust[i][1] - 1]++;
            }
        }
        int rev = -1;
        for (int i = 0; i < n; i++) {
            if (judge[i] == n - 1) {
                if (rev != -1)
                    return -1;
                rev = i + 1;
            }
                
        }
        return rev;
    }

    //method 2 计算节点的出入度 O(n + m) O(n)
    public int findJudge(int n, int[][] trust) {
        int[] inDegrees = new int[n + 1];
        int[] outDegrees = new int[n + 1];
        for (int[] edge : trust) {
            int x = edge[0], y = edge[1];
            ++inDegrees[y];
            ++outDegrees[x];
        }
        for (int i = 1; i <= n; ++i) {
            if (inDegrees[i] == n - 1 && outDegrees[i] == 0) {
                return i;
            }
        }
        return -1;
    }


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
