package org.rock.main.serivce;

import com.alibaba.fastjson.JSONArray;
import lombok.Getter;
import lombok.Setter;
import org.rock.main.db.elasticsearch.BaseElasticSearchService;
import org.rock.main.pojo.index.FileIndex;

import java.util.List;

/**
 * elastic search 测试服务
 */
public interface TestElasticSearchService extends BaseElasticSearchService {

    /**
     * 测试 文件搜索
     *
     * @return
     */
    FileIndexSearchResult fileSearch();

    @Setter
    @Getter
    class FileIndexSearchResult {
        //数据结果
        private List<FileIndex> fileList;
        //聚合结果
        private JSONArray aggregations;
        //总数
        private Long total;
    }

}
