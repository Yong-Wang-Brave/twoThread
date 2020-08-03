import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*transfer阻塞操作*/
public class transferQUene {

    static Thread t1 = new Thread();
    static Thread t2 = new Thread();

    public static void main(String[] args) throws Exception {
        final Object o =new Object();
        char[] c1 = "123".toCharArray();
        char[] c2 = "ABC".toCharArray();

     TransferQueue<Character>  queue =new LinkedTransferQueue<>();
        t1 = new Thread(() -> {
            try {
                for (char c : c2) {
                 System.out.print(queue.take());
                queue.transfer(c);
                }

            }catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "t1");
        t2 = new Thread(() -> {
            try {
                for (char c : c1) {
                    queue.transfer(c);
                    System.out.print(queue.take());
                }

            }catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "t2");

        t1.start();
        t2.start();

    }
}
