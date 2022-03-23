package org.rock.base.controller;

import org.rock.base.auth.LoginAuth;
import org.rock.base.common.JsonResponse;
import org.rock.base.constant.HttpConst;
import org.rock.base.serivce.test.TestElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件控制层
 *
 * @Author ayl
 * @Date 2022-03-09
 */
@RestController
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private TestElasticSearchService testElasticSearchService;

    @LoginAuth
    @PostMapping(value = "/uploadFile", produces = HttpConst.RESPONSE_HEADERS_CONTENT_TYPE_APPLICATION_JSON)
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        //todo
        return JsonResponse.success().toString();
    }

    @GetMapping(value = "/search", produces = HttpConst.RESPONSE_HEADERS_CONTENT_TYPE_APPLICATION_JSON)
    public String search() {
        //返回
        return JsonResponse.success().put("result", null).toString();
    }

}
