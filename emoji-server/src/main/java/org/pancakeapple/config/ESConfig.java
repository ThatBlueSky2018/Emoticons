package org.pancakeapple.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ESConfig {
    @Value("${elasticsearch.host}")
    private String host;
    @Value("${elasticsearch.port}")
    private String port;

    @Bean
    public ElasticsearchClient client() {
        // Create the low-level client
        RestClient restClient = RestClient.builder(
                new HttpHost(host, Integer.parseInt(port))).build();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        // And create the API client
        return new ElasticsearchClient(transport);
    }

    @Bean
    public Map<Integer,String> sortMappings() {
        Map<Integer,String> sortMappings=new HashMap<>();
        sortMappings.put(1,"hits");
        sortMappings.put(2,"comments");
        sortMappings.put(3,"favorite");
        return sortMappings;
    }
}
