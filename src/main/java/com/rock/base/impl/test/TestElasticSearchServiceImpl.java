package com.rock.base.impl.test;

import com.rock.base.db.elasticsearch.BaseElasticSearchServiceImpl;
import com.rock.base.pojo.index.FileIndex;
import com.rock.base.serivce.test.TestElasticSearchService;
import org.springframework.stereotype.Service;

/**
 * elastic search 测试实现
 */
@Service
public class TestElasticSearchServiceImpl extends BaseElasticSearchServiceImpl<FileIndex> implements TestElasticSearchService {


}
