package src.printtreenode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * è¾å¥ä¸?ä¸ªæ´æ°æ°ç»ï¼å¤æ­è¯¥æ°ç»æ¯ä¸æ¯æäºåæç´¢æ çååºéåç»æã?å¦ææ¯åè¿å? trueï¼å¦åè¿å? falseã?
 * åè®¾è¾å¥çæ°ç»çä»»æä¸¤ä¸ªæ°å­é½äºä¸ç¸åã??
 * @author Dreihunde
 *
 */
public class ListIsTreePostOrder {
	//method 1 åæ²»éå½ O(n2) O(n)
    public boolean verifyPostorder1(int[] postorder) {
        return cur(postorder, 0, postorder.length - 1);
    }

    private boolean cur(int[] postorder, int i, int j) {
        if (i >= j) {
            return true;
        }
        int p = i;
        //ååå·¦å³å­æ ï¼? éåååºéåç? [i, j][i,j] åºé´åç´ ï¼å¯»æ? ç¬¬ä¸ä¸ªå¤§äºæ ¹èç¹ çèç¹ï¼ç´¢å¼è®°ä¸º m ã?
        //æ­¤æ¶ï¼å¯åååºå·¦å­æ åºé´ [i,m-1][i,mâ?1] ãå³å­æ åºé´ [m, j - 1][m,jâ?1] ãæ ¹èç¹ç´¢å¼ j
        while (postorder[p] < postorder[j]) {
            p++;
        }
        int m = p;
        while (postorder[p] > postorder[j]) {
            p++;
        }

        return p == j && cur(postorder, i, m - 1) && cur(postorder, m, j - 1);
    }

    //method 2 éå O(n) O(n)
    public boolean verifyPostorder2(int[] postorder) {
        Deque<Integer> stack = new ArrayDeque<>();
        int root = Integer.MAX_VALUE;
        //ååºéåçé?éå?
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > root)    return false;
            //åæ¢éåèç¹ï¼æ¸é¤ä¸æ¬¡éåæ°æ®ï¼åºæ 
            while (!stack.isEmpty() && postorder[i] < stack.peek()) {
                root = stack.pop();
            }
            //åè°éå¢é»è¾ï¼åæ ?
            stack.push(postorder[i]);
        }

        return true;

    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListIsTreePostOrder lt = new ListIsTreePostOrder();
		int[] postOrder = new int[] {4, 8, 6, 12, 16, 14, 10};
		
		long startTime = System.nanoTime();
		System.out.println(lt.verifyPostorder1(postOrder));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
				
		startTime = System.nanoTime();
		System.out.println(lt.verifyPostorder2(postOrder));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}
