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
import org.rock.main.db.elasticsearch.BaseElasticSearchTemplate;
import org.rock.main.pojo.index.FileIndex;
import org.rock.main.serivce.TestElasticSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestElasticSearchServiceImpl extends BaseElasticSearchTemplate implements TestElasticSearchService {

    private static final Logger logger = LoggerFactory.getLogger(TestElasticSearchServiceImpl.class);

    //es 测试 文件索引
    private final static String FILE_INDEX = "file";

    @Override
    public FileIndexSearchResult fileSearch() {
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
            SearchResponse searchResponse = client().search(searchRequest, RequestOptions.DEFAULT);
            //获取响应数据
            SearchHits searchHits = searchResponse.getHits();
            //组装总数
            result.setTotal(searchHits.getTotalHits().value);
            //解析文件实体列表
            List<FileIndex> fileList = Arrays.stream(searchHits.getHits())
                    .map(SearchHit::getSourceAsString)
                    .map(p -> JSON.parseObject(p, FileIndex.class))
                    .collect(Collectors.toList());
            //组装结果和总数
            result.setFileList(fileList);
        } catch (IOException e) {
            logger.error("es search fail:{}", e.getMessage());
        }
        //返回结果
        return result;
    }

}
