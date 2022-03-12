package org.rock.main.db;

import com.alibaba.fastjson.JSONObject;
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
        //key
        String key = "rock@123";
        JSONObject json = new JSONObject();
        json.put("name", "rock");
        baseRedisService.set(key, json.toJSONString());
        //搜索
        JSONObject o = baseRedisService.getJSONObject(key);
        System.out.println(o);
    }

}
