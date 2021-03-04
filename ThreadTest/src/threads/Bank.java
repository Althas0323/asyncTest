package threads;

import java.util.Arrays;

public class Bank {
    private final double[] accounts;

    public Bank(int accountCount, double initBalance) {
        accounts = new double[accountCount];
        /*for (int i = 0; i < accountCount; i++) {
            accounts[i] = initBalance;
        }*/
        Arrays.fill(accounts, initBalance);
    }

    public void transfer(int from, int to, double amount) {
        if (from < 0 || from >=  getAccountSize() || to < 0 || to >= getAccountSize()) {
            System.out.println("account num illegal");
            return;
        }

        if (amount < 0 || amount > accounts[from]) {
            //System.out.println("transfer amount exceeds the balance of account " + from);
            return;
        }

        if (from == to) {
            return;
        }

        System.out.print("Thread: " + Thread.currentThread());

        String strFormat = String.format("%.2f", amount);
        String strAmount = "$" + strFormat;

        System.out.printf(" transfer %7s from %d to %d: ", strAmount, from, to);
        accounts[from] -= amount;
        accounts[to] += amount;

        strFormat = String.format("%.2f", getSum());
        strAmount = "$" + strFormat;

        System.out.printf("the sum balance is %s %n", strAmount);
    }

    public int getAccountSize() {
        return accounts.length;
    }

    public double getSum() {
        double sum = 0;
        for (int i = 0; i < getAccountSize(); i++) {
            sum += accounts[i];
        }
        return sum;
    }
}
