package com.rock.base.job.event.listener;

import com.rock.base.job.event.event.TestEvent;
import com.rock.base.job.schedule.async.ThreadPoolTaskConfig;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 测试 监听事件
 *
 * @Author ayl
 * @Date 2024-12-31
 */
@Component
public class TestEventListener {

    //加异步注解为异步、不加为同步
    @Async(ThreadPoolTaskConfig.SYNC_TASK_POOL_EXECUTOR)
    //监听注解
    @EventListener
    public void handleTestEvent(TestEvent event) {
        //获取值
        String value = event.getValue();
        //发送邮件的逻辑
        System.out.println(String.format("[测试][Spring Event][监听]实现,线程=[%s],value=%s",
                Thread.currentThread().getName(),
                value)
        );
    }

}
