package org.rock.base.pojo.doc;

import lombok.Getter;
import lombok.Setter;
import org.rock.base.pojo.base.BaseDocument;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * mongo 测试实体
 *
 * @Author ayl
 * @Date 2022-03-10
 */
@Setter
@Getter
@Document(collection = "main.test")
public class TestDoc extends BaseDocument {

    private static final long serialVersionUID = 1L;

    //编号
    private String number;

    //值
    private String value;

}
