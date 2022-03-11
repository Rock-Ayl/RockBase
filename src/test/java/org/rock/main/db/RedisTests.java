package org.rock.main.db;

import org.junit.jupiter.api.Test;
import org.rock.main.db.redis.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisTests {


    @Autowired
    private BaseRedisService baseRedisService;

    @Test
    void test() {
        //搜索
        Object o = baseRedisService.get("user@2");
        System.out.println(o);
    }

}
