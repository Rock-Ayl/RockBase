package org.rock.base.db;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.rock.base.pojo.mdo.UserDO;
import org.rock.base.serivce.TestMySqlService;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class MysqlTests {

    @Resource
    private TestMySqlService testMySqlService;

    @Test
    void create() {

        List<UserDO> list = new ArrayList<>();
        UserDO one = new UserDO();
        UserDO two = new UserDO();
        list.add(one);
        list.add(two);
        testMySqlService.create(list);
        System.out.println();
    }

    @Test
    void search() {

        UserDO one = testMySqlService.get("1");

        List<UserDO> list = testMySqlService.list(Arrays.asList("1", "2"));

        System.out.println();
    }

    @Test
    void updateSkipNull() {
        UserDO update = new UserDO();
        update.setId("1");
        update.setName("测试修改");
        //修改
        testMySqlService.updateSkipNull(update);
    }

    @Test
    void updateByWrapper() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.isNull("name");
        UserDO update = new UserDO();
        update.setName("测试更新");
        testMySqlService.updateByWrapper(update, queryWrapper);
    }

}
