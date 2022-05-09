package src.enumtest;

/**
 * 给你�?个字符串 date ，按 YYYY-MM-DD 格式表示�?�? 现行公元纪年�? 日期。请你计算并返回该日期是当年的第几天�?
 * 通常情况下，我们认为 1 �? 1 日是每年的第 1 天，1 �? 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）�?致�??
 * @author Dreihunde
 *
 */
public class DayOfYear {
	//method 1 预处�? O(1) O(1)
    public int dayOfYear(String date) {
        int years = Integer.parseInt(date.substring(0, 4));
        int months = Integer.parseInt(date.substring(5, 7));
        int days = Integer.parseInt(date.substring(8));
        int[] amount = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (years % 400 == 0 || (years % 4 == 0 && years % 100 != 0)) {
                amount[1]++;
        }
        int ans = 0;
        for (int i = 0; i < months - 1; i++) {
            ans += amount[i];
            
        }
        
        
        ans += days;
        return ans;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
