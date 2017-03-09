package thread;

/**
 * @Title Created by xzm
 * @date 2017/3/7.
 */
public class BusinessDemo {

    private boolean lock=true;

    public synchronized void subBusiness(int i){
        if(!lock){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 0; j < 30; j++) {
            System.out.println("这是子线程第"+i+"轮第"+j+"次执行业务");
        }
        lock=false;
        this.notify();
    }

    public synchronized void mainBusiness(int i){
        if(lock){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 0; j < 40; j++) {
            System.out.println("这是主线程第"+i+"轮第"+j+"次执行业务");
        }
        lock=true;
        this.notify();
    }

    public static void main(String[] args) {
        final BusinessDemo businessDemo = new BusinessDemo();
            new Thread(){
                @Override
                public void run() {
                    for (int i = 1; i <= 5; i++) {
                        businessDemo.subBusiness(i);
                        System.out.println("=====================================");
                    }
                }
            }.start();

        for (int i = 1; i <= 5; i++) {
            businessDemo.mainBusiness(i);
            System.out.println("----------------------------------------------");
        }
    }
}
