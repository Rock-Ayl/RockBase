package org.rock.base.db;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.rock.base.db.redis.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisTests {


    @Autowired
    private BaseRedisService baseRedisService;

    @Test
    void test() {
        //key
        String key = "rock@123";
        JSONObject json = new JSONObject();
        json.put("name", "rock");
        baseRedisService.set(key, json.toJSONString());
        //搜索
        JSONObject o = baseRedisService.getJSONObject(key);
        System.out.println(o);

        String key2 = "rock@4444";
        long count = baseRedisService.incr(key2, 1, 100);
        System.out.println(123);
        long count2 = baseRedisService.incr(key2, 2, 500);
        System.out.println(123);
    }

}
