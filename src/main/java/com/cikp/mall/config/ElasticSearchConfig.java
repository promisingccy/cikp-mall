package com.cikp.mall.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

/**
 * @ClassName ElasticSearchConfig
 * @Description //TODO
 * @Author ccy
 * @Date 2020/12/22 19:27
 * @Version 1.0
 **/
@Configuration
public class ElasticSearchConfig {

    @Value("${spring.elasticsearch.rest.username}")
    private String username;
    @Value("${spring.elasticsearch.rest.password}")
    private String password;
    @Value("${spring.elasticsearch.rest.uris}")
    private String uris;
    @Value("${esCert}")
    private String esCert;

    @Bean
    public RestHighLevelClient getClient() throws Exception {
        HttpHost[] httpHostsList = new HttpHost[1];
        //https://localhost:9200
        String scheme = uris.substring(0, uris.indexOf(":"));//https
        String host = uris.substring(uris.indexOf("//") + 2, uris.indexOf(":", uris.indexOf("//")));//localhost
        Integer port = Integer.valueOf(uris.substring(uris.indexOf(":", uris.indexOf("//")) + 1));
        HttpHost h = new HttpHost(host, port, scheme);
        httpHostsList[0] = h;

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        // Path caCertificatePath = Paths.get("/etc/prs/certs/elasticsearch/ca/ca.crt");
        Path caCertificatePath = Paths.get(esCert);

        try {
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            InputStream is = Files.newInputStream(caCertificatePath);
            Throwable var11 = null;

            Certificate trustedCa;
            try {
                trustedCa = factory.generateCertificate(is);
            } catch (Throwable var21) {
                var11 = var21;
                throw var21;
            } finally {
                if (is != null) {
                    if (var11 != null) {
                        try {
                            is.close();
                        } catch (Throwable var20) {
                            var11.addSuppressed(var20);
                        }
                    } else {
                        is.close();
                    }
                }
            }

            KeyStore trustStore = KeyStore.getInstance("pkcs12");
            trustStore.load((InputStream)null, (char[])null);
            trustStore.setCertificateEntry("ca", trustedCa);
            SSLContextBuilder sslContextBuilder = SSLContexts.custom().loadTrustMaterial(trustStore, (TrustStrategy)null);
            SSLContext sslContext = sslContextBuilder.build();
            RestClientBuilder builder = RestClient.builder(httpHostsList);
            builder.setHttpClientConfigCallback((httpClientBuilder) -> {
                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider).setSSLContext(sslContext).setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);
            });
            RestHighLevelClient restHighLevelClient = new RestHighLevelClient(builder);
            return restHighLevelClient;
        } catch (Exception var23) {
            throw new Exception("Building elastic search rest high level client failed!", var23);
        }
    }
}
