package com.flysoloing.learning.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch示例<br>
 * User: laitao<br>
 * Date: 2015/12/3<br>
 * Time: 1:05<br>
 */
public class CountDownLatchSample {

    public static void main(String[] args) {
        //创建一个
        final CountDownLatch countDownLatch = new CountDownLatch(4);
    }
}
