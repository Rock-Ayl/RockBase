package org.rock.main.pojo.base;

import lombok.Getter;
import lombok.Setter;
import org.rock.main.util.IdUtils;

import java.util.Date;

/**
 * DB的基底(mysql mongo elasticsearch)
 *
 * @Author ayl
 * @Date 2022-03-09
 */
@Getter
@Setter
public class BaseDO {

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
     * @param baseDO
     */
    public static void createBuild(BaseDO baseDO) {
        baseDO.setVer(System.currentTimeMillis());
        baseDO.setCreateDate(new Date());
        baseDO.setUpdateDate(new Date());
        baseDO.setId(IdUtils.genGUID());
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
