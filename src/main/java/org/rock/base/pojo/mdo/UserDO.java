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

    String name;
    String pwd;
    String email;
    String phone;
    String remark;

}
