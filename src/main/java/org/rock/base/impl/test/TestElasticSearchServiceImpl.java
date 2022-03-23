package org.rock.base.impl.test;

import org.rock.base.db.elasticsearch.BaseElasticSearchServiceImpl;
import org.rock.base.pojo.index.FileIndex;
import org.rock.base.serivce.test.TestElasticSearchService;
import org.springframework.stereotype.Service;

/**
 * elastic search 测试实现
 */
@Service
public class TestElasticSearchServiceImpl extends BaseElasticSearchServiceImpl<FileIndex> implements TestElasticSearchService {


}
