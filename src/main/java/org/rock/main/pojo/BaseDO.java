package org.rock.main.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 所有DB的基底
 *
 * @Author ayl
 * @Date 2022-03-09
 */
@Getter
@Setter
@ToString
public class BaseDO {

    //唯一id
    private String id;
    //创建时间
    private Date createDate;
    //最后修改时间
    private Date updateDate;
    //创建用户id
    private String createUserId;
    //最后修改用户id
    private String updateUserId;
    //状态删除字段
    private boolean del;
    //版本号
    private Long ver;

}
