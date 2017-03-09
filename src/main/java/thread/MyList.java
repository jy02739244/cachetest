package thread;

import java.lang.reflect.Field;

/**
 * @Title Created by xzm
 * @date 2017/3/8.
 */
public class MyList {

    public  void test(){
        synchronized(MyList.class){

            System.out.println("0");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1");
        }
    }

    public static void swap(Integer a,Integer b) throws Exception{
            Field f = Integer.class.getDeclaredField("value");
            f.setAccessible(true);
            f.set(a,b.intValue());
    }

    public static void main(String[] args) throws Exception {
        Integer a=1;
        Integer b=5;
        swap(a,b);
        System.out.println(a);
        System.out.println(b);
    }
}
