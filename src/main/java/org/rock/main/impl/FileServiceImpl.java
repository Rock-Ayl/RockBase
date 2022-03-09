package org.rock.main.impl;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.rock.main.controller.FileController;
import org.rock.main.elasticsearch.ElasticSearchTemplate;
import org.rock.main.pojo.es.FileIndex;
import org.rock.main.serivce.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户业务实现层
 */
@Service
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private ElasticSearchTemplate elasticSearchTemplate;

    //es文件索引
    private final static String FILE_INDEX = "file";

    @Override
    public FileIndexSearchResult search() {
        //初始化
        FileIndexSearchResult result = new FileIndexSearchResult();
        //创建查询函数构造对象
        SearchSourceBuilder builder = new SearchSourceBuilder();
        //最外层是布尔查询
        MatchAllQueryBuilder match = QueryBuilders.matchAllQuery();
        builder.query(match);
        //构造请求发起对象,这里直接配置索引名即可
        SearchRequest searchRequest = new SearchRequest(FILE_INDEX);
        //把查询函数构造对象注入查询请求中
        searchRequest.source(builder);
        try {
            //查询
            SearchResponse searchResponse = elasticSearchTemplate.client().search(searchRequest, RequestOptions.DEFAULT);
            //获取响应数据
            SearchHits searchHits = searchResponse.getHits();
            //初始化文件列表
            List<FileIndex> fileList = new ArrayList<>();
            //循环参数
            for (SearchHit hit : searchHits.getHits()) {
                //解析为实体
                fileList.add(JSON.parseObject(hit.getSourceAsString(), FileIndex.class));
            }
            //组装结果和总数
            result.setFileList(fileList);
            //组装总数
            result.setTotal(searchHits.getTotalHits().value);
        } catch (IOException e) {
            logger.error("es search fail:{}", e.getMessage());
        }
        //返回结果
        return result;
    }

}
