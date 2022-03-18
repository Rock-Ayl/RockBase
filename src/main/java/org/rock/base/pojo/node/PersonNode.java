package org.rock.base.pojo.node;

import lombok.Getter;
import lombok.Setter;
import org.rock.base.pojo.base.BaseNode;
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

    //姓名
    private String name;

    //性别
    private String sex;

    //备注
    private String remark;

}