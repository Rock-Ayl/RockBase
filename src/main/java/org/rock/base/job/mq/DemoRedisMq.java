package org.rock.base.job.mq;

import org.rock.base.constant.RedisKey;
import org.rock.base.db.redis.BaseRedisService;
import org.rock.base.job.schedule.ThreadPoolTaskConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 简单的mq实现demo
 * 借用 Redis list,实现MQ的逻辑, 可以理解为 定时器+redis_list
 *
 * @Author ayl
 * @Date 2022-03-16
 */
@Component
public class DemoRedisMq {

    private static final Logger logger = LoggerFactory.getLogger(DemoRedisMq.class);

    @Autowired
    private BaseRedisService baseRedisService;

    /**
     * 每3秒执行一次,异步定时任务,固定消费队列中最左边的一条记录
     */
    @Async(ThreadPoolTaskConfig.SYNC_TASK_POOL_EXECUTOR)
    @Scheduled(cron = "0/1 * *  * * ?")
    public void consumer() {
        //todo
    }

    /**
     * 每5秒执行一次,异步定时任务,固定给队列最右边增加一个可消费记录
     */
    @Async(ThreadPoolTaskConfig.SYNC_TASK_POOL_EXECUTOR)
    @Scheduled(cron = "0/1 * *  * * ?")
    public void sender() {
        //生成单个消费内容
        String value = "消费内容:" + System.currentTimeMillis();
        //发送
        baseRedisService.setListRight(RedisKey.DEMO_MQ_ONE, value);
        logger.info("产生一个可消费内容[{}]", value);
    }

}