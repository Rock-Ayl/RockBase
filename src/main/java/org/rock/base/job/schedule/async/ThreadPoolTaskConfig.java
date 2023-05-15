package org.rock.base.job.schedule.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;


/**
 * 定时任务的线程池配置,所有异步的定时任务走这个bean
 * 默认情况下,在创建了线程池后,线程池中的线程数为0,当有任务来之后,就会创建一个线程去执行任务,
 * 当线程池中的线程数目达到corePoolSize后,就会把到达的任务放到缓存队列当中；
 * 当队列满了,就继续创建线程,当线程数量大于等于maxPoolSize后,开始使用拒绝策略拒绝
 *
 * @Author ayl
 * @Date 2022-03-14
 */
//开启异步定时任务,并初始化异步线程池配置
@EnableAsync
@Configuration
public class ThreadPoolTaskConfig {

    //本配置的bean名名称
    public static final String SYNC_TASK_POOL_EXECUTOR = "syncTaskPoolExecutor";

    //线程池名前缀
    private static final String THREAD_NAME_PREFIX = "Async-Job-";
    //核心线程数（默认线程数）
    private static final int CORE_POOL_SIZE = 20;
    //最大线程数
    private static final int MAX_POOL_SIZE = 100;
    //允许线程空闲时间（单位：默认为秒）
    private static final int KEEP_ALIVE_TIME = 10;
    //缓冲队列大小
    private static final int QUEUE_CAPACITY = 200;

    //bean的名称,默认为首字母小写的方法名
    @Bean(SYNC_TASK_POOL_EXECUTOR)
    public ThreadPoolTaskExecutor syncTaskPoolExecutor() {
        //线程池对象
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //组装配置
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setKeepAliveSeconds(KEEP_ALIVE_TIME);
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        //线程池对拒绝任务的处理策略
        //CallerRunsPolicy：由调用线程（提交任务的线程）处理该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //初始化
        executor.initialize();
        return executor;
    }

}