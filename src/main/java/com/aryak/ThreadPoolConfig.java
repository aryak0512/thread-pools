package com.aryak;

import java.util.concurrent.*;

/**
 * this class is a singleton
 */
public class ThreadPoolConfig {

    private final ThreadPoolExecutor executor;
    private static volatile ThreadPoolConfig instance = null;

    /**
     * defining thread pool props
     */
    private ThreadPoolConfig() {

        if ( instance != null ) {
            throw new RuntimeException("direct instantiation not allowed!");
        }

        int corePoolSize = 10;
        int maximumPoolSize = 10;
        int keepAliveTime = 30;
        TimeUnit tu = TimeUnit.SECONDS;
        int queueCapacity = 100;
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(queueCapacity);
        RejectedExecutionHandler rejectedExecutionHandler = new RejectedTaskHandler();
        this.executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, tu, queue, rejectedExecutionHandler);
    }

    /**
     * method used to access the instance of this singleton
     * @return ThreadPoolConfig
     */
    public static ThreadPoolConfig getInstance() {

        if ( instance == null ) {
            synchronized (ThreadPoolConfig.class) {
                if ( instance == null ) {
                    instance = new ThreadPoolConfig();
                }
            }
        }
        return instance;
    }

    public ThreadPoolExecutor getExecutor() {
        return executor;
    }

}
