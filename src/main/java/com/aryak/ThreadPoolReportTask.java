package com.aryak;

import java.util.Date;
import java.util.TimerTask;

/**
 * @author aryak
 * Daemon task which will print thread pool stats every 2 seconds
 */
public class ThreadPoolReportTask extends TimerTask {

    @Override
    public void run() {

        var executor = ThreadPoolConfig.getInstance().getExecutor();

        // retrieving thread pool stats
        var activeCount = executor.getActiveCount();
        var poolSize = executor.getPoolSize();
        var size = executor.getQueue().size();

        System.out.println("[" + new Date() + "] Active : " + activeCount + " | Queue : " + size + " | Pool size : "+ poolSize);
    }
}
