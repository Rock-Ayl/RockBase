package org.rock.main.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.rock.main.constant.JsonConst;
import org.rock.main.controller.FileController;
import org.rock.main.elasticsearch.ElasticSearchTemplate;
import org.rock.main.serivce.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 用户业务实现层
 */
@Service
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private ElasticSearchTemplate elasticSearchTemplate;

    //es文件索引
    private final static String FileIndex = "file";

    @Override
    public String listFile() {
        //初始化
        JSONObject result = new JSONObject();
        //创建查询函数构造对象
        SearchSourceBuilder builder = new SearchSourceBuilder();
        //最外层是布尔查询
        MatchAllQueryBuilder match = QueryBuilders.matchAllQuery();
        builder.query(match);
        //构造请求发起对象,这里直接配置索引名即可
        SearchRequest searchRequest = new SearchRequest(FileIndex);
        //把查询函数构造对象注入查询请求中
        searchRequest.source(builder);
        try {
            //创建响应对象
            SearchResponse searchResponse = elasticSearchTemplate.client().search(searchRequest, RequestOptions.DEFAULT);
            //获取响应中的列表数据
            SearchHits searchHits = searchResponse.getHits();
            //初始化结果集
            JSONArray items = new JSONArray();
            //获取响应中的列表数据总数
            long queryCount = searchHits.getTotalHits().value;
            //循环参数
            for (SearchHit hit : searchHits.getHits()) {
                //获取该参数
                String value = hit.getSourceAsString();
                //解析成Json
                JSONObject valueJson = JSON.parseObject(value);
                //组装进items
                items.add(valueJson);
            }
            //items组装至result
            result.put(JsonConst.Items, items);
            //组装总数
            result.put(JsonConst.Total, queryCount);
        } catch (IOException e) {
            logger.error("es search fail:{}", e.getMessage());
        }
        return result.toJSONString();
    }

}
