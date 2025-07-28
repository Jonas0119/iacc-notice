// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.config;

import org.springframework.context.annotation.Bean;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.ExecutorService;
import org.springframework.context.annotation.Configuration;

@Configuration("tklThreadPoolConfig")
public class ThreadPoolConfig
{
    @Bean({ "threadPoolInstance" })
    public ExecutorService createThreadPoolInstance() {
        final ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-pool-%d").build();
        final ExecutorService threadPool = new ThreadPoolExecutor(10, 16, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100), threadFactory, new ThreadPoolExecutor.AbortPolicy());
        return threadPool;
    }
}
