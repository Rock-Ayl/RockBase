package org.rock.main.db;

import org.junit.jupiter.api.Test;
import org.rock.main.db.redis.RedisTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisTests {


    @Autowired
    private RedisTable redisTable;

    @Test
    void test() {
        //搜索
        Object o = redisTable.get("user@2");
        System.out.println(o);
    }

}
