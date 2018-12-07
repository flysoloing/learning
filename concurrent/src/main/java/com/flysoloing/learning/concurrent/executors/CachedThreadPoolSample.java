package com.flysoloing.learning.concurrent.executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * Executors.newCachedThreadPool示例<p>
 *
 * User: laitao<br>
 * Date:  2015/11/18 0:36
 */
public class CachedThreadPoolSample {

    private static final Logger logger = LoggerFactory.getLogger(CachedThreadPoolSample.class);

    public static void main(String[] args) {
        //创建一个线程有效期为60秒的缓存线程池
//        final ExecutorService executorService = Executors.newCachedThreadPool();
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        //Timer守护线程执行计时，每隔5秒输出一次当前时间和线程池监控信息
//        Timer timer = new Timer(true);
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                logger.info("daemon thread current time: " + System.currentTimeMillis());
//                logger.info(ThreadPoolMonitor.monitorInfo(executorService));
//            }
//        }, 0L, 5000L);

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleWithFixedDelay(() -> {
            logger.info("daemon thread current time: " + System.currentTimeMillis());
            logger.info(ThreadPoolMonitor.monitorInfo(executorService));
        }, 0L, 5000L, TimeUnit.MILLISECONDS);

        //循环调用50次，每次执行线程睡眠1秒，那么每隔1秒，执行完成的任务数 = 最大线程数（等于当前任务数）
        loop(executorService, 50, 1000L);

        //当前主线程睡眠30秒
        try {
            Thread.sleep(30000L);
        } catch (InterruptedException e) {
            logger.error("Occurred exception, ", e);
        }

        //循环调用100次，每次执行线程睡眠1秒，那么每隔1秒，执行完成的任务数 = 最大线程数（等于当前任务数）
        loop(executorService, 100, 1000L);

        //由于主线程睡眠30秒后，前一次循环执行50次创建的线程尚未失效，因此后一次循环执行100次，缓存线程池只需额外创建50个新线程
    }

    /**
     * 循环执行
     * @param executor
     * @param times
     * @param millis
     */
    private static void loop(ThreadPoolExecutor executor, final int times, final long millis) {
        for (int i = 0; i < times; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(millis);
                    logger.info("Thread id: " + Thread.currentThread().getId() + ", Thread name: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    logger.error("Occurred exception, ", e);
                }
            });
        }
    }
}
