package org.rock.main.impl;

import org.rock.main.db.mysql.BaseMysqlServiceImpl;
import org.rock.main.pojo.mdo.UserDO;
import org.rock.main.serivce.TestMySqlService;
import org.springframework.stereotype.Service;

/**
 * 测试mysql实现层
 */
@Service
public class TestMysqlServiceImpl extends BaseMysqlServiceImpl<UserDO> implements TestMySqlService {

}
