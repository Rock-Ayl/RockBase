package org.rock.main.controller;

import com.alibaba.fastjson.JSONObject;
import org.rock.main.constant.HttpConst;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件
 *
 * @Author ayl
 * @Date 2022-03-09
 */
@RestController
@RequestMapping(value = "/file")
public class FileController {

    @PostMapping(value = "/uploadFile", produces = HttpConst.CONTENT_TYPE_APPLICATION_JSON)
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        //todo
        return new JSONObject().toJSONString();
    }

    @GetMapping(value = "/listFile", produces = HttpConst.CONTENT_TYPE_APPLICATION_JSON)
    public String listFile(String type) {
        //todo
        return new JSONObject().toJSONString();
    }

}
