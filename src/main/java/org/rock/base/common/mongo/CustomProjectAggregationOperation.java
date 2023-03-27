package org.rock.base.common.mongo;

import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;

/**
 * 一个自定义的 Mongo operation 实体
 *
 * @Author ayl
 * @Date 2022-09-28
 */
public class CustomProjectAggregationOperation implements AggregationOperation {

    private String json;

    public CustomProjectAggregationOperation(String json) {
        this.json = json;
    }

    @Override
    public Document toDocument(AggregationOperationContext aggregationOperationContext) {
        return aggregationOperationContext.getMappedObject(Document.parse(json));
    }

}
