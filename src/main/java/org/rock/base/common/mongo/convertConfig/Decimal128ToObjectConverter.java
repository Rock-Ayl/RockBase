package org.rock.base.common.mongo.convertConfig;


import org.bson.types.Decimal128;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

/**
 * Decimal128 -> Object 读取转换器
 */
@ReadingConverter
public class Decimal128ToObjectConverter implements Converter<Decimal128, Object> {

    @Override
    public Object convert(Decimal128 decimal128) {
        return decimal128.bigDecimalValue();
    }

}