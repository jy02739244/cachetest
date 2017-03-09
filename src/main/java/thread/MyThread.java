package thread;

/**
 * @Title Created by xzm
 * @date 2017/3/8.
 */
public class MyThread implements Runnable{
    @Override
    public void run() {
        MyList myList = new MyList();
        myList.test();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new MyThread()).start();
        }
    }
}
