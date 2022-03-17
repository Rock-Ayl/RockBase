package org.rock.base.pojo.base;

import lombok.Getter;
import lombok.Setter;
import org.rock.base.util.IdUtils;
import org.springframework.data.neo4j.core.schema.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Neo4j节点 实体的基底
 *
 * @Author ayl
 * @Date 2022-03-09
 */
@Getter
@Setter
public class BaseNode implements Serializable {

    private static final long serialVersionUID = 1L;

    //唯一id
    @Id
    private String id;
    //创建时间
    private Date createDate;
    //最后修改时间
    private Date updateDate;
    //状态删除字段,基本类型,进库默认false
    private boolean del;
    //版本号
    private Long ver;

    /**
     * 给新增数据的基底赋予基础数据
     *
     * @param baseNode
     */
    public static void createBuild(BaseNode baseNode) {
        baseNode.setVer(System.currentTimeMillis());
        baseNode.setCreateDate(new Date());
        baseNode.setUpdateDate(new Date());
        baseNode.setId(IdUtils.genGUID());
    }

    /**
     * 给修改数据的基底赋予基础数据
     *
     * @param baseNode
     */
    public static void updateBuild(BaseNode baseNode) {
        baseNode.setVer(System.currentTimeMillis());
        baseNode.setUpdateDate(new Date());
    }

}
