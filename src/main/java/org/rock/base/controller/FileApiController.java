package org.rock.base.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.rock.base.auth.LoginAuth;
import org.rock.base.common.JSONResponse;
import org.rock.base.constant.HttpConst;
import org.rock.base.db.elasticsearch.BaseElasticSearchService;
import org.rock.base.pojo.index.FileIndex;
import org.rock.base.serivce.test.TestElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件控制层
 *
 * @Author ayl
 * @Date 2022-03-09
 */
@Api(tags = "文件模块")
@RestController
@RequestMapping(value = "/file")
public class FileApiController {

    @Autowired
    private TestElasticSearchService testElasticSearchService;

    @LoginAuth
    @ApiOperation(value = "上传文件")
    @PostMapping(value = "/uploadFile", produces = HttpConst.RESPONSE_HEADERS_CONTENT_TYPE_APPLICATION_JSON)
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        //todo
        return JSONResponse.success().toString();
    }

    @ApiOperation(value = "文件搜索")
    @GetMapping(value = "/search", produces = HttpConst.RESPONSE_HEADERS_CONTENT_TYPE_APPLICATION_JSON)
    public String search() {
        //布尔查询
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        //聚合搜索
        AbstractAggregationBuilder abstractAggregationBuilder = AggregationBuilders
                .terms("md5Count").field("md5.keyword");
        //查询
        BaseElasticSearchService.RollPageResult<FileIndex> rollPageResult = testElasticSearchService.rollPage(FileIndex.class, query, abstractAggregationBuilder, null, null);
        //返回
        return JSONResponse.success().putResult(rollPageResult).toString();
    }

}
