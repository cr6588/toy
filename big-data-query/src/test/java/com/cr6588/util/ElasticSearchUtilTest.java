package com.cr6588.util;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.cr6588.entity.User;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.CreateRequest;
import co.elastic.clients.elasticsearch.indices.ElasticsearchIndicesClient;
import co.elastic.clients.elasticsearch.indices.GetIndexRequest;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;

/**
 * create in 2022年04月13日
 * @category TODO
 * @author chenyi
 */
class ElasticSearchUtilTest {

    private ElasticsearchClient client = null;

    @Before
    public void init() {
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        client = new ElasticsearchClient(transport);
    }

    @Test
    void test() throws ElasticsearchException, IOException {

        // client.index

        // boolean created = client.indices().create(req).acknowledged();
        // System.out.println(created);
        // IndicesResponse res = client.cat().indices();
        // for(IndicesRecord record:res.valueBody()) {
        // System.out.println(record.index());
        // }
//        InputStream stream = this.getClass().getResourceAsStream("data/user.json");
//        CreateIndexRequest req = CreateIndexRequest.of(b -> b.index("user").withJson(stream));
        ElasticsearchIndicesClient indices = client.indices();
//        indices.create(req);
        GetIndexRequest getIndexReq = GetIndexRequest.of(a -> a.index("user"));
        GetIndexResponse response = indices.get(getIndexReq);
        System.out.println(response.toString());;
    }

    @Test
    public void addDataTest() throws Exception {
        User user = new User("username", "name", "ps", "usernamelike", "nameLike");
        CreateRequest<User> reqqq = CreateRequest.of(p -> p.index("user").document(user));
        client.create(reqqq);
    }
}
