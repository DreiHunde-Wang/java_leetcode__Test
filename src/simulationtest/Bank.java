package src.simulationtest;

/**
 * 你的任务是为�?个很受欢迎的银行设计�?款程序，以自动化执行�?有传入的交易（转账，存款和取款）。银行共�? n 个账户，编号�? 1 �? n 。每个账号的初始余额存储在一个下标从 0 �?始的整数数组 balance 中，其中�? (i + 1) 个账户的初始余额�? balance[i] �?
 * 请你执行�?�? 有效�? 交易。如果满足下面全部条件，则交�? 有效 �?
 * 指定的账户数量在 1 �? n 之间，且
 * 取款或�?�转账需要的钱的总数 小于或�?�等�? 账户余额�?
 * 实现 Bank 类：
 * Bank(long[] balance) 使用下标�? 0 �?始的整数数组 balance 初始化该对象�?
 * boolean transfer(int account1, int account2, long money) 从编号为 account1 的账户向编号�? account2 的账户转�? money 美元�?
 * 如果交易成功，返�? true ，否则，返回 false �?
 * boolean deposit(int account, long money) 向编号为 account 的账户存�? money 美元�?
 * 如果交易成功，返�? true ；否则，返回 false �?
 * boolean withdraw(int account, long money) 从编号为 account 的账户取�? money 美元�?
 * 如果交易成功，返�? true ；否则，返回 false �?
 * 链接：https://leetcode-cn.com/problems/simple-bank-system
 * @author Dreihunde
 *
 */
class Bank {
    //method 1 模拟 O(1) O(n)
    int n;
    long[] balance;

    public Bank(long[] balance) {
        this.balance = balance;
        this.n = balance.length;
    }
    
    public boolean transfer(int account1, int account2, long money) {
        if (account1 > n || balance[account1 - 1] < money || account2 > n) {
            return false;
        }
        balance[account1 - 1] -= money;
        balance[account2 - 1] += money;
        return true;
    }
    
    public boolean deposit(int account, long money) {
        if (account > n) {
            return false;
        }
        balance[account - 1] += money;
        return true;
    }
    
    public boolean withdraw(int account, long money) {
        if (account > n || balance[account - 1] < money) {
            return false;
        }
        balance[account - 1] -= money;
        return true;
    }
}
