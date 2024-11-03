package com.rock.base.impl.test;

import com.rock.base.db.mysql.BaseMysqlServiceImpl;
import com.rock.base.pojo.mdo.UserDO;
import com.rock.base.serivce.test.TestMySqlService;
import org.springframework.stereotype.Service;

/**
 * 测试mysql实现层
 */
@Service
public class TestMysqlServiceImpl extends BaseMysqlServiceImpl<UserDO> implements TestMySqlService {

}
