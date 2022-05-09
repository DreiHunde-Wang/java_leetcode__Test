package src.dptest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两位玩家分别扮演猫和老鼠，在�?�? 无向 图上进行游戏，两人轮流行动�??
 * 图的形式是：graph[a] 是一个列表，由满足 ab 是图中的�?条边的所有节�? b 组成�?
 * 老鼠从节�? 1 �?始，第一个出发；猫从节点 2 �?始，第二个出发�?�在节点 0 处有�?个洞�?
 * 在每个玩家的行动中，他们必须沿着图中与所在当前位置连通的�?条边移动。例如，如果老鼠在节�? 1 ，那么它必须移动�? graph[1] 中的任一节点�?
 * 此外，猫无法移动到洞中（节点 0）�??
 * 然后，游戏在出现以下三种情形之一时结束：
 * 如果猫和老鼠出现在同�?个节点，猫获胜�??
 * 如果老鼠到达洞中，�?�鼠获胜�?
 * 如果某一位置重复出现（即，玩家的位置和移动顺序都与上�?次行动相同），游戏平�?�?
 * 给你�?张图 graph ，并假设两位玩家都都以最佳状态参与游戏：
 * 如果老鼠获胜，则返回 1�?
 * 如果猫获胜，则返�? 2�?
 * 如果平局，则返回 0 �?
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
 * method 1 dp + 记忆化搜�? O(n^4) O(n^3)
 * @author Dreihunde
 *
 */
class Solution1 {
	//定义状�??
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
        //初始状�?�为 mouse:1 cat:2 turns:0
        return getResult(1, 2, 0);

    }
    private int getResult(int mouse, int cat, int turns) {
    	//当轮数到�?2 * n时，即为平局
        if (turns == 2 * n) {
            return Draw;
        }
        //当前状�?�未访问时，更新状�??
        if (dp[mouse][cat][turns] < 0) {
        	//老鼠到节�?0，即为�?�鼠�?
            if (mouse == 0) {
                dp[mouse][cat][turns] = Mouse_Win;
            //猫碰到�?�鼠，即为猫�?
            } else if (cat == mouse) {
                dp[mouse][cat][turns] = Cat_Win;
            //都不是则寻找�?佳状�?
            } else {
                getNextResult(mouse, cat, turns);
            }
        }

        return dp[mouse][cat][turns];
    }

    private void getNextResult(int mouse, int cat, int turns) {
    	//判断当前移动的节点为猫还是�?�鼠 
        int moveCur = turns % 2 == 0 ? mouse : cat;
        //老鼠移动前，默认猫赢，猫移动前，默认老鼠�?
        int defaultResult = moveCur == mouse ? Cat_Win : Mouse_Win;
        int result = defaultResult;
        
        //遍历当前节点可以访问的节�?
        int[] nextNodes = graph[moveCur];
        for (int next : nextNodes) {
        	//猫没法访�?0节点
            if (moveCur == cat && next == 0) {
                continue;
            }
            //谁能移动，则更新�?
            int nextMouse = moveCur == mouse ? next : mouse;
            int nextCat = moveCur == cat ? next : cat;
            //判断下一个节点的状�??
            int nextResult = getResult(nextMouse, nextCat, turns + 1);
            //如果和默认状态不�?致，则更新状态，如果�?有节点的更新状�?�都为平�?，则新状态为平局
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
 * method 2 �?大最小博�? O(n^4) O(n^3)
 * @author Dreihunde
 *
 */
class Solution2 {
	//设定游戏状�??
    private final int Mouse_Win = 1;
    private final int Cat_Win = 2;
    private final int Draw = 0;
    
    //记录出现过的状�?�Map<turns, Map<mouse * n + cat, res>>
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
    	//结束状�??
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
        //如果是该老鼠走，则res预设�?1
        int res = turns % 2 == 0 ? 1 : -1;
        if (turns % 2 == 0) {
        	//遍历老鼠的下�?步中，res�?低的�?�?
            for (int next : graph[mouse]) {
                res = Math.min(res, minMax(next, cat, turns + 1));
                if (res == -1) {
                    break;
                }
            }
        } else {
        	//遍历猫的下一步中，res�?高的�?�?
            for (int next : graph[cat]) {
            	//猫不能走�?0
                if (next == 0) {
                    continue;
                }
                res = Math.max(res, minMax(mouse, next, turns + 1));
                if (res == 1) {
                    break;
                }
            }
        }
        //遍历过的结果记录到cache�?
        memo.put(key, res);
        cache.put(turns, memo);
        return res;
    }
}
