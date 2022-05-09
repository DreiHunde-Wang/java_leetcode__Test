package src.mathtest;

/**
 * С�����������ڴ������� numExchange ���վ�ƿ���Զһ�һƿ�¾ơ��㹺���� numBottles ƿ�ơ�
 * ����ȵ��˾�ƿ�еľƣ���ô��ƿ�ͻ��ɿյġ�
 * ������� ��� �ܺȵ�����ƿ�ơ�
 * @author Dreihunde
 *
 */
public class BottleExchange {
	//method 1 ģ��bfs O(b / e) O(1)
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
