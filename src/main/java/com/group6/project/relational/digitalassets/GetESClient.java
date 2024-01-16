package com.group6.project.relational.digitalassets;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *  这个类负责创建和配置ElasticsearchClient对象，这是与Elasticsearch交互的主要入口。
 *  代码中使用了RestClientBuilder和相关的配置来初始化这个客户端。
 */
@Component
public class GetESClient {

    //getElasticsearchClient() 方法被标记为 @Bean，意味着它会返回一个Spring管理的bean，这里是 ElasticsearchClient
    @Bean
    public ElasticsearchClient getElasticsearchClient(){

        //使用 RestClient.builder 方法创建一个 RestClientBuilder 实例，配置连接到本地运行的Elasticsearch服务器（默认端口9200，使用https协议）。
        RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "https"));

        //创建了一个 HttpClientConfigImpl 实例，并将其设置为 RestClientBuilder 的 HttpClientConfigCallback。这是用于配置底层HTTP客户端的高级设置，如认证和SSL配置。
        RestClientBuilder.HttpClientConfigCallback httpClientConfigCallback = new HttpClientConfigImpl();
        builder.setHttpClientConfigCallback(httpClientConfigCallback);

        //使用 builder.build() 来构建 RestClient 实例
        RestClient restClient = builder.build();

        //使用新创建的 RestClient 和 JacksonJsonpMapper（用于JSON处理）创建一个 RestClientTransport 实例。
        RestClientTransport restClientTransport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        //最后，使用 RestClientTransport 实例创建并返回一个 ElasticsearchClient 实例，该客户端将用于与Elasticsearch进行交互。
        return new ElasticsearchClient(restClientTransport);
    }
}
