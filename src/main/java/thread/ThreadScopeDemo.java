package thread;

import java.util.Random;

/**
 * @Title Created by xzm
 * @date 2017/3/7.
 */
public class ThreadScopeDemo {


    public static void main(String[] args) {
        final Random random = new Random();
        for (int i = 0; i < 3; i++) {

            new Thread(){
                @Override
                public void run() {
                   int data= random.nextInt(10000);
                    System.out.println("当前线程"+Thread.currentThread().getName()+"数据为："+data);
                    MyDealScope instance = MyDealScope.getInstance();
                    instance.setMoney(data);
                    new BusinessA().get();
                    new BusinessB().get();
                }
            }.start();
        }
    }

    static class BusinessA{
        public void get(){
            MyDealScope instance = MyDealScope.getInstance();
            System.out.println("进入A模块的线程是"+Thread.currentThread().getName()+",数据为"+ instance.getMoney());
        }
    }


    static class BusinessB{
        public void get(){
            MyDealScope instance = MyDealScope.getInstance();
            System.out.println("进入B模块的线程是"+Thread.currentThread().getName()+",数据为"+instance.getMoney());
        }
    }


}
class MyDealScope{
    private int money;
    private static ThreadLocal<MyDealScope> map=new ThreadLocal<MyDealScope>();
    private MyDealScope(){

    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public static MyDealScope getInstance(){
        MyDealScope scope = map.get();
        if(scope==null){
            scope=new MyDealScope();
            map.set(scope);
        }
        return scope;
    }
}