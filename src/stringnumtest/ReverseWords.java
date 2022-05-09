package src.stringnumtest;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;


/**
 * ����һ���ַ��� s �������ת�ַ����е����� ���� ��
 * ���� ���ɷǿո��ַ���ɵ��ַ�����s ��ʹ������һ���ո��ַ����е� ���� �ָ�����
 * ���㷵��һ����ת s �е���˳���õ����ո��������ַ�����
 * ˵����
 * �����ַ��� s ������ǰ�桢������ߵ��ʼ��������Ŀո�
 * ��ת�󵥴ʼ�Ӧ������һ���ո�ָ���
 * ��ת����ַ����в�Ӧ��������Ŀո�
 * @author Dreihunde
 *
 */
public class ReverseWords {
	//method1
	public String reverseWords1(String s) {
        s = s.trim();
        List<String> list = Arrays.asList(s.split("\\s+"));
        Collections.reverse(list);
        return String.join(" ", list);
    }
	
	//method2
	public String reverseWords2(String s) {
        StringBuilder sb = trimSpaces(s);

        // ��ת�ַ���
        reverse(sb, 0, sb.length() - 1);

        // ��תÿ������
        reverseEachWord(sb);

        return sb.toString();
    }

    public StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        // ȥ���ַ�����ͷ�Ŀհ��ַ�
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }

        // ȥ���ַ���ĩβ�Ŀհ��ַ�
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }

        // ���ַ��������Ŀհ��ַ�ȥ��
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);

            if (c != ' ') {
                sb.append(c);
            } else if (sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }

            ++left;
        }
        return sb;
    }

    public void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    public void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;

        while (start < n) {
            // ѭ�������ʵ�ĩβ
            while (end < n && sb.charAt(end) != ' ') {
                ++end;
            }
            // ��ת����
            reverse(sb, start, end - 1);
            // ����start��ȥ����һ������
            start = end + 1;
            ++end;
        }
    }
    
    //method3 ˫�˶���
    public String reverseWords3(String s) {
        int left = 0, right = s.length() - 1;
        // ȥ���ַ�����ͷ�Ŀհ��ַ�
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }

        // ȥ���ַ���ĩβ�Ŀհ��ַ�
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }

        Deque<String> d = new ArrayDeque<String>();
        StringBuilder word = new StringBuilder();
        
        while (left <= right) {
            char c = s.charAt(left);
            if ((word.length() != 0) && (c == ' ')) {
                // ������ push �����е�ͷ��
                d.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            ++left;
        }
        d.offerFirst(word.toString());

        return String.join(" ", d);
    }
    
    //method 4 ˫ָ��
    public String reverseWords4(String s) {
        s = s.trim();
        int left = s.length() - 1;
        int right = left;
        StringBuffer sb = new StringBuffer();

        while (left >= 0) {
            //��ѯ������һ�����ʣ������ո�ֹͣ
            while (left >= 0 && s.charAt(left) != ' ') {
                left--;
            }
            //leftָ�򵥴���ߵ�λ�ã�rightָ�򵥴ʵ�������ĸ
            sb.append(s.substring(left + 1, right + 1) + " ");
            //left�����ո�
            while (left >= 0 && s.charAt(left) == ' ') {
                left--;
            }
            //right����Ϊ��һ�����ʵ�������ĸ
            right = left;
        }
        //ȥ��append��ӵ����һ���ո�
        return sb.toString().trim();
    }
    
    public static void main(String[] args) {
		String s = "the sky is blue";
		ReverseWords rw = new ReverseWords();
		
		long startTime=System.nanoTime(); 
		System.out.println(rw.reverseWords1(s));
		long endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime=System.nanoTime(); 
		System.out.println(rw.reverseWords2(s));
		endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime=System.nanoTime(); 
		System.out.println(rw.reverseWords3(s));
		endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime=System.nanoTime(); 
		System.out.println(rw.reverseWords4(s));
		endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}


}
