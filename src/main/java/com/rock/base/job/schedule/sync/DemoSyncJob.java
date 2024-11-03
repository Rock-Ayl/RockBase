package com.rock.base.job.schedule.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 同步定时任务模板
 *
 * @Author ayl
 * @Date 2022-03-09
 */
@Component
public class DemoSyncJob {

    private static final Logger logger = LoggerFactory.getLogger(DemoSyncJob.class);

    /**
     * 每5秒执行一次,同步任务(所有同步任务共用一个线程,如果该任务效率很快建议同步)
     */
    @Scheduled(cron = "0/5 * *  * * ?")
    public void demo() {
        logger.info("五秒一次的输出:[{}]", System.currentTimeMillis());
    }

    /**
     * 每10秒执行一次,同步任务(所有同步任务共用一个线程,如果该任务效率很快建议同步)
     */
    @Scheduled(cron = "0/10 * *  * * ?")
    public void demo2() {
        logger.info("十秒一次的输出:[{}]", System.currentTimeMillis());
    }

}