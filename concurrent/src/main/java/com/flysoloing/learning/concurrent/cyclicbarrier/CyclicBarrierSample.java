package com.flysoloing.learning.concurrent.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier示例<br>
 * User: laitao<br>
 * Date: 2015/12/3<br>
 * Time: 1:09<br>
 */
public class CyclicBarrierSample {

    public static void main(String[] args) {
        //创建一个
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
    }
}
