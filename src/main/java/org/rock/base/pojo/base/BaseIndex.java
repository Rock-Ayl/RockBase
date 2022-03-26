package org.rock.base.pojo.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.rock.base.util.IdUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * ElasticSearch 实体的基底
 *
 * @Author ayl
 * @Date 2022-03-10
 */
@Getter
@Setter
public class BaseIndex implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一id")
    private String id;

    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("最后修改时间")
    private Date updateDate;

    @ApiModelProperty("状态删除字段")
    private boolean del;

    @ApiModelProperty("版本号")
    private Long ver;

    /**
     * 给新增数据的基底赋予基础数据
     *
     * @param baseIndex
     */
    public static void createBuild(BaseIndex baseIndex) {
        baseIndex.setVer(System.currentTimeMillis());
        baseIndex.setCreateDate(new Date());
        baseIndex.setUpdateDate(new Date());
        baseIndex.setId(IdUtils.genGUID());
    }

    /**
     * 给修改数据的基底赋予基础数据
     *
     * @param baseIndex
     */
    public static void updateBuild(BaseIndex baseIndex) {
        baseIndex.setVer(System.currentTimeMillis());
        baseIndex.setUpdateDate(new Date());
    }

}