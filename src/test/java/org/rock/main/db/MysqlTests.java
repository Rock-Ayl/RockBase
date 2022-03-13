package org.rock.main.db;

import org.junit.jupiter.api.Test;
import org.rock.main.pojo.mdo.UserDO;
import org.rock.main.serivce.TestMySqlService;
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
        testMySqlService.insert(list);
        List<UserDO> userList = testMySqlService.selectList(null);
        System.out.println(12);
    }

}
