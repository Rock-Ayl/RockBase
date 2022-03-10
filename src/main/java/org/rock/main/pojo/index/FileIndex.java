package org.rock.main.pojo.index;

import lombok.Getter;
import lombok.Setter;
import org.rock.main.pojo.base.BaseDO;

/**
 * 文件ES索引实体
 *
 * @Author ayl
 * @Date 2022-03-09
 */
@Getter
@Setter
public class FileIndex extends BaseDO {

    private static final long serialVersionUID = 1L;

    //文件名
    private String fileName;
    //文件后缀
    private String fileExt;
    //文件MD5
    private String fileMD5;
    //文件大小
    private Long fileSize;

}
