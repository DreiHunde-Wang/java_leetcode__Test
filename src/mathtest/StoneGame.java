package mathtest;

/**
 * Alice 和 Bob 再次设计了一款新的石子游戏。现有一行 n 个石子，每个石子都有一个关联的数字表示它的价值。
 * 给你一个整数数组 stones ，其中 stones[i] 是第 i 个石子的价值。
 * Alice 和 Bob 轮流进行自己的回合，Alice 先手。每一回合，玩家需要从 stones 中移除任一石子。
 * 如果玩家移除石子后，导致 所有已移除石子 的价值 总和 可以被 3 整除，那么该玩家就 输掉游戏 。
 * 如果不满足上一条，且移除后没有任何剩余的石子，那么 Bob 将会直接获胜（即便是在 Alice 的回合）。
 * 假设两位玩家均采用 最佳 决策。如果 Alice 获胜，返回 true ；如果 Bob 获胜，返回 false 。
 * @author Dreihunde
 *
 */
public class StoneGame {
	//method 1 模拟 O(n) O(1)
    public boolean stoneGameIX1(int[] stones) {
        //余数为0的石子，余数为1的石子和余数为2的石子
        int cnt0 = 0, cnt1 = 0, cnt2 = 0;
        for (int val : stones) {
            int type = val % 3;
            if (type == 0) {
                ++cnt0;
            } else if (type == 1) {
                ++cnt1;
            } else {
                ++cnt2;
            }
        }
        //不考虑石头中有3的倍数个的石头的话，A先手只有两种情况:
        //先拿1： B若要不失败，则只能拿1，然后A只能拿2，B只能拿1，A继续只能拿2，如此A2B1循环，直到有一种石子没有，此时根据剩的石子判断获胜方: 剩2时，A胜利
        //先拿2： B若要不失败，则只能拿2，然后A只能拿1，B只能拿2，A继续只能拿1，如此A1B2循环，直到有一种石子没有，此时根据剩的石子判断获胜方: 剩1时，A胜利
        //因此，此时若1和2的数量均不少于1，A只要选择数量少的那种石子即可。A必胜
        //而如果1或者2有一方的数量为0，则A必败，因为A第二次选择时只能拿和B刚才选择的石头相同的石头
        if (cnt0 % 2 == 0) {
            return cnt1 >= 1 && cnt2 >= 1;
        }
        //当考虑有数量为3的倍数的石头时，相当于多了换手机会
        //如果石头数量为偶数，则没有影响(总能转回到没有数量为3的倍数的石头的情况)
        //若石头数量为奇数，则可以认为是A和B更换了先后手顺序
        //则此时，我们可以考虑A的必胜策略为拿光一种石子，然后逼迫B只能选择和自己之前的必败策略同样的行为(1或者2有一方的数量为0,然后B两次选择同样的石头)
        //而若两种石子的数量差不超过2，则A必败(B必胜): B转为了先手，而A无法拿成只剩下1或者只剩下2的情形，B总是可以通过拿数量少的那种石子获得胜利
        return cnt1 - cnt2 > 2 || cnt2 - cnt1 > 2;
    }

    //method 2 math统计余数为0, 1, 2的棋子个数 O(n) O(1)
    public boolean stoneGameIX2(int[] stones) {
        int[] cnts = new int[3];
        for (int i : stones) cnts[i % 3]++;
        return cnts[0] % 2 == 0 ? !(cnts[1] == 0 || cnts[2] == 0) : !(Math.abs(cnts[1] - cnts[2]) <= 2);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] stones = new int[] {5,1,2,4,3};
		StoneGame sg = new StoneGame();
		System.out.println(sg.stoneGameIX1(stones));
		System.out.println(sg.stoneGameIX2(stones));

	}

}
