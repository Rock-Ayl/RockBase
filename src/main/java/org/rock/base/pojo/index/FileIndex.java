package org.rock.base.pojo.index;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.rock.base.pojo.base.BaseIndex;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 文件ES索引测试实体
 *
 * @Author ayl
 * @Date 2022-03-09
 */
@Getter
@Setter
@Document(indexName = "file")
public class FileIndex extends BaseIndex {

    private static final long serialVersionUID = 1L;

    @Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    @ApiModelProperty("文件名称")
    private String name;

    @Field(type = FieldType.Keyword)
    @ApiModelProperty("文件后缀")
    private String ext;

    @Field(type = FieldType.Keyword)
    @ApiModelProperty("文件MD5")
    private String md5;

    @Field(type = FieldType.Long)
    @ApiModelProperty("文件大小")
    private Long size;

}
