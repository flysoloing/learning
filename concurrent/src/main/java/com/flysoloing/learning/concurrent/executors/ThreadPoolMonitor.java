package com.flysoloing.learning.concurrent.executors;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池监控器<p>
 *
 * User: laitao<br>
 * Date:  2015/11/18 0:11
 */
public class ThreadPoolMonitor {

    /**
     * 输出线程池监控信息
     * @param threadPoolExecutor
     * @return
     */
    public static String monitorInfo(final ThreadPoolExecutor threadPoolExecutor) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CurrentPoolSize: ").append(threadPoolExecutor.getPoolSize());
        stringBuilder.append(", CorePoolSize: ").append(threadPoolExecutor.getCorePoolSize());
        stringBuilder.append(", MaximumPoolSize: ").append(threadPoolExecutor.getMaximumPoolSize());
        stringBuilder.append(", ActiveTaskCount: ").append(threadPoolExecutor.getActiveCount());
        stringBuilder.append(", QueuedTaskCount: ").append(threadPoolExecutor.getQueue().size());
        stringBuilder.append(", CompletedTaskCount: ").append(threadPoolExecutor.getCompletedTaskCount());
        stringBuilder.append(", TotalTaskCount: ").append(threadPoolExecutor.getTaskCount());
        return stringBuilder.toString();
    }
}
