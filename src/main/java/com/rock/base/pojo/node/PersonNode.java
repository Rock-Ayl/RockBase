package com.rock.base.pojo.node;

import com.rock.base.pojo.base.BaseNode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * neo4j节点 测试类 人
 *
 * @Author ayl
 * @Date 2022-03-17
 */
@Getter
@Setter
@Node("person")
public class PersonNode extends BaseNode {

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("备注")
    private String remark;

}