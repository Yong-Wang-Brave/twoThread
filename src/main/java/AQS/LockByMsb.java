package AQS;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class LockByMsb implements Lock {

   private   Sync sync= new Sync();
    @Override
    public void lock() {
   sync.acquire(1);//内部已经实现如果没拿到锁就去 排队
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
   sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    private class Sync extends AbstractQueuedSynchronizer{

      protected boolean tryAcquire(int arg){
          assert arg==1;
          if(compareAndSetState(0,1)){

              setExclusiveOwnerThread(Thread.currentThread());
              return  true;
          }
        return false;
      }


        protected boolean tryRelese(int arg){
            assert arg==1;
                    if(! isHeldExclusively())  throw new IllegalMonitorStateException();
                    setExclusiveOwnerThread(null);
                    setState(0);
            return true;
        }

        protected boolean isHeldExclusively(int arg){
            return getExclusiveOwnerThread()==Thread.currentThread();
        }

    }

}
