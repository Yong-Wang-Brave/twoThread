package AQS;

import java.util.concurrent.locks.Lock;

public class CountDownLatch {
    public static int m = 0;
    public static Lock lock = new LockByMyself();
  public static   java.util.concurrent.CountDownLatch cd =new java.util.concurrent.CountDownLatch(100);
    public static void main(String[] args) throws Exception{
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                lock.lock();
                try {
                    for (int j = 0; j < 100; j++) {
                        m++;
                    }
                } finally {
                    lock.unlock();
                }

                    cd.countDown();


            });
        }
        for (Thread t : threads) t.start();
         cd.await();
        System.out.println(m);
    }
}
