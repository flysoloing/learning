package com.flysoloing.learning.concurrent.executors;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Executors.newCachedThreadPool示例<p>
 *
 * User: laitao<br>
 * Date:  2015/11/18 0:36
 */
public class CachedThreadPoolSample {

    public static void main(String[] args) {
        //创建一个线程有效期为60秒的缓存线程池
//        final ExecutorService executorService = Executors.newCachedThreadPool();
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        //Timer守护线程执行计时，每隔5秒输出一次当前时间和线程池监控信息
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("daemon thread current time: " + System.currentTimeMillis());
                System.out.println(ThreadPoolMonitor.monitorInfo(executorService));
            }
        }, 0L, 5000L);

        //循环调用50次，每次执行线程睡眠1秒，那么每隔1秒，执行完成的任务数 = 最大线程数（等于当前任务数）
        for (int i = 0; i < 50; i++) {
            executorService.execute(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000L);
                        System.out.println("Thread id: " + Thread.currentThread().getId() + ", Thread name: " + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        //当前主线程睡眠30秒
        try {
            Thread.sleep(30000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //循环调用100次，每次执行线程睡眠1秒，那么每隔1秒，执行完成的任务数 = 最大线程数（等于当前任务数）
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000L);
                        System.out.println("Thread id: " + Thread.currentThread().getId() + ", Thread name: " + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        //由于主线程睡眠30秒后，前一次循环执行50次创建的线程尚未失效，因此后一次循环执行100次，缓存线程池只需额外创建50个新线程
    }
}
