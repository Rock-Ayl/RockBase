package org.rock.base.pojo.mdo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.rock.base.pojo.base.BaseDO;

/**
 * mysql 测试实体
 *
 * @Author ayl
 * @Date 2022-03-13
 */
@Getter
@Setter
@TableName("rock_user")
public class UserDO extends BaseDO {

    //名称
    private String name;
    //密码
    private String pwd;
    //邮箱
    private String email;
    //手机号
    private String phone;
    //备注
    private String remark;

}
