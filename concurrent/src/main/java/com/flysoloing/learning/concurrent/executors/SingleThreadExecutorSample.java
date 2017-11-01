package com.flysoloing.learning.concurrent.executors;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Executors.newSingleThreadExecutor示例<p>
 *
 * User: laitao<br>
 * Date:  2015/11/18 0:06
 */
public class SingleThreadExecutorSample {

    public static void main(String[] args) {
        //
        final ExecutorService executor = Executors.newSingleThreadExecutor();
//        final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newSingleThreadExecutor();

        //Timer守护线程执行计时，每隔1秒输出一次当前时间和线程池监控信息
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("daemon thread current time: " + System.currentTimeMillis());
//                System.out.println(ThreadPoolMonitor.monitorInfo((ThreadPoolExecutor) executor));
            }
        }, 0l, 1000l);

        //循环调用50次，每次执行线程睡眠0.5秒，那么每隔1秒，执行完成的任务数 = 2 * 核心线程数
        for (int i = 0; i < 50; i++) {
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(500l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
