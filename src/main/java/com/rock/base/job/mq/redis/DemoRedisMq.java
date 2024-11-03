package com.rock.base.job.mq.redis;

import com.rock.base.constant.RedisKey;
import com.rock.base.db.redis.BaseRedisService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 简单的mq实现demo
 * 借用 Redis list,实现MQ的逻辑,可以理解为[定时器+redis_list]
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
     * 同步操作,每1秒执行一次,固定消费队列中最左边的一条记录
     */
    @Scheduled(cron = "0/1 * *  * * ?")
    public void consumer() {
        //如果不存在可消费队列
        if (baseRedisService.containsKey(RedisKey.DEMO_MQ_ONE) == false) {
            //默认日志
            logger.info("无可消费内容,跳过!");
            //过
            return;
        }
        //从队列左端获取一个消息
        String value = baseRedisService.listLeftPop(RedisKey.DEMO_MQ_ONE);
        //如果存在可消费的内容
        if (StringUtils.isNotBlank(value)) {
            //日志
            logger.info("消费了一个内容[{}]", value);
            //过
            return;
        }
    }

    /**
     * 同步操作,每3秒执行一次,固定给队列最右边增加一个可消费记录
     */
    @Scheduled(cron = "0/3 * *  * * ?")
    public void sender() {
        //生成单个消费内容
        String value = "消费内容:" + System.currentTimeMillis();
        //推送至队列右端
        baseRedisService.setListRight(RedisKey.DEMO_MQ_ONE, value);
        //日志
        logger.info("产生一个可消费内容[{}]", value);
    }

}