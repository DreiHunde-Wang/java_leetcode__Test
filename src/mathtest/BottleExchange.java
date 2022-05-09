package src.mathtest;

/**
 * 小区便利店正在促销，用 numExchange 个空酒瓶可以兑换一瓶新酒。你购入了 numBottles 瓶酒。
 * 如果喝掉了酒瓶中的酒，那么酒瓶就会变成空的。
 * 请你计算 最多 能喝到多少瓶酒。
 * @author Dreihunde
 *
 */
public class BottleExchange {
	//method 1 模拟bfs O(b / e) O(1)
    public int numWaterBottles1(int numBottles, int numExchange) {
        int sumDrink = 0;
        int emptyBottles = 0;
        sumDrink += numBottles;
        emptyBottles = numBottles;
        while (emptyBottles >= numExchange) {
            numBottles = emptyBottles / numExchange;
            sumDrink +=  numBottles;
            emptyBottles = emptyBottles % numExchange + numBottles;

        }
        return sumDrink;
    }

    //method 2 dfs O(b/e) O(1)
    public int numWaterBottles2(int numBottles, int numExchange) {
        return dfs(numBottles, numExchange, 0);
    }

    private int dfs(int numBottles, int numExchange, int emptyBottles) {
        if (numBottles + emptyBottles < numExchange) {
            return numBottles;
        }
        emptyBottles = numBottles + emptyBottles;
        return numBottles + dfs(emptyBottles / numExchange, numExchange, emptyBottles % numExchange);
    }

    //method 3 math O(1) O(1)
    public int numWaterBottles(int numBottles, int numExchange) {
        return numBottles >= numExchange ? (numBottles - numExchange) / (numExchange - 1) + 1 + numBottles : numBottles;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
