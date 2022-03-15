package org.rock.base.pojo.index;

import lombok.Getter;
import lombok.Setter;
import org.rock.base.pojo.base.BaseIndex;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 文件ES索引实体
 *
 * @Author ayl
 * @Date 2022-03-09
 */
@Getter
@Setter
@Document(indexName = "file")
public class FileIndex extends BaseIndex {

    private static final long serialVersionUID = 1L;

    //文件名,使用ik
    @Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String name;

    //文件后缀
    @Field(type = FieldType.Keyword)
    private String ext;

    //文件MD5
    @Field(type = FieldType.Keyword)
    private String md5;

    //文件大小
    @Field(type = FieldType.Long)
    private Long size;

}
