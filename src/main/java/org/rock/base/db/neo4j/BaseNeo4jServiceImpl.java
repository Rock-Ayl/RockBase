package org.rock.base.db.neo4j;

import org.rock.base.pojo.base.BaseNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseNeo4jServiceImpl<T extends BaseNode> implements BaseNeo4jService<T> {

    @Autowired
    private BasNeo4TemplateMapper<T> basNeo4TemplateMapper;

    public T createNode(T node) {
        //创建前初始化
        BaseNode.createBuild(node);
        //实现
        basNeo4TemplateMapper.save(node);
        //插入
        return node;
    }

    @Override
    public List<T> createNode(List<T> nodeList) {
        //循环
        for (T node : nodeList) {
            //创建前初始化
            BaseNode.createBuild(node);
        }
        //实现
        basNeo4TemplateMapper.saveAll(nodeList);
        //返回
        return nodeList;
    }

}

