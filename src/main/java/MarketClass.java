import java.util.Random;

public class MarketClass implements Runnable {

    int storedMoney = 10000;


    public void run() {
        int c = 100;
        System.out.println("Current savings: " + storedMoney);
        while(c>0) {

            Random generator = new Random();
            int i = generator.nextInt(1000);
            int j = generator.nextInt(1200);

            try {
                withdraw(j);
                deposit(i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            c--;
        }
    }


    public synchronized void deposit(int amount) throws InterruptedException {
        storedMoney += amount;
        System.out.println(Thread.currentThread().getName() + " deposited " + amount + ", updated balance: " + storedMoney);
        Thread.currentThread().sleep(500);
    }

    public synchronized void withdraw(int amount) throws InterruptedException {
        if (storedMoney - amount <= 0) {
            System.out.println("Not enough saving to withdraw!");

        } else {
            storedMoney -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrawed " + amount + ", updated balance: " + storedMoney);
        }
        Thread.currentThread().sleep(500);
    }


    public static void main(String[] args) {
        MarketClass marketClass = new MarketClass();
        Thread thread1 = new Thread(marketClass);
        thread1.setName("husband");
        Thread thread2 = new Thread(marketClass);
        thread2.setName("wife");
        thread1.start();
        thread2.start();

    }
}
