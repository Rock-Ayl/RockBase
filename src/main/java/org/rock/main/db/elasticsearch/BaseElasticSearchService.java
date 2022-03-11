package org.rock.main.db.elasticsearch;

import org.elasticsearch.client.RestHighLevelClient;

/**
 * elastic search 服务基底
 *
 * @Author ayl
 * @Date 2022-3-9
 */
public interface BaseElasticSearchService {

    /**
     * 获取连接
     *
     * @return
     */
    RestHighLevelClient client();

}
