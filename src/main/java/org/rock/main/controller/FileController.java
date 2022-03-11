package org.rock.main.controller;

import org.rock.main.common.JsonResponse;
import org.rock.main.constant.HttpConst;
import org.rock.main.serivce.TestElasticSearchService;
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

    @PostMapping(value = "/uploadFile", produces = HttpConst.CONTENT_TYPE_APPLICATION_JSON)
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        //todo
        return JsonResponse.success().toString();
    }

    @GetMapping(value = "/search", produces = HttpConst.CONTENT_TYPE_APPLICATION_JSON)
    public String search() {
        //搜索实现
        TestElasticSearchService.FileIndexSearchResult result = testElasticSearchService.fileSearch();
        //返回
        return JsonResponse.success().put("result", result).toString();
    }

}
