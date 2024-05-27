package com.test.automation.services.testNg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class TestNGThreadConfig {

    private final Logger log = LoggerFactory.getLogger(TestNGThreadConfig.class.getName());

    @Value("${async.executor.to.run.test.core.pool.size}")
    Integer corePoolSize;

    @Value("${async.executor.to.run.test.max.pool.size}")
    Integer maxPoolSize;

    @Value("${async.executor.to.run.test.queue.capacity}")
    Integer queueCapacity;

    @Value("${async.executor.to.run.test.thread.name.prefix}")
    String threadNamePrefix;

    @Bean("AsyncExecutorToRunTest")
    public Executor getAsyncExecutorToRunTest() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(this.corePoolSize);
        executor.setMaxPoolSize(this.maxPoolSize);
        executor.setQueueCapacity(this.queueCapacity);
        executor.setThreadNamePrefix(this.threadNamePrefix);
        executor.setRejectedExecutionHandler((r, executor1) -> log.warn("Task rejected, thread pool is full and queue is also full"));
        executor.initialize();
        return executor;
    }
}
