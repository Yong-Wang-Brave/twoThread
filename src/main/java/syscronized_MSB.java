import java.util.concurrent.locks.LockSupport;

public class syscronized_MSB {


    static Thread t1 = new Thread();
    static Thread t2 = new Thread();

    public static void main(String[] args) throws Exception {
         final Object o =new Object();
        char[] c1 = "123".toCharArray();
        char[] c2 = "ABC".toCharArray();

        t1 = new Thread(() -> {
            synchronized (o) {
                for (char c : c1) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();//由于无论哪个小城最后执行的都是wait，jvm不会退出 ，所以需要执行唤醒。
            }
    }, "t1");
        t2= new Thread(() -> {
            synchronized (o) {
                for (char c : c2) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }, "t2");


        t1.start();
        t2.start();

    }
}