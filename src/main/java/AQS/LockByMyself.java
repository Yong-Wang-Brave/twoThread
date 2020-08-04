package AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
/*继承lock接口实现自定义的锁*/
public class LockByMyself  implements Lock {

     private static  volatile int i =0;
    @Override
    public void lock() {

        synchronized (this){
            if(i!=0){//锁已经被占用
                try {
                    this.wait();//AQS阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        i=1;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
           synchronized (this){
               this.notifyAll();
           }
        i=0;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
