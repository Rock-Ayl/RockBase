package org.rock.base.impl.test;

import org.rock.base.db.mysql.BaseMysqlServiceImpl;
import org.rock.base.pojo.mdo.UserDO;
import org.rock.base.serivce.test.TestMySqlService;
import org.springframework.stereotype.Service;

/**
 * 测试mysql实现层
 */
@Service
public class TestMysqlServiceImpl extends BaseMysqlServiceImpl<UserDO> implements TestMySqlService {

}
