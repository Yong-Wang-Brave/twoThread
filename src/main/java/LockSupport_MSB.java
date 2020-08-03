import java.util.concurrent.locks.LockSupport;
/*两个线程一个输出数字，一个输出字母，交替输出实现1A2B3C*/

/*1)先定义两个字符数组
2）每次线程进行遍历输出
3）LockSupport park阻塞 unpark非阻塞*/

public class LockSupport_MSB {
  static   Thread t1 =new Thread();
   static  Thread t2 =new Thread();

public static void main(String[] args) throws Exception{

    char[]  c1 ="123".toCharArray();
    char[] c2 ="ABC".toCharArray();

    t1=new Thread(()  -> {
        for (char c :c1){
            System.out.print(c);
            LockSupport.unpark(t2);//唤醒t2
           LockSupport.park();//阻塞自己
        }

    },"t1");
     t2=new Thread(()  -> {
        for (char c :c2){
            LockSupport.park();//先阻塞自己在输出
            System.out.print(c);
            LockSupport.unpark(t1);

        }

    },"t2");


     t1.start();
     t2.start();

}




















}
