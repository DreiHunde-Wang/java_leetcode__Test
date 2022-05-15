package src.mathtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * �������������ļ��ϣ�������ȡ��������������Σ���������ɵ���������ε������
 * https://leetcode.cn/problems/largest-triangle-area/
 * @author Dreihunde
 *
 */
public class LargestTriangleArea {
	//method 1 ģ�� O(n^3) O(1)
    public double largestTriangleArea1(int[][] points) {
        double ans = 0;
        int n = points.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    ans = Math.max(ans, getArea(points[i], points[j], points[k]));
                }
            }
        }
        return ans;
    }
    //        |x1 y1 1|
    // area = |x2 y2 1|
    //        |x3 y3 1|
    public double getArea(int[] p1, int[] p2, int[] p3) {
        return 0.5 * Math.abs(p1[0] * p2[1] +  p1[1] * p3[0] + p2[0] * p3[1] - p2[1] * p3[0] - p3[1] * p1[0] - p1[1] * p2[0]);
    }
    
    
  //method 2 ͹���㷨 O(n^2) O(n)
    public double largestTriangleArea(int[][] points) {
        //��ʹ��Andrew�㷨������е��Ӧ��͹��convexHull
        int[][] convexHull = getConvexHull(points);
        int n = convexHull.length;
        double ret = 0.0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1, k = i + 2; j + 1 < n; j++) {
                while (k + 1 < n) {
                    double curArea = triangleArea(convexHull[i][0], convexHull[i][1], convexHull[j][0], convexHull[j][1], convexHull[k][0], convexHull[k][1]);
                    double nextArea = triangleArea(convexHull[i][0], convexHull[i][1], convexHull[j][0], convexHull[j][1], convexHull[k + 1][0], convexHull[k + 1][1]);
                    if (curArea >= nextArea) {
                        break;
                    }
                    k++;
                }
                double area = triangleArea(convexHull[i][0], convexHull[i][1], convexHull[j][0], convexHull[j][1], convexHull[k][0], convexHull[k][1]);
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }

    public int[][] getConvexHull(int[][] points) {
        int n = points.length;
        if (n < 4) {
            return points;
        }
        /* ���� x ��С����������� x ��ͬ������ y �Ĵ�С�������� */
        Arrays.sort(points, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        List<int[]> hull = new ArrayList<int[]>();
        /* ���͹�����°벿�� */
        for (int i = 0; i < n; i++) {
            while (hull.size() > 1 && cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) <= 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(points[i]);
        }
        int m = hull.size();
        /* ���͹�����ϰ벿�� */
        for (int i = n - 2; i >= 0; i--) {
            while (hull.size() > m && cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) <= 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(points[i]);
        }
        /* hull[0] ͬʱ����͹�����ϰ벿�ּ�⣬�����ȥ���ظ��� hull[0] */
        hull.remove(hull.size() - 1);
        m = hull.size();
        int[][] hullArr = new int[m][];
        for (int i = 0; i < m; i++) {
            hullArr[i] = hull.get(i);
        }
        return hullArr;
    }

    public double triangleArea(int x1, int y1, int x2, int y2, int x3, int y3) {
        return 0.5 * Math.abs(x1 * y2 + x2 * y3 + x3 * y1 - x1 * y3 - x2 * y1 - x3 * y2);
    }

    public int cross(int[] p, int[] q, int[] r) {
        return (q[0] - p[0]) * (r[1] - q[1]) - (q[1] - p[1]) * (r[0] - q[0]);
    }

}
