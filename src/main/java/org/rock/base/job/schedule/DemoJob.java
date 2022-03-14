package org.rock.base.job.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
     * 每3秒执行一次
     */
    @Scheduled(cron = "0/3 * *  * * ?")
    private void demo() {
        logger.info("当前时间:[{}]", System.currentTimeMillis());
    }

    /**
     * 每5秒执行一次
     */
    @Scheduled(cron = "0/5 * *  * * ?")
    private void demo2() {
        logger.info("当前随机数:[{}]", new Random().nextInt(100));
    }

}