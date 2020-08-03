import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*1）一个condition相当于一个队列
2）等同于sys，不同之处是可以控制释放哪个队列的锁*/
public class condition {

    static Thread t1 = new Thread();
    static Thread t2 = new Thread();

    public static void main(String[] args) throws Exception {
        final Object o =new Object();
        char[] c1 = "123".toCharArray();
        char[] c2 = "ABC".toCharArray();

        Lock lock =new ReentrantLock();
        Condition  ct1=lock.newCondition();
        Condition  ct2=lock.newCondition();
        t1 = new Thread(() -> {
            try {
                lock.lock();
                for (char c : c1) {
                    System.out.print(c);
                    ct2.signal();//唤醒队列2
                    ct1.await();
                    }
                ct2.signal();//自己不可能叫醒自己，只能叫醒别人

            }catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                lock.unlock();
            }

        }, "t1");
        t2 = new Thread(() -> {
            try {
                lock.lock();
                for (char c : c2) {
                    System.out.print(c);
                    ct1.signal();
                    ct2.await();
                }

                ct1.signal();

            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }, "t2");


        t1.start();
        t2.start();

    }

}
