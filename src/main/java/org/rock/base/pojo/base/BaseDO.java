package org.rock.base.pojo.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.rock.base.util.IdExtraUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Mysql 实体的基底
 *
 * @Author ayl
 * @Date 2022-03-09
 */
@Getter
@Setter
public class BaseDO implements Serializable {

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
     * @param baseDO
     */
    public static void createBuild(BaseDO baseDO) {
        baseDO.setVer(System.currentTimeMillis());
        baseDO.setCreateDate(new Date());
        baseDO.setUpdateDate(new Date());
        baseDO.setId(IdExtraUtils.genGUID());
    }

    /**
     * 给修改数据的基底赋予基础数据
     *
     * @param baseDO
     */
    public static void updateBuild(BaseDO baseDO) {
        baseDO.setVer(System.currentTimeMillis());
        baseDO.setUpdateDate(new Date());
    }

}
