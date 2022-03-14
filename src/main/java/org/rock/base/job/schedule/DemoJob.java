package org.rock.base.job.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 定时任务模板
 *
 * @Author ayl
 * @Date 2022-03-09
 */
@Component
public class DemoJob {

    private static final Logger logger = LoggerFactory.getLogger(DemoJob.class);

    /**
     * 每2秒执行一次,同步任务(所有同步任务共用一个线程)
     */
    @Scheduled(cron = "0/2 * *  * * ?")
    public void demo() throws InterruptedException {
        logger.info("当前时间1:[{}]", System.currentTimeMillis());
        Thread.sleep(5000);
    }

    /**
     * 每2秒执行一次,同步任务(所有同步任务共用一个线程)
     */
    @Scheduled(cron = "0/2 * *  * * ?")
    public void demo2() throws InterruptedException {
        logger.info("当前时间2:[{}]", System.currentTimeMillis());
        Thread.sleep(5000);
    }

    /**
     * 每2秒执行一次,异步任务(异步任务单独使用一个线程),指定的是线程池bean名称
     */
    @Async(ThreadPoolTaskConfig.SYNC_TASK_POOL_EXECUTOR)
    @Scheduled(cron = "0/2 * *  * * ?")
    public void demo3() {
        logger.info("当前随机数:[{}]", new Random().nextInt(100));
    }

}