package AQS;


/*100个线程对m值操作每个线程操作100次,加上同步锁*/
public class thread1 {

     public static int m=0;

    public static void main(String[] args) throws InterruptedException {
         Thread[] threads =new Thread[100];
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new Thread(() -> {
                    //锁住的是m++
                    synchronized (thread1.class){
                    for (int j = 0; j < 100; j++) {
                        m++;
                    }}
                });
            }
       for (Thread t: threads) t.start();
        for (Thread t: threads) t.join();
System.out.println(m);

    }



}
