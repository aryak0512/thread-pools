package com.aryak;

import java.time.Duration;
import java.util.Date;

public class MyTask implements Runnable {

    private final int taskId;

    public MyTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {

        try {

            System.out.println("[" + new Date() + "] Task " + taskId + " started on thread : " + Thread.currentThread().getName());

            // simulate DB call
            Thread.sleep(Duration.ofSeconds(3));

            System.out.println("[" + new Date() + "] Task " + taskId + " finished on thread : " + Thread.currentThread().getName());


        } catch (InterruptedException e) {
            System.out.println("Interrupted task :" + taskId);
        }

    }
}
