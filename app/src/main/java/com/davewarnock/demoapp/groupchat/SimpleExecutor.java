package com.davewarnock.demoapp.groupchat;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimpleExecutor {

    static ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    static synchronized void queue(Runnable r) {
        executorService.submit(r);
    }

    static synchronized void queue(Runnable r, long delay) {
        executorService.schedule(r, delay, TimeUnit.MILLISECONDS);
    }
}
