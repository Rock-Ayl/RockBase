package org.rock.base.db.neo4j;

import org.rock.base.pojo.base.BaseNode;

/**
 * neo4j 服务基底
 *
 * @Author ayl
 * @Date 2022-03-09
 */
public interface BaseNeo4jService<T extends BaseNode> {

    /**
     * 插入节点
     *
     * @param node
     * @return
     */
    T create(T node);

}
