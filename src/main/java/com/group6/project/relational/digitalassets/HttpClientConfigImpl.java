package com.group6.project.relational.digitalassets;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import java.io.File;

/**
 *  这个类实现了RestClientBuilder.HttpClientConfigCallback接口，
 *  用于定制HTTP客户端，这对于设置与Elasticsearch服务器的连接是必要的。它特别配置了身份验证和SSL上下文
 *
 *  这个类与之前的GetESClient类相关联，因为GetESClient类在构建ElasticsearchClient时使用了HttpClientConfigImpl来配置底层的HTTP客户端。
 *  这样确保了客户端在与Elasticsearch服务器通信时具备了必要的认证和安全设置。
 */

//实现了RestClientBuilder.HttpClientConfigCallback接口，这意味着它提供了一个方法来自定义HTTP客户端。
@Configuration
public class HttpClientConfigImpl  implements RestClientBuilder.HttpClientConfigCallback{
    @Override
    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
        try {
            //自定义HTTP客户端：在customizeHttpClient方法中，它接收一个HttpAsyncClientBuilder对象，并对其进行定制。
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials("elastic", "=Fpegbl9f+lJhfFKnR21r");
            //设置认证：使用BasicCredentialsProvider来设置基本的用户名和密码认证。这在与Elasticsearch交互时需要认证的场景中非常重要。
            credentialsProvider.setCredentials(AuthScope.ANY,usernamePasswordCredentials);
            httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);


            String trustStoreLocation = "C:\\elastic_search_project\\certs\\truststore.p12";
            File truestStoreLocationFile = new File(trustStoreLocation);

            //配置SSL：配置SSL上下文以支持HTTPS连接。这包括指定信任存储的位置（即包含服务器证书的存储库）以及加载该信任存储。
            SSLContextBuilder sslContextBuilder = SSLContexts.custom().loadTrustMaterial(truestStoreLocationFile, "password".toCharArray());
            SSLContext sslContext = sslContextBuilder.build();

            httpAsyncClientBuilder.setSSLContext(sslContext);

        }catch (Exception e){
            e.printStackTrace();
        }

        return httpAsyncClientBuilder;
    }
}
