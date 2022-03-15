package org.rock.base.db.elasticsearch;

import org.rock.base.pojo.base.BaseDO;
import org.rock.base.pojo.base.BaseIndex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * mongo 服务基底实现
 *
 * @Author ayl
 * @Date 2022-03-09
 */
@Service
public class BaseElasticSearchServiceImpl<T extends BaseIndex> implements BaseElasticSearchService<T> {

    private static final Logger LOG = LoggerFactory.getLogger(BaseElasticSearchServiceImpl.class);

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public T create(T index) {
        //创建前初始化
        BaseDO.createBuild(index);
        //插入
        elasticsearchRestTemplate.save(index);
        //返回
        return index;
    }

    @Override
    public Collection<T> create(List<T> indexList) {
        //循环
        for (T index : indexList) {
            //创建前初始化
            BaseDO.createBuild(index);
        }
        //批量插入
        elasticsearchRestTemplate.save(indexList);
        //返回
        return indexList;
    }

    @Override
    public T get(Class<T> clazz, String id) {
        //查询
        return elasticsearchRestTemplate.get(id, clazz);
    }

    @Override
    public List<T> list(Class<T> clazz, List<String> idList) {
        //查询
        return elasticsearchRestTemplate.multiGet(new NativeSearchQueryBuilder().withIds(idList).build(), clazz);
    }

    @Override
    public void delete(String id, Class<T> clazz) {
        //删除
        elasticsearchRestTemplate.delete(id, clazz);
    }

}

