package com.rock.base.event;

import com.rock.base.job.event.publish.TestEventPublish;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试 Spring Event
 *
 * @Author ayl
 * @Date 2024-12-31
 */
@SpringBootTest
public class TestEvent {

    @Autowired
    private TestEventPublish testEventPublish;

    /**
     * 实现发布事件、执行事件
     */
    @Test
    void execute() {
        testEventPublish.publish("Rock");
    }

}
