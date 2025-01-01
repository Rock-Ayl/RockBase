package com.rock.base.job.event.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * 测试 事件类
 *
 * @Author ayl
 * @Date 2024-12-31
 */
@Getter
@Setter
public class TestEvent extends ApplicationEvent {

    //测试值
    private String value;

    /**
     * 初始化
     *
     * @param source 父类
     * @param value  测试值
     */
    public TestEvent(Object source, String value) {
        //调用父类
        super(source);
        //初始化字段
        this.value = value;
    }

}