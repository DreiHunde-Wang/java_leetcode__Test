package dfsandbfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * �����ǰ��һ�¾��εġ��� n ��ש����ɵ�שǽ����Щש��߶���ͬ��Ҳ����һ����λ�ߣ����ǿ�Ȳ�ͬ��ÿһ��ש��Ŀ��֮����ȡ�
 * ������Ҫ��һ�� �Զ����� �ġ����� ���� ש��Ĵ��ߡ�����㻭����ֻ�Ǵ�ש��ı�Ե�������Ͳ��㴩�����ש���㲻������ǽ��������ֱ��Ե֮һ���ߣ�������Ȼ��û�д���һ��ש�ġ�
 * ����һ����ά���� wall ��������������ǽ�������Ϣ�����У�wall[i] ��һ�������������ÿ��ש�Ŀ�ȵ����顣
 * ����Ҫ�ҳ�����������ʹ������ ������ש���������� �����ҷ��� ������ש������ ��
 * @author Dreihunde
 *
 */
public class BrickWall {
	//method 1 ��ϣ�� ����שǽ����=height - ����ש�����
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (List<Integer> widths : wall) {
            int n = widths.size();
            int sum = 0;
            for (int i = 0; i < n - 1; i++) {
                sum += widths.get(i);
                //��������ھ�+1�������½�
                cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
            }
        }
        int maxCnt = 0;
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            maxCnt = Math.max(maxCnt, entry.getValue());
        }
        return wall.size() - maxCnt;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<Integer>> wall = new ArrayList<>();
		wall.add(new ArrayList<Integer>(Arrays.asList(1, 2, 2, 1)));
		wall.add(new ArrayList<Integer>(Arrays.asList(3, 1, 2)));
		wall.add(new ArrayList<Integer>(Arrays.asList(1, 3, 2)));
		wall.add(new ArrayList<Integer>(Arrays.asList(2, 4)));
		wall.add(new ArrayList<Integer>(Arrays.asList(3, 1, 2)));
		wall.add(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 1)));
		
		BrickWall bk = new BrickWall();
		long startTime = System.nanoTime();
		System.out.println(bk.leastBricks(wall));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}
