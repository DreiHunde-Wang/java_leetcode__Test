package dptest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两位玩家分别扮演猫和老鼠，在一张 无向 图上进行游戏，两人轮流行动。
 * 图的形式是：graph[a] 是一个列表，由满足 ab 是图中的一条边的所有节点 b 组成。
 * 老鼠从节点 1 开始，第一个出发；猫从节点 2 开始，第二个出发。在节点 0 处有一个洞。
 * 在每个玩家的行动中，他们必须沿着图中与所在当前位置连通的一条边移动。例如，如果老鼠在节点 1 ，那么它必须移动到 graph[1] 中的任一节点。
 * 此外，猫无法移动到洞中（节点 0）。
 * 然后，游戏在出现以下三种情形之一时结束：
 * 如果猫和老鼠出现在同一个节点，猫获胜。
 * 如果老鼠到达洞中，老鼠获胜。
 * 如果某一位置重复出现（即，玩家的位置和移动顺序都与上一次行动相同），游戏平局。
 * 给你一张图 graph ，并假设两位玩家都都以最佳状态参与游戏：
 * 如果老鼠获胜，则返回 1；
 * 如果猫获胜，则返回 2；
 * 如果平局，则返回 0 。
 * @author Dreihunde
 *
 */
public class CatMouseGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] graph = new int[][] {{1,3}, {0}, {3}, {0,2}};
		Solution1 method1 = new Solution1();
		System.out.println(method1.catMouseGame(graph));

	}

}

/**
 * method 1 dp + 记忆化搜索 O(n^4) O(n^3)
 * @author Dreihunde
 *
 */
class Solution1 {
	//定义状态
    private final int Mouse_Win = 1;
    private final int Cat_Win = 2;
    private final int Draw = 0;
    
    //全局变量
    int n;
    private int[][][] dp;
    private int[][] graph;

    public int catMouseGame(int[][] graph) {
        n = graph.length;
        //dp[mouse][cat][turns] mouse的位置[0, n) cat的位置[0, n) 总共轮数[0, 2n)
        dp = new int[n][n][n * 2];
        this.graph = graph;
        
        //未访问过的状态为-1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        //初始状态为 mouse:1 cat:2 turns:0
        return getResult(1, 2, 0);

    }
    private int getResult(int mouse, int cat, int turns) {
    	//当轮数到达2 * n时，即为平局
        if (turns == 2 * n) {
            return Draw;
        }
        //当前状态未访问时，更新状态
        if (dp[mouse][cat][turns] < 0) {
        	//老鼠到节点0，即为老鼠赢
            if (mouse == 0) {
                dp[mouse][cat][turns] = Mouse_Win;
            //猫碰到老鼠，即为猫赢
            } else if (cat == mouse) {
                dp[mouse][cat][turns] = Cat_Win;
            //都不是则寻找最佳状态
            } else {
                getNextResult(mouse, cat, turns);
            }
        }

        return dp[mouse][cat][turns];
    }

    private void getNextResult(int mouse, int cat, int turns) {
    	//判断当前移动的节点为猫还是老鼠 
        int moveCur = turns % 2 == 0 ? mouse : cat;
        //老鼠移动前，默认猫赢，猫移动前，默认老鼠赢
        int defaultResult = moveCur == mouse ? Cat_Win : Mouse_Win;
        int result = defaultResult;
        
        //遍历当前节点可以访问的节点
        int[] nextNodes = graph[moveCur];
        for (int next : nextNodes) {
        	//猫没法访问0节点
            if (moveCur == cat && next == 0) {
                continue;
            }
            //谁能移动，则更新谁
            int nextMouse = moveCur == mouse ? next : mouse;
            int nextCat = moveCur == cat ? next : cat;
            //判断下一个节点的状态
            int nextResult = getResult(nextMouse, nextCat, turns + 1);
            //如果和默认状态不一致，则更新状态，如果所有节点的更新状态都为平局，则新状态为平局
            if (nextResult != defaultResult) {
                result = nextResult;
                if (result != Draw) {
                    break;
                }
            }
        }
        dp[mouse][cat][turns] = result;
    }
}

/**
 * method 2 最大最小博弈 O(n^4) O(n^3)
 * @author Dreihunde
 *
 */
class Solution2 {
	//设定游戏状态
    private final int Mouse_Win = 1;
    private final int Cat_Win = 2;
    private final int Draw = 0;
    
    //记录出现过的状态Map<turns, Map<mouse * n + cat, res>>
    private HashMap<Integer, Map<Integer, Integer>> cache;

    int n;
    private int[][] graph;



    public int catMouseGame(int[][] graph) {
        cache = new HashMap<>();
        n = graph.length;
        this.graph = graph;
        
        int ans = minMax(1, 2, 0);
        if (ans == -1) {
            return Mouse_Win;
        } else if (ans == 1) {
            return Cat_Win;
        } else {
            return Draw;
        }
        
    }

    private int minMax(int mouse, int cat, int turns) {
    	//结束状态
        if (turns >= 2 * n) {
            return 0;
        }
        if (mouse == 0) {
            return -1;
        } 
        if (cat == mouse) {
            return 1;
        }
        
        //对应轮数中，不同位置下的结果
        Map<Integer, Integer> memo = cache.getOrDefault(turns, new HashMap<Integer, Integer>());
        int key = mouse * n + cat;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        //如果是该老鼠走，则res预设为1
        int res = turns % 2 == 0 ? 1 : -1;
        if (turns % 2 == 0) {
        	//遍历老鼠的下一步中，res最低的一步
            for (int next : graph[mouse]) {
                res = Math.min(res, minMax(next, cat, turns + 1));
                if (res == -1) {
                    break;
                }
            }
        } else {
        	//遍历猫的下一步中，res最高的一步
            for (int next : graph[cat]) {
            	//猫不能走到0
                if (next == 0) {
                    continue;
                }
                res = Math.max(res, minMax(mouse, next, turns + 1));
                if (res == 1) {
                    break;
                }
            }
        }
        //遍历过的结果记录到cache中
        memo.put(key, res);
        cache.put(turns, memo);
        return res;
    }
}
