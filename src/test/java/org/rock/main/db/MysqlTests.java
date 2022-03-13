package org.rock.main.db;

import org.junit.jupiter.api.Test;
import org.rock.main.mapper.UserMapper;
import org.rock.main.pojo.mdo.UserDO;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MysqlTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void test() {

        UserDO user = userMapper.selectById("1");
        System.out.println(12);

        List<UserDO> userList = userMapper.selectList(null);
        System.out.println(12);
    }

}
