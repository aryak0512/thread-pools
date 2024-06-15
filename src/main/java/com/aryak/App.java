package com.aryak;

import java.util.Date;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {

        // start thread pool reporting task in background
        Thread.ofVirtual().start(() -> new Timer().scheduleAtFixedRate(new ThreadPoolReportTask(), 0, 1000));

        // start simulation of 1 user request every 100 ms
        RequestTask requestTask = new RequestTask();
        Thread.ofVirtual().start(() -> new Timer().scheduleAtFixedRate(requestTask, 0, 100));

        Thread t = new Thread(() -> {

            var executor = ThreadPoolConfig.getInstance().getExecutor();
            executor.shutdown(); // Disable new tasks from being submitted
            System.err.println("[" + new Date() + "] Shutdown hook executed.");

            try {
                // Wait a while for existing tasks to terminate
                if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                    executor.shutdownNow(); // Cancel currently executing tasks
                    System.out.println("[" + new Date() + "] Executor shutdown happened.");
                    // Wait a while for tasks to respond to being cancelled
                    if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                        System.err.println("Pool did not terminate");
                    }
                }
            } catch (InterruptedException ie) {
                // (Re-)Cancel if current thread also interrupted
                executor.shutdownNow();
                // Preserve interrupt status
                Thread.currentThread().interrupt();
            }
        });

        Runtime.getRuntime().addShutdownHook(t);
    }
}
