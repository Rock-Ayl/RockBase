package org.rock.base.pojo.base;

import lombok.Getter;
import lombok.Setter;
import org.rock.base.util.IdUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Mongo 实体的基底
 *
 * @Author ayl
 * @Date 2022-03-10
 */
@Getter
@Setter
public class BaseDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    //唯一id
    private String id;
    //创建时间
    private Date createDate;
    //最后修改时间
    private Date updateDate;
    //状态删除字段,基本类型,进库默认false
    private boolean del;
    //版本号
    private Long ver;

    /**
     * 给新增数据的基底赋予基础数据
     *
     * @param baseDocument
     */
    public static void createBuild(BaseDocument baseDocument) {
        baseDocument.setVer(System.currentTimeMillis());
        baseDocument.setCreateDate(new Date());
        baseDocument.setUpdateDate(new Date());
        baseDocument.setId(IdUtils.genGUID());
    }

    /**
     * 给修改数据的基底赋予基础数据
     *
     * @param baseDocument
     */
    public static void updateBuild(BaseDocument baseDocument) {
        baseDocument.setVer(System.currentTimeMillis());
        baseDocument.setUpdateDate(new Date());
    }

}
