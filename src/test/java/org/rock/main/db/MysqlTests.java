package org.rock.main.db;

import org.junit.jupiter.api.Test;
import org.rock.main.pojo.mdo.UserDO;
import org.rock.main.serivce.TestMySqlService;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MysqlTests {

    @Resource
    private TestMySqlService testMySqlService;

    @Test
    void test() {
        List<UserDO> userList = testMySqlService.selectList(null);
        System.out.println(12);
    }

}
