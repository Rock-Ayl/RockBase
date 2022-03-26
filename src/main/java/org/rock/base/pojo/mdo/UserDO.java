package org.rock.base.pojo.mdo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.rock.base.pojo.base.BaseDO;

/**
 * 用户实体
 *
 * @Author ayl
 * @Date 2022-03-13
 */
@Getter
@Setter
@ApiModel("用户实体")
@TableName("rock_user")
public class UserDO extends BaseDO {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("密码")
    private String pwd;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("用户备注")
    private String remark;

}
