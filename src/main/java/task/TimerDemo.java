package task;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Title Created by xzm
 * @date 2017/3/8.
 */
public class TimerDemo extends TimerTask{
    @Override
    public void run() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Timer task..."+format.format(this.scheduledExecutionTime()));
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TimerTask timerDemo = new TimerDemo();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerDemo,1000,1000);
    }
}
