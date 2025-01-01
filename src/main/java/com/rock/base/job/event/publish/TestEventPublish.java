package com.rock.base.job.event.publish;

import com.rock.base.job.event.event.TestEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 发布事件
 *
 * @Author ayl
 * @Date 2024-12-31
 */
@Component
public class TestEventPublish {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 发布
     *
     * @param value 测试值
     */
    public void publish(String value) {
        //初始化事件
        TestEvent event = new TestEvent(this, value);
        //发布事件
        applicationEventPublisher.publishEvent(event);
    }

}