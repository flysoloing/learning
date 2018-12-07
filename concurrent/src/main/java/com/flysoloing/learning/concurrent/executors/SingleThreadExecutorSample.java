package com.flysoloing.learning.concurrent.executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * Executors.newSingleThreadExecutor示例<p>
 *
 * User: laitao<br>
 * Date:  2015/11/18 0:06
 */
public class SingleThreadExecutorSample {

    private static final Logger logger = LoggerFactory.getLogger(SingleThreadExecutorSample.class);

    public static void main(String[] args) {
        //
        final ExecutorService executor = Executors.newSingleThreadExecutor();
//        final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newSingleThreadExecutor();

        //Timer守护线程执行计时，每隔1秒输出一次当前时间和线程池监控信息
//        Timer timer = new Timer(true);
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("daemon thread current time: " + System.currentTimeMillis());
//                System.out.println(ThreadPoolMonitor.monitorInfo((ThreadPoolExecutor) executor));
//            }
//        }, 0L, 1000L);

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleWithFixedDelay(() -> {
            logger.info("daemon thread current time: " + System.currentTimeMillis());
            logger.info(ThreadPoolMonitor.monitorInfo((ThreadPoolExecutor) executor));
        }, 0L, 1000L, TimeUnit.MILLISECONDS);

        //循环调用50次，每次执行线程睡眠0.5秒，那么每隔1秒，执行完成的任务数 = 2 * 核心线程数
        for (int i = 0; i < 50; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    logger.error("Occurred exception, ", e);
                }
            });
        }
    }
}
