package org.rock.base.pojo.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;

import java.io.Serializable;

/**
 * Neo4j节点 实体的基底
 *
 * @Author ayl
 * @Date 2022-03-09
 */
@Getter
@Setter
public class BaseNode extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ApiModelProperty("唯一id")
    private String id;

}
