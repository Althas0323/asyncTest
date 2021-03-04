package async;

import threads.Bank;

public class AsyncThread {
    private static final double INIT_BALANCE = 1000;

    private static final int ACCOUNT_COUNT = 10;

    private static final int SLEEP_TIME = 5 * 1000;

    public static void main(String[] args) {
        Bank bank = new Bank(ACCOUNT_COUNT, INIT_BALANCE);

        for (int i = 0; i < ACCOUNT_COUNT; i++) {
            int fromAccount = i;
            Runnable r = () -> {
                try
                {
                    while (true) {
                        int toAccount = (int) (ACCOUNT_COUNT * Math.random());
                        double amount = INIT_BALANCE * Math.random();
                        int sleepTime = (int) (SLEEP_TIME * Math.random());

                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep(sleepTime);
                    }
                }
                catch (InterruptedException e) {
                    System.out.println("Interrupted by Exception");
                }
            };
            Thread thread = new Thread(r);
            thread.start();
        }
    }
}
