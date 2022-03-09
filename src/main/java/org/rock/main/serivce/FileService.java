package org.rock.main.serivce;

import com.alibaba.fastjson.JSONArray;
import lombok.Getter;
import lombok.Setter;
import org.rock.main.pojo.es.FileIndex;

import java.util.List;

/**
 * 文件服务
 */
public interface FileService {

    /**
     * 文件搜索
     *
     * @return
     */
    FileIndexSearchResult search();

    @Setter
    @Getter
    class FileIndexSearchResult {
        //数据结果
        private List<FileIndex> fileList;
        //聚合结果
        private JSONArray aggregations;
        //总数
        private long total;
    }

}
