package com.cr6588.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.Test;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.cat.IndicesResponse;
import co.elastic.clients.elasticsearch.cat.indices.IndicesRecord;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;

/**
 * create in 2022年04月13日
 * @category TODO
 * @author chenyi
 */
class ElasticSearchUtilTest {


    @Test
    void test() throws ElasticsearchException, IOException {
        RestClient restClient = RestClient.builder(
            new HttpHost("localhost", 9200)).build();
        ElasticsearchTransport transport = new RestClientTransport(
            restClient, new JacksonJsonpMapper());
        ElasticsearchClient client = new ElasticsearchClient(transport);
//        client.index
//        InputStream stream = this.getClass().getResourceAsStream("data/es.json");
//        CreateIndexRequest req = CreateIndexRequest.of(b -> b
//            .index("test1-index")
//            .withJson(stream) 
//        );
//        boolean created = client.indices().create(req).acknowledged();
//        System.out.println(created);
        IndicesResponse res = client.cat().indices();
        for(IndicesRecord record:res.valueBody()) {
            System.out.println(record.index());
        }
    }

}
