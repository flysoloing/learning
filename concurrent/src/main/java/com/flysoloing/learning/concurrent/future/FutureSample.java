package com.flysoloing.learning.concurrent.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * future示例<p>
 *
 * User: laitao<br>
 * Date:  2015/11/15 15:44
 */
public class FutureSample {

    private static final Logger logger = LoggerFactory.getLogger(FutureSample.class);

    public static void main(String[] args) {
        //1、新建一个FutureTask任务
//        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
//            public String call() throws Exception {
//                Thread.sleep(4000l);
//                return "TYPE 1 SUCCESS";
//            }
//        });
        //1.1、新建线程执行FutureTask
//        Thread thread = new Thread(future);
//        thread.start();
        //1.2、线程池执行FutureTask
//        Executors.newWorkStealingPool().execute(future);

        //2、使用线程池提交一个Callable线程
//        final Future<String> future = Executors.newCachedThreadPool().submit(new Callable<String>() {
//        final Future<String> future = Executors.newSingleThreadExecutor().submit(new Callable<String>() {
        final Future<String> future = Executors.newFixedThreadPool(1).submit(new Callable<String>() {
//        final Future<String> future = Executors.newScheduledThreadPool(1).submit(new Callable<String>() {
//        final Future<String> future = Executors.newWorkStealingPool().submit(new Callable<String>() {
            public String call() throws Exception {
                Thread.sleep(4000L);
                return "TYPE 2 SUCCESS";
            }
        });

        //Timer守护线程执行计时，每隔1秒输出一次当前时间
//        Timer timer = new Timer(true);
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("daemon thread current time: " + System.currentTimeMillis());
//            }
//        }, 0L, 1000L);

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleWithFixedDelay(() -> {
            logger.info("daemon thread current time: " + System.currentTimeMillis());
        }, 0L, 1000L, TimeUnit.MILLISECONDS);

        String result = "NULL";

        try {
            long begin = System.currentTimeMillis();
            logger.info("1、thread blocking, current time: " + begin);
            result = future.get(2000L, TimeUnit.MILLISECONDS);
            long end = System.currentTimeMillis();
            logger.info("1、get result: " + result + ", current time: " + end + ", consumed time: " + (end - begin));
        } catch (Exception e) {
            logger.info("1、get result: " + result + ", current time: " + System.currentTimeMillis());
            logger.error("Occurred exception, ", e);
        }

        try {
            long begin = System.currentTimeMillis();
            logger.info("2、thread blocking, current time: " + begin);
            result = future.get(4000L, TimeUnit.MILLISECONDS);
            long end = System.currentTimeMillis();
            logger.info("2、get result: " + result + ", current time: " + end + ", consumed time: " + (end - begin));
        } catch (Exception e) {
            logger.info("2、get result: " + result + ", current time: " + System.currentTimeMillis());
            logger.error("Occurred exception, ", e);
        }

        try {
            long begin = System.currentTimeMillis();
            logger.info("3、thread blocking, current time: " + begin);
            result = future.get(8000L, TimeUnit.MILLISECONDS);
            long end = System.currentTimeMillis();
            logger.info("3、get result: " + result + ", current time: " + end + ", consumed time: " + (end - begin));
        } catch (Exception e) {
            logger.info("3、get result: " + result + ", current time: " + System.currentTimeMillis());
            logger.error("Occurred exception, ", e);
        }

        try {
            long begin = System.currentTimeMillis();
            logger.info("4、thread blocking, current time: " + begin);
            result = future.get();
            long end = System.currentTimeMillis();
            logger.info("4、get result: " + result + ", current time: " + end + ", consumed time: " + (end - begin));
        } catch (Exception e) {
            logger.info("4、get result: " + result + ", current time: " + System.currentTimeMillis());
            logger.error("Occurred exception, ", e);
        }
    }

}
