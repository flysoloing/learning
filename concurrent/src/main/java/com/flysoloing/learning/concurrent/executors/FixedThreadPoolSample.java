package com.flysoloing.learning.concurrent.executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * Executors.newFixedThreadPool示例<p>
 *
 * User: laitao<br>
 * Date:  2015/11/17 22:44
 */
public class FixedThreadPoolSample {

    private static final Logger logger = LoggerFactory.getLogger(FixedThreadPoolSample.class);

    public static void main(String[] args) {
        //创建一个固定线程数量的线程池，核心线程数为4，最大线程数为4
//        final ExecutorService executorService = Executors.newFixedThreadPool(4);
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);

        //Timer守护线程执行计时，每隔1秒输出一次当前时间和线程池监控信息
//        Timer timer = new Timer(true);
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("daemon thread current time: " + System.currentTimeMillis());
//                System.out.println(ThreadPoolMonitor.monitorInfo(executorService));
//            }
//        }, 0L, 1000L);

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleWithFixedDelay(() -> {
            logger.info("daemon thread current time: " + System.currentTimeMillis());
            logger.info(ThreadPoolMonitor.monitorInfo(executorService));
        }, 0L, 1000L, TimeUnit.MILLISECONDS);

        //循环调用50次，每次执行线程睡眠0.5秒，那么每隔1秒，执行完成的任务数 = 2 * 核心线程数
        for (int i = 0; i < 50; i++) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    logger.error("Occurred exception, ", e);
                }
            });
        }
    }

}
