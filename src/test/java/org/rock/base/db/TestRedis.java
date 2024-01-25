package org.rock.base.db;

import org.junit.jupiter.api.Test;
import org.rock.base.db.redis.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestRedis {


    @Autowired
    private BaseRedisService baseRedisService;

    @Test
    void test() {

        String key2 = "rock@4444";
        long count = baseRedisService.incr(key2, 1, 100);
        System.out.println(123);
        long count2 = baseRedisService.incr(key2, 2, 500);
        System.out.println(123);
    }

}
