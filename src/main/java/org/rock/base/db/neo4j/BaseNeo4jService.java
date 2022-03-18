package org.rock.base.db.neo4j;

import org.rock.base.pojo.base.BaseNode;

import java.util.List;

/**
 * neo4j 服务基底
 *
 * @Author ayl
 * @Date 2022-03-18
 */
public interface BaseNeo4jService<T extends BaseNode> {

    /**
     * 创建节点
     *
     * @param node 节点实体
     * @return
     */
    T createNode(T node);

    /**
     * 批量创建节点
     *
     * @param nodeList 节点实体列表
     * @return
     */
    List<T> createNode(List<T> nodeList);

}
