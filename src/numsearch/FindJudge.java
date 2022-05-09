package src.numsearch;

/**
 * 小镇里有 n 个人，按从 1 到 n 的顺序编号。传言称，这些人中有一个暗地里是小镇法官。
 * 如果小镇法官真的存在，那么：
 * 小镇法官不会信任任何人。
 * 每个人（除了小镇法官）都信任这位小镇法官。
 * 只有一个人同时满足属性 1 和属性 2 。
 * 给你一个数组 trust ，其中 trust[i] = [ai, bi] 表示编号为 ai 的人信任编号为 bi 的人。
 * 如果小镇法官存在并且可以确定他的身份，请返回该法官的编号；否则，返回 -1 。
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
