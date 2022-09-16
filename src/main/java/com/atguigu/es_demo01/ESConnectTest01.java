package com.atguigu.es_demo01;

import com.atguigu.es_demo01.bean.Anime01;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.UUID;

/**
 * @Description: TODO
 * @BelongsProject: es_demo01
 * @BelongsPackage: com.atguigu.es_demo01
 * @Version: 1.0
 * @CreateTime: 2022-09-03 20:18:36
 * @Author: 02雪乃赤瞳楪祈校条祭制作委员会 wyq_start
 */
public class ESConnectTest01 {

    public static RestHighLevelClient rHLC01 = new RestHighLevelClient(RestClient.builder(new
            HttpHost("localhost", 9200, "http")));


    @Test
    public void test18() throws IOException {

        SearchRequest searchRequest = new SearchRequest();

        searchRequest.indices("anime");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        boolQueryBuilder.must(QueryBuilders.matchQuery("animeName","刀剑神域"));

        searchSourceBuilder.query(boolQueryBuilder);

        searchRequest.source(searchSourceBuilder);

        SearchResponse search = rHLC01.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = search.getHits();

        hits.forEach(hit->{
            System.out.println(hit.getSourceAsString());
        });
    }

    @Test
    public void test17() throws IOException {

        SearchRequest searchRequest = new SearchRequest();

        searchRequest.indices("anime");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        String [] excludes = {};
        String [] includes = {"animeName","time"};


        searchSourceBuilder.fetchSource(includes,excludes);

        SearchResponse search = rHLC01.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = search.getHits();

        hits.forEach(hit->{
            System.out.println(hit.getSourceAsString());
        });


    }


    @Test
    public void test16() throws IOException {

        SearchRequest searchRequest = new SearchRequest();

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        searchSourceBuilder.sort("characters", SortOrder.DESC);

        searchRequest.source(searchSourceBuilder);

        SearchResponse search = rHLC01.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = search.getHits();

        hits.forEach(System.out::println);


    }

    @Test
    public void test15() throws IOException {

        SearchRequest searchRequest = new SearchRequest();

        searchRequest.indices("anime");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        searchSourceBuilder.from(0);

        searchSourceBuilder.size(2);

        searchRequest.source(searchSourceBuilder);

        SearchResponse search = rHLC01.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = search.getHits();

        hits.forEach(System.out::println);




    }


    @Test
    public void test14() throws IOException {

        SearchRequest searchRequest = new SearchRequest();

        searchRequest.indices("anime");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(QueryBuilders.termQuery("characters",8));

        searchRequest.source(searchSourceBuilder);

        SearchResponse search = rHLC01.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = search.getHits();

        System.out.println(hits.getTotalHits());

        hits.forEach(System.out::println);


    }

    @Test
    public void test13() throws IOException {

        SearchRequest searchRequest = new SearchRequest();

        searchRequest.indices("anime");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        searchRequest.source(searchSourceBuilder);

        SearchResponse search = rHLC01.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = search.getHits();

        hits.forEach(System.out::println);

    }



    @Test
    public void test12() throws IOException {

        BulkRequest bulkRequest = new BulkRequest();

        bulkRequest.add(new DeleteRequest().index("anime").id("1001"));
        bulkRequest.add(new DeleteRequest().index("anime").id("1002"));
        bulkRequest.add(new DeleteRequest().index("anime").id("1003"));

        BulkResponse bulk = rHLC01.bulk(bulkRequest, RequestOptions.DEFAULT);

        System.out.println(bulk.status());

        System.out.println(bulk.getTook());

    }


    @Test
    public void test11() throws IOException {


        Anime01 _anime01 = new Anime01("影宅", "2020", 8);
        Anime01 _anime02 = new Anime01("刀剑神域", "2012", 3);
        Anime01 _anime03 = new Anime01("lycores", "2022", 4);

        ObjectMapper objectMapper = new ObjectMapper();

        String anime01 = objectMapper.writeValueAsString(_anime01);
        String anime02 = objectMapper.writeValueAsString(_anime02);
        String anime03 = objectMapper.writeValueAsString(_anime03);

        BulkRequest bulkRequest = new BulkRequest();

        bulkRequest.add(new IndexRequest().index("anime").id("1001").source(XContentType.JSON, "name",
                anime01));

        bulkRequest.add(new IndexRequest().index("anime").id("1002").source(XContentType.JSON, "name",
                anime02));

        bulkRequest.add(new IndexRequest().index("anime").id("1003").source(XContentType.JSON, "name",
                anime03));

        BulkResponse bulk = rHLC01.bulk(bulkRequest, RequestOptions.DEFAULT);

        System.out.println(bulk.status());

        System.out.println("getTook" + bulk.getTook());

        BulkRequest bulkRequest1 = new BulkRequest();

        rHLC01.close();


    }


    @Test
    public void test10() throws IOException {

        DeleteRequest deleteRequest = new DeleteRequest().index("anime01").id("1001");

        DeleteResponse delete = rHLC01.delete(deleteRequest, RequestOptions.DEFAULT);

        System.out.println(delete.status());

    }

    @Test
    public void test09() throws IOException {

//        GetRequest getRequest = new GetRequest().index("anime01").id("1001");
        GetRequest getRequest = new GetRequest().index("a1").id("1001");

        GetResponse response = rHLC01.get(getRequest, RequestOptions.DEFAULT);

        System.out.println(response.getIndex());

        System.out.println(response.getId());


        System.out.println(response.getSourceAsString());

    }


    @Test
    public void test08() throws IOException {

        GetIndexRequest anime01 = new GetIndexRequest("anime01");

        GetIndexResponse response = rHLC01.indices().get(anime01, RequestOptions.DEFAULT);

        System.out.println(response.getAliases().entrySet());

        System.out.println(response.getMappings().entrySet());

        System.out.println(response.getSettings().entrySet());


    }


    @Test
    public void test07() throws IOException {

        UpdateRequest ur = new UpdateRequest();

        ur.index("anime01").id("1001");

        ur.doc(XContentType.JSON, "time", 2020);

        UpdateResponse response = rHLC01.update(ur, RequestOptions.DEFAULT);

        System.out.println(response.status());

        System.out.println(response.getIndex());

        System.out.println(response.getId());

        System.out.println(response.getResult());

    }


    @Test
    public void test06() throws Exception {

        IndexRequest anime01 = new IndexRequest();

        anime01.index("anime01").id("1001");

        Anime01 anime011 = new Anime01("影宅", "2022", 4);

        ObjectMapper objectMapper = new ObjectMapper();

        String s = objectMapper.writeValueAsString(anime011);

        anime01.source(s, XContentType.JSON);

        IndexResponse indexAnime01 = rHLC01.index(anime01, RequestOptions.DEFAULT);

        System.out.println(indexAnime01.status());

        System.out.println(indexAnime01.getIndex());

        System.out.println(indexAnime01.getId());


//        GetIndexRequest anime012 = new GetIndexRequest("anime01");
//
//        GetIndexResponse response = rHLC01.indices().get(anime012, RequestOptions.DEFAULT);
//
//
//        System.out.println(response.getAliases().toString());
//
//        System.out.println(response.getMappings().toString());
//
//        System.out.println(response.getSettings().toString());


    }


    @Test
    public void test05() {

//        String s = UUID.randomUUID().toString();

        String s = UUID.randomUUID() + "";


    }


    @Test
    public void test04() throws IOException {

        DeleteIndexRequest user = new DeleteIndexRequest("user");

        AcknowledgedResponse delete = rHLC01.indices().delete(user, RequestOptions.DEFAULT);

        System.out.println(delete.isAcknowledged());


    }


    @Test
    public void test03() throws IOException {

        GetIndexRequest user = new GetIndexRequest("user");

        GetIndexResponse response = rHLC01.indices().get(user, RequestOptions.DEFAULT);

        System.out.println(" >> " + response.getAliases());
        System.out.println(" >> " + response.getMappings());
        System.out.println(" >> " + response.getSettings());


    }


    @Test
    public void test02() throws IOException {

        CreateIndexRequest anime01 = new CreateIndexRequest("user");

        CreateIndexResponse response = rHLC01.indices().create(anime01, RequestOptions.DEFAULT);

        System.out.println(" >> " + response.isAcknowledged());

    }


    @Test
    public void test01() throws IOException {

        RestHighLevelClient rHC01 = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost"
                , 9200, "http")));
        rHC01.close();
    }
}
