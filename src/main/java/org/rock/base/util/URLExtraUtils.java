package org.rock.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * url 扩展工具包
 *
 * @Author ayl
 * @Date 2023-11-04
 */
public class URLExtraUtils {

    private static final Logger LOG = LoggerFactory.getLogger(URLExtraUtils.class);

    //中文正则
    private final static Pattern CN_PATTERN = Pattern.compile("[\u4e00-\u9fa5]");

    /**
     * 解决[文件URL]路径中,特殊字符的问题,包含:[中文,空格]
     *
     * @param url
     * @return
     */
    public static String urlEncode(String url) {
        //判空
        if (url == null) {
            //过
            return null;
        }
        try {
            Matcher matcher = CN_PATTERN.matcher(url);
            String tmp;
            while (matcher.find()) {
                tmp = matcher.group();
                url = url.replaceAll(tmp, URLEncoder.encode(tmp, "UTF-8"));
            }
        } catch (Exception e) {
            LOG.error("URLExtraUtils urlEncode error", e);
        }
        //最后替换空格
        return url.replace(" ", "%20");
    }

}
