package org.rock.base.db;

import org.junit.jupiter.api.Test;
import org.rock.base.pojo.mdo.UserDO;
import org.rock.base.serivce.TestMySqlService;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MysqlTests {

    @Resource
    private TestMySqlService testMySqlService;

    @Test
    void test() {

        List<UserDO> list = new ArrayList<>();
        UserDO one = new UserDO();
        UserDO two = new UserDO();
        list.add(one);
        list.add(two);
        testMySqlService.create(list);
        List<UserDO> userList = testMySqlService.selectList(null);
        System.out.println(12);
    }

}
