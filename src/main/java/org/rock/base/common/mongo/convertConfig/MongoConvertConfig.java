package org.rock.base.common.mongo.convertConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

/**
 * mongo 读写转换配置类
 *
 * @Author ayl
 * @Date 2023-04-21
 */
@Configuration
public class MongoConvertConfig {

    /**
     * mongoCustomConversions会由spring进行管理,
     * 按照加入的转换器,在数据库读写时对数据类型进行转换
     *
     * @return
     */
    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<>();
        converterList.add(new BigDecimalToDecimal128Converter());
        converterList.add(new Decimal128ToBigDecimalConverter());
        return new MongoCustomConversions(converterList);
    }

}