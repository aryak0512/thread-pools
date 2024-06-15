package com.aryak;

import java.util.TimerTask;

/**
 * simulate user requests
 */
public class RequestTask extends TimerTask {

    int requests = 0;

    @Override
    public void run() {
        requests ++;
        ThreadPoolConfig.getInstance().getExecutor().execute(new MyTask(requests));
        //Future<Integer> future = ThreadPoolConfig.getInstance().getExecutor().submit(new AdditionTask());
    }
}
