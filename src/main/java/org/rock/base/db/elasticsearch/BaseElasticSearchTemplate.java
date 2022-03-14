package org.rock.base.db.elasticsearch;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.stereotype.Service;

/**
 * elastic search 服务基底实现
 *
 * @Author ayl
 * @Date 2022-3-9
 */
@Service
public class BaseElasticSearchTemplate implements BaseElasticSearchService {

    //连接
    private RestHighLevelClient client;

    @Value("${elastic.host.url:}")
    private String elasticHost;
    @Value("${elastic.auth.username:}")
    private String userName;
    @Value("${elastic.auth.password:}")
    private String password;

    public RestHighLevelClient client() {
        //如果不存在
        if (this.client == null) {
            //host
            ClientConfiguration.MaybeSecureClientConfigurationBuilder builder = ClientConfiguration
                    .builder()
                    .connectedTo(this.elasticHost);
            //如果有用户名和密码
            if (StringUtils.isNoneBlank(this.userName, this.password)) {
                //使用用户名密码
                builder.withBasicAuth(this.userName, this.password);
            }
            ClientConfiguration clientConfiguration = builder.build();
            //初始化连接
            this.client = RestClients.create(clientConfiguration).rest();
        }
        //返回连接
        return this.client;
    }

}
